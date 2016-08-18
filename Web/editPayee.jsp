<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="tags" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Payee</title>
</head>
<body>
<br><br>
<tags:form action="updatePayee">
<table>
	<tr>
		<td>
		<tags:textfield label="Payee Name" key="payee.payeeName"/>
		</td>
	</tr>
	<tr>
		<td>
		<tags:textfield label="Payee Desc" key="payee.payeeDesc"/>
		</td>
	</tr>
	<tr>
		<td><tags:select list="categoryList" label="Transaction Category" headerKey="-1" headerValue="Select Option" value="payee.payeeCateogry.codeID" listKey="codeID" listValue="codeDesc" name="selectedCategoryID"/></td>
	</tr>
	<tr>
		<td>
		<tags:select list="#{'true':'ACTIVE','false':'IN-ACTIVE'}" label="Select Status" key="payee.payeeStatus"/>
<%-- 		<tags:textfield label="Group Code" key="codeGroup.groupStatus"/> --%>
		</td>
	</tr>
	
	<tr>
		<td>
			<tags:hidden key="payee.payeeID"/>
		</td>
	</tr>
	<tr>
		<td>
		<tags:submit label="Update"/>
		</td>
	</tr>
</table>
</tags:form>


</body>
</html>