<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="tags" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="jquery/jquery-ui.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
.ui-progressbar .ui-widget-header {
	 background-image: none !important;
	 background-color: #0097A7 !important; //Any colour can go here
    }
 .ui-progressbar.ui-widget-content {
 	background-image: none !important;
    background-color: #B3E5FC !important; //Any colour can go here
  } 
   #progressbar span 
   {
    	float:left; 
    	text-align:left;
    	font-family:tahoma;
    	font-size:8pt; 
    	color:white; 
    	padding: 5px 20px;
    	margin-left:10%;
	}
	#progressbar
	{
		float:left;
		
	}
	.MyDashBoard
	{
		position: relative;
		padding-top: 10px;
		padding-left: 100px;
		top: -10px;
		float:left;
		height: auto
		/* border: 1px solid black; */
	}
</style>
<title>DashBoard</title>
</head>
<body>

<div class="MyDashBoard">
	<form action="progressBar">
		<tags:hidden name="budgetProgress" class="hiddenProgres"></tags:hidden>
	</form>
	<table height="400" width="90%" border="0">
		<tr>
			<td colspan="2"><div id="progressbar" style="align:center;"><span><tags:property value="budgetProgress"/>%</span></div></td>
		</tr>
		<tr><td colspan="2">&nbsp;</td></tr>
		<tr><td colspan="2"><img src="monthWiseBarChart" style="float:center;"/></td>
	</table>
</div>
 

<script src="jquery/external/jquery/jquery.js"></script>
<script src="jquery/jquery-ui.js"></script>
<script>
$(document).ready(function(){
	 var progresVal=$("input[name='budgetProgress']").val();
		$("#progressbar").width(300);
		$("#progressbar").height(25);
		$( "#progressbar" ).progressbar({
		  value: parseInt(progresVal)
		 });
		 
});
	
</script>
</body>
</html>