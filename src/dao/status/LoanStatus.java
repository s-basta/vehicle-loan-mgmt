package dao.status;

import java.util.Date;

public class LoanStatus {
    private int statusID;
    private int loanID;
    private int currentEMI;
    private String paymentStatus; // 'Paid' or 'Unpaid'
    private java.util.Date paymentDate;

    public LoanStatus(int statusID, int loanID, int currentEMI, String paymentStatus, Date paymentDate) {
		super();
		this.statusID = statusID;
		this.loanID = loanID;
		this.currentEMI = currentEMI;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
	}

	// Getters and Setters
    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public int getCurrentEMI() {
        return currentEMI;
    }

    public void setCurrentEMI(int currentEMI) {
        this.currentEMI = currentEMI;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public java.util.Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(java.util.Date paymentDate) {
        this.paymentDate = paymentDate;
    }

	@Override
	public String toString() {
		return "LoanStatus [statusID=" + statusID + ", loanID=" + loanID + ", currentEMI=" + currentEMI
				+ ", paymentStatus=" + paymentStatus + ", paymentDate=" + paymentDate + "]";
	}
}
