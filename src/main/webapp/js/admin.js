$('#toggle-left-menu').click(function() {
	$('#left-menu, #logo, #page-container, #header .header-left').toggleClass('small-left-menu');
	$('#logo .big-logo, #logo .small-logo').toggle('300');
	$('#logo').toggleClass('p-0 pl-1');
});

$('#left-menu li > a').click(function(e) {
	e.preventDefault();

	$('#left-menu li').removeClass('active');

	$(this).parent().addClass('active');

	if ($(this).next('ul').length) {
		$(this).next('ul').toggleClass('open');
		$(this).parent().toggleClass('rotate');

		$('#left-menu ul').not($(this).next('ul')).removeClass('open');
		$('#left-menu li').not($(this).parent()).removeClass('rotate');
	} else {
		$('#left-menu ul').removeClass('open');
		$('#left-menu li').removeClass('rotate');
	}
});

$(window).resize(function() {
	windowResize();
});

$(window).on('load', function() {
	windowResize();
});

function windowResize() {
	var width = $(window).width();
	if (width <= 992) {
		$('#left-menu, #logo').addClass('small-left-menu p-0 pl-1');
		$('#page-container, #header .header-left').addClass('small-left-menu');
	} else {
		$('#left-menu, #logo').removeClass('small-left-menu p-0 pl-1');
		$('#page-container, #header .header-left').removeClass('small-left-menu');
	}
}

function logout() {
	sessionStorage.removeItem('userId');

	window.location.href = 'login.html';
}

