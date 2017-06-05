<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Portal</title>
</head>
<body>
	
	<div>
	<h3>Change user account type</h3>
	<form action="updateUserType.do" method="post">
		<select name="username">
			<c:forEach var="u" items="${users}">
				<option>${u.username}</option>
			</c:forEach>
		</select> <select name="choice">
			<option>ADMIN</option>
			<option>USER</option>
		</select>

		<button type="submit">Make Change</button>
	</form>
	</div>
	
</body>
</html>