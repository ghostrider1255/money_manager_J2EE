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
<title>Income</title>
	<style>
		div.main
		{
			position: relative;
			padding-top: 0px;
			left: 0px;
			top: -20px;
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
		.IncomeTable th
		{
			text-align: center;
		}
		.IncomeTable td
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
		<img src="displayChart" style="float:left;" id="incomeJFreeChart"/>
	</div>
	<div class="myDataTable">
		<table class="IncomeTable" cellspacing="0" width="100%" >
			<thead>
	        	<tr>
	        		<th>Description</th>
	        		<th>Category</th>
	                <th>Payee</th>
	                <th>Date</th>
	                <th>Amount</th>
	                <th>Income ID</th>
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
	<div id="editIncomeDialoug" title="Update record">
		<tags:form action="updateIncome" theme="simple">
			<table class="editIncome">
				<tr>
					<td>
						<tags:label>Amount</tags:label>
					</td>
					<td>
						<tags:textfield key="income.amount" label="Amount" id="editAmount"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<tags:label>Description</tags:label>
					</td>
					<td>
						<tags:textfield key="income.description" label="Description" id="editDesc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<tags:label>Category</tags:label>
					</td>
					<td>
						<tags:select list="categoryList" value="income.catergory.userCodeID" label="Category" headerKey="-1" headerValue="Select Option" listKey="userCodeID" listValue="userCodeName" name="userCodeID" id="editUserCodeID"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<tags:label>Payee</tags:label>
					</td>
					<td>
						<tags:select list="payeesList" value="income.payee.payeeID" label="Payees" headerKey="-1" headerValue="Select Option" listKey="payeeID" listValue="payeeDesc" name="selectedPayeeID" id="editPayeeID"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<tags:label>Transaction Date</tags:label>
					</td>
					<td>
						<tags:textfield key="income.transactionDate" id="Editdatepicker" label="Transaction Date"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<tags:hidden key="income.incomeID" id="editIncomeID"/>
					</td>
				</tr>
			</table>
		</tags:form>
	</div>
	<div id="AddIncome" title="New Record">
		<tags:form method="post" id="addIncomeForm" theme="simple">
		<table>
			<tr>
				<td><tags:label>Amount</tags:label></td>
				<td><tags:textfield key="income.amount" label="Amount" id="amount"/></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td><tags:label>Description</tags:label></td>
				<td><tags:textfield key="income.description" label="Description" id="description"/>
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
				<td><tags:textfield key="transactionDate" id="datepicker" label="Transaction Date" /></td>
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
			        	var tempIncomeID=$(".IncomeTable").dataTable().fnGetData(removerRowID).incomeID;
			        	
			       	 	$.getJSON("deleteUserIncomesJson",{incomeID:tempIncomeID},
			       	 	function(data)
			       	 	{
			       	 		$("#incomeJFreeChart").attr("src", "displayChart?timestamp=" + new Date().getTime());// to refresh the chart
			       			$(".IncomeTable").dataTable().fnDeleteRow(removerRowID);
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
			var tempIncomeID=$(".IncomeTable").dataTable().fnGetData(removerRowID).incomeID;
			$.getJSON("editIncomeJson",{incomeID:tempIncomeID},function(data){
	   			
				var dateString=data.income.transactionDate;
				var transaDate=new Date(dateString.substring(6,10),parseInt(dateString.substring(3,5))-1,dateString.substring(0,2));
				
				if(transaDate.getMonth()==new Date().getMonth())
				{
					$("#editAmount").val(data.income.amount);
		   			$("#editDesc").val(data.income.description);
		   			$("#editUserCodeID").val(data.income.catergory.userCodeID);
			   		$("#editPayeeID").val(data.income.payee.payeeID);
			   		$("#Editdatepicker").val(data.income.transactionDate);
			   		$("#editIncomeID").val(data.income.incomeID);
			   	
			   		$("#editIncomeDialoug").dialog({
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
					   		var f_incomeID=$("#editIncomeID").val();
					   		
					   		$.getJSON("updateIncomeJson",
					 				 {
					 			"income.amount":f_amount,
							 	"income.description":f_desc,
							 	"userCodeID":f_uCodeID,
							 	"selectedPayeeID":f_payeeID,
							 	"income.transactionDate":f_fromDate,
							 	"income.incomeID":f_incomeID
					 				 },
					 				 function(response)
					 				 {
			 					       $(".IncomeTable").find("tr").eq(removerRowID).find('td').eq(0).text(response.income.description);
			 					       $(".IncomeTable").find("tr").eq(removerRowID).find('td').eq(1).text(response.income.catergory.userCodeName);
			 					       $(".IncomeTable").find("tr").eq(removerRowID).find('td').eq(2).text(response.income.payee.payeeName);
			 					       $(".IncomeTable").find("tr").eq(removerRowID).find('td').eq(3).text(response.income.transactionDate);
			 					       $(".IncomeTable").find("tr").eq(removerRowID).find('td').eq(4).text(response.income.amount);
			 					      
			 					       var oTable=$(".IncomeTable").dataTable();
			 					         oTable.fnDraw();
			 					        $("#incomeJFreeChart").attr("src", "displayChart?timestamp=" + new Date().getTime());
					 				});
					   		
					   		$( "#editIncomeDialoug" ).dialog( "close" );
					   		
					        },
					        "Cancel":function(){
					        	$( "#editIncomeDialoug" ).dialog( "close" );
					        }
					      }
					});
				}
				else
				{
					alert("Can not edit previous Month records");
				}
	   		});
		}
	}
});