$(document).ready(function() {

	var table = $('#pending_applicants_table').DataTable({
		"ajax": {
			"url": "/api/v1/applicant?status=PENDING",
			"type": "GET",
			"dataSrc": function(json) {
				json.forEach(function(item, index) {
					$.ajax({
						url: '/api/v1/user/' + item.userID,
						type: 'GET',
						async: false,
						success: function(response) {
							item.username = response.username;
						},
						error: function() {
							item.username = 'N/A';
						}
					});
				});

				return json;
			}
		},
		"columns": [
			{
				"data": null,
				"title": "Sr.No.",
				"render": function(data, type, row, meta) {
					return meta.row + 1;
				}
			},
			{
				"data": "username",
				"title": "Username"
			},
			{
				"data": "vehicleMake",
				"title": "Car Make"
			},
			{
				"data": "loanAmount",
				"title": "Loan Amount"
			},
			{
				"data": "loanTenure",
				"title": "Loan Tenure"
			},
			{
				"data": "applicationDate",
				"title": "Applied On"
			}
		]
	});



	$('#pending_applicants_table tbody').on('click', 'tr', function() {
		var data = table.row(this).data();
		var applicationID = data.applicationID;

		$.ajax({
			url: '/api/v1/applicant/' + applicationID,
			type: 'GET',
			success: function(response) {
				$.ajax({
					url: '/api/v1/user/' + response.userID,
					type: 'GET',
					success: function(userResponse) {
						$('#modalApplicationID').text(response.applicationID);
						$('#modalUserID').text(response.userID);
						$('#modalVehicleMake').text(response.vehicleMake);
						$('#modalVehicleType').text(response.vehicleType);
						$('#modalExShowroomPrice').text(response.exShowroomPrice);
						$('#modalOnRoadPrice').text(response.onRoadPrice);
						$('#modalTypeOfEmployment').text(response.typeOfEmployment);
						$('#modalYearlySalary').text(response.yearlySalary);
						$('#modalExistingEMI').text(response.existingEMI);
						$('#modalMobileNumber').text(response.mobileNumber);
						$('#modalEmailID').text(response.emailID);
						$('#modalAddress').text(response.houseNumber + ', ' + response.streetName + ', ' + response.city + ', ' + response.state + ' - ' + response.pinCode);
						$('#modalLoanAmount').text(response.loanAmount);
						$('#modalLoanTenure').text(response.loanTenure);
						$('#modalRateOfInterest').text(response.rateOfInterest);
						$('#modalApplicationDate').text(response.applicationDate);
						$('#modalPanNumber').text(response.panNumber || 'N/A');
						$('#modalAadharNumber').text(response.aadharNumber || 'N/A');

						$('#modalUsername').text(userResponse.username);
						$('#modalFirstName').text(userResponse.firstName);
						$('#modalLastName').text(userResponse.lastName);

						$('#detailsModal').modal('show');
					},
					error: function(jqXHR, textStatus, errorThrown) {
						console.error('Failed to fetch user details:', textStatus, errorThrown);
					}
				});
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.error('Failed to fetch application details:', textStatus, errorThrown);
			}
		});
	});

	$('#approveButton').on('click', function() {
		var applicationID = $('#modalApplicationID').text();

		var requestBody = {
			applicationID: applicationID,
			loanStatus: "APPROVED"
		};

		$.ajax({
			url: '/api/v1/applicant',
			type: 'PUT',
			contentType: 'application/json',
			data: JSON.stringify(requestBody),
			success: function(response) {
				alert('Application Approved Successfully!');
				$('#detailsModal').modal('hide');
				$('#pending_applicants_table').DataTable().ajax.reload();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.error('Failed to approve application:', textStatus, errorThrown);
				alert('Failed to approve application.');
			}
		});
	});

	$('#rejectButton').on('click', function() {
		var applicationID = $('#modalApplicationID').text();

		var requestBody = {
			applicationID: applicationID,
			loanStatus: "REJECTED"
		};

		$.ajax({
			url: '/api/v1/applicant',
			type: 'PUT',
			contentType: 'application/json',
			data: JSON.stringify(requestBody),
			success: function(response) {
				alert('Application Rejected Successfully!');
				$('#detailsModal').modal('hide');
				$('#pending_applicants_table').DataTable().ajax.reload();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.error('Failed to approve application:', textStatus, errorThrown);
				alert('Failed to approve application.');
			}
		});
	});

	$('#all_users_table').DataTable({
		"ajax": {
			"url": "/api/v1/user",
			"type": "GET",
			"dataSrc": function(json) {
				return json;
			}
		},
		"columns": [
			{
				"data": null,
				"title": "Sr.No.",
				"render": function(data, type, row, meta) {
					return meta.row + 1;
				}
			},
			{ "data": "firstName", "title": "First Name" },
			{ "data": "lastName", "title": "Last Name" },
			{ "data": "dateOfBirth", "title": "Date of Birth" },
			{ "data": "username", "title": "Username" },
			{ "data": "email", "title": "Email" },
			{ "data": "mobile", "title": "Mobile" },
			{ "data": "typeOfEmployment", "title": "Type of Employment" },
			{ "data": "salary", "title": "Salary" }
		]
	});

	$('#a_applications_table').DataTable({
		"ajax": {
			"url": '/api/v1/applicant?status=APPROVED',
			"type": "GET",
			"dataSrc": function(json) {
				json.forEach(function(item, index) {
					$.ajax({
						url: '/api/v1/user/' + item.userID,
						type: 'GET',
						async: false,
						success: function(response) {
							item.username = response.username;
						},
						error: function() {
							item.username = 'N/A';
						}
					});
				});

				return json;
			}
		},
		"columns": [
			{
				"data": null,
				"title": "Sr.No.",
				"render": function(data, type, row, meta) {
					return meta.row + 1;
				}
			},
			{ "data": "username", "title": "Username" },
			{ "data": "vehicleMake", "title": "Vehicle Make" },
			{ "data": "loanAmount", "title": "Loan Amount" },
			{ "data": "loanTenure", "title": "Loan Tenure" },
			{ "data": "applicationDate", "title": "Applied On" }
		]
	});

	$('#r_applications_table').DataTable({
		"ajax": {
			"url": '/api/v1/applicant?status=REJECTED',
			"type": "GET",
			"dataSrc": function(json) {
				json.forEach(function(item, index) {
					$.ajax({
						url: '/api/v1/user/' + item.userID,
						type: 'GET',
						async: false,
						success: function(response) {
							item.username = response.username;
						},
						error: function() {
							item.username = 'N/A';
						}
					});
				});

				return json;
			}
		},
		"columns": [
			{
				"data": null,
				"title": "Sr.No.",
				"render": function(data, type, row, meta) {
					return meta.row + 1;
				}
			},
			{
				"data": "username",
				"title": "Username"
			},
			{ "data": "vehicleMake", "title": "Vehicle Make" },
			{ "data": "loanAmount", "title": "Loan Amount" },
			{ "data": "loanTenure", "title": "Loan Tenure" },
			{ "data": "applicationDate", "title": "Applied On" }
		]
	});

	$('#edit_changes').on('click', function(e) {
		e.preventDefault();

		var user = {
			userId: 1,
			firstName: $('#firstName').val(),
			lastName: $('#lastName').val(),
			dateOfBirth: $('#dateOfBirth').val(),
			gender: $('#gender').val(),
			email: $('#email').val(),
			mobile: $('#mobile').val(),
			typeOfEmployment: $('#employmentType').val(),
			salary: $('#salary').val(),
			panCardNumber: $('#panCardNumber').val() || null,
			aadharNumber: $('#aadharNumber').val() || null,
		};

		$.ajax({
			url: '/api/v1/user',
			type: 'PUT',
			contentType: 'application/json',
			data: JSON.stringify(user),
			success: function(response) {
				console.log(response);
				$('#registerResponse').text('Registration successful! Response ID: ' + response.id);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.error('AJAX request failed:', textStatus, errorThrown);
				$('#registerResponse').text('Error processing request.');
			}
		});
	});
});
