<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete a Employee</title>

</head>
<script src="jquery.min.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		$("#btnDelete").click(deleteEmpRecord);
	});
	function deleteEmpRecord() {

		$.ajax({
			type : 'DELETE',
			url : 'http://localhost:8002/RESTDBLab1/rest/emp/employee/'
					+ $('#deleteId').val(),
			success : function(data) {
				alert('Employee Record deleted successfully');
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('Employee Record error');
			}
		});
	}
</script>

<body>


	<input id="deleteId" name="id" type="text" />

	<button id="btnDelete">Delete</button>


</body>
</html>