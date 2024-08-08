package com.vehicleloan.dao.acceptedLoan;

import java.util.Date;

public class AcceptedLoan {
    private Integer applicationID;
    private Double emiAmount;
    private Integer totalEMIs;
    private Date loanStartDate;
    private Date loanEndDate;
    
	public AcceptedLoan(Integer applicationID, Double emiAmount, Integer totalEMIs,
			Date loanStartDate, Date loanEndDate) {
		super();
		this.applicationID = applicationID;
		this.emiAmount = emiAmount;
		this.totalEMIs = totalEMIs;
		this.loanStartDate = loanStartDate;
		this.loanEndDate = loanEndDate;
	}

	public Integer getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(Integer applicationID) {
		this.applicationID = applicationID;
	}

	public Double getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(Double emiAmount) {
		this.emiAmount = emiAmount;
	}

	public Integer getTotalEMIs() {
		return totalEMIs;
	}

	public void setTotalEMIs(Integer totalEMIs) {
		this.totalEMIs = totalEMIs;
	}

	public Date getLoanStartDate() {
		return loanStartDate;
	}

	public void setLoanStartDate(Date loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	public Date getLoanEndDate() {
		return loanEndDate;
	}

	public void setLoanEndDate(Date loanEndDate) {
		this.loanEndDate = loanEndDate;
	}
}
