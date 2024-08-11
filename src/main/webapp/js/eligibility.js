$(document).ready(function() {
    // Retrieve userId from sessionStorage
    var userId = sessionStorage.getItem('userId');

    if (userId) {
        // Fetch user data from API
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
                $('#employmentType').val(response.typeOfEmployment || 'N/A'); // Fallback if null
                $('#salary').val(response.salary || 'N/A'); // Fallback if null
                $('#panCardNumber').val(response.panCardNumber || 'N/A'); // Fallback if null
                $('#aadharNumber').val(response.aadharNumber || 'N/A'); // Fallback if null
            },
            error: function(error) {
                console.error('Error fetching user data:', error);
                // Handle the error case
                alert('Failed to load user data.');
            }
        });
    } else {
        // Handle case where userId is not found in sessionStorage
        alert('User ID not found. Please log in again.');
        window.location.href = 'login.html'; // Redirect to login page
    }
});
