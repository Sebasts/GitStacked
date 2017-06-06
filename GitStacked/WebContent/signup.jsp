<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up</title>
</head>
<body>
	<jsp:include page="navbar.jsp"/> 
	<div>
	<form:form action = "createUser.do" method = "POST" modelAttribute="user">
	<form:label path = "fName">First Name:</form:label>
	<form:input path = "fName"/>
	
	<form:label path = "lName">Last Name:</form:label>
	<form:input path = "lName"/>
	
	<form:label path = "userWeight">Weight:</form:label>
	<form:input path = "userWeight"/>lbs
	
	<form:label path = "heightFeet">Height (ft):</form:label>
	<form:input path = "heightFeet"/>
	
	<form:label path = "heightInch">Height (in):</form:label>
	<form:input path = "heightInch"/>
	
	<form:label path = "username">Username:</form:label>
	<form:input path = "username"/>
	
	<form:label path = "password">Password:</form:label>
	<form:input path = "password"/>
	
	<input type = "submit" value = "Sign Up">
	
	</form:form>
	</div>

</body>
</html>