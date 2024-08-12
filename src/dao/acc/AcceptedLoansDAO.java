package dao.acc;

import java.sql.Connection;
import java.util.List;

public interface AcceptedLoansDAO {
    void insertAcceptedLoan(AcceptedLoan loan);
    void updateAcceptedLoan(AcceptedLoan loan);
    boolean deleteAcceptedLoan(int loanID);
    AcceptedLoan getAcceptedLoanById(int loanID);
    List<AcceptedLoan> getAllAcceptedLoans();
	Connection getConnection();
}