$(document).ready(function() {
    // Register Form Submission
    $('#registerForm').on('submit', function(e) {
        e.preventDefault();

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

        // Make the AJAX POST request
        $.ajax({
            url: '/api/v1/user',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(user),
            success: function(response) {
                if (response.success) {
                    $('#registerResponse').text('Registration successful!');
                } else {
                    $('#registerResponse').text('Registration failed: ' + response.message);
                }
            },
            error: function() {
                $('#registerResponse').text('Error processing request.');
            }
        });
    });

    // Add similar code for login form submission if needed
});
