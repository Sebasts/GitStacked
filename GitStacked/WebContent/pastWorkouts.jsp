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
	<jsp:include page="navbar.jsp" />

	<c:forEach var="w" items="${userWorkouts}">
		<h4>${w.name}</h4>
		<h5>${w.date}</h5>
		<form action="removeWorkout.do" method="POST">
			<input type="hidden" name="id" value="${w.id}"> <input
				type="submit" value="Delete Workout">
		</form>
		<c:forEach var="we" items="${w.workoutExercise}">
			<table>
				<tr>
					<th>Exercise Name</th>
					<th>Image</th>
					<th>Description</th>
				</tr>
				<tr>
					<td>${we.exercise.name}</td>
					<td><img src="${we.exercise.imageUrl}" alt="exercise image" />
					</td>
					<td>${we.exercise.description}</td>
				</tr>
			</table>
		</c:forEach>
	</c:forEach>

</body>
</html>