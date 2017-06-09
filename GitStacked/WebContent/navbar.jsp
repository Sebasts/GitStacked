<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="navbar.css">
<link rel="stylesheet" href="master.css">
<link rel="stylesheet" href="forms.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<nav>
<ul>
	<c:if test="${sessionScope.user.username != null }">
	<li><label class="navlink"><a href="home.do">Home</a></label></li>
	<li><label class="navlink"><a href="account.jsp">Edit Account</a></label></li>
	<li><label class="navlink"><a href="createWorkout.do">Create Workout</a></label></li>
	<li><label id="searchbar">Search Workouts: <input id="search" type="text"></label></li>
	</c:if>
	<c:if test="${sessionScope.user.username == null }">
	<li><label class="navlink"><a href="login.do">Sign in</a></label></li>
	</c:if>
	<c:if test="${sessionScope.user.username != null }">
	<li><label class="navlink"><a href="logout.do">Logout</a></label></li>
	</c:if>

</ul>
</nav>

	<script src="test.js"></script>
</body>
</html>