package application.Functions;

import java.awt.Event;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.Controller.DatabaseController;
import application.Model.User;

/**
 * Servlet implementation class GetEvents
 */
@WebServlet("/GetEvents")
public class GetEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController controller;

    /**
     * Default constructor. 
     */
    public GetEvents() {
        controller = new DatabaseController();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Integer userId = DatabaseController.activeUser.user_id;
		Resultset rows = controller.selectEvents(userId);
		
		if(rows.first())
		{
			//rows verf�gbar
			Event e = new Event();
			e.id = rows.getString("event_id");
			
			rows.Next();
		}*/
	}
}