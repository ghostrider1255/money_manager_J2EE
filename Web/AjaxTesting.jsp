<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="tags" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AJAX in Struts 2 using JSON and jQuery</title>
<script src="js/DropDown.js" type="text/javascript"></script>
<script>
$(document).ready(function() {
   $('#country').change(function(event) {
      var country = $("select#country").val();
      $.getJSON('ajaxAction', {
        countryName : country
      }, function(jsonResponse) {
    	  
        $('#ajaxResponse').text(jsonResponse.dummyMsg);
        var select = $('#states');
        select.find('option').remove();
        $.each(jsonResponse.stateMap, function(key, value) {
          $('<option>').val(key).text(value).appendTo(select);
        });
      });
      });
});
</script>
</head>
<body>
	<h3>AJAX calls to Struts 2 using JSON and jQuery</h3>
	<tags:select list="{'Select Country','India','US'}" id="country" name="country" label="select country"/>
	<br />
	<br />
	<tags:select list="{'select state'}" id="states" name="states" label="select state"/>
	<br />
	<br />
	<div id="ajaxResponse"></div>
</body>
</html>