<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Build Workout</title>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	Choose from existing workout: 
	<form name="createWorkout" action="createWorkout2.do" method="POST">
	<div id="formelements">
	<select name="workoutName">
					<c:forEach var="w" items="${userWorkouts}">
						<option value="${w.name}">${w.name}</option>
					</c:forEach>
				</select> 
	<br>
	Or create a new workout<input type="text" name="newWorkoutName">
	<br>
				Exercise: <select name="exerciseId">
					<c:forEach var="e" items="${exercises}">
						<option value="${e.id}">${e.name}</option>
					</c:forEach>
				</select> 
				<br>
				Date: <input type="text" id="datepicker" name="date"></p>
				Reps: <input type="text" name="reps"> <br>
				Weight: <input type="text" name="weight"> <br>
				Duration: <input type="text" name="duration"> <br>
				
			</div>
<!-- 				<input type = "submit" value="Complete Workout">-->		
			<div id="target">
			</div>
			<button type="button" id="btnAddForm"
			onclick="CloneForm('createWorkout');">Add</button>
			<input type="submit" value="Complete Workout">
			</form>
			

			
	<script type="text/javascript">
			function CloneForm(formName) {
				var div = document.getElementById("formelements");
				var target = document.getElementById("target");
				var clone = div.cloneNode(true);
				
				target.appendChild(clone);
			}
		</script> 

	
</body>
</html>