<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.Doctor" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor Registration</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/doctors.js"></script>
</head>
<body>
<div>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Doctor Registration Portal</h1>
				<p>Please fill in this form to register doctor. </p>
				<form id="formDoctor" name="formDoctor" style="font-weight: bold">
					Doctor Name:
					<input id="drName" name="drName" type="text" placeholder="Enter the name" class="form-control form-control-sm">
					
					<br>Password:
					<input id="drPwd" name="drPwd" type="password" placeholder="Enter the password" class="form-control form-control-sm">
					
					<br>Gender:
					<input id="drGender" name="drGender" type="text" placeholder="Enter the Gender" class="form-control form-control-sm">
					
					<br>Email:
					<input id="drEmail" name="drEmail" type="text" placeholder="Enter your Email as example@gmail.com" class="form-control form-control-sm">
					
					<br>Address:
					<input id="drAddress" name="drAddress" type="text" placeholder="Enter your Address" class="form-control form-control-sm">
					
					<br>Phone No:
					<input id="drPhone" name="drPhone" type="text" placeholder="Enter the Phone No" class="form-control form-control-sm">
					
					<br>
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
					
					<input type="hidden" id="hidDoctorIDSave" name="hidDoctorIDSave" value="">
					
				</form>
				<div id= "alertSuccess" class="alert alert-success"></div>
				<div id= "alertError" class="alert alert-danger"></div>
				<br>
				<div id="divDoctorGrid">
					<%
						Doctor drObj = new Doctor();
						out.print(drObj.readDr());
					%>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>