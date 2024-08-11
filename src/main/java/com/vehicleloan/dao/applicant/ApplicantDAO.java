package com.vehicleloan.dao.applicant;

import java.util.List;

import com.vehicleloan.constant.LoanStatus;

public interface ApplicantDAO {
	Integer create(Applicant Applicant);
    Applicant get(int applicationID);
    List<Applicant> get(LoanStatus loanStatus , Integer userId);
    boolean update(Applicant Applicant);
    boolean delete(int applicationID);
}
