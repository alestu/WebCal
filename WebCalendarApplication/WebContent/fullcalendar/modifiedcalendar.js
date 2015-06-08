/**
 * Handles several calendar options.
 * Created by Erik.
 **/

$(document).ready(function(){
    $("#calendar").fullCalendar({
        dayClick: function(date, jsEvent, view){
            /*$("#datepicker0").data({date: date.format()});
            $("#datepicker0").datepicker("update");
            $("#datepicker1").data({date: date.format()});
            $("#datepicker1").datepicker("update");*/
        	$("#datepicker0").datepicker("setDate", date);
                    	
            $("#createEvent").modal("show");
        },
        lang: "de",
        aspectRatio: 2.5,
        header: false
    });
    
	$("#title").text($("#calendar").fullCalendar("getView").title);
    
    $("#agendaDay").click(function(){
    	$("#calendar").fullCalendar("changeView", "agendaDay");
    	$("#title").text($("#calendar").fullCalendar("getView").title);
    });
    $("#agendaWeek").click(function(){
    	$("#calendar").fullCalendar("changeView", "agendaWeek");
    	$("#title").text($("#calendar").fullCalendar("getView").title);
    });
    $("#month").click(function(){
    	$("#calendar").fullCalendar("changeView", "month");
    	$("#title").text($("#calendar").fullCalendar("getView").title);
    });
    $("#prev").click(function(){
    	$("#calendar").fullCalendar("prev");
    	$("#title").text($("#calendar").fullCalendar("getView").title);
    });
    $("#today").click(function(){
    	$("#calendar").fullCalendar("today");
    	$("#title").text($("#calendar").fullCalendar("getView").title);
    });
    $("#next").click(function(){
    	$("#calendar").fullCalendar("next");
    	$("#title").text($("#calendar").fullCalendar("getView").title);
    });
});