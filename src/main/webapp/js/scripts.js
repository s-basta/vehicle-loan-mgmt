$(document).ready(function() {
	// Register Form Submission
	$('#registerForm').on('submit', function(e) {
		e.preventDefault();
		console.log("hi");
		// Construct the user object with the new fields
		var user = {
			firstName: $('#firstName').val(),
			lastName: $('#lastName').val(),
			dateOfBirth: $('#dob').val(),
			gender: $('#gender').val(),
			username: $('#username').val(),
			email: $('#emailRegister').val(),
			mobile: $('#mobile').val(), // Added mobile field
			isAdmin: false, // Default value for isAdmin
			password: $('#passwordRegister').val()
		};
		console.log("hi-bye");
		// Make the AJAX POST request
		$.ajax({
			url: '/api/v1/user',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(user),
			success: function(response) {
				console.log(response); // For debugging
				$('#registerResponse').text('Registration successful! Response ID: ' + response.id);
				window.location.href = 'userPanel.html';
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.error('AJAX request failed:', textStatus, errorThrown);
				$('#registerResponse').text('Error processing request.');
			}
		});
	});

	// Add similar code for login form submission if needed
});
