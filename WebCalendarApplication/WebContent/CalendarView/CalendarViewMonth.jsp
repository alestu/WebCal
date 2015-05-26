<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Calendar View</title>
</head>
<body>
	<%
		Calendar calendar = Calendar.getInstance();
		int month = calendar.getTime().getMonth();
		int dayOfWeek = calendar.getTime().getDay();
		calendar.set(calendar.getTime().getYear() + 1900, calendar.getTime().getMonth(), 1);
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM YYYY");
		
		int kw[][] = new int[6][8];
		for(int i = 0; i < kw.length; i++)
		{
			kw[i][0] = calendar.get(calendar.WEEK_OF_YEAR) + i;
			for(int j = 0; j < kw[i].length; j++)
			{
				
			}
		}
	%>
	<h2><%=formatter.format(calendar.getTime())%></h2>
	
	<table>
		<tr>
			<th>KW</th>
			<th>Montag</th>
			<th>Dienstag</th>
			<th>Mittwoch</th>
			<th>Donnerstag</th>
			<th>Freitag</th>
			<th>Samstag</th>
			<th>Sonntag</th>
		</tr>
		<tr>
			<td><%=kw[0][0]%></td>
			<td><%=kw[0][1]%></td>
			<td><%=kw[0][2]%></td>
			<td><%=kw[0][3]%></td>
			<td><%=kw[0][4]%></td>
			<td><%=kw[0][5]%></td>
			<td><%=kw[0][6]%></td>
			<td><%=kw[0][7]%></td>
		</tr>
		<tr>
			<td><%=kw[1][0]%></td>
			<td><%=kw[1][1]%></td>
			<td><%=kw[1][2]%></td>
			<td><%=kw[1][3]%></td>
			<td><%=kw[1][4]%></td>
			<td><%=kw[1][5]%></td>
			<td><%=kw[1][6]%></td>
			<td><%=kw[1][7]%></td>
		</tr>
		<tr>
			<td><%=kw[2][0]%></td>
			<td><%=kw[2][1]%></td>
			<td><%=kw[2][2]%></td>
			<td><%=kw[2][3]%></td>
			<td><%=kw[2][4]%></td>
			<td><%=kw[2][5]%></td>
			<td><%=kw[2][6]%></td>
			<td><%=kw[2][7]%></td>
		</tr>
		<tr>
			<td><%=kw[3][0]%></td>
			<td><%=kw[3][1]%></td>
			<td><%=kw[3][2]%></td>
			<td><%=kw[3][3]%></td>
			<td><%=kw[3][4]%></td>
			<td><%=kw[3][5]%></td>
			<td><%=kw[3][6]%></td>
			<td><%=kw[3][7]%></td>
		</tr>
		<tr>
			<td><%=kw[4][0]%></td>
			<td><%=kw[4][1]%></td>
			<td><%=kw[4][2]%></td>
			<td><%=kw[4][3]%></td>
			<td><%=kw[4][4]%></td>
			<td><%=kw[4][5]%></td>
			<td><%=kw[4][6]%></td>
			<td><%=kw[4][7]%></td>
		</tr>
		<tr>
			<td><%=kw[5][0]%></td>
			<td><%=kw[5][1]%></td>
			<td><%=kw[5][2]%></td>
			<td><%=kw[5][3]%></td>
			<td><%=kw[5][4]%></td>
			<td><%=kw[5][5]%></td>
			<td><%=kw[5][6]%></td>
			<td><%=kw[5][7]%></td>
		</tr>
	</table>
</body>
</html>

