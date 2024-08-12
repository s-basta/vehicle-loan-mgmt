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

				// Set placeholder based on employment type
				updateSalaryPlaceholder(response.typeOfEmployment || 'N/A');
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

	// Fetch vehicle makes on page load
	$.ajax({
		url: 'http://localhost:8080/api/v1/vehicle/makes',
		type: 'GET',
		success: function(data) {
			data.forEach(function(make) {
				$('#vehicleMake').append(`<option value="${make}">${make}</option>`);
			});
		}
	});

	// Fetch vehicle models when a vehicle make is selected
	$('#vehicleMake').change(function() {
		var selectedMake = $(this).val();
		if (selectedMake) {
			$.ajax({
				url: `http://localhost:8080/api/v1/vehicle/vehiclesByMake/${selectedMake}`,
				type: 'GET',
				success: function(data) {
					$('#vehicleModel').empty().append('<option value="">Select Vehicle Model</option>');
					data.forEach(function(vehicle) {
						$('#vehicleModel').append(`<option value="${vehicle.vehicleModel}">${vehicle.vehicleModel}</option>`);
					});
					$('#vehicleModel').attr('placeholder', ''); // Clear placeholder when models are available
				}
			});
		} else {
			$('#vehicleModel').empty().append('<option value="">Select Vehicle Model</option>');
			$('#vehicleModel').attr('placeholder', 'Select Vehicle Make first to see available models'); // Set placeholder when no make is selected
		}
	});

	// Fetch prices when a vehicle model is selected
	$('#vehicleModel').change(function() {
		var selectedMake = $('#vehicleMake').val();
		var selectedModel = $(this).val();
		if (selectedMake && selectedModel) {
			$.ajax({
				url: `http://localhost:8080/api/v1/vehicle/prices?vehicleMake=${selectedMake}&vehicleModel=${selectedModel}`,
				type: 'GET',
				success: function(data) {
					$('#showroomPrice').val(data.showroomPrice || 'N/A');
					$('#onRoadPrice').val(parseInt(data.onRoadPrice) || 'N/A');
				}
			});
		} else {
			$('#showroomPrice').val('');
			$('#onRoadPrice').val('');
		}
	});

	// Update placeholder based on selected employment type
	$('#employmentType').change(function() {
		var selectedType = $(this).val();
		updateSalaryPlaceholder(selectedType);
	});

	function updateSalaryPlaceholder(employmentType) {
		var placeholder = 'Enter annual income';
		if (employmentType === 'HOUSEWIFE') {
			placeholder = "Enter spouse's/Household Annual Income";
		}
		$('#salary').attr('placeholder', placeholder);
	}

	// Store form values in sessionStorage
	function storeFormValues() {
		sessionStorage.setItem('employmentType', $('#employmentType').val());
		sessionStorage.setItem('salary', $('#salary').val());
		sessionStorage.setItem('existingEMIs', $('#existingEMIs').val());
		sessionStorage.setItem('vehicleMake', $('#vehicleMake').val());
		sessionStorage.setItem('vehicleModel', $('#vehicleModel').val());
		sessionStorage.setItem('showroomPrice', $('#showroomPrice').val());
		sessionStorage.setItem('onRoadPrice', $('#onRoadPrice').val());
		sessionStorage.setItem('loanTenureMonths', $('#loanTenureMonths').val());
	}

	// Handle Get Eligibility button click
	$('#getEligibility').click(function(event) {
		event.preventDefault(); // Prevent form submission

		storeFormValues();

		// Retrieve values from sessionStorage
		var employmentType = sessionStorage.getItem('employmentType');
		var annualIncome = parseFloat(sessionStorage.getItem('salary'));
		var existingEMIs = parseFloat(sessionStorage.getItem('existingEMIs')); // Retrieve this value as needed
		var vehiclePrice = parseInt(sessionStorage.getItem('showroomPrice'));
		var loanTenureMonths = parseFloat(sessionStorage.getItem('loanTenureMonths')); // Retrieve this value as needed

		// Call eligibility function
		var eligibility = calculateLoanEligibility(employmentType, annualIncome, existingEMIs, vehiclePrice, loanTenureMonths);

		// Set modal content and buttons based on eligibility
		var modalBody = $('#modalBody');
		var modalFooter = $('#modalFooter');

		if (eligibility === "Eligible for vehicle loan for the selected vehicle !!") {
			modalBody.text('Congratulations! You are eligible for the loan for the selected vehicle.');
			modalFooter.html('<button type="button" class="btn btn-primary" id="getLoanQuote">Get Loan Quote</button>' +
				'<button type="button" class="btn btn-secondary" data-dismiss="modal">Recheck Eligibility</button>');
		} else {
			modalBody.text(eligibility);
			modalFooter.html('<button type="button" class="btn btn-secondary" data-dismiss="modal">Recheck Eligibility</button>');
		}

		// Show the modal
		$('#eligibilityModal').modal('show');

		// Handle the Get Loan Quote button click
		$('#getLoanQuote').click(function() {
			window.location.href = 'quote.html'; // Redirect to quote.html
		});


	});

	function calculateLoanEligibility(employmentType, annualIncome, existingEMIs, vehiclePrice, loanTenureMonths) {
		// Define eligibility criteria based on employment type
		const criteria = {
			salaried: {
				minAnnualIncome: 30000,
				maxEMIPercentage: 0.50 // 50%
			},
			self_employed: {
				minAnnualIncome: 50000,
				maxEMIPercentage: 0.40 // 40%
			},
			pensioner: {
				minAnnualIncome: 20000,
				maxEMIPercentage: 0.30 // 30%
			},
			housewife: {
				minAnnualIncome: 25000, // Assuming household or spouse's income
				maxEMIPercentage: 0.30 // 30% of household income
			}
		};

		// Constants
		const annualInterestRate = 0.08; // 8% annual interest rate
		const monthlyInterestRate = annualInterestRate / 12; // Monthly interest rate

		// Determine the eligibility criteria based on employment type
		const criteriaForType = criteria[employmentType.toLowerCase()];

		if (!criteriaForType) {
			return "Invalid employment type";
		}

		// Calculate the monthly income
		const monthlyIncome = annualIncome / 12;

		// Calculate the maximum allowed EMI based on the percentage of monthly income
		const maxAllowedEMI = monthlyIncome * criteriaForType.maxEMIPercentage;

		// Check if the annual income meets the minimum requirement for the employment type
		if (annualIncome < criteriaForType.minAnnualIncome) {
			return "Income below the minimum requirement for this employment type, not eligible for vehicle loan.";
		}

		// Check if existing EMIs are within the allowed limit
		if (existingEMIs > maxAllowedEMI) {
			return "Existing EMIs exceed the allowed limit. Ensure to enter Annual Income and Total Existing EMIs per month.";
		}

		// Calculate the maximum loan amount (80% of vehicle price)
		const maxLoanAmount = vehiclePrice * 0.80;

		// EMI calculation using the provided formula
		const P = maxLoanAmount;
		const R = monthlyInterestRate;
		const n = loanTenureMonths;

		const EMI = P * R * (Math.pow(1 + R, n) / (Math.pow(1 + R, n) - 1));
		sessionStorage.setItem("calculatedEMI",EMI);
		// Check if the calculated EMI, combined with existing EMIs, is within the allowed EMI
		if (EMI + existingEMIs > maxAllowedEMI) {
			return "Calculated EMI exceeds the allowed limit based on your income and existing EMIs, maybe try another vehicle ?";
		}

		// If all checks pass, the person is eligible
		return "Eligible for vehicle loan for the selected vehicle !!";
	}
});
