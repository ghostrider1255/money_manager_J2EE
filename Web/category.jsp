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
<title>User Categories</title>
	<style>
		
		tr td
		{
			text-align: center;
		}
		.ui-widget, .ui-widget button {
        font-family: Verdana,Arial,sans-serif;
        font-size: 0.8em;
        }
        div.myDataTable
		{
			position: relative;
			padding-top: 120px;
			top: 30px;
			left: 100px;
			width: 800px;
		}
		div.main
		{
			position: relative;
			padding-top: 0px;
			left: 0px;
			top: -20px;
			height: auto;
			width: 800px;
			/* overflow:scroll;
			overflow-x: hidden; */
			/* border: 1px solid black;  */
		}
		.UserCategoryTable td
		{
			font-size: 13px;
		}
		.UserCategoryTable th
		{
			text-align: center;
		}
	</style> 
</head>
<body>

<div class="main">
	<div class="myDataTable">
		<table class="UserCategoryTable" cellspacing="0" width="100%" >
			<thead>
	        	<tr>
	        		<th>Category Name</th>
	        		<th>Category Description</th>
	                <th>Budget Type</th>
	                <th>Category ID</th>
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
	<div id="editCategoryDialoug" title="Update record">
		<tags:form theme="simple">
			<table class="editCategory">
				<tr>
					<td>
						<tags:label value="Category Name"></tags:label>
					</td>
					<td>
						<tags:textfield key="userCodeValue.userCodeName" label="Category Name" id="editCategoryName"/>
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
						<tags:textfield key="userCodeValue.userCodeDesc" label="Description" id="editCategoryDesc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<tags:label value="Budget Type"></tags:label>
					</td>
					<td>
						<tags:select list="listCodeValues" label="Budget Category" value="userCodeValue.codeValues.codeID" 
						name="codeID" listKey="codeID" listValue="codeDesc" headerKey="-1" headerValue="--Select Category--"
						id="editCodeValueType"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						<tags:hidden key="userCodeValue.userCodeID" id="editUserCategoryID"/> 
					</td>
				</tr>
			</table>
		</tags:form>
	</div>
	<div id="AddCategory" title="New Record">
		<tags:form method="post" id="addCategoryForm" theme="simple">
			<table>
				<tr>
					<td><tags:label>Category Name</tags:label></td>
					<td><tags:textfield key="userCodeValue.userCodeName" label="Category Name" id="categoryName"/></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td><tags:label>Description</tags:label></td>
					<td><tags:textfield key="userCodeValue.userCodeDesc" label="Description" id="categoryDesc"/></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td><tags:label>Budget Type</tags:label></td>
					<td><tags:select list="listCodeValues" label="Budget Category" value="userCodeValue.codeValues.codeID"
			 			name="codeID" listKey="codeID" listValue="codeDesc" 
						headerKey="-1" headerValue="--Select Category--" id="codeValueType"/></td>
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
			        	var tempCategoryID=$(".UserCategoryTable").dataTable().fnGetData(removerRowID).userCodeID;
			        	
			       	 	$.getJSON("deleteUserCodeValueJson",{selectedUserCodeValueID:tempCategoryID},
			       	 	function(data)
			       	 	{
			       			$(".UserCategoryTable").dataTable().fnDeleteRow(removerRowID);
			       			removerRowID=-1;
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
			var tempCategoryID=$(".UserCategoryTable").dataTable().fnGetData(removerRowID).userCodeID;
			
			
			$.getJSON("editUserCodeValueJson",{selectedUserCodeValueID:tempCategoryID},function(data){
	   			
				
				$("#editCategoryName").val(data.userCodeValue.userCodeName);
				$("#editCategoryDesc").val(data.userCodeValue.userCodeDesc);
				$("#editCodeValueType").val(data.userCodeValue.codeValues.codeID);
				$("#editUserCategoryID").val(data.userCodeValue.userCodeID);
				
				$("#editCategoryDialoug").dialog({
					resizable: false,
				      height:300,
				      width: 370,
				      modal: true,
				      async: false,
				     buttons: {
				        "Save Changes": function() {
				        	var f_categoryName=$("#editCategoryName").val();
				        	var f_categoryDesc=$("#editCategoryDesc").val();
				        	var f_categoryTypeID=$("#editCodeValueType").val();
				        	var f_categoryID=$("#editUserCategoryID").val();
				   		
				        	$.getJSON("updateUserCodeValueJson",
				        			{
				        				"userCodeValue.userCodeName":f_categoryName,
				        				"userCodeValue.userCodeDesc":f_categoryDesc,
				        				"codeID":f_categoryTypeID,
				        				"userCodeValue.userCodeID":f_categoryID,
				        			},
				        			function(response){
				        				$(".UserCategoryTable").find("tr").eq(removerRowID).find('td').eq(0).text(response.userCodeValue.userCodeName);
				        				$(".UserCategoryTable").find("tr").eq(removerRowID).find('td').eq(1).text(response.userCodeValue.userCodeDesc);
				        				$(".UserCategoryTable").find("tr").eq(removerRowID).find('td').eq(2).text(response.userCodeValue.codeValues.codeDesc);

				        				var oTable=$(".UserCategoryTable").dataTable();
				        				oTable.fnDraw();
				        			});
				        	
				   		$( "#editCategoryDialoug" ).dialog( "close" );
				   		
				        },
				        "Cancel":function(){
				        	$( "#editCategoryDialoug" ).dialog( "close" );
				        }
				      }
				});
	   			
	   		});
		}
	}
});

