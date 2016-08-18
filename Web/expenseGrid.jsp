<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="tags" uri="/struts-tags"%>
    <%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="jquery/dt_bot_select/TableTools-2.2.4/css/dataTables.tableTools.css">
<link rel="stylesheet" href="jquery/jquery-ui.css">
<link rel="stylesheet" href="jquery/datatable/css/demo_page.css">
<link rel="stylesheet" href="jquery/datatable/css/demo_table_jui.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Expense</title>
	<style>
		div.main
		{
			position: relative;
			padding-top: 0px;
			left: 0px;
			top: -40px;
			height: auto;
			/* overflow:scroll;
			overflow-x: hidden; */
			/* border: 1px solid black;  */
		}
		div.myDataTable
		{
			position: relative;
			padding-top: 50px;
			top: 30px;
			left: 100px;
			width: 800px;
		}
		div.chartDiv
		{
			position: relative;
			padding-top: 50px;
			padding-left: 50px;
			left: 30px;
		}
		.ExpenseTable th
		{
			text-align: center;
		}
		.ExpenseTable td
		{
			text-align: center;
			font-size: 13px;
		}
		.ui-widget, .ui-widget button {
        font-family: Verdana,Arial,sans-serif;
        font-size: 0.8em;
        }
                
	</style> 
</head>
<body>
<div class="main">
	<div class="chartDiv">
		<img src="displayExpenseCategoryChart" style="float:left;" id="expenseJfreeChart"/>
	</div>
	<div class="myDataTable">
		<table class="ExpenseTable" cellspacing="0" width="100%" >
			<thead>
	        	<tr>
	        		<th>Description</th>
	        		<th>Category</th>
	                <th>Payee</th>
	                <th>Date</th>
	                <th>Amount</th>
	                <th>Expense ID</th>
	        	</tr>
	        </thead>
	        <tbody>
	        </tbody>
		</table>
	</div>
	
	<div id="dialog-confirm" title="Confirm Action?">
		<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
  		This record will be permanently deleted. Are you sure?</p>
	</div>
	<div id="recordAdded" title="Message">
  		<p><span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 20px 0;"></span>
  		New Record is Inserted.</p>
	</div>
	<div id="editExpenseDialoug" title="Update record">
		<tags:form action="updateExpense" theme="simple">
			<table>
				<tr>
					<td>
						<tags:label value="Amount"></tags:label>
					</td>
					<td>
						<tags:textfield key="expense.amount" label="Amount" id="editAmount"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<tags:label value="Description"></tags:label>
					</td>
					<td>
						<tags:textfield key="expense.description" label="Description" id="editDesc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<tags:label value="Category"></tags:label>
					</td>
					<td>
						<tags:select list="categoryList" value="expense.catergory.userCodeID" label="Category" headerKey="-1" headerValue="Select Option" listKey="userCodeID" listValue="userCodeName" name="userCodeID" id="editUserCodeID"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<tags:label value="Payye"></tags:label>
					</td>
					<td>
						<tags:select list="payeesList" value="expense.payee.payeeID" label="Payees" headerKey="-1" headerValue="Select Option" listKey="payeeID" listValue="payeeDesc" name="selectedPayeeID" id="editPayeeID"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<tags:label value="Transaction Date"></tags:label>
					</td>
					<td>
						<tags:textfield key="expense.transactionDate" id="Editdatepicker" label="Transaction Date"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<tags:hidden key="expense.expenseID" id="editExpenseID"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
			</table>
		</tags:form>
	</div>
	<div id="AddExpense" title="New Record">
		<tags:form method="post" id="addExpenseForm" theme="simple">
		<table>
			<tr>
				<td><tags:label>Amount</tags:label></td>
				<td><tags:textfield key="expense.amount" label="Amount" id="amount"/>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td><tags:label>Description</tags:label></td>
				<td><tags:textfield key="expense.description" label="Description" id="description"/></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td><tags:label>Category</tags:label></td>
				<td><tags:select list="categoryList" label="Category" headerKey="-1" headerValue="Select Option" listKey="userCodeID" listValue="userCodeName" name="userCodeID" id="userCodeID"/></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td><tags:label>Payee</tags:label></td>
				<td><tags:select list="payeesList" label="Payees" headerKey="-1" headerValue="Select Option" listKey="payeeID" listValue="payeeDesc" name="selectedPayeeID" id="selectedPayeeID"/></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td><tags:label>Transaction Date:</tags:label></td>
				<td><tags:textfield key="transactionDate" id="datepicker" label="Transaction Date" /></td></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
		</table>
		</tags:form>
	</div>		
</div>

