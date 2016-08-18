<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="tags" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Log-in</title>
</head>
<body>
<br>
	<tags:form action="loginUser" method="post">
		<tags:textfield key="user.userCode" label="User Name"/>
		<tags:password key="user.password" label="Password"/>
		<tags:submit value="Log-in"/>
	</tags:form>
	<tags:a href="registerUser.jsp">Register new User</tags:a><br><br>
</body>
</html>