$.fn.dataTable.TableTools.buttons.new_rec= $.extend(true,{},$.fn.dataTable.TableTools.buttonBase,{
	"fnClick": function( button, conf ) {

		$("#AddCategory").dialog({
			resizable: false,
		      height:300,
		      width: 370,
		      modal: true,
		      async: false,
		     buttons: {
		        "Create": function() {
		          
		        	var f_catName=$("#categoryName").val();
			 		var f_catDesc=$("#categoryDesc").val();
			 		var f_catTypeID=$("#codeValueType").val();
			 		
			 		 $.getJSON("addUserCodeValueJson",
			 				 {
			 			"userCodeValue.userCodeName":f_catName,
					 	"userCodeValue.userCodeDesc":f_catDesc,
					 	"codeID":f_catTypeID,
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
			 					          $( "#recordAdded" ).dialog( "close" );
			 					         var oTable=$(".UserCategoryTable").dataTable();
			 					         oTable.fnDraw();
			 					         $('#addCategoryForm').trigger("reset");
			 					        }
			 					      }
			 					});
			 				});
		   		
		   		$( "#AddCategory" ).dialog( "close" );
		   		
		        },
		        "Cancel":function(){
		        	$( "#AddCategory" ).dialog( "close" );
		        }
		      }
		});
	}
});


$(document).ready(function(){
	
	var oTable=$(".UserCategoryTable").dataTable({
		"paging":true,
        "searching": true,
        "bProcessing" : true,
        "bServerSide" : true,
        "bJQueryUI" : true,
        "info":true,
        "bAutoWidth": false,
        "aLengthMenu": [[10, 15, 20], [10, 15, 20]],
        "sPaginationType" : "full_numbers",
        "ajax" : "refreshUserCategoryData",
        "sDom": 'T<"clear">lfrtip',
        "sSwfPath" :"jquery/dt_bot_select/Buttons-1.1.0/swf/flashExport.swf",
        "tableTools": {
            "sRowSelect": "single",
            "aButtons":[{"sExtends":"new_rec","sButtonText": "New Category"},
                        {"sExtends":"edit_rec","sButtonText": "Edit Category"},
                        {"sExtends":"remove_rec","sButtonText": "Remove"}]
        },
        "aoColumns": [
             {"mData":"userCodeName","bSearchable": true,"bSortable": true},
             {"mData":"userCodeDesc","bSearchable": false,"bSortable": false},
             {"mData":"codeValues.codeDesc","bSearchable": true,"bSortable": true},
             {"mData":"userCodeID","bVisible":false}]
	});
	
	$(".UserCategoryTable tbody").on('click','tr',function(){
		  //var myTable=$('.UserCategoryTable').DataTable();
		   removerRowID=$(".UserCategoryTable tr.selected").index();
		   
	   });
	
	$("#dialog-confirm").hide();
	$("#recordAdded").hide();
	$("#editCategoryDialoug").hide();
	$("#AddCategory").hide();
	
});



</script>
</body>
</html>