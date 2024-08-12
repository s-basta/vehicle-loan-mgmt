package dao.status;

import java.util.List;

public interface LoanStatusDAO {
    void insertLoanStatus(LoanStatus status);
    void updateLoanStatus(LoanStatus status);
    boolean deleteLoanStatus(int statusID);
    LoanStatus getLoanStatusById(int statusID);
    List<LoanStatus> getAllLoanStatuses();
}
