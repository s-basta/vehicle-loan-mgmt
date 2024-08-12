// loanApplication.js
$(document).ready(function() {
	// Retrieve userId from sessionStorage
	var userId = sessionStorage.getItem('userId');
	if (!userId) {
		// Handle case where userId is not found in sessionStorage
		alert('User ID not found. Please log in again.');
		window.location.href = 'login.html'; // Redirect to login page
	}
	// Function to populate the form fields with sessionStorage data
	function populateForm() {
		$('#employmentType').val(sessionStorage.getItem('employmentType'));
		$('#salary').val(sessionStorage.getItem('salary'));
		$('#existingEMIs').val(sessionStorage.getItem('existingEMIs'));
		$('#vehicleMake').val(sessionStorage.getItem('vehicleMake'));
		$('#vehicleModel').val(sessionStorage.getItem('vehicleModel'));
		$('#showroomPrice').val(sessionStorage.getItem('showroomPrice'));
		$('#onRoadPrice').val(parseInt(sessionStorage.getItem('onRoadPrice')));
		$('#loanTenureMonths').val(sessionStorage.getItem('loanTenureMonths'));
		$('#calculatedEMI').val(sessionStorage.getItem('calculatedEMI'));
	}

	// Fetch user profile data
	$.ajax({
		url: '/api/v1/user/' + userId,
		type: 'GET',
		success: function(response) {
			// Populate form fields with the user data
			$('#firstName').val(response.firstName);
			$('#lastName').val(response.lastName);
			$('#dateOfBirth').val(response.dateOfBirth);
			$('#gender').val(response.gender);
			$('#username').val(response.username);
			$('#email').val(response.email);
			$('#mobile').val(response.mobile);
			$('#panCardNumber').val(response.panCardNumber || 'N/A'); // Fallback if null
			$('#aadharNumber').val(response.aadharNumber || 'N/A'); // Fallback if null

		},
		error: function(error) {
			console.error('Error fetching user profile:', error);
		}
	});

	// Populate form with session storage data
	populateForm();

	// Navigation between tabs
	$('#toEligibility').click(function() {
		$('#formTabs a[href="#eligibility"]').tab('show');
	});

	$('#toAdditional').click(function() {
		$('#formTabs a[href="#additional"]').tab('show');
	});

	$('#toProfile').click(function() {
		$('#formTabs a[href="#profile"]').tab('show');
	});

	$('#toEligibilityBack').click(function() {
		$('#formTabs a[href="#eligibility"]').tab('show');
	});

	// Handle form submission
	$('#loanApplicationForm').submit(function(event) {
		event.preventDefault();

		// Collect data from form fields
		var formData = {
			userID: userId, // Example userID; adjust as needed
			vehicleMake: $('#vehicleMake').val(),
			vehicleType: $('#vehicleModel').val(), // Add this field if it's part of your form
			exShowroomPrice: $('#showroomPrice').val(),
			onRoadPrice: $('#onRoadPrice').val(),
			typeOfEmployment: $('#employmentType').val(),
			yearlySalary: $('#salary').val(),
			existingEMI: $('#existingEMIs').val(),
			mobileNumber: $('#mobile').val(),
			emailID: $('#email').val(),
			houseNumber: $('#houseNumber').val(),
			streetName: $('#streetName').val(),
			city: $('#city').val(),
			state: $('#state').val(),
			pinCode: $('#pinCode').val(),
			loanAmount: $('#onRoadPrice').val(), // Make sure to add this field in your form
			loanTenure: $('#loanTenureMonths').val(),
			rateOfInterest: 7.5, // Hardcoded or dynamically set as needed
			loanStatus: 'PENDING',
			applicationDate: new Date().toISOString(),
			panNumber: $('#panNumber').val(),
			aadharNumber: $('#aadharNumber').val()
		};

		$.ajax({
			url: 'http://localhost:8080/api/v1/applicant', // Update with actual endpoint
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(formData),
			success: function(response) {
				alert('Application submitted for approval successfully! You can check the status of your application under `All Applications` in your Dashboard');
				// Clear session storage and redirect
				sessionStorage.clear();
				// Optional: Store the userId or any other necessary data
				sessionStorage.setItem("userId", userId);
				// Redirect to the user panel
				window.location.href = 'userPanel.html';
			},
			error: function(error) {
				console.error('Error submitting application:', error);
			}
		});
	});
});
