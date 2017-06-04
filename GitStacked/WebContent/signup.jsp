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

	<div>
	<form:form action = "createUser.do" method = "POST">
	<form:label path = "fname">First Name:</form:label>
	<form:input path = "fname"/>
	
	<form:label path = "lname">Last Name:</form:label>
	<form:input path = "fname"/>
	
	<form:label path = "weight">Weight:</form:label>
	<form:input path = "weight"/>lbs
	
	<form:label path = "height">Height:</form:label>
	<form:input path = "heightFeet"/>
	<form:input path = "heightInch"/>
	
	<form:label path = "username">Username:</form:label>
	<form:input path = "username"/>
	
	<form:label path = "password">Password:</form:label>
	<form:input path = "password"/>
	
	</form:form>
	</div>

</body>
</html>