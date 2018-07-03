
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a employee detail</title>
<script src="jquery.min.js"></script>
</head>



<script type="text/javascript">
	$(document).ready(function() {
		$("#btnSubmit").click(addEmpRecord);

	});
	function addEmpRecord() {

		$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : 'http://localhost:8002/RESTDBLab1/rest/emp/employee/',
			dataType : "json",
			data : formToJSON(),
			success : function(data) {
				alert('Employee Record inserted successfully');
				console.log(data);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('Employee Record error');
			}
		});
	}

	function formToJSON() {

		return JSON.stringify({

			"name" : $('#empName').val(),
			"designation" : $('#empDesignation').val(),
			"salary" : $('#empSalary').val(),
			"address" : {
				"doorno" : $('#empDoorno').val(),
				"street" : $('#empStreet').val(),
				"location" : $('#empLocation').val(),
				"city" : $('#empCity').val()
			}
		});
		

	}
</script>

<body>


	Name:
	<input id="empName" name="id" type="text" />
	<br /> Designation:
	<input id="empDesignation" name="design" type="text" />
	<br /> Salary:
	<input id="empSalary" name="sal" type="text" />
	<br /> Door Number:
	<input id="empDoorno" name="doorNo" type="text" />
	<br /> Street:
	<input id="empStreet" name="street" type="text" />
	<br /> Location:
	<input id="empLocation" name="loc" type="text" />
	<br /> City:
	<input id="empCity" name="city" type="text" />
	<br />


	<button id="btnSubmit">Submit</button>

</body>
</html>