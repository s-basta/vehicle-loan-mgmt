package com.vehicleloan.dao.emiStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vehicleloan.config.Database;
import com.vehicleloan.constant.PaymentStatus;

@Service
public class EMIStatusDAOImplementation implements EMIStatusDAO {
	private Connection conn;

	public EMIStatusDAOImplementation() {
		conn = Database.getConnection(); // Ensure you have a Database class for managing connections
	}

	@Override
	public List<EMIStatus> getByUserId(Integer userId) {
		List<EMIStatus> emiStatuses = new ArrayList<>();
		String sql = "SELECT vloanEMIStatus.* FROM vloanEMIStatus , vloanApplicant WHERE vloanEMIStatus.applicationId = vloanApplicant.applicationId AND userId = ? ORDER BY scheduledPaymentDate";

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				EMIStatus emiStatus = new EMIStatus(rs.getInt("statusId"), rs.getInt("applicationId"),
						PaymentStatus.valueOf(rs.getString("paymentStatus")), rs.getDate("scheduledPaymentDate"),
						rs.getDate("actualPaymentDate"));
				emiStatuses.add(emiStatus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return emiStatuses;
	}

	@Override
	public List<EMIStatus> get(Integer applicationId) {
		List<EMIStatus> emiStatuses = new ArrayList<>();
		String sql = "SELECT * FROM vloanEMIStatus WHERE applicationId = ?";

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, applicationId);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				EMIStatus emiStatus = new EMIStatus(rs.getInt("statusId"), rs.getInt("applicationId"),
						PaymentStatus.valueOf(rs.getString("paymentStatus")), rs.getDate("scheduledPaymentDate"),
						rs.getDate("actualPaymentDate"));
				emiStatuses.add(emiStatus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return emiStatuses;
	}

	@Override
	public boolean create(EMIStatus emiStatus) {
		String sql = "INSERT INTO vloanEMIStatus (applicationId, paymentStatus, scheduledPaymentDate) VALUES (?, ?, ?)";

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, emiStatus.getApplicationId());
			pst.setString(2, emiStatus.getPaymentStatus().name());
			pst.setDate(3, new Date(emiStatus.getScheduledPaymentDate().getTime()));

			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean update(EMIStatus emiStatus) {
		if (emiStatus == null || emiStatus.getStatusId() == null) {
			throw new IllegalArgumentException("EMIStatus and StatusId must not be null");
		}

		List<String> setClauses = new ArrayList<>();
		List<Object> parameters = new ArrayList<>();

		if (emiStatus.getApplicationId() != null) {
			setClauses.add("applicationId = ?");
			parameters.add(emiStatus.getApplicationId());
		}
		if (emiStatus.getPaymentStatus() != null) {
			setClauses.add("paymentStatus = ?");
			parameters.add(emiStatus.getPaymentStatus().name());
		}
		if (emiStatus.getScheduledPaymentDate() != null) {
			setClauses.add("scheduledPaymentDate = ?");
			parameters.add(new Date(emiStatus.getScheduledPaymentDate().getTime()));
		}
		if (emiStatus.getActualPaymentDate() != null) {
			setClauses.add("actualPaymentDate = ?");
			parameters.add(new Date(emiStatus.getActualPaymentDate().getTime()));
		}

		if (setClauses.isEmpty()) {
			throw new IllegalArgumentException("No fields to update");
		}

		String sql = "UPDATE vloanEMIStatus SET " + String.join(", ", setClauses) + " WHERE statusId = ?";
		parameters.add(emiStatus.getStatusId()); // Add the statusId for the WHERE clause

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			for (int i = 0; i < parameters.size(); i++) {
				pst.setObject(i + 1, parameters.get(i));
			}

			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delete(Integer statusId) {
		if (statusId == null) {
			throw new IllegalArgumentException("StatusId must not be null");
		}

		String sql = "DELETE FROM vloanEMIStatus WHERE statusId = ?";

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, statusId);

			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteByApplicationId(Integer applicationId) {
		if (applicationId == null) {
			throw new IllegalArgumentException("aaplicationId must not be null");
		}

		String sql = "DELETE FROM vloanEMIStatus WHERE applicationId = ?";

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, applicationId);

			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}
