package dao.acc;

import java.util.List;

public class AcceptedLoanMain {
    public static void main(String[] args) {
        AcceptedLoansDAO dao = new AcceptedLoansDAOImpl();

        // Insert a new accepted loan
        AcceptedLoan newLoan = new AcceptedLoan(0, 0, 0, 0, 0, 0, null, null);
//        newLoan.setApplicationID(1);
//        newLoan.setUserID(2);
//        newLoan.setEmiAmount(5000.0);
//        newLoan.setTotalEMIs(12);
//        newLoan.setPaidEMIs(3);
//        newLoan.setLoanStartDate(new java.util.Date());
//        newLoan.setLoanEndDate(new java.util.Date()); // Set to an appropriate end date
//        dao.insertAcceptedLoan(newLoan);

        // Update an existing loan
//        newLoan.setPaidEMIs(4);
//        dao.updateAcceptedLoan(newLoan);
//
//        // Fetch a loan by ID
//        AcceptedLoan fetchedLoan = dao.getAcceptedLoanById(1);
//        System.out.println("Fetched Loan: " + fetchedLoan);
//
//        // Fetch all loans
        List<AcceptedLoan> loans = dao.getAllAcceptedLoans();
        for (AcceptedLoan loan : loans) {
            System.out.println("Loan: " + loan.toString());
        }
//
//        // Delete a loan
//        dao.deleteAcceptedLoan(1);
    }
}
