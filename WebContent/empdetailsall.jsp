<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get all employee details</title>
<script src="jquery.min.js"></script>
</head>



<script type="text/javascript">
	$.ajax({
		type : 'GET',
		url : 'http://localhost:8002/RESTDBLab1/rest/emp/employee/',

		dataType : 'json',

		success : function(data) {

			drawTable(data);
		}
	});
	function drawTable(data) {
		for (var i = 0; i < data.length; i++) {

			var row = $("<tr />")
			console.log(data[i]);
			$("#personDataTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
			row.append($("<td>" + data[i].id + "</td>"));
			row.append($("<td>" + data[i].name + "</td>"));
			row.append($("<td>" + data[i].designation + "</td>"));
			row.append($("<td>" + data[i].salary + "</td>"));
			row.append($("<td>" + data[i].address.doorno + "</td>"));
			row.append($("<td>" + data[i].address.street + "</td>"));
			row.append($("<td>" + data[i].address.location + "</td>"));
			row.append($("<td>" + data[i].address.city + "</td>"));
		}
	}
</script>

<body>

	<table id="personDataTable" border="10px">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Designation</th>
			<th>Salary</th>
			<th>doorno</th>
			<th>street</th>
			<th>location</th>
			<th>city</th>
		</tr>
	</table>


</body>
</html>