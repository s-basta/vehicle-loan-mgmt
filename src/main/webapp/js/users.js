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

function logout() {
	sessionStorage.removeItem('userId');

	window.location.href = 'login.html'; 
}

$(document).ready(function() {
	var userId = sessionStorage.getItem('userId');

	if (!userId) {
		console.error('User is not logged in. Redirecting to login page.');
		window.location.href = 'login.html'; 
		return;
	}
	$.ajax({
		url: 'http://localhost:8080/api/v1/accepted-loan?userId=' + userId,
		type: 'GET',
		success: function(response) {
			var totalLoans = response.length;

			$('#total-loans-card .card-block h2 span').text(totalLoans);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.error('Failed to fetch loan data:', textStatus, errorThrown);
		}
	});

	$.ajax({
		url: 'http://localhost:8080/api/v1/emi-status/user/' + userId,
		type: 'GET',
		success: function(response) {
			if (response == undefined) {
				$('#next-installment-date h2 span').text('-');
			}
			else {
				var nextInstallmentDate = response.length > 0 ? response[0].scheduledPaymentDate : '-';
				$('#next-installment-date h2 span').text(nextInstallmentDate);
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.error('Failed to fetch the next installment date:', textStatus, errorThrown);
			$('#next-installment-date h2 span').text('-');
		}
	});

	$('#installments_table').DataTable({
		"ajax": {
			"url": "/api/v1/emi-status/user/" + userId,
			"type": "GET",
			"dataSrc": function(json) {
				json.forEach(function(item, index) {
					$.ajax({
						url: '/api/v1/applicant/' + item.applicationId,
						type: 'GET',
						async: false,
						success: function(response) {
							item.vehicleMake = response.vehicleMake;
						},
						error: function() {
							item.vehicleMake = 'N/A';
						}
					});

					$.ajax({
						url: '/api/v1/accepted-loan/' + item.applicationId,
						type: 'GET',
						async: false,
						success: function(response) {
							item.emiAmount = response.emiAmount;
						},
						error: function() {
							item.emiAmount = 'N/A';
						}
					});
				});

				return json;
			}
		},
		"columns": [{
			"data": null,
			"title": "Sr.No.",
			"render": function(data, type, row, meta) {
				return meta.row + 1;
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
			"url": "/api/v1/applicant?userId=" + userId,
			"type": "GET",
			"dataSrc": function(json) {
				return json;
			}
		},
		"columns": [{
			"data": null,
			"title": "Sr.No.",
			"render": function(data, type, row, meta) {
				return meta.row + 1;
			}
		}, {
			"data": "applicationID",
			"title": "Application ID"
		}, {
			"data": "vehicleMake",
			"title": "Car Make"
		}, {
			"data": "loanAmount",
			"title": "Loan Amount"
		}, {
			"data": "loanStatus",
			"title": "Loan Status"
		}, {
			"data": "applicationDate",
			"title": "Applied On"
		}]
	});

	$.ajax({
		url: "/api/v1/user/" + userId,
		type: 'GET',
		success: function(response) {
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
		}
	});


	$('#edit_changes').on('click', function(e) {
		e.preventDefault();

		var user = {
			userId: userId,
			firstName: $('#firstName').val(),
			lastName: $('#lastName').val(),
			dateOfBirth: $('#dateOfBirth').val(),
			gender: $('#gender').val(),
			email: $('#email').val(),
			mobile: $('#mobile').val(),
			typeOfEmployment: $('#employmentType').val(),
			salary: $('#salary').val(),
			panCardNumber: $('#panCardNumber').val() || null,
			aadharNumber: $('#aadharNumber').val() || null,
		};

		$.ajax({
			url: '/api/v1/user',
			type: 'PUT',
			contentType: 'application/json',
			data: JSON.stringify(user),
			success: function(response) {
				console.log(response);
				$('#registerResponse').text('Registration successful! Response ID: ' + response.id);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.error('AJAX request failed:', textStatus, errorThrown);
				$('#registerResponse').text('Error processing request.');
			}
		});
	});
});
