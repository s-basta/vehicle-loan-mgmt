import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import constants.Gender;
import dao.loanApplicant.LoanApplicant;
import dao.loanApplicant.LoanApplicantDAO;
import dao.loanApplicant.LoanApplicantDAOImpl;
import dao.user.User;
import dao.user.UserDAO;
import dao.user.UserDAOImplementation;
import dao.vehicle.Vehicle;
import dao.vehicle.VehicleDAO;
import dao.vehicle.VehicleDAOImplementation;
import utils.Utility;

public class Main {
	public static void registerUser() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("------------Register User-----------");

		// First Name
		System.out.print("First Name : ");
		String firstName = scanner.next();

		// Last Name
		System.out.print("Last Name : ");
		String lastName = scanner.next();

		// Date of birth
		LocalDate dob;
		while (true) {
			try {
				System.out.print("Date of Birth[yyyy-mm-dd] : ");
				String dobStr = scanner.next();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				dob = LocalDate.parse(dobStr, formatter);
				break;
			} catch (DateTimeParseException ex) {
				System.out.println("Please Enter the date in correct format(yyyy-mm-dd)");
			}
		}

		// Gender
		Gender gender = null;
		while (true) {
			System.out.print("Gender (MALE, FEMALE, OTHER) : ");

			String input = scanner.next().trim().toUpperCase();

			try {
				gender = Gender.valueOf(input);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid input. Please enter a valid gender.");
			}
		}

		// Username
		System.out.print("Username : ");
		String username = scanner.next();

		// Email
		String email;
		while (true) {
			System.out.print("Email : ");
			email = scanner.next();
			if (Utility.isEmail(email))
				break;
			System.out.println("Enter valid Email address.");
		}

		String mobile;
		while (true) {
			System.out.print("Mobile Number : ");
			mobile = scanner.next();
			if (Utility.isValidMobileNo(mobile))
				break;
			System.out.println("Please enter valid mobile number.");
		}

		String password;
		System.out.println(
				"Password should contain miniumum 8 characters with atleast one small letter, capital letter, number and special character");
		while (true) {
			System.out.print("Password : ");
			password = scanner.next();
			if (Utility.isValidPassword(password))
				break;
			System.out.println("Please enter a valid password");
		}

		while (true) {
			System.out.print("Confirm Password : ");
			String confirmPassword = scanner.next();
			if (password.equals(confirmPassword))
				break;
			System.out.println("Confirm Password doesn't match with Password");
		}

		UserDAO userDAO = new UserDAOImplementation();
		boolean isCreated = userDAO.create(new User(null, firstName, lastName, dob, gender, username, email, mobile,
				password, false, null, null, null, null));

