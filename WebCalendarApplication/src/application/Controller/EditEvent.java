package application.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.ResultSet;

@WebServlet("/EditEvent")
public class EditEvent extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
   
    public EditEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	       
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("Editiere...");
		//Antonio Nunziata
	    Integer eventID=Integer.parseInt(request.getParameter("eventID"));
	    DatabaseController dbCon = new DatabaseController();
	    String data = dbCon.selectEvent(eventID);
	   
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(data);
        
		       
	}

}
