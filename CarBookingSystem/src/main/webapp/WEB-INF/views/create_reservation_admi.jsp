<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
    <%@include file="./base.jsp" %>
</head>
<body>
 
<div align="center"><br><br>
    <h2>Create Reservation</h2><br><br>
    <form action="${pageContext.request.contextPath}/handle-create-reservation" method="post">
        Select a User Name:&nbsp;
        <select name="userID">
            <c:forEach items="${userall}" var="category">
                <option value="${category.id}">
                    ${category.name}
                </option>
            </c:forEach>
        </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         Select a vehicle:&nbsp;
         <select name="vehivleID">
            <c:forEach items="${vehicleall}" var="vehicle">
                <option value="${vehicle.id}">
                    ${vehicle.VName}
                </option>
            </c:forEach>
        </select>
    
			<input type="submit" value="Book Vehicle" class="btn btn-outline-primary"/>
			<a href="/CarBookingSystem/welcome" class="btn btn-outline-danger"> Back</a>
			

       
    </form>
</div>
</body>
</html>