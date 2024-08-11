$('#toggle-left-menu').click(function() {
	$('#left-menu, #logo, #page-container, #header .header-left').toggleClass('small-left-menu');
	$('#logo .big-logo, #logo .small-logo').toggle('300');
	$('#logo').toggleClass('p-0 pl-1');
});

$('#left-menu li > a').click(function(e) {
	e.preventDefault();

	// Remove 'active' class from all menu items
	$('#left-menu li').removeClass('active');

	// Add 'active' class to the clicked menu item
	$(this).parent().addClass('active');

	// Toggle the submenu if exists
	if ($(this).next('ul').length) {
		$(this).next('ul').toggleClass('open');
		$(this).parent().toggleClass('rotate');

		// Close other open submenus
		$('#left-menu ul').not($(this).next('ul')).removeClass('open');
		$('#left-menu li').not($(this).parent()).removeClass('rotate');
	} else {
		// Hide all other submenus if no submenu exists for this item
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
    // Clear userId from sessionStorage
    sessionStorage.removeItem('userId');

    // Optionally, redirect the user to a login page or homepage
    window.location.href = 'login.html'; // Change 'login.html' to your desired URL
}

$(document).ready(function() {
	// Show the default page (e.g., dashboard) 

	var table = $('#pending_applicants_table').DataTable({
		"ajax": {
			"url": "/api/v1/applicant?status=PENDING", // Replace with your API endpoint
			"type": "GET",
			"dataSrc": function(json) {
				json.forEach(function(item, index) {
					$.ajax({
						url: '/api/v1/user/' + item.userID, // API endpoint to get the username
						type: 'GET',
						async: false, // Make it synchronous
						success: function(response) {
							item.username = response.username; // Assuming the response contains a `username` field
						},
						error: function() {
							item.username = 'N/A'; // Fallback in case of error
						}
					});
				});

				// Now the json array has the updated usernames
				return json;
			}
		},
		"columns": [
			{
				"data": null, // Data is not coming from the API
				"title": "Sr.No.",
				"render": function(data, type, row, meta) {
					return meta.row + 1; // Serial number starts from 1
				}
			},
			{
				"data": "username", // This will now contain the fetched username
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

		// Make an AJAX call to fetch additional details based on applicationID
		$.ajax({
			url: '/api/v1/applicant/' + applicationID, // Replace with your API endpoint
			type: 'GET',
			success: function(response) {
				// Make another AJAX call to get user details using userID
				$.ajax({
					url: '/api/v1/user/' + response.userID, // Replace with your user API endpoint
					type: 'GET',
					success: function(userResponse) {
						// Populate the modal with the response data
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

						// Populate user details
						$('#modalUsername').text(userResponse.username);
						$('#modalFirstName').text(userResponse.firstName);
						$('#modalLastName').text(userResponse.lastName);

						// Show the modal
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

	// Handle the Approve button click
	$('#approveButton').on('click', function() {
		var applicationID = $('#modalApplicationID').text();

		// Construct the request body
		var requestBody = {
			applicationID: applicationID,
			loanStatus: "APPROVED"
		};

		// Make an AJAX PUT request to approve the application
		$.ajax({
			url: '/api/v1/applicant', // Replace with your API endpoint
			type: 'PUT',
			contentType: 'application/json',
			data: JSON.stringify(requestBody),
			success: function(response) {
				alert('Application Approved Successfully!');
				$('#detailsModal').modal('hide');
				$('#pending_applicants_table').DataTable().ajax.reload(); // Reload the DataTable to reflect changes
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.error('Failed to approve application:', textStatus, errorThrown);
				alert('Failed to approve application.');
			}
		});
	});

	// Handle the Approve button click
	$('#rejectButton').on('click', function() {
		var applicationID = $('#modalApplicationID').text();

		// Construct the request body
		var requestBody = {
			applicationID: applicationID,
			loanStatus: "REJECTED"
		};

		// Make an AJAX PUT request to approve the application
		$.ajax({
			url: '/api/v1/applicant', // Replace with your API endpoint
			type: 'PUT',
			contentType: 'application/json',
			data: JSON.stringify(requestBody),
			success: function(response) {
				alert('Application Rejected Successfully!');
				$('#detailsModal').modal('hide');
				$('#pending_applicants_table').DataTable().ajax.reload(); // Reload the DataTable to reflect changes
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.error('Failed to approve application:', textStatus, errorThrown);
				alert('Failed to approve application.');
			}
		});
	});

	$('#all_users_table').DataTable({
		"ajax": {
			"url": "/api/v1/user", // Replace with your API endpoint
			"type": "GET",
			"dataSrc": function(json) {
				// Return the single object as an array to fit the DataTable format
				return json;
			}
		},
		"columns": [
			{
				"data": null, // Data is not coming from the API
				"title": "Sr.No.",
				"render": function(data, type, row, meta) {
					return meta.row + 1; // Serial number starts from 1
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
						url: '/api/v1/user/' + item.userID, // API endpoint to get the username
						type: 'GET',
						async: false, // Make it synchronous
						success: function(response) {
							item.username = response.username; // Assuming the response contains a `username` field
						},
						error: function() {
							item.username = 'N/A'; // Fallback in case of error
						}
					});
				});

				// Now the json array has the updated usernames
				return json;
			}
		},
		"columns": [
			{
				"data": null, // Data is not coming from the API
				"title": "Sr.No.",
				"render": function(data, type, row, meta) {
					return meta.row + 1; // Serial number starts from 1
				}
			},
			{
				"data": "username", "title": "Username"
			},
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
						url: '/api/v1/user/' + item.userID, // API endpoint to get the username
						type: 'GET',
						async: false, // Make it synchronous
						success: function(response) {
							item.username = response.username; // Assuming the response contains a `username` field
						},
						error: function() {
							item.username = 'N/A'; // Fallback in case of error
						}
					});
				});

				// Now the json array has the updated usernames
				return json;
			}
		},
		"columns": [
			{
				"data": null, // Data is not coming from the API
				"title": "Sr.No.",
				"render": function(data, type, row, meta) {
					return meta.row + 1; // Serial number starts from 1
				}
			},
			{
				"data": "username", // This will now contain the fetched username
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

		// Construct the user object with the new fields
		var user = {
			userId: 1,
			firstName: $('#firstName').val(),
			lastName: $('#lastName').val(),
			dateOfBirth: $('#dateOfBirth').val(),
			gender: $('#gender').val(),
			email: $('#email').val(),
			mobile: $('#mobile').val(), // Added mobile field
			typeOfEmployment: $('#employmentType').val(),
			salary: $('#salary').val(),
			panCardNumber: $('#panCardNumber').val() || null,
			aadharNumber: $('#aadharNumber').val() || null,
		};
		// Make the AJAX POST request
		$.ajax({
			url: '/api/v1/user',
			type: 'PUT',
			contentType: 'application/json',
			data: JSON.stringify(user),
			success: function(response) {
				console.log(response); // For debugging
				$('#registerResponse').text('Registration successful! Response ID: ' + response.id);
				/*window.location.href = 'userPanel.html';*/
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.error('AJAX request failed:', textStatus, errorThrown);
				$('#registerResponse').text('Error processing request.');
			}
		});
	});
});
