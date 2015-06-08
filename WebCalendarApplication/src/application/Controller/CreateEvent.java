package application.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jmx.snmp.Timestamp;

import application.Model.Event;

/**
 * Servlet implementation class CreateEvent
 */
@WebServlet("/CreateEvent")
public class CreateEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEvent() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	private Calendar GetDateFormat(String date, String time)
    {
    	//Antonio Nunziata
		 System.out.println("Datum Eingang: " + date);
		 System.out.println("Zeit Eingang: " + time);
		 try
		 {
			 //Zeitangabe wird in zwei Strings (Stunden & Minuten) geteilt.
			 String[] hoursMinutes = time.split(":");
			 int hours = Integer.parseInt(hoursMinutes[0]);
			 int minutes = Integer.parseInt(hoursMinutes[1]);
						 
			 SimpleDateFormat curFormater = new SimpleDateFormat("dd/MMM/yyyy"); //Eingangsformat
			 Date dateObj = curFormater.parse(date);
			 Calendar calendar = Calendar.getInstance();
			 calendar.setTime(dateObj); //Datumobjekt in ein Calendarobjekt
			 calendar.set(Calendar.HOUR_OF_DAY,hours);
			 calendar.set(Calendar.MINUTE,minutes);
			 System.out.println("Datum Ausgang: "+calendar.getTime());
			 return calendar;
	      } 
		 catch (ParseException pe) 
		 {
			 pe.printStackTrace();
	     }
		 
		 return null;
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Antonio Nunziata
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//Termin Objekt zusammenbauen
		Event e = new Event();		
		e.title = request.getParameter("title");
		e.description = request.getParameter("description");
		e.place = request.getParameter("place");
		e.event_begin = request.getParameter("startdatum")+" "+request.getParameter("startzeit");
		e.event_end = request.getParameter("enddatum")+" "+request.getParameter("endzeit");
		e.full_day = false; //Nachträglich ändern
		e.category  = request.getParameter("category");
		e.user_id = DatabaseController.activeUser.user_id;
			
		System.out.println("Writing event in database ...");
		DatabaseController controller = new DatabaseController();
		controller.insertEvent(e); //Datenobjekt übergeben
		
		response.sendRedirect("Navigationbar/MainMenu.jsp");
	}
}
