<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="navbar.css">
<link rel="stylesheet" href="master.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<nav>
<ul>
	<li><a href="index.jsp">Home</a></li>
	<c:if test="${sessionScope.user.username == null }">
	<li><a href="login.do">Sign in</a></li>
	</c:if>
	<c:if test="${sessionScope.user.username != null }">
	<li><a href="editUser.do">Edit Account</a></li>
	<li><a href="logout.do">Logout</a></li>
	</c:if>

</ul>
</nav>
</body>
</html>