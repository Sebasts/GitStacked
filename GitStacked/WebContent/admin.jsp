<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
	<form:form action = "createExercise.do" method = "POST" modelAttribute="exercise">s
	
	<form:label path = "name">Name of Exercise:</form:label>
	<form:input path = "name"/>[Limited to 100 chars]
	
	<form:label path = "desc">Description/Instruction:</form:label>
	<form:input path = "desc"/>[Limited to 1000 chars]
	
	<form:label path = "imageUrl">Image Url:</form:label>
	<form:input path = "imageUrl"/>
	
	<form:label path = "calories">Calories per Movement:</form:label>
	<form:input path = "calories"/>[Integer]
	
	<form:label path = "muscleGroup">Muscle Group ENUM:</form:label>
	<form:input path = "muscleGroup"/>[ARMS,LEGS,ABS,CHEST,BACK,SHOULDERS]
	
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