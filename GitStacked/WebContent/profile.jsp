<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Your Profile</title>
</head>
<body>
	<jsp:include page="navbar.jsp" />

	<br>
	<h3>Welcome, ${user.getFName()}</h3>
	<br>
	<div>
		<form action="createWorkout.do" method="GET">
			<input type="submit" value="Create Workout">
		</form>
	</div>
	<div>
		<a href="pastWorkouts.do">Previous workouts</a>
	</div>
	<div>
		<a href="">Planned workouts</a>
	</div>
	<br>
	<label>Search Through Workouts: <input id="search" type="text"></label>

	<h2>Your List Of Workouts:</h2>
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
					<td><form action="removeWorkoutExercise.do" method="POST">
							<input type="hidden" name="id" value="${we.id}"> <input
								type="submit" value="Remove Exercise">
						</form>
					</td>
				</tr>
			</table>
		</c:forEach>
	</c:forEach>
	<script src="test.js"></script>
</body>
</html>