//package com.vehicleloan;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.vehicleloan.constant.LoanStatus;
//import com.vehicleloan.constant.PaymentStatus;
//import com.vehicleloan.constant.TypeOfEmployment;
//import com.vehicleloan.dao.acceptedLoan.AcceptedLoan;
//import com.vehicleloan.dao.acceptedLoan.AcceptedLoanDAO;
//import com.vehicleloan.dao.applicant.Applicant;
//import com.vehicleloan.dao.applicant.ApplicantDAOImplementation;
//import com.vehicleloan.dao.emiStatus.EMIStatus;
//import com.vehicleloan.dao.emiStatus.EMIStatusDAO;
//import com.vehicleloan.dao.user.User;
//import com.vehicleloan.dao.user.UserDAO;
//import com.vehicleloan.utils.DateUtils;
//import com.vehicleloan.utils.EMIUtils;
//
//class ApplicantDAOImplementationTest {
//
//    @Mock
//    private Connection conn;
//    
//    @Mock
//    private PreparedStatement pst;
//    
//    @Mock
//    private ResultSet resultSet;
//    
//    @Mock
//    private UserDAO userDAO;
//    
//    @Mock
//    private AcceptedLoanDAO acceptedLoanDAO;
//    
//    @Mock
//    private EMIStatusDAO emiStatusDAO;
//    
//    @InjectMocks
//    private ApplicantDAOImplementation applicantDAO;
//
//    @BeforeEach
//    void setUp() throws SQLException {
//        MockitoAnnotations.openMocks(this);
//        when(conn.prepareStatement(anyString())).thenReturn(pst);
//    }
//
//    @Test
//    void testCreateApplicant() throws SQLException {
//        // Setup
//        Applicant applicant = new Applicant(
//            12, 1, "Toyota", "SUV", 1000000.0, 1200000.0, 
//            TypeOfEmployment.SALARIED, 500000.0, 0.0, 
//            "9999999999", "email@example.com", "123", 
//            "Main St", "City", "State", "123456", 
//            800000.0, 60, 7.5, LoanStatus.PENDING, 
//            new java.util.Date(), "PAN123", "AADHAR123"
//        );
//
//        when(pst.executeUpdate()).thenReturn(1);
//
//        // Act
//        boolean result = applicantDAO.create(applicant);
//
//        // Assert
//        assertTrue(result);
//        verify(pst, times(1)).executeUpdate();
//    }
//
//    @Test
//    void testGetApplicantById() throws SQLException {
//        // Setup
//        when(pst.executeQuery()).thenReturn(resultSet);
//        when(resultSet.next()).thenReturn(true);
//
//        when(resultSet.getInt("applicationID")).thenReturn(1);
//        when(resultSet.getInt("userID")).thenReturn(1);
//        when(resultSet.getString("vehicleMake")).thenReturn("Toyota");
//        when(resultSet.getString("vehicleType")).thenReturn("SUV");
//        when(resultSet.getDouble("exShowroomPrice")).thenReturn(1000000.0);
//        when(resultSet.getDouble("onRoadPrice")).thenReturn(1200000.0);
//        when(resultSet.getString("typeOfEmployment")).thenReturn("SALARIED");
//        when(resultSet.getDouble("yearlySalary")).thenReturn(500000.0);
//        when(resultSet.getDouble("existingEMI")).thenReturn(0.0);
//        when(resultSet.getString("mobileNumber")).thenReturn("9999999999");
//        when(resultSet.getString("emailID")).thenReturn("email@example.com");
//        when(resultSet.getString("houseNumber")).thenReturn("123");
//        when(resultSet.getString("streetName")).thenReturn("Main St");
//        when(resultSet.getString("city")).thenReturn("City");
//        when(resultSet.getString("state")).thenReturn("State");
//        when(resultSet.getString("pinCode")).thenReturn("123456");
//        when(resultSet.getDouble("loanAmount")).thenReturn(800000.0);
//        when(resultSet.getInt("loanTenure")).thenReturn(60);
//        when(resultSet.getDouble("rateOfInterest")).thenReturn(7.5);
//        when(resultSet.getString("loanStatus")).thenReturn("PENDING");
//        when(resultSet.getDate("applicationDate")).thenReturn(new Date(System.currentTimeMillis()));
//
//        // Mock the UserDAO call
//        User mockUser = mock(User.class);
//        when(mockUser.getPanCardNumber()).thenReturn("PAN123");
//        when(mockUser.getAadharNumber()).thenReturn("AADHAR123");
//        when(userDAO.get(anyInt())).thenReturn(mockUser);
//
//        // Act
//        Applicant result = applicantDAO.get(1);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals("Toyota", result.getVehicleMake());
//        verify(pst, times(1)).executeQuery();
//    }
//
//    @Test
//    void testGetApplicantsByStatusAndUserId() throws SQLException {
//        // Setup
//        when(pst.executeQuery()).thenReturn(resultSet);
//        when(resultSet.next()).thenReturn(true, false); // Simulate one result
//
//        when(resultSet.getInt("applicationID")).thenReturn(1);
//        when(resultSet.getInt("userID")).thenReturn(1);
//        when(resultSet.getString("vehicleMake")).thenReturn("Toyota");
//        when(resultSet.getString("vehicleType")).thenReturn("SUV");
//        when(resultSet.getDouble("exShowroomPrice")).thenReturn(1000000.0);
//        when(resultSet.getDouble("onRoadPrice")).thenReturn(1200000.0);
//        when(resultSet.getString("typeOfEmployment")).thenReturn("SALARIED");
//        when(resultSet.getDouble("yearlySalary")).thenReturn(500000.0);
//        when(resultSet.getDouble("existingEMI")).thenReturn(0.0);
//        when(resultSet.getString("mobileNumber")).thenReturn("9999999999");
//        when(resultSet.getString("emailID")).thenReturn("email@example.com");
//        when(resultSet.getString("houseNumber")).thenReturn("123");
//        when(resultSet.getString("streetName")).thenReturn("Main St");
//        when(resultSet.getString("city")).thenReturn("City");
//        when(resultSet.getString("state")).thenReturn("State");
//        when(resultSet.getString("pinCode")).thenReturn("123456");
//        when(resultSet.getDouble("loanAmount")).thenReturn(800000.0);
//        when(resultSet.getInt("loanTenure")).thenReturn(60);
//        when(resultSet.getDouble("rateOfInterest")).thenReturn(7.5);
//        when(resultSet.getString("loanStatus")).thenReturn("PENDING");
//        when(resultSet.getDate("applicationDate")).thenReturn(new Date(System.currentTimeMillis()));
//
//        // Mock the UserDAO call
//        User mockUser = mock(User.class);
//        when(mockUser.getPanCardNumber()).thenReturn("PAN123");
//        when(mockUser.getAadharNumber()).thenReturn("AADHAR123");
//        when(userDAO.get(anyInt())).thenReturn(mockUser);
//
//        // Act
//        List<Applicant> applicants = applicantDAO.get(LoanStatus.PENDING, 1);
//
//        // Assert
//        assertNotNull(applicants);
//        assertEquals(1, applicants.size());
//        assertEquals("Toyota", applicants.get(0).getVehicleMake());
//        verify(pst, times(1)).executeQuery();
//    }

