<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" type="text/css"
	href="../bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css"
	href="../bootstrap/css/alerts.css">
<link rel="stylesheet" type="text/css"
	href="../bootstrap/css/signin.css">
<%
	if (session.getAttribute("email") == null) {

	} else {
%>
<jsp:forward page="../Navigationbar/MainMenu.jsp" />
<%
	}

boolean userNameExist = false;
String preMail = request.getParameter("user");						
if(preMail != null && !preMail.isEmpty())
{
	userNameExist= true;
}
%>

<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1">
<title>Login</title>
</head>
<body>
	<div class="container">

		<form class="form-signin" method="post"
			action="../Navigationbar/MainMenu.jsp">
			<h2 class="form-signin-heading">Please sign in</h2>
			<label for="inputEmail" class="sr-only">Email address</label> <input
				name="email" type="email" id="inputEmail" class="form-control"
				placeholder="Email address" value="<%
				if(userNameExist)
						{
					out.write(preMail);
					}else
						{out.write("");}%>" required autofocus> <label
				for="inputPassword" class="sr-only">Password</label> <input
				name="password" type="password" id="inputPassword"
				class="form-control" placeholder="Password" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					Remember me
				</label> <label class="createAccount"><a
					href="../Register/createAccount.jsp"><strong>Create an
							Account</strong></a></label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>

			<%
					String registered = request.getParameter("reg");						
					if(registered != null && !registered.isEmpty())
					{
						if(registered.compareTo("true")==0)
						{
						%><div class="alert alert-success matop" role="alert"><strong>You have been successfuly registered! Please log in to continue.</div><%
						}
					}
			
			%>
		</form>

	</div>
</body>
</html>