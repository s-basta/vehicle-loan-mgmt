$(document).ready(function() {
    $('#loginForm').on('submit', function(e) {
        e.preventDefault();

        var loginData = {
            username: $('#email').val(),
            password: $('#password').val()
        };

        $.ajax({
            url: '/api/v1/user/login', 
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(loginData),
            success: function(response) {
                $('#loginResponse').text('Login successful!');
				
                sessionStorage.setItem('userId', response.userId);
				if(response.isAdmin == true){
					sessionStorage.setItem('isAdmin', true);
					alert('Login successful! Redirecting to Admin dashboard.');
					window.location.href='adminPanel.html';
				}
				else{
					alert('Login successful! Redirecting to user dashboard.');
					window.location.href = 'userPanel.html'; 
					 	
				}           	
               },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('AJAX request failed:', textStatus, errorThrown);
                $('#loginResponse').text('Login failed. Please check your username and password.');
            }
        });
    });
});
