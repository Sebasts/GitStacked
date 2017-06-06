<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Your Profile</title>
</head>
<body>

	<form action="createWorkout.do" method="GET">
		<input type="submit" value="Create Exercise">
	</form>

	<div id="nav">

		<div>
			<form action="logout.do" method="POST">
				<input type="submit" value="Logout">
			</form>
		</div>
		<div>
			<a href="account.jsp">Account management</a>
		</div>
		<div>
			<a href="profile.jsp">Home</a>
		</div>
		<----- Navigation Bar ----->
	</div>
	<br>
	<br>
	<br> Welcome, ${user.getFName()}

	<br>
	<br>
	<br>
	<div>
		<a href="">Create new workout</a>
	</div>
	<div>
		<a href="">Previous workouts</a>
	</div>
	<div>
		<a href="">Planned workouts</a>
	</div>
	<----- Links for other website functionality ----->

	<h2>Workouts planned for today</h2>
	<----- This should show all workouts planned for today ------->
	<c:forEach var="w" items="${userWorkouts}">
		<c:forEach var="we" items="${w.workoutExercise}">
			<h1>${we.exercise.name}</h1>
		</c:forEach>
	</c:forEach>

</body>
</html>