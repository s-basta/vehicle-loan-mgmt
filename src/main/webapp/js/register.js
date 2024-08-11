
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
		
		// Make AJAX POST Request
		// now reverse testing againnn
		$.ajax({
		                    url: '/api/v1/user', // Example API URL
		                    type: 'POST',
		                    contentType: 'application/json',
		                    data: JSON.stringify(user),
		                    success: function(response) {
		                        console.log(response); // For debugging
		                        $('#registerResponse').text('Registration successful!');
								alert('Registration successful! Please login now.');
								        
						        // Redirect to login.html after the alert is dismissed
						        window.location.href = 'login.html';
		                    },
		                    error: function(jqXHR, textStatus, errorThrown) {
		                        console.error('AJAX request failed:', textStatus, errorThrown);
		                        $('#registerResponse').text('Error processing request. Please make sure you have not already registered / used an existing username/email/name.');
		                    }
		                });
		            });

});

        // Use localStorage for storing credentials in this example
        // Note: This is not secure for real applications

function checkPasswordStrength(passwordId, strengthId) {
    const password = document.getElementById(passwordId).value;
    const strength = document.getElementById(strengthId);
    const regex = {
        capital : /[A-Z]/,
        small : /[a-z]/,
        special : /[!@#$%^&*(),.?":{}|<>]/,
        number : /[0-9]/,
        length : /.{8,}/
    };
    let valid = true;
    let message = 'Password must include: ';
    if (!regex.capital.test(password)) {
        message += 'one capital letter, ';
        valid = false;
    }
    if (!regex.small.test(password)) {
        message += 'one small letter, ';
        valid = false;
    }
    if (!regex.special.test(password)) {
        message += 'one special character, ';
        valid = false;
    }
    if (!regex.number.test(password)) {
        message += 'one number, ';
        valid = false;
    }
    if (!regex.length.test(password)) {
        message += 'minimum 8 characters.';
        valid = false;
    }
    if (valid) {
        strength.textContent = 'Password is strong.';
        strength.className = 'valid';
    } else {
        strength.textContent = message;
        strength.className = 'invalid';
    }
}

function checkPasswordMatch(passwordId, confirmPasswordId, matchId) {
    const password = document.getElementById(passwordId).value;
    const confirmPassword = document.getElementById(confirmPasswordId).value;
    const match = document.getElementById(matchId);
    if (password === confirmPassword) {
        match.textContent = 'Passwords match.';
        match.className = 'valid';
    } else {
        match.textContent = 'Passwords do not match.';
        match.className = 'invalid';
    }
}
