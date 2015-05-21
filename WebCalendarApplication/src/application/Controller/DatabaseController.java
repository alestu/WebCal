package application.Controller;

/*Singleton DatabaseController */

public class DatabaseController 
{

	private static DatabaseController instance;

	private DatabaseController() 
	{
		
	}
	
	/* Thread-Safe Singleton */
	public static synchronized DatabaseController getInstance() {
		if (DatabaseController.instance == null) {
			DatabaseController.instance = new DatabaseController();
		}
		return DatabaseController.instance;

	}

}
