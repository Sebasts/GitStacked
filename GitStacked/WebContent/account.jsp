<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="navbar.css">
<link rel="stylesheet" href="form.css">
<title>Account Profile</title>
</head>
<body>
<jsp:include page="navbar.jsp"/> 
	<form action = "editUser.do" method ="post" modelAttribute="user">
	<label>First Name</label><input type="text" value="${user.getFName()}" name="fName"><br>
	<label>Last Name</label><input type="text" value="${user.getLName()}" name="lName"><br>
	<%-- <label>Email</label><input type="text" value="${user.userWeight}" name="userWeight"><br> --%>
	<label>Weight</label><input type="text" value="${user.userWeight}" name="userWeight"><br>
	<label>Height (feet)</label><input type="text" value="${user.heightFeet}" name="heightFeet"><br>
	<label>Height (inches)</label><input type="text" value="${user.heightInch}" name="heightInch"><br>
	<label>Password</label><input type="password" value="${user.password}" name="password"><br>
		<input type="hidden" name="userId" value="${user.id}">
		<input type="submit" value="Submit">
	</form>

</body>
</html>