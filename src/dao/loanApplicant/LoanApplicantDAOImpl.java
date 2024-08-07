package dao.loanApplicant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import config.Database;

public class LoanApplicantDAOImpl implements LoanApplicantDAO {
	Connection conn = null;

	public LoanApplicantDAOImpl() {
		try {
			System.out.println("Registering driver...");
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			System.out.println("Driver registered....");

			System.out.println("Trying to connect to the DB");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehicle_loan", "root", "@Cricket1603");
			System.out.println("Connected to the DB : " + conn);
		} catch (Exception e) {
			System.out.println("Problem : " + e);
		}
	}

	private LoanApplicant resultSetToLoanApplicantConvertor(ResultSet resultSet) throws SQLException {
		return new LoanApplicant(resultSet.getInt("applicationID"), resultSet.getInt("userId"),
				resultSet.getString("carMake"), resultSet.getString("carModel"), resultSet.getDouble("exShowroomPrice"),
				resultSet.getDouble("onRoadPrice"), resultSet.getString("typeOfEmployment"),
				resultSet.getDouble("yearlySalary"), resultSet.getString("mobileNumber"),
				resultSet.getString("emailID"), resultSet.getString("houseNumber"), resultSet.getString("streetName"),
				resultSet.getString("city"), resultSet.getString("state"), resultSet.getString("pinCode"),
				resultSet.getDouble("loanAmount"), resultSet.getInt("loanTenure"),
				resultSet.getDouble("rateOfInterest"), resultSet.getString("aadharNumber"),
				resultSet.getString("panCard"));
	}

