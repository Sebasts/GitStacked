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
<title>Your Workouts</title>
</head>
<body>
	<jsp:include page="navbar.jsp" />

	<br>
	<c:if test="${sessionScope.user.username != null }">
	<h2>Welcome, ${user.getFName()}</h2>
	</c:if>
	
	<br>
<!-- 	<div>
		<a href="pastWorkouts.do">Previous workouts</a>
	</div>
	<div>
		<a href="">Planned workouts</a>
	</div> -->

	<h3>Your List Of Workouts:</h3>
	
	
	<c:forEach var="w" items="${userWorkouts}">
	<table>
		<tr>
		<th colspan="2">${w.name}
		<form action="removeWorkout.do" method="POST">
			<input type="hidden" name="id" value="${w.id}"> 
			<input type="submit" value="Delete Workout"> 
		</form> 
		</th>
		<th colspan="2">${w.date}</th>
		</tr>
		<tr>
					<th>Exercise Name</th>
					<th>Image</th>
					<th>Description</th>
					<th>Details</th>
				</tr>
		<c:forEach var="we" items="${w.workoutExercise}">
			
				
				<tr>
					<td>${we.exercise.name}
					<br>
					<form action="removeWorkoutExercise.do" method="POST">
							<input type="hidden" name="id" value="${we.id}"> 
							<input type="submit" value="Remove Exercise"> 
						</form>
						</td>
					<td><img src="${we.exercise.imageUrl}" alt="exercise image" />
					</td>
					<td>${we.exercise.description}</td>
					<td>Reps: ${we.reps}<br>
						Sets: ${we.duration}<br>
						Weight: ${we.weight}<br>
					</td>
				</tr>
		</c:forEach>
			</table>
			<br>
	</c:forEach>

</body>
</html>