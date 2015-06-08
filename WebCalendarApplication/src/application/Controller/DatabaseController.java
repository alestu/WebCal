package application.Controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import application.Model.Event;
import application.Model.User;




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
	 public static User activeUser;
	
	

	public DatabaseController()
	{
		activeUser = new User();
		LoadDatabase();
		//conn = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
	}
	
	
	/*Querys*/
	private void setUserData(String email)
	{
		System.out.println("User Objekt bauen");
		try 
		{
			String sqlString = "SELECT * from users WHERE email = '"+ email +"';";		
			ResultSet res = stmt.executeQuery(sqlString);
			if(res.first())
			{
				System.out.println("User Daten aus DB gelesen");
				DatabaseController.activeUser.first_name = res.getString("first_name");
				DatabaseController.activeUser.last_name = res.getString("last_name");
				DatabaseController.activeUser.password_ = res.getString("password_");
				DatabaseController.activeUser.email = email;
				DatabaseController.activeUser.user_id = Integer.parseInt(res.getString("user_id"));
				System.out.println("Objekt erfolgreich zusammengestellt!");
				stmt.close();
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean checkEmailAndPassword(String email,String password_) 
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
			System.out.println("Login war erfolgreich!");
			setUserData(email); //Objekt bauen
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
	public ResultSet selectEventsWithFilter(int user_id, String filter)
	{
		//Antonio Nunziata
		try
		{		    
			String query = "SELECT * FROM event WHERE event.user_id ="+user_id+" AND (title LIKE '%"+filter+"%' OR description LIKE '%"+filter+"%' OR ort LIKE '%"+filter+"%' OR category LIKE'%"+filter+"%');" ;		                
			ResultSet res = stmt.executeQuery(query);
			return res;
			
		}
		catch(Exception ex)
		{ 
			System.out.println("Failed selecting events");
			return null;
		}
		
	}
	public boolean deleteEvent(int event_id)
	{
		//Antonio Nunziata
		try
		{
			String query = "DELETE FROM event WHERE event_id="+event_id+";";
			stmt.execute(query);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Failed deleting event");
			return false;
		}
	}
	public String selectEvent(int event_id)
	{
		//Antonio Nunziata
		/*Mithilfe des übergebenen Terminschlüssel (event_id) sollen zugehörige Daten eines Termins selektiert werden
		 * Diese werden zu einem einzigen String zusammengesetzt und als Rückgabewert gesendet.
		 * Die einzelnen Werte werden voneinander mit dem Seperator ';' getrennt.
		 * Später kann eine Funktion diese auswerten und einzelne Strings daraus produzieren*/
		try
		{		    
			String query = "SELECT title, description, place,event_begin,event_end,full_day,category FROM event WHERE event.event_id ="+event_id+";";			
			ResultSet res = stmt.executeQuery(query);
			
			if(res.first())
			{
				System.out.println("Event gefunden!");				
				String data = res.getString("title")+";"+res.getString("description")+";"+res.getString("place")+";"+res.getString("event_begin")+";"+res.getString("event_end")+";"+res.getString("full_day")+";"+res.getString("category");
				return data;
			
			}
			return null;
			
		}
		catch(Exception ex)
		{ 
			System.out.println("Failed selecting events");
			return null;
		}
	}
	public ArrayList<Event> selectEvents(int user_id)
	{
		//Antonio Nunziata
		try
		{		    
			String query = "SELECT * FROM event WHERE event.user_id ="+user_id+";";			
			ResultSet res = stmt.executeQuery(query);
			ArrayList<Event> events = new ArrayList<Event>();
			
			//Aus den selektierten Daten ein Event-Objekt bauen und der Event-Liste hinzufügen
			//Anschließend wird die ArrayList zurückgegeben
			while (res.next()) 
			{
				Event e = new Event();
				e.event_id = Integer.parseInt(res.getString("event_id"));
				e.title = res.getString("title");
				e.description = res.getString("description");
				e.place = res.getString("place");
				e.category = res.getString("category");
				e.user_id = Integer.parseInt(res.getString("user_id"));
				e.event_begin = res.getString("event_begin");
				e.event_end = res.getString("event_end");
				events.add(e);
		    }
			
			return events;
			
		}
		catch(Exception ex)
		{ 
			System.out.println("Failed selecting events");
			return null;
		}
	}
	public boolean insertEvent(Event e)
	{
		//Antonio Nunziata
		try
		{
			
			String query = "INSERT INTO event (title,description,place,event_begin,event_end,full_day,category) VALUES('"+e.title+"','"+e.description+"','"+e.place+"','"+e.event_begin+"','"+e.event_end+"',"+e.full_day+",'"+e.category+"');";
			stmt.execute(query);
			System.out.println("Inserting was successfully");
			return true;
			
		}
		catch(Exception ex)
		{ 
			System.out.println("Failed inserting event");
			return false;
		}
	}
	public boolean updateEvent(Event e)
	{		
		//Antonio Nunziata		 
		try
		{  
			 
			String query = "UPDATE event SET title='"+e.title+"',description='"+e.description+"',place='"+e.place+"',full_day="+e.full_day+",category='"+e.category+"',event_begin='"+e.event_begin+"',event_end='"+e.event_end+"' WHERE event_id="+e.event_id+";";
			stmt.execute(query);
			return true;
			
		}
		catch(Exception ex)
		{ 
			System.out.println("Failed updating event");
			return false;
		}
		
	}
	public boolean RegisterUser(User u)
	{
		//Alessandro Stuckenschnieder
		//Ergänzt: Antonio Nunziata
		try
		{		
			int address_id;			
			String sqlString = "INSERT INTO address (street,street_nr,postcode,city) VALUES('"+u.adress.street+"','"+u.adress.street_nr+"',"+u.adress.postcode+",'"+u.adress.city+"');";
			System.out.println("address created..." + String.valueOf(stmt.executeUpdate(sqlString)));
			sqlString = "select address_id from address where street = "+"'"+u.adress.street+"' and street_nr = '"+u.adress.street_nr+ "' and postcode = "+u.adress.postcode+" and city = '"+u.adress.city+"';";
			System.out.println(sqlString);
			ResultSet res = stmt.executeQuery(sqlString);
			if(res.next())
			{
				address_id = res.getInt(1);
				System.out.println("Fetching address_id..");
				sqlString = "INSERT INTO users (email,password_,first_name,last_name,address_id) VALUES ('"+u.email+"','"+u.password_+"','"+u.first_name+"','"+u.last_name+"',"+address_id+");";
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
		try
		{
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
