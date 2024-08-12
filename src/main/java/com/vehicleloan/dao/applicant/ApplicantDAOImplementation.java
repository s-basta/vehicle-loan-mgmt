package com.vehicleloan.dao.applicant;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vehicleloan.config.Database;
import com.vehicleloan.constant.LoanStatus;
import com.vehicleloan.constant.TypeOfEmployment;
import com.vehicleloan.dao.user.User;
import com.vehicleloan.dao.user.UserDAO;

@Service
public class ApplicantDAOImplementation implements ApplicantDAO {
    private Connection conn;
    private UserDAO userDAO;

    public ApplicantDAOImplementation(UserDAO userDAO) {
        conn = Database.getConnection();
        this.userDAO = userDAO;
    }

    private Applicant resultSetToApplicantConvertor(ResultSet result) throws SQLException {
        User user = userDAO.get(result.getInt("userID"));

        return new Applicant(
                result.getInt("applicationID"),
                result.getInt("userID"),
                result.getString("vehicleMake"),
                result.getString("vehicleType"),
                result.getDouble("exShowroomPrice"),
                result.getDouble("onRoadPrice"),
                TypeOfEmployment.valueOf(result.getString("typeOfEmployment")),
                result.getDouble("yearlySalary"),
                result.getDouble("existingEMI"),
                result.getString("mobileNumber"),
                result.getString("emailID"),
                result.getString("houseNumber"),
                result.getString("streetName"),
                result.getString("city"),
                result.getString("state"),
                result.getString("pinCode"),
                result.getDouble("loanAmount"),
                result.getInt("loanTenure"),
                result.getDouble("rateOfInterest"),
                LoanStatus.valueOf(result.getString("loanStatus")),
                result.getDate("applicationDate"),
                user.getPanCardNumber(),
                user.getAadharNumber());
    }

