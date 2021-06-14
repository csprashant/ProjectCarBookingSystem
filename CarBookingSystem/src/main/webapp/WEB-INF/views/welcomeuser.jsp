<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page 	errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="./base.jsp"%>
</head>
<body>
<br>
<p><h4 align="center">Welcome ${user.name }</h4></P>
				

<br>
<br>
<nav><center>
||<a href="/CarBookingSystem/list-all-vehicle-user" >List All Vehicle</a>||&nbsp;&nbsp;&nbsp;
||<a href="/CarBookingSystem/reservation-status" >Reservation Status</a>||&nbsp;&nbsp;&nbsp;
||<a href="/CarBookingSystem/logout" >Logout</a></center>
</nav>
</body>
</html>