package com.vehicleloan.dao.acceptedLoan;

import java.util.List;

public interface AcceptedLoanDAO {
	List<AcceptedLoan> getAll(Integer userId);

	AcceptedLoan get(Integer applicationId);

	boolean create(AcceptedLoan acceptedLoan);

	boolean update(AcceptedLoan acceptedLoan);

	boolean delete(Integer applicationId);
}
