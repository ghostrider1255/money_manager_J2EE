<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tags" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css\default_table.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%-- <a href='<tags:url action="userHomePage"></tags:url>'>Home</a><br> --%>

<%-- <display:table name="listCodeValues" pagesize="5" cellpadding="5px" cellspacing="5px" style="margin-left:50px;margin-top:20px" 
sort="list" >

<display:column property="codeName" title="Code Name"/>
<display:column property="codeDesc" title="Code Desc"/>
<display:column property="codeGroup.groupCode" title="Code Group"/>
</display:table> --%>

<tags:form>
<table align="center" border="1" cellspacing="0" class="defaultTable">
 <thead bgcolor="tan">
	<td>Code Name</td>
	<td>Code Description</td>
	<td>Code Group</td>
	<td> &nbsp; </td>
</thead>
<tags:iterator value="listCodeValues">
<tr class="tElement">
	<td>
		<%-- <a href="<tags:url action='editCodeValue'/>?selectedCodeID=<tags:property value="codeID"/>"><tags:property value="codeName"/></a> --%>
		<tags:property value="codeName"/>
	</td>
	<td><tags:property value="codeDesc"/></td>
	<td><tags:property value="codeGroup.groupCode"/></td>
	<td>
		&nbsp;&nbsp;
			<a style="text-decoration: none" href="<tags:url action="editCodeValue">
				<tags:param name="selectedCodeID" value="%{codeID}"/></tags:url>">
				<img width="20px" height="20px" src="<tags:url value="/image/edit_notes.png"/>" height="15"/>
			</a>
			&nbsp;&nbsp;
		&nbsp;&nbsp;
		<a href="<tags:url action="deleteCodeValue">
		<tags:param name="selectedCodeID" value="%{codeID}"/>
		</tags:url>"><img src="<tags:url value="/image/Trash.png"/>" height="15"/></a> &nbsp;&nbsp;
	</td>
</tr>
</tags:iterator>
<tfoot><td colspan="4">&nbsp;</td></tfoot>
</table> 
</tags:form>
</body>
</html>