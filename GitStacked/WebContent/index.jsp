<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Git Stacked - Homepage</title>
</head>
<body>
<jsp:include page="navbar.jsp"/> 
Start building your own workout plan

<div class="left">
Sign Up Now!
<form action = "createUser.do" method = "GET">
<input type = "submit" value = "Sign Up">
</form>
Already have an account?
<a href="login.do">Click here</a>
</div>


<div class="left">
Search for an Exercise
<form action = "getExercise.do" method = "GET">
<input type = "text" name = "exerciseName">
<input type = "submit" value = "Search">
</form>
</div>

<div class="frontPage">
Start Getting In Shape
</div>

</body>
</html>