<script src="jquery/external/jquery/jquery.js"></script>
<script src="jquery/jquery-ui.js"></script>
<script src="jquery/datatable/js/jquery.dataTables.js"></script>
<script src="jquery/datatable/js/jquery.dataTables.min.js"></script>
<script src="jquery/dt_bot_select/TableTools-2.2.4/js/dataTables.tableTools.js"></script>
<script>
var removerRowID;
$.fn.dataTable.TableTools.buttons.remove_rec= $.extend(true,{},$.fn.dataTable.TableTools.buttonBase,{
	"fnClick": function( button, conf ) {
		if(removerRowID!=null && removerRowID>=0)
		{
			$( "#dialog-confirm" ).dialog(
			{
			      resizable: false,
			      height:180,
			      width: 370,
			      modal: true,
			      buttons: 
			      {
			        "Delete item": function() 
			        {
			        	var tempExpenseID=$(".ExpenseTable").dataTable().fnGetData(removerRowID).expenseID;
			        	
			       	 	$.getJSON("deleteUserExpenseJson",{expenseID:tempExpenseID},
			       	 	function(data)
			       	 	{
			       	 		$("#expenseJfreeChart").attr("src", "displayExpenseCategoryChart?timestamp=" + new Date().getTime());
			       			$(".ExpenseTable").dataTable().fnDeleteRow(removerRowID);
			       			removerRowID=null;
			     		});
				      		
			          	$( "#dialog-confirm" ).dialog( "close");
			          
			         },
			         Cancel: function() 
			         {
			          	$( "#dialog-confirm" ).dialog( "close" );
			         }
			      }
			}).css("font-size", "12px");
		}
    }
});

$.fn.dataTable.TableTools.buttons.edit_rec= $.extend(true,{},$.fn.dataTable.TableTools.buttonBase,{
	"fnClick": function( button, conf ) {
		if(removerRowID!=null && removerRowID>=0)
		{
			var tempExpenseID=$(".ExpenseTable").dataTable().fnGetData(removerRowID).expenseID;
			$.getJSON("editExpenseJson",{expenseID:tempExpenseID},function(data){
	   			
				var dateString=data.expense.transactionDate;
				var transaDate=new Date(dateString.substring(6,10),parseInt(dateString.substring(3,5))-1,dateString.substring(0,2));
				
				if(transaDate.getMonth()==new Date().getMonth())
				{
					$("#editAmount").val(data.expense.amount);
		   			$("#editDesc").val(data.expense.description);
		   			$("#editUserCodeID").val(data.expense.catergory.userCodeID);
			   		$("#editPayeeID").val(data.expense.payee.payeeID);
			   		$("#Editdatepicker").val(data.expense.transactionDate);
			   		$("#editExpenseID").val(data.expense.expenseID);
			   		
			   		$("#editExpenseDialoug").dialog({
						resizable: false,
					      height:400,
					      width: 370,
					      modal: true,
					      async: false,
					     buttons: {
					        "Save Changes": function() {
					          
					   		var f_amount=$("#editAmount").val();
					   		var f_desc=$("#editDesc").val();
					   		var f_uCodeID=$("#editUserCodeID").val();
					   		var f_payeeID=$("#editPayeeID").val();
					   		var f_fromDate=$("#Editdatepicker").val();
					   		var f_expenseID=$("#editExpenseID").val();
					   		
					   		$.getJSON("updateExpenseJson",
					 				 {
					 			"expense.amount":f_amount,
							 	"expense.description":f_desc,
							 	"userCodeID":f_uCodeID,
							 	"selectedPayeeID":f_payeeID,
							 	"expense.transactionDate":f_fromDate,
							 	"expense.expenseID":f_expenseID
					 				 },
					 				 function(response){
			 					       $(".ExpenseTable").find("tr").eq(removerRowID).find('td').eq(0).text(response.expense.description);
			 					      $(".ExpenseTable").find("tr").eq(removerRowID).find('td').eq(1).text(response.expense.catergory.userCodeName);
			 					     $(".ExpenseTable").find("tr").eq(removerRowID).find('td').eq(2).text(response.expense.payee.payeeName);
			 					    $(".ExpenseTable").find("tr").eq(removerRowID).find('td').eq(3).text(response.expense.transactionDate);
			 					   $(".ExpenseTable").find("tr").eq(removerRowID).find('td').eq(4).text(response.expense.amount);
			 					      var oTable=$(".ExpenseTable").dataTable();
			 					         oTable.fnDraw();
			 					        $("#expenseJfreeChart").attr("src", "displayExpenseCategoryChart?timestamp=" + new Date().getTime());
			 					         
					 				});
					   		
					   		$( "#editExpenseDialoug" ).dialog( "close" );
					   		
					        },
					        "Cancel":function(){
					        	$( "#editExpenseDialoug" ).dialog( "close" );
					        }
					      }
					});
				}
				else
				{
					alert("You can not edit previous month records");
				}
		   		
	   			
	   		});
		}
	}
});

