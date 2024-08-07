package dao.status;

import java.util.List;

public class LoanStatusMain {
    public static void main(String[] args) {
        LoanStatusDAO dao = new LoanStatusDAOImpl();

        // Insert a new loan status
        LoanStatus newStatus = new LoanStatus(0, 0, 0, null, null);
//        newStatus.setLoanID(1);
//        newStatus.setCurrentEMI(2);
//        newStatus.setPaymentStatus("Unpaid");
//        newStatus.setPaymentDate(new java.util.Date());
//        dao.insertLoanStatus(newStatus);

        // Update an existing loan status
//        newStatus.setPaymentStatus("Paid");
//        dao.updateLoanStatus(newStatus);
//
//        // Fetch a status by ID
//        LoanStatus fetchedStatus = dao.getLoanStatusById(1);
//        System.out.println("Fetched Status: " + fetchedStatus);
//
//        // Fetch all loan statuses
        List<LoanStatus> statuses = dao.getAllLoanStatuses();
        for (LoanStatus status : statuses) {
            System.out.println("Status: " + status);
        }
//
//        // Delete a loan status
//        dao.deleteLoanStatus(1);
    }
}
