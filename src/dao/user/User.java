package dao.user;

import java.time.LocalDate;
import constants.*;

public class User {
	private Integer userId;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private Gender gender;
	private String username;
	private String email;
	private String mobile;
	private String password;
	private Boolean isAdmin;
	private TypeOfEmployment typeOfEmployment;
	private Double salary;
	private String panCardNumber;
	private String aadharNumber;
	
	public User(Integer userId, String firstName, String lastName, LocalDate dateOfBirth, Gender gender,
			String username, String email, String mobile, String password, Boolean isAdmin,
			TypeOfEmployment typeOfEmployment, Double salary , String panCardNumber , String aadharNumber) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.isAdmin = isAdmin;
		this.typeOfEmployment = typeOfEmployment;
		this.salary = salary;
		this.panCardNumber = panCardNumber;
		this.aadharNumber = aadharNumber;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public TypeOfEmployment getTypeOfEmployment() {
		return typeOfEmployment;
	}

	public void setTypeOfEmployment(TypeOfEmployment typeOfEmployment) {
		this.typeOfEmployment = typeOfEmployment;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getPanCardNumber() {
		return panCardNumber;
	}

	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", username=" + username + ", email=" + email + ", mobile="
				+ mobile + ", password=" + password + ", isAdmin=" + isAdmin + ", typeOfEmployment=" + typeOfEmployment
				+ ", salary=" + salary + ", panCardNumber=" + panCardNumber + ", aadharNumber=" + aadharNumber + "]";
	}
}
