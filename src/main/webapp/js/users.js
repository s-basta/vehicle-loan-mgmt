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

$(document).ready(function() {
	// Retrieve userId from sessionStorage
	    var userId = sessionStorage.getItem('userId');

	    // Check if userId is available
	    if (!userId) {
	        console.error('User is not logged in. Redirecting to login page.');
	        window.location.href = 'login.html'; // Redirect to login if no userId
	        return;
	    }
	// Show the default page (e.g., dashboard) 
	$('#installments_table').DataTable({
		"ajax": {
			"url": "/api/v1/emi-status/user/"+ userId, // Use dynamic userId
			"type": "GET",
			"dataSrc": function(json) {
				json.forEach(function(item, index) {
					// Fetch vehicleMake based on applicationId
					$.ajax({
						url: '/api/v1/applicant/' + item.applicationId, // API endpoint to get the vehicleMake
						type: 'GET',
						async: false, // Make it synchronous
						success: function(response) {
							item.vehicleMake = response.vehicleMake; // Assuming the response contains a `vehicleMake` field
						},
						error: function() {
							item.vehicleMake = 'N/A'; // Fallback in case of error
						}
					});

					// Fetch EMI amount based on applicationId
					$.ajax({
						url: '/api/v1/accepted-loan/' + item.applicationId, // Replace with your API endpoint to get the EMI amount
						type: 'GET',
						async: false, // Make it synchronous
						success: function(response) {
							item.emiAmount = response.emiAmount; // Assuming the response contains an `emiAmount` field
						},
						error: function() {
							item.emiAmount = 'N/A'; // Fallback in case of error
						}
					});
				});

				// Now the json array has the updated vehicleMake and emiAmount
				return json;
			}
		},
		"columns": [{
			"data": null, // Data is not coming from the API
			"title": "Sr.No.",
			"render": function(data, type, row, meta) {
				return meta.row + 1; // Serial number starts from 1
			}
		}, {
			"data": "applicationId",
			"title": "Loan ID"
		}, {
			"data": "vehicleMake",
			"title": "Car Make"
		}, {
			"data": "emiAmount",
			"title": "EMI Amount"
		}, {
			"data": "scheduledPaymentDate",
			"title": "Payment Date"
		}]
	});


	$('#applications_table').DataTable({
		"ajax": {
			"url": "/api/v1/applicant", // Replace with your API endpoint
			"type": "GET",
			"dataSrc": function(json) {
				// Return the array of data directly
				return json;
			}
		},
		"columns": [{
			"data": "applicationID",
			"title": "Application ID"
		}, {
			"data": "loanAmount",
			"title": "Loan Amount"
		}, {
			"data": "yearlySalary",
			"title": "Yearly Salary"
		}, {
			"data": "applicationDate",
			"title": "Application Date"
		}]
	}); // Initialize DataTable

	$.ajax({
		url: "/api/v1/user/"+ userId, // Use dynamic userId,
		type: 'GET',
		success: function(response) {
			// Assuming the response is an object containing user data
			$('#firstName').val(response.firstName);
			$('#lastName').val(response.lastName);
			$('#dateOfBirth').val(response.dateOfBirth);
			$('#gender').val(response.gender);
			$('#username').val(response.username);
			$('#email').val(response.email);
			$('#mobile').val(response.mobile);
			$('#employmentType').val(response.typeOfEmployment);
			$('#salary').val(response.salary);
			$('#panCardNumber').val(response.panCardNumber);
			$('#aadharNumber').val(response.aadharNumber);
		},
		error: function(error) {
			console.error('Error fetching user data:', error);
			// Handle the error case here, e.g., show an alert or a message
		}
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
