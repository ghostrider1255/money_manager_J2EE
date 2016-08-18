<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="tags" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="jquery/dt_bot_select/TableTools-2.2.4/css/dataTables.tableTools.css">
<link rel="stylesheet" href="jquery/jquery-ui.css">
<link rel="stylesheet" href="css/datatable/demo_page.css">
<link rel="stylesheet" href="css/datatable/demo_table_jui.css">
<link rel="stylesheet" href="css/default_table.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Budget</title>
	<style>
		
		.ui-widget, .ui-widget button 
		{
        	font-family: Verdana,Arial,sans-serif;
        	font-size: 0.8em;
        }
        #budgetWidget
        {
        	position: relative;
        	padding-top: 0px;
        	left: 0px;
        	width: auto;
        	height: 500px;
        }
        .budgetSection
        {
        	position: relative;
        	padding-top: 200px;
        	padding-left: 100px;
        	width: 900px;
        	top: 10px;
        }
        .BudgetTable th
		{
			text-align: center;
		}
		.BudgetTable td
		{
			text-align: center;
			font-size: 13px;
		}
	</style> 
</head>
<body>

<div id="budgetWidget">
	<div class="budgetSection">
		<table class="BudgetTable" cellspacing="0" width="100%" >
			<thead>
	        	<tr>
	        		<th>Budget Start Date</th>
	        		<th>Budget End Date</th>
	                <th>Income Amount</th>
	                <th>Expense Amount</th>
	                <th>Status</th>
	        	</tr>
	        </thead>
	        <tbody>
	        </tbody>
		</table>
	</div>
</div>

<script src="jquery/external/jquery/jquery.js"></script>
<script src="jquery/jquery-ui.js"></script>
<script src="jquery/datatable/jquery.dataTables.js"></script>
<script src="jquery/datatable/jquery.dataTables.min.js"></script>
<script src="jquery/dt_bot_select/TableTools-2.2.4/js/dataTables.tableTools.js"></script>
<script>

$(document).ready(function(){
	
	$(".BudgetTable").dataTable({
		"paging":true,
        "searching": true,
        "bProcessing" : true,
        "bServerSide" : true,
        "bJQueryUI" : true,
        "info":true,
        "bAutoWidth": false,
        "aLengthMenu": [[10, 15, 20], [10, 15, 20]],
        "sPaginationType" : "full_numbers",
        "ajax" : "refreshBudgetData",
        "aoColumns": [
             {"mData":"budgetStartDate","bSearchable": true,"bSortable": true},
             {"mData":"budgetEndDate","bSearchable": false,"bSortable": false},
             {"mData":"incomeAmount","bSearchable": false,"bSortable": true},
             {"mData":"expenseAmount","bSearchable": false,"bSortable": true},
             {"mData":"budgetStatus.codeDesc","bSearchable": false,"bSortable": false}]
	});

});

</script>
</body>
</html>