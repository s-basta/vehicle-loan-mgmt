package dao.status;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import config.Database;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class LoanStatusDAOImplTest {
    private Connection conn;
    private LoanStatusDAOImpl loanStatusDAO;
    private Connection testConn;
    private Savepoint savepoint;

    @Before
    public void setUp() throws Exception {
        conn = Database.getConnection();
        testConn = conn;
        testConn.setAutoCommit(false);
        savepoint = testConn.setSavepoint();
        loanStatusDAO = new LoanStatusDAOImpl() {
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
//    public void testCreateLoanStatus() throws SQLException {
//        LoanStatus loanStatus = new LoanStatus(212, 7, 1, "Unpaid", new Date());
//        loanStatusDAO.insertLoanStatus(loanStatus);
//        LoanStatus retrievedStatus = loanStatusDAO.getLoanStatusById(212);
//        assertNotNull(retrievedStatus);
//        assertEquals(loanStatus.getStatusID(), retrievedStatus.getStatusID());
//    }

//    @Test
//    public void testGetLoanStatusById() throws SQLException {
//        LoanStatus loanStatus = new LoanStatus(11, 10, 1, "Paid", new Date());
//        loanStatusDAO.insertLoanStatus(loanStatus);
//        LoanStatus retrievedStatus = loanStatusDAO.getLoanStatusById(10);
//        assertNotNull(retrievedStatus);
//        assertEquals(loanStatus.getPaymentStatus(), retrievedStatus.getPaymentStatus());
//    }
//
//    @Test
//    public void testGetAllLoanStatuses() throws SQLException {
//        LoanStatus loanStatus1 = new LoanStatus(1, 1, 1, "Pending", new Date());
//        LoanStatus loanStatus2 = new LoanStatus(2, 2, 2, "Approved", new Date());
//        loanStatusDAO.insertLoanStatus(loanStatus1);
//        loanStatusDAO.insertLoanStatus(loanStatus2);
//        List<LoanStatus> statuses = loanStatusDAO.getAllLoanStatuses();
//        assertNotNull(statuses);
//        assertEquals(2, statuses.size());
//    }
//
//    @Test
//    public void testUpdateLoanStatus() throws SQLException {
//        LoanStatus loanStatus = new LoanStatus(1, 1, 1, "Pending", new Date());
//        loanStatusDAO.insertLoanStatus(loanStatus);
//        loanStatus.setPaymentStatus("Approved");
//        loanStatusDAO.updateLoanStatus(loanStatus);
//        LoanStatus updatedStatus = loanStatusDAO.getLoanStatusById(1);
//        assertNotNull(updatedStatus);
//        assertEquals("Approved", updatedStatus.getPaymentStatus());
//    }
//
//    @Test
//    public void testDeleteLoanStatus() throws SQLException {
//        LoanStatus loanStatus = new LoanStatus(1, 1, 1, "Pending", new Date());
//        loanStatusDAO.insertLoanStatus(loanStatus);
//        boolean result = loanStatusDAO.deleteLoanStatus(1);
//        assertTrue(result);
//        LoanStatus deletedStatus = loanStatusDAO.getLoanStatusById(1);
//        assertNull(deletedStatus);
//    }
}
