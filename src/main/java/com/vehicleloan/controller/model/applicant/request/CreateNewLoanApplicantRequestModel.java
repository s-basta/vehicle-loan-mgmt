package com.vehicleloan.controller.model.applicant.request;

import java.util.Date;

import com.vehicleloan.constant.LoanStatus;

import jakarta.validation.constraints.NotNull;

public class CreateNewLoanApplicantRequestModel {
	@NotNull private Integer applicationID;
	@NotNull private Integer userID;
	@NotNull private String carMake;
	@NotNull private String carModel;
	@NotNull private Double exShowroomPrice;
	@NotNull private Double onRoadPrice;
	@NotNull private String typeOfEmployment;
	@NotNull private Double yearlySalary;
	@NotNull private Double existingEMIPerMonth;
	@NotNull private String mobileNumber;
	@NotNull private String emailID;
	@NotNull private String houseNumber;
	@NotNull private String streetName;
	@NotNull private String city;
	@NotNull private String state;
	@NotNull private String pinCode;
	@NotNull private Double loanAmount;
	@NotNull private Integer loanTenure;
	@NotNull private Double rateOfIntegererest;
	@NotNull private String aadharNumber;
	@NotNull private String panCard;
	@NotNull private LoanStatus loanStatus;
	@NotNull private Date applicationDate;
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
	public String getCarMake() {
		return carMake;
	}
	public void setCarMake(String carMake) {
		this.carMake = carMake;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
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
	public String getTypeOfEmployment() {
		return typeOfEmployment;
	}
	public void setTypeOfEmployment(String typeOfEmployment) {
		this.typeOfEmployment = typeOfEmployment;
	}
	public Double getYearlySalary() {
		return yearlySalary;
	}
	public void setYearlySalary(Double yearlySalary) {
		this.yearlySalary = yearlySalary;
	}
	public Double getExistingEMIPerMonth() {
		return existingEMIPerMonth;
	}
	public void setExistingEMIPerMonth(Double existingEMIPerMonth) {
		this.existingEMIPerMonth = existingEMIPerMonth;
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
	public Double getRateOfIntegererest() {
		return rateOfIntegererest;
	}
	public void setRateOfIntegererest(Double rateOfIntegererest) {
		this.rateOfIntegererest = rateOfIntegererest;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getPanCard() {
		return panCard;
	}
	public void setPanCard(String panCard) {
		this.panCard = panCard;
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
}
