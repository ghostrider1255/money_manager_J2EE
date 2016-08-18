<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="tags" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css\default_table.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Incomes List</title>
</head>
<body>
<tags:form>
<table align="center" border="1" cellspacing="0" cellpadding="0" class="defaultTable">
	<thead>
		<td align="center">&nbsp;&nbsp;Payee Name&nbsp;&nbsp;</td>
		<td align="center">&nbsp;&nbsp;Description&nbsp;&nbsp;</td>
		<td align="center">&nbsp;&nbsp;Category&nbsp;&nbsp;</td>
		<td> &nbsp; </td>
	</thead>
	<tags:iterator value="payeeList">
	<tr class="tElement">
		<td align="center">
			<%-- <a href="<tags:url action='editPayee'/>?selectedPayeeID=<tags:property value="payeeID"/>"><tags:property value="payeeName"/></a> --%>
			<tags:property value="payeeName"/>
		</td>
		<td align="center"><tags:property value="payeeDesc"/></td>
		<td align="center"><tags:property value="payeeCateogry.codeDesc"/></td>
		<td align="center">
		&nbsp;&nbsp;
		<a style="text-decoration: none" href="<tags:url action="editPayee">
				<tags:param name="selectedPayeeID" value="%{payeeID}"/></tags:url>">
				<img width="20px" height="20px" src="<tags:url value="/image/edit_notes.png"/>"/>
		</a>
		
			&nbsp;&nbsp;
		<a href="<tags:url action="deletePayee">
				<tags:param name="selectedPayeeID" value="%{payeeID}"/></tags:url>">
				<img src="<tags:url value="/image/Trash.png"/>" height="15"/></a> &nbsp;&nbsp;
		</td>
	</tr>
	</tags:iterator>
	<tfoot>
		<td colspan="4"> &nbsp; </td>
	</tfoot>
</table> 
</tags:form>
</body>
</html>