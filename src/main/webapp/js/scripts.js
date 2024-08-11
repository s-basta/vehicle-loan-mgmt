// openModal functionality
function openModal(modalId) {
    document.getElementById(modalId).style.display = 'block';
}

function closeModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}

// Close modal when clicking outside of it
window.onclick = function(event) {
    var modals = document.getElementsByClassName('modal');
    for (var i = 0; i < modals.length; i++) {
        if (event.target == modals[i]) {
            modals[i].style.display = 'none';
        }
    }
};


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
		
		// Make AJAX POST Request
		// now reverse testing againnn
		$.ajax({
		                    url: '/api/v1/user', // Example API URL
		                    type: 'POST',
		                    contentType: 'application/json',
		                    data: JSON.stringify(user),
		                    success: function(response) {
		                        console.log(response); // For debugging
		                        $('#registerResponse').text('Registration successful! Response ID: ' + response.userId);
		                    },
		                    error: function(jqXHR, textStatus, errorThrown) {
		                        console.error('AJAX request failed:', textStatus, errorThrown);
		                        $('#registerResponse').text('Error processing request.');
		                    }
		                });
		            });

});
