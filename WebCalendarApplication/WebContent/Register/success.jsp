<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../bootstrap/css/createAccount.css">
<title>Account erstellen</title>
</head>
<body>


<div class="container">


		<form class="form-create" method="post" action="http://localhost:8080/WebCalendarApplication/RegisterAccount">
			<h2 class="form-signin-heading">Create Account</h2>
			
			<input name="email" type="email" id="tfield"  class="form-control" placeholder="Email address" required autofocus>
			<input  name="password" type="password" id="tfield" class="form-control" placeholder="Password" required>	

			<input name="firstname"  id="tfield" type="text" class="form-control"placeholder="First Name" required autofocus>			
			<input name="lastname" id="tfield"  type="text" class="form-control" placeholder="Last Name" required>
			<div id="tfield"><input name="street" type="text" id="street" class="form-control" placeholder="Street" required>
			<input name="streetnr"  type="text" id="number" class="form-control" placeholder="Nr" required></div>
			<div id="tfield" ><input name="postcode" type="text" id="zipcode" class="form-control" placeholder="Area Code" required>
			<input name="city" type="text" id="city" class="form-control" placeholder="City" required></div>
			
			<div class="alert alert-success" role="alert"><strong>You have been successfuly registered! </div>			
			<button class="btn btn-lg btn-primary btn-block">Login</button>
		</form>

	</div>

</body>
</html>