		if (isCreated)
			System.out.println("User Account created successfully");
		else
			System.out.println("UserAccount not created");
	}

	public static void showUsers() {
		UserDAO userDAO = new UserDAOImplementation();

		List<User> users = userDAO.getAll();
		for (User user : users) {
			System.out.println(user);
		}
	}

	public static void showUser(int userId) {
		UserDAO userDAO = new UserDAOImplementation();
		User user = userDAO.getById(userId);
		System.out.println(user);

	}

	public static void updateUser(User user) {
		UserDAO userDAO = new UserDAOImplementation();
		boolean isUpdated = userDAO.update(user);
		System.out.println("User Updated Successfully");
	}

	public static void login(String username, String password) {
		UserDAO userDAO = new UserDAOImplementation();

		User user = userDAO.getByUsername(username);
		if (user == null)
			user = userDAO.getByEmail(username);

		if (user != null && user.getPassword().equals(password))
			System.out.println("User Logged In");
		else
			System.out.println("Invalid User Credentials");
	}

	public static Vehicle getVehicleById(int vehicleId) {
		VehicleDAO vehicleDAO = new VehicleDAOImplementation();

		return vehicleDAO.getByVehicleId(vehicleId);
	}

	public static List<String> getVehicleTypes() {
		VehicleDAO vehicleDAO = new VehicleDAOImplementation();

		return vehicleDAO.getVehicleType();
	}

	public static List<Vehicle> getVehiclesByVehicleType(String vehicleType) {
		VehicleDAO vehicleDAO = new VehicleDAOImplementation();

		return vehicleDAO.getVehiclesByVehicleType(vehicleType);
	}

	public static void createVehicle(Vehicle vehicle) {
		VehicleDAO vehicleDAO = new VehicleDAOImplementation();

		boolean isCreated = vehicleDAO.create(vehicle);

		if (isCreated)
			System.out.println("Vehicle Created Successfully");
		else
			System.out.println("Vehicle Not Created");
	}

	public static void updateVehicle(Vehicle vehicle) {
		VehicleDAO vehicleDAO = new VehicleDAOImplementation();

		boolean isUpdated = vehicleDAO.update(vehicle);

		if (isUpdated)
			System.out.println("Vehicle Updated Successfully");
		else
			System.out.println("Vehicle Not Updated");
	}

	public static void deleteVehicle(int vehicleId) {
		VehicleDAO vehicleDAO = new VehicleDAOImplementation();

		boolean isDeleted = vehicleDAO.delete(vehicleId);

		if (isDeleted)
			System.out.println("Vehicle Deleted Successfully");
		else
			System.out.println("Vehicle not deleted");
	}

	public static void createLoan(LoanApplicant loanApplicant) {
		LoanApplicantDAO loanApplicantDAO = new LoanApplicantDAOImpl();

		// Insert the new LoanApplicant record into the database
		boolean isCreated = loanApplicantDAO.create(loanApplicant);

		// Print the result
		if (isCreated) {
			System.out.println("Loan application created successfully!");
		} else {
			System.out.println("Failed to create loan application.");
		}
	}

	public static void getLoanById(int id) {
		LoanApplicantDAO loanApplicantDAO = new LoanApplicantDAOImpl();
		LoanApplicant loanApplicant = loanApplicantDAO.getById(id);

		// Check if the loan applicant was found and print details
		if (loanApplicant != null) {
			System.out.println("Loan Applicant Details:");
			System.out.println("Application ID: " + loanApplicant.getApplicationID());
			System.out.println("User ID: " + loanApplicant.getUserID());
			System.out.println("Car Make: " + loanApplicant.getCarMake());
			System.out.println("Car Model: " + loanApplicant.getCarModel());
			System.out.println("Ex-Showroom Price: " + loanApplicant.getExShowroomPrice());
			System.out.println("On-Road Price: " + loanApplicant.getOnRoadPrice());
			System.out.println("Type of Employment: " + loanApplicant.getTypeOfEmployment());
			System.out.println("Yearly Salary: " + loanApplicant.getYearlySalary());
			System.out.println("Existing EMI: " + loanApplicant.getExistingEMI());
			System.out.println("Mobile Number: " + loanApplicant.getMobileNumber());
			System.out.println("Email ID: " + loanApplicant.getEmailID());
			System.out.println("House Number: " + loanApplicant.getHouseNumber());
			System.out.println("Street Name: " + loanApplicant.getStreetName());
			System.out.println("City: " + loanApplicant.getCity());
			System.out.println("State: " + loanApplicant.getState());
			System.out.println("Pin Code: " + loanApplicant.getPinCode());
			System.out.println("Loan Amount: " + loanApplicant.getLoanAmount());
			System.out.println("Loan Tenure: " + loanApplicant.getLoanTenure());
			System.out.println("Rate of Interest: " + loanApplicant.getRateOfInterest());
			System.out.println("Aadhar Number: " + loanApplicant.getAadharNumber());
			System.out.println("PAN Card: " + loanApplicant.getPanCard());
			System.out.println("Loan Status: " + loanApplicant.getLoanStatus());
			System.out.println("Application Date: " + loanApplicant.getApplicationDate());
		} else {
			System.out.println("Loan Applicant not found for Application ID: " + id);
		}
	}

	public static void getAllLoans() {
		LoanApplicantDAO loanApplicantDAO = new LoanApplicantDAOImpl();
		List<LoanApplicant> loanApplicants = loanApplicantDAO.getAll();

		// Print all loan applicants
		for (LoanApplicant loanApplicant : loanApplicants) {
			System.out.println(loanApplicant);
		}
	}

	public static void updateLoan(int id, LoanApplicant newApplicant) {
		LoanApplicantDAO loanApplicantDAO = new LoanApplicantDAOImpl();
		LoanApplicant existingLoanApplicant = loanApplicantDAO.getById(id);
		if (existingLoanApplicant != null) {

			// Update the LoanApplicant record in the database
			boolean isUpdated = loanApplicantDAO.update(newApplicant);

			if (isUpdated) {
				System.out.println("LoanApplicant record updated successfully.");
			} else {
				System.out.println("Failed to update LoanApplicant record.");
			}
		} else {
			System.out.println("LoanApplicant record not found.");
		}
	}

	public static void deleteLoanApplicant(int id) {
		LoanApplicantDAO loanApplicantDAO = new LoanApplicantDAOImpl();
		LoanApplicant existingLoanApplicant = loanApplicantDAO.getById(id);
		if (existingLoanApplicant != null) {
			boolean isDeleted = loanApplicantDAO.deleteById(id);

			if (isDeleted) {
				System.out.println("LoanApplicant record deleted successfully.");
			} else {
				System.out.println("Failed to delete LoanApplicant record.");
			}
		} else {
			System.out.println("LoanApplicant record not found.");
		}
	}

	public static void main(String[] args) {
		// Meet
//		LoanApplicant loanApplicant = new LoanApplicant(1, 1, // ApplicationID will be auto-incremented
//				"Santro", // CarMake
//				"Camry", // CarModel
//				254000.00, // ExShowroomPrice
//				30000.00, // OnRoadPrice
//				"Salaried", // TypeOfEmployment
//				50000.00, // YearlySalary
//				"1234567890", // MobileNumber
//				"example@example.com", // EmailID
//				"1234", // HouseNumber
//				"Example Street", // StreetName
//				"Example City", // City
//				"Example State", // State
//				"123456", // PinCode
//				20000.00, // LoanAmount
//				60, // LoanTenure
//				7.5, // RateOfInterest
//				"123412341234", // AadharNumber
//				"ABC1234567");
//		createLoan(loanApplicant);
//		getLoanById(1);
//		getAllLoans();
//		updateLoan(1, loanApplicant);
//		deleteLoanApplicant(3);
		
		
		
		// Khabilan
//		updateUser(new User(1 , null, "Sundar", null, null, null, null, null, null, null, null, null, null, null ));
//		login("khabilansomasundar@gmail.com" , "Acere2200hq$");
//		System.out.println(getVehicleById(1));
//		System.out.println(getVehicleTypes());
//		System.out.println(getVehiclesByVehicleType("Sedan"));
//		createVehicle(new Vehicle(1 , "Maruti" , "Sedan" , (double) 115643 , (double) 134653));
//		updateVehicle(new Vehicle(86 , "Maruti" , "Sedan" , (double) 1156430 , (double) 1346530));
//		deleteVehicle(86);
	}
}