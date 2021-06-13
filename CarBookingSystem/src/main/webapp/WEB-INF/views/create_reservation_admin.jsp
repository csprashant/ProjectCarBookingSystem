<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page 	errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="./base.jsp" %>
</head>
<body>
	<h1 align = "center">Book Vehicle </h1>
	<form action="${pageContext.request.contextPath}/handle-create-reservation-admin" method="post">
	<table align="center">
		<tr>
			<td>Vehicle ID</td><td><input type="text" name="vehicleId" value="${vehivleID}" /></td>
		</tr>
			
		<tr>
			<td>User ID</td><td><input type="text" name="userId" /></td>
		</tr>
			<tr>
			<td>User Name</td><td><input type="text" name="userName" /></td>
		</tr>
		<tr>
		
			<td>From Date</td><td><input type="date"  name="fromDate"/></td>
		</tr>
		<tr>
			<td>To Date</td><td><input type="date" name="toDate"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="Book Vehicle" class="btn btn-outline-primary"/> </td>
			<td><a href="/CarBookingSystem/list-all-vehicle" class="btn btn-outline-danger"> Back</a></td>
			
		</tr>
	</table>
	</form>
			<font color="green"><CENTER><c:out value="${msg}"></c:out></CENTER></font>
</body>
</html>