	@Override
	public boolean create(LoanApplicant loanApplicant) {
		try {
			String sql = "INSERT INTO `LoanApplicant` ("
					+ "`userID`, `carMake`, `carModel`, `exShowroomPrice`, `onRoadPrice`, "
					+ "`typeOfEmployment`, `yearlySalary`, `existingEMI`, `mobileNumber`, `emailID`, "
					+ "`houseNumber`, `streetName`, `city`, `state`, `pinCode`, "
					+ "`loanAmount`, `loanTenure`, `rateOfInterest`, `aadharNumber`, `panCard`, `loanStatus`, `applicationDate`) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, loanApplicant.getUserID());
			pst.setString(2, loanApplicant.getCarMake());
			pst.setString(3, loanApplicant.getCarModel());
			pst.setDouble(4, loanApplicant.getExShowroomPrice());
			pst.setDouble(5, loanApplicant.getOnRoadPrice());
			pst.setString(6, loanApplicant.getTypeOfEmployment());
			pst.setDouble(7, loanApplicant.getYearlySalary());
			pst.setDouble(8, loanApplicant.getExistingEMI());
			pst.setString(9, loanApplicant.getMobileNumber());
			pst.setString(10, loanApplicant.getEmailID());
			pst.setString(11, loanApplicant.getHouseNumber());
			pst.setString(12, loanApplicant.getStreetName());
			pst.setString(13, loanApplicant.getCity());
			pst.setString(14, loanApplicant.getState());
			pst.setString(15, loanApplicant.getPinCode());
			pst.setDouble(16, loanApplicant.getLoanAmount());
			pst.setInt(17, loanApplicant.getLoanTenure());
			pst.setDouble(18, loanApplicant.getRateOfInterest());
			pst.setString(19, loanApplicant.getAadharNumber());
			pst.setString(20, loanApplicant.getPanCard());
			pst.setString(21, loanApplicant.getLoanStatus());
			pst.setDate(22, new java.sql.Date(loanApplicant.getApplicationDate().getTime()));

			int rows = pst.executeUpdate();
			if (rows > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public LoanApplicant getById(int applicationID) {
		LoanApplicant loanApplicant = null;

		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement
					.executeQuery("SELECT * FROM LoanApplicant WHERE applicationID = " + applicationID);
			if (result.next()) {
				System.out.println(result);
				loanApplicant = resultSetToLoanApplicantConvertor(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return loanApplicant;
	}

	@Override
	public List<LoanApplicant> getAll() {
		List<LoanApplicant> loanApplicants = new ArrayList<>();

		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM LoanApplicant");
			while (result.next()) {
				LoanApplicant loanApplicant = resultSetToLoanApplicantConvertor(result);
				loanApplicants.add(loanApplicant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return loanApplicants;
	}

	@Override
	public boolean update(LoanApplicant loanApplicant) {
		if (loanApplicant == null || loanApplicant.getApplicationID() == 0) {
			throw new IllegalArgumentException("LoanApplicant and ApplicationID must not be null");
		}

		List<String> setClauses = new ArrayList<>();
		List<Object> parameters = new ArrayList<>();

		if (loanApplicant.getUserID() != 0) {
			setClauses.add("userID = ?");
			parameters.add(loanApplicant.getUserID());
		}
		if (loanApplicant.getCarMake() != null) {
			setClauses.add("carMake = ?");
			parameters.add(loanApplicant.getCarMake());
		}
		if (loanApplicant.getCarModel() != null) {
			setClauses.add("carModel = ?");
			parameters.add(loanApplicant.getCarModel());
		}
		if (loanApplicant.getExShowroomPrice() != 0) {
			setClauses.add("exShowroomPrice = ?");
			parameters.add(loanApplicant.getExShowroomPrice());
		}
		if (loanApplicant.getOnRoadPrice() != 0) {
			setClauses.add("onRoadPrice = ?");
			parameters.add(loanApplicant.getOnRoadPrice());
		}
		if (loanApplicant.getTypeOfEmployment() != null) {
			setClauses.add("typeOfEmployment = ?");
			parameters.add(loanApplicant.getTypeOfEmployment());
		}
		if (loanApplicant.getYearlySalary() != 0) {
			setClauses.add("yearlySalary = ?");
			parameters.add(loanApplicant.getYearlySalary());
		}
		if (loanApplicant.getExistingEMI() != 0) {
			setClauses.add("existingEMI = ?");
			parameters.add(loanApplicant.getExistingEMI());
		}
		if (loanApplicant.getMobileNumber() != null) {
			setClauses.add("mobileNumber = ?");
			parameters.add(loanApplicant.getMobileNumber());
		}
		if (loanApplicant.getEmailID() != null) {
			setClauses.add("emailID = ?");
			parameters.add(loanApplicant.getEmailID());
		}
		if (loanApplicant.getHouseNumber() != null) {
			setClauses.add("houseNumber = ?");
			parameters.add(loanApplicant.getHouseNumber());
		}
		if (loanApplicant.getStreetName() != null) {
			setClauses.add("streetName = ?");
			parameters.add(loanApplicant.getStreetName());
		}
		if (loanApplicant.getCity() != null) {
			setClauses.add("city = ?");
			parameters.add(loanApplicant.getCity());
		}
		if (loanApplicant.getState() != null) {
			setClauses.add("state = ?");
			parameters.add(loanApplicant.getState());
		}
		if (loanApplicant.getPinCode() != null) {
			setClauses.add("pinCode = ?");
			parameters.add(loanApplicant.getPinCode());
		}
		if (loanApplicant.getLoanAmount() != 0) {
			setClauses.add("loanAmount = ?");
			parameters.add(loanApplicant.getLoanAmount());
		}
		if (loanApplicant.getLoanTenure() != 0) {
			setClauses.add("loanTenure = ?");
			parameters.add(loanApplicant.getLoanTenure());
		}
		if (loanApplicant.getRateOfInterest() != 0) {
			setClauses.add("rateOfInterest = ?");
			parameters.add(loanApplicant.getRateOfInterest());
		}
		if (loanApplicant.getAadharNumber() != null) {
			setClauses.add("aadharNumber = ?");
			parameters.add(loanApplicant.getAadharNumber());
		}
		if (loanApplicant.getPanCard() != null) {
			setClauses.add("panCard = ?");
			parameters.add(loanApplicant.getPanCard());
		}
		if (loanApplicant.getLoanStatus() != null) {
			setClauses.add("loanStatus = ?");
			parameters.add(loanApplicant.getLoanStatus());
		}
		if (loanApplicant.getApplicationDate() != null) {
			setClauses.add("applicationDate = ?");
			parameters.add(new java.sql.Date(loanApplicant.getApplicationDate().getTime()));
		}

		if (setClauses.isEmpty()) {
			throw new IllegalArgumentException("No fields to update");
		}

		String sql = "UPDATE `LoanApplicant` SET " + String.join(", ", setClauses) + " WHERE applicationID = ?";
		parameters.add(loanApplicant.getApplicationID());

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			for (int i = 0; i < parameters.size(); i++) {
				pst.setObject(i + 1, parameters.get(i));
			}

			int rows = pst.executeUpdate();
			if (rows > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteById(int applicationID) {
		try {
			String sql = "DELETE FROM `LoanApplicant` WHERE applicationID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, applicationID);

			int rows = pst.executeUpdate();
			if (rows > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}
