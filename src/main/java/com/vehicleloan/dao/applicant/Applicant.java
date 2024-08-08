package com.vehicleloan.dao.applicant;

import java.util.Date;

import com.vehicleloan.constant.LoanStatus;
import com.vehicleloan.constant.TypeOfEmployment;

public class Applicant {
	private Integer applicationID;
	private Integer userID;
	private String vehicleMake;
	private String vehicleType;
	private Double exShowroomPrice;
	private Double onRoadPrice;
	private TypeOfEmployment typeOfEmployment;
	private Double yearlySalary;
	private Double existingEMI;
	private String mobileNumber;
	private String emailID;
	private String houseNumber;
	private String streetName;
	private String city;
	private String state;
	private String pinCode;
	private Double loanAmount;
	private Integer loanTenure;
	private Double rateOfInterest;
	private LoanStatus loanStatus;
	private Date applicationDate;
	private String panNumber;
	private String aadharNumber;

	public Applicant(Integer applicationID, Integer userID, String vehicleMake, String VehicleType,
			Double exShowroomPrice, Double onRoadPrice, TypeOfEmployment typeOfEmployment, Double yearlySalary,
			Double existingEMI, String mobileNumber, String emailID, String houseNumber, String streetName, String city,
			String state, String pinCode, Double loanAmount, Integer loanTenure, Double rateOfInterest,
			LoanStatus loanStatus, Date applicationDate, String panNumber, String aadharNumber) {
		super();
		this.applicationID = applicationID;
		this.userID = userID;
		this.vehicleMake = vehicleMake;
		this.vehicleType = VehicleType;
		this.exShowroomPrice = exShowroomPrice;
		this.onRoadPrice = onRoadPrice;
		this.typeOfEmployment = typeOfEmployment;
		this.yearlySalary = yearlySalary;
		this.existingEMI = existingEMI;
		this.mobileNumber = mobileNumber;
		this.emailID = emailID;
		this.houseNumber = houseNumber;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.loanAmount = loanAmount;
		this.loanTenure = loanTenure;
		this.rateOfInterest = rateOfInterest;
		this.loanStatus = loanStatus;
		this.applicationDate = applicationDate;
		this.panNumber = panNumber;
		this.aadharNumber = aadharNumber;
	}

	public Integer getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(Integer applicationID) {
		this.applicationID = applicationID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getVehicleMake() {
		return vehicleMake;
	}

	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Double getExShowroomPrice() {
		return exShowroomPrice;
	}

	public void setExShowroomPrice(Double exShowroomPrice) {
		this.exShowroomPrice = exShowroomPrice;
	}

	public Double getOnRoadPrice() {
		return onRoadPrice;
	}

	public void setOnRoadPrice(Double onRoadPrice) {
		this.onRoadPrice = onRoadPrice;
	}

	public TypeOfEmployment getTypeOfEmployment() {
		return typeOfEmployment;
	}

	public void setTypeOfEmployment(TypeOfEmployment typeOfEmployment) {
		this.typeOfEmployment = typeOfEmployment;
	}

	public Double getYearlySalary() {
		return yearlySalary;
	}

	public void setYearlySalary(Double yearlySalary) {
		this.yearlySalary = yearlySalary;
	}

	public Double getExistingEMI() {
		return existingEMI;
	}

	public void setExistingEMI(Double existingEMI) {
		this.existingEMI = existingEMI;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Integer getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(Integer loanTenure) {
		this.loanTenure = loanTenure;
	}

	public Double getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(Double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public LoanStatus getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(LoanStatus loanStatus) {
		this.loanStatus = loanStatus;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

}
