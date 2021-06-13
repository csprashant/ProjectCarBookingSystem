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
	<br>
	<h1 align="center">Enter Vehicle Details</h1>
	<br>
	<br>

	<form action="handle-add-vehicle" method="post">
		<table align="center">
			<tr>
				<td>Vehicle Name</td>
				<td><input type="text" name="vName" /></td>
				<td><c:if test="${not empty vName }"><font color="red"><c:out value="${vName}"></c:out></font></c:if></td>
			</tr>
			<tr>
				<td>Vehicle Color</td>
				<td><input type="text" name="vColor" /></td>
				<td><c:if test="${not empty vColor }"><font color="red"><c:out value="${vColor}"></c:out></font></c:if></td>
			</tr>
			<tr>
				<td>Vehicle Number</td>
				<td><input type="text" name="vNumber" /></td>
				<td><c:if test="${not empty vNumber }"><font color="red"><c:out value="${vNumber}"></c:out></font></c:if></td>
			</tr>
			<tr>
				<td>
					<button type="submit" class="btn btn-primary">Add Vehicle</button>
				</td>
				<td><a href="/CarBookingSystem/welcome"
					class="btn btn-outline-danger"> Back</a></td>
			</tr>
		</table>
	</form>


	<c:if test="${not empty res }">
		<center><font color="green"><c:out value="${res }" /></font></center>
	</c:if>
</body>
</html>