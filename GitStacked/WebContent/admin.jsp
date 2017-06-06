<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Portal</title>
</head>
<body>
	
	<div>
	<h3>Change user account type</h3>
	<form action="updateUserType.do" method="post">
		<select name="username">
			<c:forEach var="u" items="${users}">
				<option>${u.username}</option>
			</c:forEach>
		</select> <select name="choice">
			<option>ADMIN</option>
			<option>USER</option>
		</select>
		<button type="submit">Make Change</button>
	</form>
	</div>
	
	<div>
	<!-- <form:form action = "createExercise.do" method = "POST" modelAttribute="exercise"> -->
	<!-- <form:label path = "id">Unique Id:</form:label>
	<form:input path = "id"/> -->
	
<!-- 	<form:label path = "name">Name of Exercise:</form:label>
	<form:input path = "name"/> -->
	
<!-- 	<form:label path = "userWeight">Description/Instruction:</form:label>
	<form:input path = "userWeight"/>lbs
	
	<form:label path = "heightFeet">Height (ft):</form:label>
	<form:input path = "heightFeet"/>
	
	<form:label path = "heightInch">Height (in):</form:label>
	<form:input path = "heightInch"/>
	
	<form:label path = "username">Username:</form:label>
	<form:input path = "username"/>
	
	<form:label path = "password">Password:</form:label>
	<form:input path = "password"/> -->
	
	<input type = "submit" value = "Create Exercise">
	
	</form:form>
	</div>
	
	
	<%-- 	<div>
	<h3>Create Exercise</h3>
	<form action="deleteExercise.do" method="post">
		<select name="username">
			<c:forEach var="u" items="${users}">
				<option>${u.username}</option>
			</c:forEach>
		</select> <select name="choice">
			<option>ADMIN</option>
			<option>USER</option>
		</select>
		<button type="submit">Make Change</button>
	</form>
	</div> --%>
	
</body>
</html>