$.fn.dataTable.TableTools.buttons.new_rec= $.extend(true,{},$.fn.dataTable.TableTools.buttonBase,{
	"fnClick": function( button, conf ) 
	{
		$(":button:contains('Create')").prop("disabled", true).addClass("ui-state-disabled");
		
		$("#AddIncome").dialog(
		{
			resizable: false,
		      height:400,
		      width: 370,
		      modal: true,
		      async: false,
		     buttons: 
		     {
		        "Create": function() 
		        {
		        	var f_amount=$("#amount").val();
			 		var f_desc=$("#description").val();
			 		var f_uCodeID=$("#userCodeID").val();
			 		var f_payeeID=$("#selectedPayeeID").val();
			 		var f_fromDate=$("#datepicker").val();
			 		var f_recOrOne=$("#recurringOrOneTime").val();
			 		var f_occType=$("#occureneType").val();
			 		var f_noOfOccur=$("#noOfOccurence").val();
			 		var f_endDate=$("#transactionEndDate").val();
			 		
			 		
			 		 $.getJSON("addIncomeJson",
			 		{
			 			"income.amount":f_amount,
					 	"income.description":f_desc,
					 	"userCodeID":f_uCodeID,
					 	"selectedPayeeID":f_payeeID,
					 	"transactionDate":f_fromDate,
					 	"recurringOrOneTime":f_recOrOne,
					 	"occureneType":f_occType,
					 	"noOfOccurence":f_noOfOccur,
					 	"transactionEndDate":f_endDate
			 		},
			 		function(response)
			 		{
			 			$("#recordAdded").dialog(
			 			{
			 				resizable: false,
			 				height:180,
			 				width: 370,
			 				modal: true,
			 				async: false,
			 				buttons: 
			 				{
			 					OK: function() 
			 					{
			 						$("#incomeJFreeChart").attr("src", "displayChart?timestamp=" + new Date().getTime());
			 						
			 						$( "#recordAdded" ).dialog( "close" );
			 					    var oTable=$(".IncomeTable").dataTable();
			 					    oTable.fnDraw();
			 					    $('#addIncomeForm').trigger("reset");
			 					 }
			 				}
			 			});
			 		});
		   		
		   			$( "#AddIncome" ).dialog( "close" );
		        },
		        "Cancel":function()
		        {
		        	$( "#AddIncome" ).dialog( "close" );
		        }
		      }
		});
	}
});

var dateMonth=new Date();
var thisMonth=new Date(dateMonth.setDate("01"));
var thisMonthEnd=new Date(dateMonth.getFullYear(),dateMonth.getMonth()+1,0);

$(document).ready(function(){
	
	var oTable=$(".IncomeTable").dataTable({
		"paging":true,
        "searching": true,
        "bProcessing" : true,
        "bServerSide" : true,
        "bJQueryUI" : true,
        "info":true,
        "bAutoWidth": false,
        "aLengthMenu": [[10, 15, 20], [10, 15, 20]],
        "sPaginationType" : "full_numbers",
        "ajax" : "refreshIncomeData",
        "sDom": 'T<"clear">lfrtip',
        "sSwfPath" :"jquery/dt_bot_select/Buttons-1.1.0/swf/flashExport.swf",
        "tableTools": {
            "sRowSelect": "single",
            "aButtons":[{"sExtends":"new_rec","sButtonText": "New Income"},
                        {"sExtends":"edit_rec","sButtonText": "Edit Income"},
                        {"sExtends":"remove_rec","sButtonText": "Remove"}]
        },
        "aoColumns": [
             {"mData":"description","bSearchable": true,"bSortable": true},
             {"mData":"catergory.userCodeName","bSearchable": false,"bSortable": false},
             {"mData":"payee.payeeName","bSearchable": false,"bSortable": false},
             {"mData":"transactionDate","bSearchable": false,"bSortable": true},
             {"mData":"amount","sWidth":"30px","bSearchable": false,"bSortable": true},
             {"mData":"incomeID","bVisible":false}]
	});
	
	$(".IncomeTable tbody").on('click','tr',function(){
		  var myTable=$('.IncomeTable').DataTable();
		   //removerRowID=myTable.row(this).index();  
		   removerRowID=$(".IncomeTable tr.selected").index();
		   
	   });
	
	$("#dialog-confirm").hide();
	$("#recordAdded").hide();
	$("#editIncomeDialoug").hide();
	$("#AddIncome").hide();
	
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