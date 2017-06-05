<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Error</title>
</head>
<body>
	Click below to enter in correct username/password
	
	
	<form:form action = "login.do" method = "POST" modelAttribute="user">
	<form:label path="username">Username:</form:label>
	<form:input path="username" />

	<form:label path="password">Password:</form:label>
	<form:input path="password" />
	</form:form>

	<input type="submit" value="Sign in">
</body>
</html>