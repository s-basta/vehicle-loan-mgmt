package dao.status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanStatusDAOImpl implements LoanStatusDAO {
    private Connection conn;

    public LoanStatusDAOImpl() {
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

    private LoanStatus resultSetToLoanStatus(ResultSet resultSet) throws SQLException {
        return new LoanStatus(
                resultSet.getInt("StatusID"),
                resultSet.getInt("LoanID"),
                resultSet.getInt("CurrentEMI"),
                resultSet.getString("PaymentStatus"),
                resultSet.getDate("PaymentDate")
        );
    }

    @Override
    public void insertLoanStatus(LoanStatus status) {
        try {
            String query = "INSERT INTO LoanStatus (LoanID, CurrentEMI, PaymentStatus, PaymentDate) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, status.getLoanID());
            stmt.setInt(2, status.getCurrentEMI());
            stmt.setString(3, status.getPaymentStatus());
            stmt.setDate(4, new java.sql.Date(status.getPaymentDate().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLoanStatus(LoanStatus status) {
        try {
            String query = "UPDATE LoanStatus SET LoanID = ?, CurrentEMI = ?, PaymentStatus = ?, PaymentDate = ? WHERE StatusID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, status.getLoanID());
            stmt.setInt(2, status.getCurrentEMI());
            stmt.setString(3, status.getPaymentStatus());
            stmt.setDate(4, new java.sql.Date(status.getPaymentDate().getTime()));
            stmt.setInt(5, status.getStatusID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLoanStatus(int statusID) {
        try {
            String query = "DELETE FROM LoanStatus WHERE StatusID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, statusID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LoanStatus getLoanStatusById(int statusID) {
        LoanStatus status = null;
        try {
            String query = "SELECT * FROM LoanStatus WHERE StatusID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, statusID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                status = resultSetToLoanStatus(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<LoanStatus> getAllLoanStatuses() {
        List<LoanStatus> statuses = new ArrayList<>();
        try {
            String query = "SELECT * FROM LoanStatus";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LoanStatus status = resultSetToLoanStatus(rs);
                statuses.add(status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statuses;
    }
}
