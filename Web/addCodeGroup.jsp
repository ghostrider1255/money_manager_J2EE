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

<tags:form action="addCodeGroup">
<tags:textfield key="codeGroup.groupCode" label="Group Name" />
<tags:textfield key="codeGroup.groupDesc" label="Group Desc"/>
<tags:submit/>
</tags:form>
</body>
</html>