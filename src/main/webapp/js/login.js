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
                alert('Login successful! Redirecting to your dashboard.');

               /* // Redirect to a dashboard or another page after successful login
                window.location.href = 'dashboard.html'; // Change 'dashboard.html' to your desired URL
            */},
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('AJAX request failed:', textStatus, errorThrown);
                $('#loginResponse').text('Login failed. Please check your username and password.');
            }
        });
    });
});
