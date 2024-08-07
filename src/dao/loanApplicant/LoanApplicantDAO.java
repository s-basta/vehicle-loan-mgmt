package dao.loanApplicant;


import java.util.List;

public interface LoanApplicantDAO {
    boolean create(LoanApplicant loanApplicant);
    LoanApplicant getById(int applicationID);
    List<LoanApplicant> getAll();
    boolean update(LoanApplicant loanApplicant);
    boolean deleteById(int applicationID);
}
