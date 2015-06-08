package application.Functions;

import java.awt.Event;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException 
    {
    	ArrayList<Event> eventlist = new ArrayList<Event>();
    	
    	request.setAttribute("availableItems", eventlist);
        String nextPage = "/MainMenu.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(nextPage);
        rd.forward(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}
}
