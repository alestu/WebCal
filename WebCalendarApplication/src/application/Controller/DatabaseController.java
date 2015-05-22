package application.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/*Singleton DatabaseController */
public class DatabaseController {
	static String connectionUrl = "jdbc:mysql://localhost/";
	static String connectionUser = "root";
	static String connectionPassword = "";
	static String database = "webcalendar";
	static String webcal_table = "ConnectorJ/webcal_table.sql";
	static String webcal_db = "ConnectorJ/webcal_db.sql";
	static String webcal_testdata = "ConnectorJ/webcal_testdata.sql";

	private static DatabaseController instance;

	private DatabaseController() {

	}

	/* Thread-Safe Singleton */
	public static synchronized DatabaseController getInstance() {
		if (DatabaseController.instance == null) {
			DatabaseController.instance = new DatabaseController();
		}
		return DatabaseController.instance;

	}

	public static void LoadDatabase() {
		LoadDriver();
		try {
			Connection conn = DriverManager.getConnection(connectionUrl,
					connectionUser, connectionPassword);
			Statement stmt = conn.createStatement();

			stmt.execute("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME =\""
					+ database + "\";");

			if (!stmt.getResultSet().first()) {
				InitDatabase(stmt, conn);
				InitTables(stmt, conn);
				InitTestData(stmt, conn);
				
				// Close Statement after Initializing DB and Tables, and set
				// Catalog to created DB
				stmt.close();
				conn.setCatalog(database);
				stmt = conn.createStatement();

			} else {
			
				stmt.close();
				conn.setCatalog(database);
				System.out.println(database + " successfully loaded.\n");
				stmt = conn.createStatement();

			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}

	public static void LoadDriver() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public static void InitDatabase(Statement stmt, Connection conn)
			throws SQLException {
		try {
			System.out.println("Executing Script for Database creation..."
					+ String.valueOf(executeDBScript(webcal_db, stmt)));

			stmt.close();

		} catch (IOException ex) {
			System.out.println(ex);
		}

	}

	public static void InitTables(Statement stmt, Connection conn)
			throws SQLException {

		try {

			conn.setCatalog(database);
			stmt = conn.createStatement();
			System.out.println("Executing Script for tables... "
					+ String.valueOf(executeDBScript(webcal_table, stmt)));
		} catch (IOException ex) {
			System.out.println(ex);
		}

	}

	public static void InitTestData(Statement stmt, Connection conn)
			throws SQLException {
		try {

			conn.setCatalog(database);
			stmt = conn.createStatement();
			System.out.println("Executing Script for Testdata... "
					+ String.valueOf(executeDBScript(webcal_testdata, stmt)));
		} catch (IOException ex) {
			System.out.println(ex);
		}

	}

	public static boolean executeDBScript(String SqlFile, Statement stmt)
			throws IOException, SQLException {
		boolean scriptIsExecuted = false;
		String s = new String();
		StringBuffer sb = new StringBuffer();

		try {
			FileReader fr = new FileReader(new File(SqlFile));
			// be sure to not have line starting with "--" or "/*" or any other
			// non aplhabetical character

			BufferedReader br = new BufferedReader(fr);

			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			br.close();

			// here is our splitter ! We use ";" as a delimiter for each request
			// then we are sure to have well formed statements
			String[] inst = sb.toString().split(";");

			for (int i = 0; i < inst.length; i++) {
				// we ensure that there is no spaces before or after the request
				// string
				// in order to not execute empty statements
				if (!inst[i].trim().equals("")) {
					stmt.executeUpdate(inst[i]);
					System.out.println(">>" + inst[i]);
				}
			}
			scriptIsExecuted = true;
		} catch (Exception e) {
			System.out.println("*** Error : " + e.toString());
			System.out.println("*** ");
			System.out.println("*** Error : ");
			e.printStackTrace();
			System.out
					.println("################################################");
			System.out.println(sb.toString());
		}

		return scriptIsExecuted;

	}

}
