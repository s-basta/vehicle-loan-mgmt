package dao.acc;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import config.Database;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class AcceptedLoanDAOImplementationTest {
    private Connection conn;
    private AcceptedLoansDAOImpl acceptedLoanDAO;
    private Connection testConn;
    private Savepoint savepoint;

    @Before
    public void setUp() throws Exception {
        conn = Database.getConnection();
        testConn = conn;
        testConn.setAutoCommit(false);
        savepoint = testConn.setSavepoint();
        acceptedLoanDAO = new AcceptedLoansDAOImpl() {
            @Override
            public Connection getConnection() {
                return testConn;
            }
        };
    }

    @After
    public void tearDown() throws Exception {
        if (testConn != null) {
            testConn.rollback(savepoint);
            testConn.close();
        }
    }

//    @Test
//    public void testCreateAcceptedLoan() throws SQLException {
//        AcceptedLoan acceptedLoan = new AcceptedLoan(7, 1, 2, 5000, 12, 3, new Date(), new Date());
//        acceptedLoanDAO.insertAcceptedLoan(acceptedLoan);
//        AcceptedLoan retrievedLoan = acceptedLoanDAO.getAcceptedLoanById(7);
//        assertNotNull(retrievedLoan);
//        assertEquals(acceptedLoan.getLoanID(), retrievedLoan.getLoanID());
//    }
//
//    @Test
//    public void testGetAcceptedLoanById() throws SQLException {
//        AcceptedLoan acceptedLoan = new AcceptedLoan(21, 1, 2, 5000, 12, 3, new Date(), new Date());
//        acceptedLoanDAO.insertAcceptedLoan(acceptedLoan);
//        AcceptedLoan retrievedLoan = acceptedLoanDAO.getAcceptedLoanById(21);
//        assertNotNull(retrievedLoan);
//        assertEquals(acceptedLoan.getApplicationID(), retrievedLoan.getApplicationID());
//    }

//    @Test
//    public void testGetAllAcceptedLoans() throws SQLException {
//        AcceptedLoan acceptedLoan1 = new AcceptedLoan(7, 1, 2, 5000, 12, 3, new Date(), new Date());
//        AcceptedLoan acceptedLoan2 = new AcceptedLoan(8, 2, 3, 6000, 10, 2, new Date(), new Date());
//        acceptedLoanDAO.insertAcceptedLoan(acceptedLoan1);
//        acceptedLoanDAO.insertAcceptedLoan(acceptedLoan2);
//        List<AcceptedLoan> loans = acceptedLoanDAO.getAllAcceptedLoans();
//        assertNotNull(loans);
//        assertEquals(18, loans.size());
//    }

//    @Test
//    public void testUpdateAcceptedLoan() throws SQLException {
//        AcceptedLoan acceptedLoan = new AcceptedLoan(7, 1, 2, 5000, 12, 3, new Date(), new Date());
//        acceptedLoanDAO.insertAcceptedLoan(acceptedLoan);
//        acceptedLoan.setEmiAmount(5500);
//        acceptedLoanDAO.updateAcceptedLoan(acceptedLoan);
//        AcceptedLoan updatedLoan = acceptedLoanDAO.getAcceptedLoanById(7);
//        assertNotNull(updatedLoan);
//        assertEquals(5500, updatedLoan.getEmiAmount(), 0);
//    }
//
//    @Test
//    public void testDeleteAcceptedLoan() throws SQLException {
//        AcceptedLoan acceptedLoan = new AcceptedLoan(7, 1, 2, 5000, 12, 3, new Date(), new Date());
//        acceptedLoanDAO.insertAcceptedLoan(acceptedLoan);
//        boolean result = acceptedLoanDAO.deleteAcceptedLoan(7);
//        assertTrue(result);
//        AcceptedLoan deletedLoan = acceptedLoanDAO.getAcceptedLoanById(7);
//        assertNull(deletedLoan);
//    }
}
