package application.Model;

import java.util.Calendar;
import java.util.Date;

import com.sun.jmx.snmp.Timestamp;

public class Event 
{
	public int event_id;	//Primary Key
	public String title;
	public String description;
	public String place;
	public String category;
	public String event_begin;
	public String event_end;
	public boolean full_day;
	public int user_id;	//Foreign Key
	
	
	
	public Event()
	{
		
	}
}