    @Override
    public Integer create(Applicant applicant) {
        String sql = "INSERT INTO vloanApplicant (userID, vehicleMake, vehicleType, exShowroomPrice, onRoadPrice, typeOfEmployment, yearlySalary, existingEMI, mobileNumber, emailID, houseNumber, streetName, city, state, pinCode, loanAmount, loanTenure, rateOfInterest, loanStatus, applicationDate) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, applicant.getUserID());
            pst.setString(2, applicant.getVehicleMake());
            pst.setString(3, applicant.getVehicleType());
            pst.setDouble(4, applicant.getExShowroomPrice());
            pst.setDouble(5, applicant.getOnRoadPrice());
            pst.setString(6, applicant.getTypeOfEmployment().name());
            pst.setDouble(7, applicant.getYearlySalary());
            pst.setDouble(8, applicant.getExistingEMI());
            pst.setString(9, applicant.getMobileNumber());
            pst.setString(10, applicant.getEmailID());
            pst.setString(11, applicant.getHouseNumber());
            pst.setString(12, applicant.getStreetName());
            pst.setString(13, applicant.getCity());
            pst.setString(14, applicant.getState());
            pst.setString(15, applicant.getPinCode());
            pst.setDouble(16, applicant.getLoanAmount());
            pst.setInt(17, applicant.getLoanTenure());
            pst.setDouble(18, applicant.getRateOfInterest());
            pst.setString(19, applicant.getLoanStatus().name());
            pst.setDate(20, new Date(applicant.getApplicationDate().getTime()));

            int rows = pst.executeUpdate();
            if(rows > 0){
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Return the generated userId
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Applicant get(int applicationID) {
        String sql = "SELECT * FROM vloanApplicant WHERE applicationID = ?";
        Applicant applicant = null;

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, applicationID);
            ResultSet result = pst.executeQuery();

            if (result.next()) {
                applicant = resultSetToApplicantConvertor(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applicant;
    }

    @Override
    public List<Applicant> get(LoanStatus loanStatus, Integer userId) {
        List<Applicant> applicants = new ArrayList<>();
        String sql;
        if (loanStatus != null && userId != null) {
            sql = "SELECT * FROM vloanApplicant where loanStatus like '" + loanStatus.name() + "'" + " and userId = "
                    + userId;
        } else if (loanStatus == null && userId != null) {
            sql = "SELECT * FROM vloanApplicant where userId = " + userId;
        } else if (loanStatus != null && userId == null) {
            sql = "SELECT * FROM vloanApplicant where loanStatus like '" + loanStatus.name() + "'";
        } else {
            sql = "SELECT * FROM vloanApplicant";
        }

        try (PreparedStatement pst = conn.prepareStatement(sql); ResultSet result = pst.executeQuery()) {
            while (result.next()) {
                applicants.add(resultSetToApplicantConvertor(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applicants;
    }

    @Override
    public boolean update(Applicant applicant) {
        if (applicant == null || applicant.getApplicationID() == null) {
            throw new IllegalArgumentException("Applicant and ApplicationID must not be null");
        }

        List<String> setClauses = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        if (applicant.getUserID() != null) {
            setClauses.add("userID = ? ");
            parameters.add(applicant.getUserID());
        }
        if (applicant.getVehicleMake() != null) {
            setClauses.add("vehicleMake = ? ");
            parameters.add(applicant.getVehicleMake());
        }
        if (applicant.getVehicleType() != null) {
            setClauses.add("vehicleType = ? ");
            parameters.add(applicant.getVehicleType());
        }
        if (applicant.getExShowroomPrice() != null) {
            setClauses.add("exShowroomPrice = ? ");
            parameters.add(applicant.getExShowroomPrice());
        }
        if (applicant.getOnRoadPrice() != null) {
            setClauses.add("onRoadPrice = ? ");
            parameters.add(applicant.getOnRoadPrice());
        }
        if (applicant.getTypeOfEmployment() != null) {
            setClauses.add("typeOfEmployment = ? ");
            parameters.add(applicant.getTypeOfEmployment());
        }
        if (applicant.getYearlySalary() != null) {
            setClauses.add("yearlySalary = ? ");
            parameters.add(applicant.getYearlySalary());
        }
        if (applicant.getExistingEMI() != null) {
            setClauses.add("existingEMI = ? ");
            parameters.add(applicant.getExistingEMI());
        }
        if (applicant.getMobileNumber() != null) {
            setClauses.add("mobileNumber = ? ");
            parameters.add(applicant.getMobileNumber());
        }
        if (applicant.getEmailID() != null) {
            setClauses.add("emailID = ? ");
            parameters.add(applicant.getEmailID());
        }
        if (applicant.getHouseNumber() != null) {
            setClauses.add("houseNumber = ? ");
            parameters.add(applicant.getHouseNumber());
        }
        if (applicant.getStreetName() != null) {
            setClauses.add("streetName = ? ");
            parameters.add(applicant.getStreetName());
        }
        if (applicant.getCity() != null) {
            setClauses.add("city = ? ");
            parameters.add(applicant.getCity());
        }
        if (applicant.getState() != null) {
            setClauses.add("state = ? ");
            parameters.add(applicant.getState());
        }
        if (applicant.getPinCode() != null) {
            setClauses.add("pinCode = ? ");
            parameters.add(applicant.getPinCode());
        }
        if (applicant.getLoanAmount() != null) {
            setClauses.add("loanAmount = ? ");
            parameters.add(applicant.getLoanAmount());
        }
        if (applicant.getLoanTenure() != null) {
            setClauses.add("loanTenure = ? ");
            parameters.add(applicant.getLoanTenure());
        }
        if (applicant.getRateOfInterest() != null) {
            setClauses.add("rateOfInterest = ? ");
            parameters.add(applicant.getRateOfInterest());
        }
        if (applicant.getLoanStatus() != null) {
            setClauses.add("loanStatus = ? ");
            parameters.add(applicant.getLoanStatus().name());
        }
        if (applicant.getApplicationDate() != null) {
            setClauses.add("applicationDate = ? ");
            parameters.add(new Date(applicant.getApplicationDate().getTime()));
        }

        String sql = "UPDATE vloanApplicant SET " + String.join(", ", setClauses) + " WHERE applicationID = ?";
        parameters.add(applicant.getApplicationID());

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                pst.setObject(i + 1, parameters.get(i));
            }

            int rows = pst.executeUpdate();
            System.out.println(rows);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int applicationID) {
        String sql = "DELETE FROM vloanApplicant WHERE applicationID = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, applicationID);
            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}