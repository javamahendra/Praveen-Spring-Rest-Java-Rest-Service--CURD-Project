<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get Details by Employee Id</title>
<script src="jquery.min.js"></script>
</head>



<script type="text/javascript">
	$(document).ready(function() {
		$("#btnSubmit").click(viewEmpList);
	});
	function viewEmpList() {

		$.ajax({
			type : 'GET',

			url : 'http://localhost:8002/RESTDBLab1/rest/emp/employee/'
					+ $('#viewId').val(),

			dataType : 'json',

			success : function(data) {
				drawTable(data);

			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('Employee Record error');
			}
		});
	}
	function drawTable(data) {
		for (var i = 0; i < data.length; i++) {

			var row = $("<tr />")
			console.log('alert inside drawtable');
			console.log(data[i]);
			$("#empDataTable").append(row);
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
	$(document).ready(function() {
		$('#btnRefresh').click(function() {
			location.reload();
		});
	});
</script>

<body>


	<input id="viewId" name="id" type="text" />


	<button id="btnSubmit">Submit</button>


	<br />
	<br />


	<table id="empDataTable" border="10px">
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
	<br />
	<button id="btnRefresh">Refresh</button>

</body>
</html>