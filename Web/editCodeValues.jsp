<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="tags" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<tags:form action="updateCodeValue">
	<table>
		<tr>
			<td>
				<tags:textfield label="Code Name" key="codeValue.codeName"/>
			</td>
		</tr>
		<tr>
			<td>
				<tags:textfield label="Code Desc" key="codeValue.codeDesc"/>
			</td>
		</tr>
		<tr>
			<td>
				<tags:select list="listCodeGroups" label="Code Group" headerKey="-1" headerValue="Select Option" listKey="groupID" 
				listValue="groupCode" name="groupID" value="codeValue.codeGroup.groupID"/>
<%-- 			<tags:textfield label="Group Code" key="codeGroup.groupStatus"/> --%>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
 				<tags:hidden key="codeValue.codeID"/> 
			</td>
		</tr>
		<tr>
			<td>
				<tags:submit value="Update"/>
			</td>
		</tr>
</table>
</tags:form>
</body>
</html>