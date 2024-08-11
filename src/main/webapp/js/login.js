$(document).ready(function() {
    // Login Form Submission
    $('#loginForm').on('submit', function(e) {
        e.preventDefault();

        // Construct the login object with username and password
        var loginData = {
            username: $('#email').val(),
            password: $('#password').val()
        };

        // Make AJAX POST Request for Login
        $.ajax({
            url: '/api/v1/user/login', // Login API URL
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(loginData),
            success: function(response) {
                console.log(response); // For debugging
                $('#loginResponse').text('Login successful!');
				// Store userId in sessionStorage
                sessionStorage.setItem('userId', response.userId);
				if(response.isAdmin == true){
					sessionStorage.setItem('isAdmin', true);
					alert('Login successful! Redirecting to Admin dashboard.');
					window.location.href='adminPanel.html';
				}
				else{
					alert('Login successful! Redirecting to user dashboard.');
					// Redirect to a dashboard or another page after successful login
					window.location.href = 'userPanel.html'; // Change 'dashboard.html' to your desired URL
					 	
				}           	
               },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('AJAX request failed:', textStatus, errorThrown);
                $('#loginResponse').text('Login failed. Please check your username and password.');
            }
        });
    });
});
