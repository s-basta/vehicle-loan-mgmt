package com.vehicleloan;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vehicleloan.dao.acceptedLoan.AcceptedLoan;
import com.vehicleloan.dao.acceptedLoan.AcceptedLoanDAOImplementation;

public class AcceptedLoanDAOImplementationTest {

    @InjectMocks
    private AcceptedLoanDAOImplementation acceptedLoanDAO;

    @Mock
    private Connection conn;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(conn.prepareStatement(any(String.class))).thenReturn(preparedStatement);
    }

    @Test
    public void testGet() throws SQLException {
        AcceptedLoan expectedLoan = new AcceptedLoan(1, 1500.0, 24, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L * 365));
        
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("applicationID")).thenReturn(expectedLoan.getApplicationID());
        when(resultSet.getDouble("emiAmount")).thenReturn(expectedLoan.getEmiAmount());
        when(resultSet.getInt("totalEMIs")).thenReturn(expectedLoan.getTotalEMIs());
        when(resultSet.getDate("loanStartDate")).thenReturn(new Date(expectedLoan.getLoanStartDate().getTime()));
        when(resultSet.getDate("loanEndDate")).thenReturn(new Date(expectedLoan.getLoanEndDate().getTime()));

        AcceptedLoan actualLoan = acceptedLoanDAO.get(1);
        assertEquals(expectedLoan.getApplicationID(), actualLoan.getApplicationID());
        assertEquals(expectedLoan.getEmiAmount(), actualLoan.getEmiAmount());
        assertEquals(expectedLoan.getTotalEMIs(), actualLoan.getTotalEMIs());
        assertEquals(expectedLoan.getLoanStartDate(), actualLoan.getLoanStartDate());
        assertEquals(expectedLoan.getLoanEndDate(), actualLoan.getLoanEndDate());
    }

    @Test
    public void testGetAll() throws SQLException {
        AcceptedLoan loan1 = new AcceptedLoan(1, 1500.0, 24, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L * 365));
        AcceptedLoan loan2 = new AcceptedLoan(2, 1800.0, 36, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L * 365 * 2));
        
        List<AcceptedLoan> expectedLoans = Arrays.asList(loan1, loan2);

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("applicationID")).thenReturn(loan1.getApplicationID(), loan2.getApplicationID());
        when(resultSet.getDouble("emiAmount")).thenReturn(loan1.getEmiAmount(), loan2.getEmiAmount());
        when(resultSet.getInt("totalEMIs")).thenReturn(loan1.getTotalEMIs(), loan2.getTotalEMIs());
        when(resultSet.getDate("loanStartDate")).thenReturn(new Date(loan1.getLoanStartDate().getTime()), new Date(loan2.getLoanStartDate().getTime()));
        when(resultSet.getDate("loanEndDate")).thenReturn(new Date(loan1.getLoanEndDate().getTime()), new Date(loan2.getLoanEndDate().getTime()));

        List<AcceptedLoan> actualLoans = acceptedLoanDAO.getAll(null);
        assertEquals(expectedLoans.size(), actualLoans.size());
        for (int i = 0; i < expectedLoans.size(); i++) {
            AcceptedLoan expected = expectedLoans.get(i);
            AcceptedLoan actual = actualLoans.get(i);
            assertEquals(expected.getApplicationID(), actual.getApplicationID());
            assertEquals(expected.getEmiAmount(), actual.getEmiAmount());
            assertEquals(expected.getTotalEMIs(), actual.getTotalEMIs());
            assertEquals(expected.getLoanStartDate(), actual.getLoanStartDate());
            assertEquals(expected.getLoanEndDate(), actual.getLoanEndDate());
        }
    }

    @Test
    public void testCreate() throws SQLException {
        AcceptedLoan newLoan = new AcceptedLoan(1, 1500.0, 24, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L * 365));
        
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = acceptedLoanDAO.create(newLoan);
        assertTrue(result);
    }

    @Test
    public void testUpdate() throws SQLException {
        AcceptedLoan updatedLoan = new AcceptedLoan(1, 1600.0, 25, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L * 365));
        
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = acceptedLoanDAO.update(updatedLoan);
        assertTrue(result);
    }

    @Test
    public void testDelete() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = acceptedLoanDAO.delete(1);
        assertTrue(result);
    }
}
