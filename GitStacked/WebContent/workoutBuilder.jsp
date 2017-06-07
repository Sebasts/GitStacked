<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Build Workout</title>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div>
		<form name="createWorkout" action="createWorkoutList.do" method="POST">
				<select name="exerciseId">
					<c:forEach var="e" items="${exercises}">
						<option value="${e.id}">${e.name}</option>
					</c:forEach>
				</select> 
				Reps: <input type="text" name="reps"> 
				Weight: <input type="text" name="weight"> 
				Duration: <input type="text" name="duration"> 
				Create a name for your workout: <input type="text" name="name">
				<input type = "submit" value="Add Exercise">
		<!-- <button type="button" id="btnAddForm"
			onclick="CloneForm('createWorkout');">Add</button> -->
			
			</form>
			
			<form name="createWorkout" action="completeWorkout.do" method="POST">
			<input type="submit" value="Complete Workout">
			</form>
			
<!-- 		<script type="text/javascript">
			function CloneForm(formName) {
				var formCount = document.forms.length;
				var oForm = document.forms[formName];
				var clone = oForm.cloneNode(true);
				clone.name += "_" + formCount;
				document.body.appendChild(clone);
			}
		</script> -->

	</div>
</body>
</html>