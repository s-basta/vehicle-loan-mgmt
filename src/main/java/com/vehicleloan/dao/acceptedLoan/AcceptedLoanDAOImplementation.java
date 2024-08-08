package com.vehicleloan.dao.acceptedLoan;

import java.util.List;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.vehicleloan.config.Database;

@Service
public class AcceptedLoanDAOImplementation implements AcceptedLoanDAO {

	private Connection conn;

	public AcceptedLoanDAOImplementation() {
		conn = Database.getConnection();
	}

	private AcceptedLoan resultSetToAcceptedLoanConvertor(ResultSet resultSet) throws SQLException {
		return new AcceptedLoan(resultSet.getInt("applicationID"), resultSet.getDouble("emiAmount"),
				resultSet.getInt("totalEMIs"), resultSet.getDate("loanStartDate"), resultSet.getDate("loanEndDate"));
	}

	@Override
	public AcceptedLoan get(Integer applicationId) {
		AcceptedLoan acceptedLoan = null;
		String sql = "SELECT * FROM vloanacceptedloan WHERE applicationID = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, applicationId);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					acceptedLoan = resultSetToAcceptedLoanConvertor(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acceptedLoan;
	}

	@Override
	public List<AcceptedLoan> getAll(Integer userId) {
		List<AcceptedLoan> acceptedLoans = new ArrayList<>();
		String sql = "SELECT * FROM vloanacceptedloan";

		if (userId != null)
			sql = "SELECT vloanacceptedloan.* FROM vloanacceptedloan JOIN vloanapplicant ON vloanacceptedloan.applicationID = vloanapplicant.applicationID WHERE vloanapplicant.userId = ?";

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			if (userId != null)
				pst.setInt(1, userId);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				acceptedLoans.add(resultSetToAcceptedLoanConvertor(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acceptedLoans;
	}

	@Override
	public boolean create(AcceptedLoan acceptedLoan) {
		String sql = "INSERT INTO vloanacceptedloan (applicationID, emiAmount, totalEMIs, loanStartDate, loanEndDate) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, acceptedLoan.getApplicationID());
			pst.setDouble(2, acceptedLoan.getEmiAmount());
			pst.setInt(3, acceptedLoan.getTotalEMIs());
			pst.setDate(4, new Date(acceptedLoan.getLoanStartDate().getTime()));
			pst.setDate(5, new Date(acceptedLoan.getLoanEndDate().getTime()));
			int rows = pst.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(AcceptedLoan acceptedLoan) {
		if (acceptedLoan == null || acceptedLoan.getApplicationID() == null) {
			throw new IllegalArgumentException("AcceptedLoan and ApplicationID must not be null");
		}

		List<String> setClauses = new ArrayList<>();
		List<Object> parameters = new ArrayList<>();

		if (acceptedLoan.getEmiAmount() != null) {
			setClauses.add("emiAmount = ? ");
			parameters.add(acceptedLoan.getEmiAmount());
		}
		if (acceptedLoan.getTotalEMIs() != null) {
			setClauses.add("totalEMIs = ? ");
			parameters.add(acceptedLoan.getTotalEMIs());
		}
		if (acceptedLoan.getLoanStartDate() != null) {
			setClauses.add("loanStartDate = ? ");
			parameters.add(new Date(acceptedLoan.getLoanStartDate().getTime()));
		}
		if (acceptedLoan.getLoanEndDate() != null) {
			setClauses.add("loanEndDate = ? ");
			parameters.add(new Date(acceptedLoan.getLoanEndDate().getTime()));
		}

		if (setClauses.isEmpty()) {
			throw new IllegalArgumentException("No fields to update");
		}

		String sql = "UPDATE vloanacceptedloan SET " + String.join(", ", setClauses) + " WHERE applicationID = ?";
		parameters.add(acceptedLoan.getApplicationID());

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			for (int i = 0; i < parameters.size(); i++) {
				pst.setObject(i + 1, parameters.get(i));
			}

			int rows = pst.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public boolean delete(Integer applicationId) {
		if (applicationId == null) {
			throw new IllegalArgumentException("ApplicationID must not be null");
		}

		String sql = "DELETE FROM vloanacceptedloan WHERE applicationID = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, applicationId);
			int rows = pst.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
