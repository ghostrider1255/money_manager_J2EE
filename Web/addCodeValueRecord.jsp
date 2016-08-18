<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tags" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
		td
		{
			text-align: left;
		}
	</style> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<tags:form action="addCodeValueRecord">
<tags:textfield key="codeValue.codeName" label="Code Name"/>
<tags:textfield key="codeValue.codeDesc" label="Code Desc"/>
<tags:select list="listCodeGroups" label="Code Group" headerKey="-1" headerValue="Select Option" listKey="groupID" listValue="groupDesc" name="groupID"/>
<tags:submit/>
</tags:form>
</body>
</html>