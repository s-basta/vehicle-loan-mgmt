package dao.loanApplicant;

import java.util.Date;

public class LoanApplicant {
    private int applicationID;
    private int userID;
    private String carMake;
    private String carModel;
    private double exShowroomPrice;
    private double onRoadPrice;
    private String typeOfEmployment;
    private double yearlySalary;
    private double existingEMI;
    private String mobileNumber;
    private String emailID;
    private String houseNumber;
    private String streetName;
    private String city;
    private String state;
    private String pinCode;
    private double loanAmount;
    private int loanTenure;
    private double rateOfInterest;
    private String aadharNumber;
    private String panCard;
    private String loanStatus;
    private Date applicationDate;

    public LoanApplicant(int applicationID, int userID, String carMake, String carModel, double exShowroomPrice, double onRoadPrice,
                         String typeOfEmployment, double yearlySalary, String mobileNumber, String emailID,
                         String houseNumber, String streetName, String city, String state, String pinCode,
                         double loanAmount, int loanTenure, double rateOfInterest, String aadharNumber, String panCard) {
    	this.applicationID = applicationID;
        this.userID = userID;
        this.carMake = carMake;
        this.carModel = carModel;
        this.exShowroomPrice = exShowroomPrice;
        this.onRoadPrice = onRoadPrice;
        this.typeOfEmployment = typeOfEmployment;
        this.yearlySalary = yearlySalary;
        this.existingEMI = 0.0;
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
        this.aadharNumber = aadharNumber;
        this.panCard = panCard;
        this.loanStatus = "Pending";
        this.applicationDate = new Date();
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

    public double getExShowroomPrice() {
        return exShowroomPrice;
    }

    public void setExShowroomPrice(double exShowroomPrice) {
        this.exShowroomPrice = exShowroomPrice;
    }

    public double getOnRoadPrice() {
        return onRoadPrice;
    }

    public void setOnRoadPrice(double onRoadPrice) {
        this.onRoadPrice = onRoadPrice;
    }

    public String getTypeOfEmployment() {
        return typeOfEmployment;
    }

    public void setTypeOfEmployment(String typeOfEmployment) {
        this.typeOfEmployment = typeOfEmployment;
    }

    public double getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public double getExistingEMI() {
        return existingEMI;
    }

    public void setExistingEMI(double existingEMI) {
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

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getLoanTenure() {
        return loanTenure;
    }

    public void setLoanTenure(int loanTenure) {
        this.loanTenure = loanTenure;
    }

    public double getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(double rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
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

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }
}
