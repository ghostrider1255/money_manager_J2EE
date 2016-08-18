<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="tags" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="jquery/jquery-ui.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register new User</title>
<style>
	body{
		font: 100% "Trebuchet MS", sans-serif;
		margin: 50px;
	}
	.regstrationMain
	{
		position: relative;
		top: -20px;
		width:650px;
		
	}
	div.regDiv
	{
		word-spacing:8px;
		font-family: 'Times New Roman', Times, serif;
		font-size: 23px;
		line-height: 0.4;
	}
	div.regDiv span
	{
		color:#C0C0C0;
		word-spacing:0px;
		font-size: 17px;
		line-height: 0.4;
	}
	div.regForm
	{
		display:inline-block;
		margin: 0px auto 10px auto;
    	text-align: left;
	}
	#wait
	{
		display:none;
		width:60px;
		height:80px;
		border:1px solid black;
		position:absolute;
		top:50%;
		left:50%;
		padding:2px;
	}
	</style> 

	
</head>
<body>


<div class="regstrationMain">
	<tags:a href="Home.jsp">Home</tags:a><br>
	<div class="regDiv">
		<h2>Create an Account</h2>
		<span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Complete the form below to sign-up</span><br><br><hr>
	</div>
	<div class="regForm">
		<tags:form action="registerUser" theme="simple" id="regitForm">
			<table>
				<tr>
					<td colspan="3" id="errorMessageTD"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><tags:label>Login Name:*</tags:label></td>
					<td>&nbsp;</td>
					<td id="logNameBox"><tags:textfield key="user.userCode" label="Login Name" size="40" id="loginName"/></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><tags:label>Password:*</tags:label></td>
					<td>&nbsp;</td>
					<td id="pwdTD"><tags:password key="user.password" label="Password" size="40" id="mngpwd"/><br></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><tags:label>Confirm Password:*</tags:label></td>
					<td>&nbsp;</td>
					<td id="pwdConfirmMessage"><tags:password id="confirmPWD" label="Password" size="40"/><br></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><tags:label>First Name:*</tags:label></td>
					<td>&nbsp;</td>
					<td><tags:textfield key="user.firstName" label="First Name" size="40" id="fName"/><br></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><tags:label>Last Name:*</tags:label></td>
					<td>&nbsp;</td>
					<td><tags:textfield key="user.lastName" label="Last Name" size="40" id="lName"/><br></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>E-mail:*</td>
					<td>&nbsp;</td>
					<td><tags:textfield key="user.emailID" label="E-mail ID" size="40" id="email"/><br></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Date of Birth:*</td>
					<td>&nbsp;</td>
					<td><tags:textfield key="user.DOB" id="datepicker" label="DOB" class="datepicker"/><br></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><tags:submit value="Register" id="register"/></td>
				</tr>
			</table>
			<div id="wait">
				<img src='images/FhHRx.gif' width="64" height="64" />
				<br>Loading..
			</div>
			<script src="jquery/external/jquery/jquery.js"></script>
			<script src="jquery/jquery-ui.js"></script>
			<script>
			$(document).ready(function(){
				
				$(document).ajaxStart(function(){
				    $("#wait").css("display", "block");
				});
				
				$(document).ajaxComplete(function(){
				    $("#wait").css("display", "none");
				});
				
				$( "#datepicker" ).datepicker();
				
					$("#register").click(function(event){
					
					var ErrorMessage="";
					var valiedationcount=0;
					var loginName=$("#loginName").val();
					var pwd=$("#mngpwd").val();
					var cnfPWD=$("#confirmPWD").val();
					var fName=$("#fName").val();
					var lName=$("#lName").val();
					var email=$("#email").val();
					var dob=$("#datepicker").val();
				
					if( (loginName==null || loginName.length==0) || 
						(pwd==null || pwd.length==0) || 
						(cnfPWD==null || cnfPWD.length==0)||
						(fName==null || fName.length==0) ||
						(lName==null || lName.length==0) ||
						(email==null || email.length==0) ||
						(dob==null || dob.length==0))
					{
						ErrorMessage=("<br>*All fields should be filled");
						valiedationcount++;
					}
					if(loginName.length<8)
					{
						ErrorMessage=ErrorMessage+("<br>* Login Name minimum length is 8");
						valiedationcount++;
					}
					if(pwd.length==0 )
					{
						ErrorMessage=ErrorMessage+("<br>* Password and Confirm Password are not equal");
						valiedationcount++;
					}	
					if(fName.length<4)
					{
						ErrorMessage=ErrorMessage+("<br>* First Name minimum length is 4");
						valiedationcount++;
					}
					if(lName.length<4)
					{
						ErrorMessage=ErrorMessage+("<br>* Last Name minimum length is 4");
						valiedationcount++;
					}
					if(email.length<4)
					{
						ErrorMessage=ErrorMessage+("<br>* email can not be blank");
						valiedationcount++;
					}
					if(dob.length!=10)
					{
						ErrorMessage=ErrorMessage+("<br>* Invalied DOB");
						valiedationcount++;
					}
					if(ErrorMessage.length>0)
					{
						$("#errorMessage").remove();
						$("#errorMessageTD").append("<span id='errorMessage'>"+ErrorMessage+"</span>");
					}
					
					if(valiedationcount==0)
					{
						$("#regitForm").submit();
					}
					else
					{
						event.preventDefault();
					}
				});
				
				
				$("#confirmPWD").focusout(function(){
					var pwd=$("#mngpwd").val();
					var confirmPWD=$("#confirmPWD").val();
					
					if(pwd.length>=8 && pwd===confirmPWD)
					{
						$(".cnfMessage").remove();
						$("#pwdConfirmMessage").append("<span class='cnfMessage' style='color:green'>Password Confirmed</span>");
					}
					else if(pwd.length>0)
					{
						$(".cnfMessage").remove();
						$("#pwdConfirmMessage").append("<span class='cnfMessage' style='color:red'>password confirmation wrong</span>");
					}
					
				});
				
				$("#mngpwd").focusout(function(){
					
					var pwd=$("#mngpwd").val();
					if(pwd.length>0 && pwd.length<8)
					{
						$(".pwdMessage").remove();
						$("#pwdTD").append("<span class='pwdMessage' style='color:red'> Password length should be >=8</span>");
						$("#mngpwd").focus();
					}
				});
				$("#loginName").focusout(function(){
					var loginNameTemp=$("#loginName").val();
					
					if(loginNameTemp.length>=8)
					{
						
						$.getJSON("isUserNameAvailable",
						{
							"user.userCode":loginNameTemp
						},
						function(response)
						{
							if(response.isUserNameAlreadyAvailable=="yes")
							{
								$(".available").remove();
								$("#logNameBox").append("<span class='available' style='color:red'>&nbsp; &#10006; User name exists</span>");
								$("#loginName").focus();
							}
							else
							{
								$(".available").remove();
								$("#logNameBox").append("<span class='available' style='color:green'>&nbsp; &#10004; Available</span>");
							}
						 });
					}
					else
					{
						if(loginNameTemp.length>0)
						{
							$(".available").remove();
							$("#logNameBox").append("<span class='available' style='color:red'>&nbsp; &#9888; min length is 8 </span>");
							
							$("#loginName").focus();
						}
					}
				});
			});
		  		
		 	</script>
		</tags:form>
	</div>
	
</div>




</body>
</html>