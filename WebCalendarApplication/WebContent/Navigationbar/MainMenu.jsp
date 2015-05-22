<%@page import="com.sun.org.apache.xml.internal.utils.StringComparable"%>
<%@page import="application.Controller.DatabaseController"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

 <%
	String email ="";	
 	String password ="";
 	
 
 	if(!request.equals(null))
 	{
 		

		email = request.getParameter("email");
		password = request.getParameter("password");
		session.setAttribute("email", email );
 		session.setAttribute("password_", password );
 				
 		
 	
	
 	}
 	else
 	{
 		email = session.getAttribute("email").toString();
		password = session.getAttribute("password_").toString();
 		
 		
 	}
 	
 
	String userName = "not-assigned";
 	
	
	
	
	DatabaseController controller = new DatabaseController();
	
	if(controller.checkEmailAndPassword(email, password))
	{
		userName = controller.getFullUsernameByEmail(email);
		
	}
	else
	{
		%> <jsp:forward page="../Login/login.jsp"/><%

		
	}
 
 	
 %>  
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WebCal</title>
<link rel="stylesheet" href="../bootstrap/css/navigation.css" type="text/css">
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css" type="text/css">
<script  type="text/javascript" src="../bootstrap/js/jquery.js"></script>
<script  type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <!-- NAVIGATIONBAR ELEMENTE  -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
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
      <!-- Accountverwaltung, Importieren, Exportieren & Abmelden -->
        <li class="dropdown">
        <!-- Anzeigename -->
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><%= userName %>
        
        
     <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
          <!-- Funktionen -->
            <li><a href="#">Accountverwaltung</a></li>
            <li class="divider"></li>
            <li><a href="#">Importieren</a></li>
            <li><a href="#">Exportieren</a></li>
            <li class="divider"></li>
            <li><a href="#">Abmelden</a></li>
          </ul>
        </li>
        <!-- Termin erstellen -->
        <li>
        <button class="btn btn-default navbar-btn"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
        </li>
      </ul>
             
      <!-- Filter -->
	  <div class="btn-group navbar-form navbar-left test" data-toggle="buttons">
	  <label class="btn btn-primary active">
	    <input type="radio" name="options" id="option1" autocomplete="off"> Tag 
	  </label>
	  <label class="btn btn-primary">
	    <input type="radio" name="options" id="option2" autocomplete="off" checked> Woche
	  </label>
	  <label class="btn btn-primary">
	    <input type="radio" name="options" id="option3" autocomplete="off"> Monat
	  </label>
	  </div>
	  	  
	   <!-- Suche -->
      <form class="navbar-form navbar-right" role="search">
        <div class="form-group has-feedback-left has-feedback">
          <input type="text" class="form-control" placeholder="Suche">
          <i class="form-control-feedback glyphicon glyphicon-search"></i>
        </div>
      </form>
	  <!-- NAVIGATIONBAR ELEMENTE ENDE  -->
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</body>
</html>