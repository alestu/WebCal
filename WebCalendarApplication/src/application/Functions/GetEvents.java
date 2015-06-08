package application.Functions;

import java.awt.Event;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException {
        String name=req.getParameter("name");
        System.out.println("ajax:" + name);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(name);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer userId = DatabaseController.activeUser.user_id;
		
		ResultSet rs = controller.selectEvents(userId);
		int rowcount = 0;
		try 
		{
			if (rs.last()) 
			{
			  rowcount = rs.getRow();
			  rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			while (rs.next()) 
			{
			 application.Model.Event e = new application.Model.Event();
			 e.event_id = Integer.parseInt(rs.getString("event_id"));
			 e.title = rs.getString("title");
			 e.description = rs.getString("description");

			 //e.event_begin = rs.getString("event_begin");
			 //e.event_end = rs.getString("event_end");

			 e.place =  rs.getString("place");
			 
			}
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
	}
}
