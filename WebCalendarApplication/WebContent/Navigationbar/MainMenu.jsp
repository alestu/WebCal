<%@page import="org.apache.tomcat.jni.Time"%>
<%@page import="com.sun.org.apache.xml.internal.utils.StringComparable"%>
<%@page import="application.Controller.DatabaseController"%>
<%@page import="java.sql.*"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String email = "";
	String password = "";

	if (!request.equals(null)) {
		email = request.getParameter("email");
		password = request.getParameter("password");
		session.setAttribute("email", email);
		session.setAttribute("password_", password);
	}
	else {
		email = session.getAttribute("email").toString();
		password = session.getAttribute("password_").toString();
	}

	String userName = "not-assigned";
	DatabaseController controller = new DatabaseController();

	if (controller.checkEmailAndPassword(email, password)) {
		userName = controller.getFullUsernameByEmail(email);
	}
	else {
%>
<jsp:forward page="../Login/login.jsp" />
<%
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>WebCal</title>
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap-datepicker.css"/>
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap-select.css"/>
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap-clockpicker.min.css"/>
<link rel="stylesheet" type="text/css" href="../bootstrap/css/github.min.css"/>

<link rel="stylesheet" type="text/css" href="../bootstrap/css/termin.css"/>
<link rel="stylesheet" type="text/css" href="../bootstrap/css/navigation.css"/>
	
<link rel="stylesheet" type="text/css" href="../fullcalendar/fullcalendar.css"/>
<link rel="stylesheet" type="text/css" href="../fullcalendar/modifiedcalendar.css"/>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<!-- Navigation elements  -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">
					<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
				</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<!-- Account administration -->
					<li class="dropdown">
					<!-- Username -->
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
							<%=userName%>
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu" role="menu">
							<!-- Funktionen -->
							<li><a href="#">Abmelden</a></li>
						</ul>
					</li>
					<li>
					</li>
					
					<!-- Create event -->
					<%@include file="CreateEventModal.jsp" %>
					
					</li>
				</ul>
				
				<!-- Search -->
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group has-feedback-left has-feedback">
						<input type="text" class="form-control" placeholder="Suche" onkeydown="if (event.keyCode == 13) return false">
						<i class="form-control-feedback glyphicon glyphicon-search"></i>
					</div>
				</form>

				<!-- Calendarviews by Erik-->
				<div class="btn-group navbar-form navbar-right" data-toggle="buttons">
					<label class="btn btn-primary" id="agendaDay">
						<input type="radio" name="views" autocomplete="off"/> Tag
					</label>
					<label class="btn btn-primary" id="agendaWeek">
						<input type="radio" name="views" autocomplete="off"/> Woche
					</label>
					<label class="btn btn-primary active" id="month">
						<input type="radio" name="views" autocomplete="off"/> Monat
					</label>
				</div>
				
				<!-- Calendarcontrol by Erik-->
				<div class="btn-group navbar-form navbar-right">
					<label class="btn btn-primary" id="prev">
						<span class="glyphicon glyphicon-chevron-left"></span>
					</label>
					<label class="btn btn-primary" id="today">
						Heute
					</label>
					<label class="btn btn-primary" id="next">
						<span class="glyphicon glyphicon-chevron-right"></span>
					</label>
				</div>
				
				<!-- Calendartitle by Erik-->
				<div class="navbar-form navbar-right title">
					<span id="title"></span>
				</div>
				
				<!-- NAVIGATIONBAR ELEMENTE ENDE  -->
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	
	<div id="calendar"></div>
</body>

<script type="text/javascript" src="../bootstrap/js/jquery.js"></script>
<script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../bootstrap/js/clockpicker.js"></script>
<script type="text/javascript" src="../bootstrap/js/bootstrap-select.js"></script>
<script type="text/javascript" src="../bootstrap/js/bootstrap-datepicker.de.js"></script>
<script type="text/javascript" src="../bootstrap/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="../bootstrap/js/TimeAndDate.js"></script>

<script type="text/javascript" src="../bootstrap/js/termin.js"></script>

<script type="text/javascript" src="../fullcalendar/lib/moment.min.js"></script>
<script type="text/javascript" src="../fullcalendar/fullcalendar.js"></script>
<script type="text/javascript" src="../fullcalendar/lang-all.js"></script>
<script type="text/javascript" src="../fullcalendar/modifiedcalendar.js"></script>

<script>
$(document).ready(function(){
	
	//HIER DIE TERMINID !!!!!!
    $("#btnEditEvent").click(function()
    {
    	
    	//Mithilfe von Ajax sollen Daten gelesen und angezeigt werden, ohne einen PageLoad auszuführen	
    		$.post("http://localhost:8080/WebCalendarApplication/EditEvent",
    		{
                 eventID:1, //EventID eritteln
   
             }).done(function( data ) //Nachdem der Call fertig ist, wird die Maske geöffnet und mit Daten gefüllt
           	 {
            	 $('#myModal').modal('show');
            	 var strArr = data.split(';');
            	 //String splitten und einen String-Array für das Füllen der einzelnen Steuerelemente verwenden.
             	$("#txtEventTitle").val(strArr[0]);
            	//Beschreibung hinzufügen
             	$("#txtEventPlace").val(strArr[2]);
            	
            	/*Das Datum und die Zeit sind als ein String zusammengepackt
            	  Die beiden Werte sind mit einem ' ' voneinander getrennt und werden
            	  dementsprechend gesplittet um die einzelnen beiden Steuerelemente damit zu befüllen*/
            	  var beginDatetime = strArr[3].split(" ");
            	  $("#txtEventBeginDate").datepicker("setDate",beginDateTime[0]);
            	  //$("#txtEventBeginTime").clockpicker()
            	   alert(strArr[3]);
            	  var endDatetime = strArr[4].split(" ");
            	  $("#txtEventEndDate").datepicker("setDate",endDatetime[0]);
            	  //Fullday ...
            	  alert(strArr[5]);
            	   $("#kategorie".val(strArr[5]));
             })
    });
});
</script>
</html>
