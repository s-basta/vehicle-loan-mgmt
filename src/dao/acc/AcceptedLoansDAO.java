package dao.acc;

import java.util.List;

public interface AcceptedLoansDAO {
    void insertAcceptedLoan(AcceptedLoan loan);
    void updateAcceptedLoan(AcceptedLoan loan);
    void deleteAcceptedLoan(int loanID);
    AcceptedLoan getAcceptedLoanById(int loanID);
    List<AcceptedLoan> getAllAcceptedLoans();
}
