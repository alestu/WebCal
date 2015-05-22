package application.Controller;

import java.sql.SQLException;

public class DatabaseTest {
	
	

	public static void main(String[] args) throws SQLException 
	{
		DatabaseController controller = new DatabaseController();
		
		controller.LoadDatabase();
		
	
	}

}