//
//    @Test
//    void testUpdateApplicant() throws SQLException {
//        // Setup
//        Applicant applicant = new Applicant(
//            1, 1, "Toyota", "SUV", 1000000.0, 1200000.0, 
//            TypeOfEmployment.SALARIED, 500000.0, 0.0, 
//            "9999999999", "email@example.com", "123", 
//            "Main St", "City", "State", "123456", 
//            800000.0, 60, 7.5, LoanStatus.APPROVED, 
//            new java.util.Date(), "PAN123", "AADHAR123"
//        );
//
//        when(pst.executeUpdate()).thenReturn(1);
//        when(acceptedLoanDAO.get(anyInt())).thenReturn(null);
//
//        // Act
//        boolean result = applicantDAO.update(applicant);
//
//        // Assert
//        assertTrue(result);
//        verify(pst, times(1)).executeUpdate();
//        verify(acceptedLoanDAO, times(1)).create(any(AcceptedLoan.class));
//        verify(emiStatusDAO, times(1)).deleteByApplicationId(anyInt());
//        verify(emiStatusDAO, times(applicant.getLoanTenure())).create(any(EMIStatus.class));
//    }
//
//    @Test
//    void testDeleteApplicant() throws SQLException {
//        // Setup
//        when(pst.executeUpdate()).thenReturn(1);
//
//        // Act
//        boolean result = applicantDAO.delete(1);
//
//        // Assert
//        assertTrue(result);
//        verify(pst, times(1)).executeUpdate();
//    }
//}