$.fn.dataTable.TableTools.buttons.new_rec= $.extend(true,{},$.fn.dataTable.TableTools.buttonBase,{
	"fnClick": function( button, conf ) {

		$("#AddExpense").dialog({
			resizable: false,
		      height:400,
		      width: 370,
		      modal: true,
		      async: false,
		     buttons: {
		        "Create": function() {
		          
		        	var f_amount=$("#amount").val();
			 		var f_desc=$("#description").val();
			 		var f_uCodeID=$("#userCodeID").val();
			 		var f_payeeID=$("#selectedPayeeID").val();
			 		var f_fromDate=$("#datepicker").val();
			 		var f_recOrOne=$("#recurringOrOneTime").val();
			 		var f_occType=$("#occureneType").val();
			 		var f_noOfOccur=$("#noOfOccurence").val();
			 		var f_endDate=$("#transactionEndDate").val();
			 		
			 		
			 		 $.getJSON("addExpenseJson",
			 				 {
			 			"expense.amount":f_amount,
					 	"expense.description":f_desc,
					 	"userCodeID":f_uCodeID,
					 	"selectedPayeeID":f_payeeID,
					 	"transactionDate":f_fromDate,
					 	"recurringOrOneTime":f_recOrOne,
					 	"occureneType":f_occType,
					 	"noOfOccurence":f_noOfOccur,
					 	"transactionEndDate":f_endDate
			 				 },
			 				 function(response){
			 					$("#recordAdded").dialog({
			 						resizable: false,
			 					      height:180,
			 					      width: 370,
			 					      modal: true,
			 					      async: false,
			 					     buttons: {
			 					        OK: function() {
			 					        	$("#expenseJfreeChart").attr("src", "displayExpenseCategoryChart?timestamp=" + new Date().getTime());
			 					          $( "#recordAdded" ).dialog( "close" );
			 					         var oTable=$(".ExpenseTable").dataTable();
			 					         oTable.fnDraw();
			 					         $('#addExpenseForm').trigger("reset");
			 					        }
			 					      }
			 					});
			 				});
		   		
		   		$( "#AddExpense" ).dialog( "close" );
		   		
		        },
		        "Cancel":function(){
		        	$( "#AddExpense" ).dialog( "close" );
		        }
		      }
		});
	}
});

var dateMonth=new Date();
var thisMonth=new Date(dateMonth.setDate("01"));
var thisMonthEnd=new Date(dateMonth.getFullYear(),dateMonth.getMonth()+1,0);
$(document).ready(function(){
	
	var oTable=$(".ExpenseTable").dataTable({
		"paging":true,
        "searching": true,
        "bProcessing" : true,
        "bServerSide" : true,
        "bJQueryUI" : true,
        "info":true,
        "bAutoWidth": false,
        "aLengthMenu": [[10, 15, 20], [10, 15, 20]],
        "sPaginationType" : "full_numbers",
        "ajax" : "refreshExpenseData",
        "sDom": 'T<"clear">lfrtip',
        "sSwfPath" :"jquery/dt_bot_select/Buttons-1.1.0/swf/flashExport.swf",
        "tableTools": {
            "sRowSelect": "single",
            "aButtons":[{"sExtends":"new_rec","sButtonText": "New Expense"},
                        {"sExtends":"edit_rec","sButtonText": "Edit Expense"},
                        {"sExtends":"remove_rec","sButtonText": "Remove"}]
        },
        "aoColumns": [
             {"mData":"description","bSearchable": true,"bSortable": true},
             {"mData":"catergory.userCodeName","bSearchable": false,"bSortable": false},
             {"mData":"payee.payeeName","bSearchable": false,"bSortable": false},
             {"mData":"transactionDate","bSearchable": false,"bSortable": false},
             {"mData":"amount","sWidth":"30px","bSearchable": false,"bSortable": true},
             {"mData":"expenseID","bVisible":false}]
	});
	
	$(".ExpenseTable tbody").on('click','tr',function(){
		  //var myTable=$('.ExpenseTable').DataTable();
		   //removerRowID=myTable.row(this).index();  
		   removerRowID=$(".ExpenseTable tr.selected").index();
		   
	   });
	
	$("#dialog-confirm").hide();
	$("#recordAdded").hide();
	$("#editExpenseDialoug").hide();
	$("#AddExpense").hide();
	
	$( "#datepicker" ).datepicker({
		dateFormat: "dd-mm-yy",
		autoClose: true,
		inline: true,
		minDate: thisMonth,
		maxDate:thisMonthEnd
	});
	$( "#Editdatepicker").datepicker({
		dateFormat: "dd-mm-yy",
		autoClose: true,
		inline: true,
		minDate: thisMonth,
		maxDate:thisMonthEnd
		
	});

});

</script>
</body>
</html>