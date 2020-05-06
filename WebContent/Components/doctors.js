$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateDoctorForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	
	// If valid-------------------------

	// $("#formPayment").submit();

	var type = ($("#hidDoctorIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
	   {
		url : "DoctorsAPI",
		type : type,
		data : $("#formDoctor").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onDoctorSaveComplete(response.responseText, status);
		}
	});

});


function onDoctorSaveComplete(response, status) {
	
	if (status == "success")
		{
			var resultSet = JSON.parse(response);
			
			if (resultSet.status.trim() == "success")
				{
				$("#alertSuccess").text("Successfully saved.");
				$("#alertSuccess").show();
				
				$("#divDoctorGrid").html(resultSet.data);
				}else if (resultSet.status.trim() == "error")
					{
					$("#alertError").text(resultSet.data);
					$("#alertError").show();
					
					}
		}else if (status == "error")
		{
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
		}else
			{
			$("#alertError").text("UnKnown error while saving..");
			$("#alertError").show();
			}
	
		$("#hidDoctorIDSave").val("");
		$("#formDoctor")[0].reset();
	
}


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidDoctorIDSave").val($(this).closest("tr").find('#hidDoctorIDUpdate').val());
	$("#drName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#drPwd").val($(this).closest("tr").find('td:eq(1)').text());
	$("#drGender").val($(this).closest("tr").find('td:eq(2)').text());
	$("#drEmail").val($(this).closest("tr").find('td:eq(3)').text());
	$("#drAddress").val($(this).closest("tr").find('td:eq(4)').text());
	$("#drPhone").val($(this).closest("tr").find('td:eq(5)').text());
});


//REMOVE=========================================================
$(document).on("click", ".btnRemove", function(event)
		{
			$.ajax(
					{
						url : "DoctorsAPI",
						type : "DELETE",
						data : "drID=" + $(this).data("drid"),
						dataType : "text",
						complete : function(response, status)
						{
							onDoctorDeleteComplete(response.responseText, status);
						}
					});
		});


function onDoctorDeleteComplete(response, status) {
	
	if (status == "success")
		{
			var resultSet = JSON.parse(response);
			
			if (resultSet.status.trim() == "success")
				{
				$("#alertSuccess").text("Successfully Delete.");
				$("#alertSuccess").show();
				
				$("#divDoctorGrid").html(resultSet.data);
				}else if (resultSet.status.trim() == "error")
					{
					$("#alertError").text(resultSet.data);
					$("#alertError").show();
					
					}
		}else if (status == "error")
		{
			$("#alertError").text("Error while deleting.");
			$("#alertError").show();
		}else
			{
			$("#alertError").text("UnKnown error while deleting..");
			$("#alertError").show();
			}
	
		$("#hidDoctorIDSave").val("");
		$("#formDoctor")[0].reset();
	
}


//CLIENT-MODEL================================================================
function validateDoctorForm()
{
	// name
	if ($("#drName").val().trim() == "")
	{
		return "Insert name.";
	}
	
	// password
	if ($("#drPwd").val().trim() == "")
	{
		return "Insert password.";
	}
	
	// Gender
	if ($("#drGender").val().trim() == "")
	{
		return "Insert Gender.";
	}
	
	// Email
	if ($("#drEmail").val().trim() == "")
	{
		return "Insert Email address.";
	}
	
	
    
	
	// Address
	if ($("#drAddress").val().trim() == "")
	{
		return "Insert Address.";
	}
	
	
	// Phone
	if ($("#drPhone").val().trim() == "")
	{
		return "Insert Phone No.";
	}

	// is numerical value
	var tmpPhone = $("#drPhone").val().trim();
	if (!$.isNumeric(tmpPhone))
	{
		return "Insert a numerical value for Phone No.";
	}	

	
	
return true;
}


