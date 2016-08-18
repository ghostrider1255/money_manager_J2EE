<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tags" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css\default_table.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<tags:form>
<table align="center" border="1" cellspacing="0">

<thead class="defaultTable" align="center" border="1" cellspacing="0">
	<td>Group Name</td>
	<td>Group Description</td>
	<td>Group Status</td>
	<td> &nbsp; </td>
</thead>
<tags:iterator value="codeGroupList">
<tr class="tElement">

	<td align="center">
		<%-- <a href="<tags:url action='codeGroup/editCodeGroup'/>?selectedGroupID=<tags:property value="groupID"/>"><tags:property value="groupCode"/></a> --%>
		<tags:property value="groupCode"/>
	</td>
	<td>
		<tags:property value="groupDesc"/>
	</td>
	<td>
		<tags:property value="groupStatus"/>
	</td> 
	<td>
	&nbsp;&nbsp;
			<a style="text-decoration: none" href="<tags:url action="codeGroup/editCodeGroup">
				<tags:param name="selectedGroupID" value="%{groupID}"/></tags:url>">
				<img width="20px" height="20px" src="<tags:url value="/image/edit_notes.png"/>" height="15"/>
			</a>
			&nbsp;&nbsp;
		&nbsp;&nbsp;
		<a href="<tags:url action="codeGroup/deleteCodeGroup">
		<tags:param name="selectedGroupID" value="%{groupID}"/>
		</tags:url>"><img src="<tags:url value="/image/Trash.png"/>" height="15"/></a> &nbsp;&nbsp;
	</td>
</tr>
</tags:iterator>
<tfoot>
	<td colspan="4"> << &nbsp; &nbsp;  < &nbsp; &nbsp; > &nbsp; &nbsp; >></td>
</tfoot>
</table>
</tags:form>
</body>
</html>