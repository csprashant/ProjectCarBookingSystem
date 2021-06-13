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

<h1>
			<font color="green" ><center>ALL Vehicle Infromation </center></font>
		</h1>
		<c:if test="${empty listVehicle }">
		<font color="red"><c:out value="!!!!No Record found"></c:out></font>
		</c:if>
		<c:if test="${not empty listVehicle }">
			<div class="viewport">
				<table border="2" align="center">
					<tr>
						<th>ID</th>
						<th>Vehicle Name</th>
						<th>Vehicle Color</th>
						<th>Vehicle Number</th>
						<th>Action</th>
					</tr>
					<c:forEach items="${listVehicle}" var="res">
						<tr>
							<td>VIDX0102B<c:out value="${res.id}" /></td>
							<td><c:out value="${res.VName}" /></td>
							<td><c:out value="${res.VColor}" /></td>
							<td><c:out value="${res.VNumber}" /></td>
							<td><a href="delete/${res.id}" class="btn btn-outline-danger">Delete</a></td>
							<td><a href="update/${res.id}" class="btn btn btn-primary">update</a></td>
							<td><a href="reservation-admin/${res.id}" class="btn btn btn-primary">Create Reservation</a></td>
						</tr>
					</c:forEach>
				</table>
				<h3 align="center"><a href="/CarBookingSystem/welcome"
					class="btn btn-outline-danger"> Back</a></h3>
			</div>
		</c:if>
		
				

</body>
</html>