package com.vehicleloan;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vehicleloan.constant.PaymentStatus;
import com.vehicleloan.dao.emiStatus.EMIStatus;
import com.vehicleloan.dao.emiStatus.EMIStatusDAOImplementation;

class EMIStatusDAOImplementationTest {

    @Mock
    private Connection conn;
    
    @Mock
    private PreparedStatement pst;
    
    @Mock
    private ResultSet resultSet;
    
    @InjectMocks
    private EMIStatusDAOImplementation emiStatusDAO;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(conn.prepareStatement(anyString())).thenReturn(pst);
    }

//    @Test
//    void testGetByUserId() throws SQLException {
//        // Setup
//        when(pst.executeQuery()).thenReturn(resultSet);
//        when(resultSet.next()).thenReturn(true, false); // Simulate one result
//
//        when(resultSet.getInt("statusId")).thenReturn(1);
//        when(resultSet.getInt("applicationId")).thenReturn(1);
//        when(resultSet.getString("paymentStatus")).thenReturn("PAID");
//        when(resultSet.getDate("scheduledPaymentDate")).thenReturn(new Date(System.currentTimeMillis()));
//        when(resultSet.getDate("actualPaymentDate")).thenReturn(new Date(System.currentTimeMillis()));
//
//        // Act
//        List<EMIStatus> emiStatuses = emiStatusDAO.getByUserId(1);
//
//        // Assert
//        assertNotNull(emiStatuses);
//        assertEquals(1, emiStatuses.size());
//        assertEquals(PaymentStatus.PAID, emiStatuses.get(0).getPaymentStatus());
//        verify(pst, times(1)).executeQuery();
//    }
//
//    @Test
//    void testGetByApplicationId() throws SQLException {
//        // Setup
//        when(pst.executeQuery()).thenReturn(resultSet);
//        when(resultSet.next()).thenReturn(true, false); // Simulate one result
//
//        when(resultSet.getInt("statusId")).thenReturn(1);
//        when(resultSet.getInt("applicationId")).thenReturn(1);
//        when(resultSet.getString("paymentStatus")).thenReturn("UNPAID");
//        when(resultSet.getDate("scheduledPaymentDate")).thenReturn(new Date(System.currentTimeMillis()));
//        when(resultSet.getDate("actualPaymentDate")).thenReturn(null);
//
//        // Act
//        List<EMIStatus> emiStatuses = emiStatusDAO.get(1);
//
//        // Assert
//        assertNotNull(emiStatuses);
//        assertEquals(1, emiStatuses.size());
//        assertEquals(PaymentStatus.UNPAID, emiStatuses.get(0).getPaymentStatus());
//        verify(pst, times(1)).executeQuery();
//    }
//
//    @Test
//    void testCreateEMIStatus() throws SQLException {
//        // Setup
//        EMIStatus emiStatus = new EMIStatus(
//            1, 1, PaymentStatus.UNPAID, 
//            new java.util.Date(), null
//        );
//
//        when(pst.executeUpdate()).thenReturn(1);
//
//        // Act
//        boolean result = emiStatusDAO.create(emiStatus);
//
//        // Assert
//        assertTrue(result);
//        verify(pst, times(1)).executeUpdate();
//    }
//
//    @Test
//    void testUpdateEMIStatus() throws SQLException {
//        // Setup
//        EMIStatus emiStatus = new EMIStatus(
//            1, 1, PaymentStatus.PAID, 
//            new java.util.Date(), new java.util.Date()
//        );
//
//        when(pst.executeUpdate()).thenReturn(1);
//
//        // Act
//        boolean result = emiStatusDAO.update(emiStatus);
//
//        // Assert
//        assertTrue(result);
//        verify(pst, times(1)).executeUpdate();
//    }
//
//    @Test
//    void testDeleteEMIStatus() throws SQLException {
//        // Setup
//        when(pst.executeUpdate()).thenReturn(1);
//
//        // Act
//        boolean result = emiStatusDAO.delete(1);
//
//        // Assert
//        assertTrue(result);
//        verify(pst, times(1)).executeUpdate();
//    }
//
//    @Test
//    void testDeleteByApplicationId() throws SQLException {
//        // Setup
//        when(pst.executeUpdate()).thenReturn(1);
//
//        // Act
//        boolean result = emiStatusDAO.deleteByApplicationId(1);
//
//        // Assert
//        assertTrue(result);
//        verify(pst, times(1)).executeUpdate();
//    }
}
