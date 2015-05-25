package application.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;




/*Singleton DatabaseController */
public class DatabaseController 
{
	 String connectionUrl = "jdbc:mysql://localhost/";
	 String connectionUser = "root";
	 String connectionPassword = "";
	 String database = "webcalendar";
	 String webcal_table = "ConnectorJ/webcal_table.sql";
	 String webcal_db = "ConnectorJ/webcal_db.sql";
	 String webcal_testdata = "ConnectorJ/webcal_testdata.sql";
	 Connection conn;
	 Statement stmt; 
	
	

	public DatabaseController()
	{
		LoadDatabase();
		//conn = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
	}
	
	/*Querys*/
	public  boolean checkEmailAndPassword(String email,String password_) 
	{
		try
		{
		String sqlString = "select password_ from users where email = '"+ email +"' and password_ = '"+password_+"';";		
		
	
		
		
		ResultSet res = stmt.executeQuery(sqlString);
		if(!res.first())
		{
			stmt.close();
			return false;
			
		}
		else
		{
			stmt.close();
			return true;
		}
		
		
		
		}
		catch(SQLException ex)
		{
			System.out.println("ExceptionMessage: " +ex);
		}
		return false;
	}
	
	public boolean isEmailAlreadyinUse(String email)
	{
		try
		{
		String sqlString = "select email from users where email = '"+ email +"';";		
				
		ResultSet res = stmt.executeQuery(sqlString);
		if(res.first())
		{
			
			return true;
			
		}
		else
		{
			
			
			return false;
		}
		
		
		
		}
		catch(SQLException ex)
		{
			System.out.println("ExceptionMessage: " +ex);
			return false;
		}
		
		
		
	}
	
	public boolean RegisterUser(String email, String password_, String first_name, String last_name,String street, String street_nr,int postcode,String city)
	{
		//Benutzerobobjekt empfangen und aus dem Objekt die einzelnen Werte ziehen.
		//Ablauf INSERT: Daten lesen, Objekt zusammenbauen ("Objekth√ºlle"), in die DB schreiben
		//Ablauf SELECT:Daten aus der DB lesen, Objekt zusammenstellen, Objekt freigeben
		
		//ToDo: Parameterliste ‰ndern!
		try
		{
			int address_id;			
			String sqlString = "insert into address (street,street_nr,postcode,city) values('"+street+"','"+street_nr+"',"+postcode+",'"+city+"');";
			System.out.println("address created..." + String.valueOf(stmt.executeUpdate(sqlString)));
			sqlString = "select address_id from address where street = "+"'"+street+"' and street_nr = '"+street_nr+ "' and postcode = "+postcode+" and city = '"+city+"';";
			System.out.println(sqlString);
			ResultSet res = stmt.executeQuery(sqlString);
			if(res.next())
			{
				address_id = res.getInt(1);
				System.out.println("Fetching address_id..");
				sqlString = "insert into users (email,password_,first_name,last_name,address_id) values ('"+email+"','"+password_+"','"+first_name+"','"+last_name+"',"+address_id+");";
				System.out.println(sqlString);
				System.out.println("User created..."+String.valueOf(stmt.executeUpdate(sqlString)));
				return true;
			}
			else
			{
				System.out.println("Failed fetching address_id");
				return false;
			}
			
			
		}
		catch(Exception ex)
		{	System.out.println(ex);
			return false;
			
		}
		
		
		
	}

	public String getFullUsernameByEmail(String email) throws SQLException

	{
		try{
	
		
		conn.setCatalog(database);
		Statement stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery("select first_name,last_name from users where email = '"+email+"';");
		res.next();
		
		return String.format("%s %s", res.getString(1), res.getString(2)); 
		
		
		}catch(SQLException ex)
		{
			
			
		}
		return "";
		
	}
	
	
	/*Database Loading and Init */
	public void LoadDatabase() {
		LoadDriver();
		
		
		try {
			conn = DriverManager.getConnection(connectionUrl,
					connectionUser, connectionPassword);	
			
			stmt = conn.createStatement();

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

	public void LoadDriver()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void ConnectDB() throws SQLException
	{		
		try{
			 conn = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
	
		}
		catch(SQLException ex)
		{
			System.err.println("Failed to Connect: "+ex);
			
		}
		
	}
	
	public void InitDatabase(Statement stmt, Connection conn)
			throws SQLException {
		try {
			System.out.println("Executing Script for Database creation..."
					+ String.valueOf(executeDBScript(webcal_db, stmt)));

			stmt.close();

		} catch (IOException ex) {
			System.out.println(ex);
		}

	}

	public void InitTables(Statement stmt, Connection conn)
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

	public void InitTestData(Statement stmt, Connection conn)
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

	public boolean executeDBScript(String SqlFile, Statement stmt)
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
