<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="tags" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Payee</title>
<style>
		td
		{
			text-align: left;
		}
	</style> 
</head>
<body>
<%-- <a href='<tags:url action="userHomePage"></tags:url>'>Home</a><br> --%>

<tags:form action="addPayee" method="post">
	<tags:textfield key="payee.payeeName" label="Payee Name"/>
	<tags:textfield key="payee.payeeDesc" label="Description"/>
	<tags:select list="categoryList" label="Category" headerKey="-1" headerValue="Select Option" listKey="codeID" listValue="codeDesc" name="selectedCategoryID"/>
	
	<tags:submit value="Add Payee"/>
</tags:form>
</body>
</html>