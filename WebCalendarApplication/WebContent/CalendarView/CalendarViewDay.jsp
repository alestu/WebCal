<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Calendar View</title>
<link rel='stylesheet' href='../fullcalendar/fullcalendar.css' />
<script src='../fullcalendar/lib/jquery.min.js'></script>
<script src='../fullcalendar/lib/moment.min.js'></script>
<script src='../fullcalendar/fullcalendar.js'></script>
<script src='../fullcalendar/lang-all.js'></script>
<script>
$(document).ready(function(){
    $('#calendar').fullCalendar({
        lang: 'de'
    });
    
    $('#calendar').fullCalendar('changeView', 'agendaDay');
});
</script>
</head>
<body>
	<div id='calendar'></div>
</body>
</html>
