import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import constants.Gender;
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
		while(true) {		
			try {
				System.out.print("Date of Birth[yyyy-mm-dd] : ");
				String dobStr = scanner.next();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				dob = LocalDate.parse(dobStr , formatter);				
				break;
			} catch (DateTimeParseException ex) {
				System.out.println("Please Enter the date in correct format(yyyy-mm-dd)");
			}
		}
		
		// Gender
		Gender gender = null;
		while(true) {
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
		while(true) {
			System.out.print("Email : ");
			email = scanner.next();
			if(Utility.isEmail(email)) break;
			System.out.println("Enter valid Email address.");
		}
		
		String mobile;
		while(true) {
			System.out.print("Mobile Number : ");
			mobile = scanner.next();
			if(Utility.isValidMobileNo(mobile)) break;
			System.out.println("Please enter valid mobile number.");			
		}
		
		
		String password;
		System.out.println("Password should contain miniumum 8 characters with atleast one small letter, capital letter, number and special character");
		while(true) {
			System.out.print("Password : ");
			password = scanner.next();
			if(Utility.isValidPassword(password)) break;
			System.out.println("Please enter a valid password");
		}
		
		while(true) {
			System.out.print("Confirm Password : ");
			String confirmPassword = scanner.next();
			if(password.equals(confirmPassword)) break;
			System.out.println("Confirm Password doesn't match with Password");
		}
		
		UserDAO userDAO = new UserDAOImplementation();
		boolean isCreated = userDAO.create(new User(null, firstName , lastName, dob, gender, username, email, mobile, password, false, null, null, null, null));
		
		if(isCreated) System.out.println("User Account created successfully");
		else System.out.println("UserAccount not created");
	}
	
	public static void showUsers() {
		UserDAO userDAO = new UserDAOImplementation();
		
		List<User> users = userDAO.getAll();
		for(User user : users) {
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
	
	public static void login(String username , String password) {
		UserDAO userDAO = new UserDAOImplementation();
		
		User user = userDAO.getByUsername(username);
		if(user == null) user = userDAO.getByEmail(username);
		 
		if(user != null && user.getPassword().equals(password)) System.out.println("User Logged In");
		else System.out.println("Invalid User Credentials");		
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
	
	public static void main(String[] args) {
//		updateUser(new User(1 , null, "Sundar", null, null, null, null, null, null, null, null, null, null, null ));
//		login("khabilansomasundar@gmail.com" , "Acere2200hq$");
//		System.out.println(getVehicleById(1));
//		System.out.println(getVehicleTypes());
//		System.out.println(getVehiclesByVehicleType("Sedan"));
	}
}