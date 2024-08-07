package dao.acc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcceptedLoansDAOImpl implements AcceptedLoansDAO {
    private Connection conn;

    public AcceptedLoansDAOImpl() {
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

    private AcceptedLoan resultSetToAcceptedLoan(ResultSet resultSet) throws SQLException {
        return new AcceptedLoan(
                resultSet.getInt("LoanID"),
                resultSet.getInt("ApplicationID"),
                resultSet.getInt("UserID"),
                resultSet.getDouble("EMIAmount"),
                resultSet.getInt("TotalEMIs"),
                resultSet.getInt("PaidEMIs"),
                resultSet.getDate("LoanStartDate"),
                resultSet.getDate("LoanEndDate")
        );
    }

    @Override
    public void insertAcceptedLoan(AcceptedLoan loan) {
        try {
            String query = "INSERT INTO AcceptedLoans (ApplicationID, UserID, EMIAmount, TotalEMIs, PaidEMIs, LoanStartDate, LoanEndDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, loan.getApplicationID());
            stmt.setInt(2, loan.getUserID());
            stmt.setDouble(3, loan.getEmiAmount());
            stmt.setInt(4, loan.getTotalEMIs());
            stmt.setInt(5, loan.getPaidEMIs());
            stmt.setDate(6, new java.sql.Date(loan.getLoanStartDate().getTime()));
            stmt.setDate(7, new java.sql.Date(loan.getLoanEndDate().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAcceptedLoan(AcceptedLoan loan) {
        try {
            String query = "UPDATE AcceptedLoans SET ApplicationID = ?, UserID = ?, EMIAmount = ?, TotalEMIs = ?, PaidEMIs = ?, LoanStartDate = ?, LoanEndDate = ? WHERE LoanID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, loan.getApplicationID());
            stmt.setInt(2, loan.getUserID());
            stmt.setDouble(3, loan.getEmiAmount());
            stmt.setInt(4, loan.getTotalEMIs());
            stmt.setInt(5, loan.getPaidEMIs());
            stmt.setDate(6, new java.sql.Date(loan.getLoanStartDate().getTime()));
            stmt.setDate(7, new java.sql.Date(loan.getLoanEndDate().getTime()));
            stmt.setInt(8, loan.getLoanID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAcceptedLoan(int loanID) {
        try {
            String query = "DELETE FROM AcceptedLoans WHERE LoanID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, loanID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AcceptedLoan getAcceptedLoanById(int loanID) {
        AcceptedLoan loan = null;
        try {
            String query = "SELECT * FROM AcceptedLoans WHERE LoanID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, loanID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                loan = resultSetToAcceptedLoan(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loan;
    }

    @Override
    public List<AcceptedLoan> getAllAcceptedLoans() {
        List<AcceptedLoan> loans = new ArrayList<>();
        try {
            String query = "SELECT * FROM AcceptedLoans";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AcceptedLoan loan = resultSetToAcceptedLoan(rs);
                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }
}
