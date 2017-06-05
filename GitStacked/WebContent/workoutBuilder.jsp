<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<form action="createWorkout.do" method="POST" modelAttribute="workoutExercise">
		<select name="exerciseId">
			<c:forEach var="e" items="${exercises}">
				<option value="${e.id}">${e.name}</option>
			</c:forEach>
		</select> 
		Reps:
		<input type="text" name="reps">
		Weight:
		<input type="text" name="weight">
		<input type="submit" value="Create Workout">
	</form>
</div>
</body>
</html>