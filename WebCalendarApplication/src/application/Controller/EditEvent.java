package application.Controller;

import java.io.IOException;
import java.sql.SQLException;

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
	/**
	* Das Erhalten von Daten eines Events zum bearbeiten
	*
	* @author Antonio Nunziata
	*
	* @version 1.0
	* 
	* 
	 * @throws SQLException 
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("Editiere...");
		//Antonio Nunziata
	    Integer eventID=Integer.parseInt(request.getParameter("eventID"));
	    DatabaseController dbCon = new DatabaseController();
	    String data = null;
		try {
			data = dbCon.selectEvent(eventID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(data);
        
		       
	}

}
