package dao.acc;

import java.util.Date;

public class AcceptedLoan {
    private int loanID;
    private int applicationID;
    private int userID;
    private double emiAmount;
    private int totalEMIs;
    private int paidEMIs;
    private Date loanStartDate;
    private Date loanEndDate;

    public AcceptedLoan(int loanID, int applicationID, int userID, double emiAmount, int totalEMIs, int paidEMIs,
			Date loanStartDate, Date loanEndDate) {
		super();
		this.loanID = loanID;
		this.applicationID = applicationID;
		this.userID = userID;
		this.emiAmount = emiAmount;
		this.totalEMIs = totalEMIs;
		this.paidEMIs = paidEMIs;
		this.loanStartDate = loanStartDate;
		this.loanEndDate = loanEndDate;
	}

	// Getters and Setters
    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(double emiAmount) {
        this.emiAmount = emiAmount;
    }

    public int getTotalEMIs() {
        return totalEMIs;
    }

    public void setTotalEMIs(int totalEMIs) {
        this.totalEMIs = totalEMIs;
    }

    public int getPaidEMIs() {
        return paidEMIs;
    }

    public void setPaidEMIs(int paidEMIs) {
        this.paidEMIs = paidEMIs;
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

	@Override
	public String toString() {
		return "AcceptedLoan [loanID=" + loanID + ", applicationID=" + applicationID + ", userID=" + userID
				+ ", emiAmount=" + emiAmount + ", totalEMIs=" + totalEMIs + ", paidEMIs=" + paidEMIs
				+ ", loanStartDate=" + loanStartDate + ", loanEndDate=" + loanEndDate + "]";
	}
}
