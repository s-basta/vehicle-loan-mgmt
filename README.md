# GaadiFinance : Simplifying Your Vehicle Financing Journey

Welcome to GaadiFinance! Our platform is designed to simplify your vehicle financing journey, from loan application to management. Enjoy a streamlined experience with our key features:

## Key Features

### 1. Instant Loan Eligibility Check
Evaluate your eligibility for a vehicle loan quickly with a simple form that considers your income and existing EMI obligations.

### 2. Comprehensive Loan Application
Fill out a detailed application form that covers personal, employment, and vehicle details to receive a tailored loan offer.

### 3. Real-Time Vehicle Pricing
Access real-time ex-showroom and on-road prices for all vehicles in India, along with instant road tax calculations based on your location.

### 4. Admin Loan Management Dashboard
Admins can efficiently review, manage, and either approve or reject loan applications, and track active loans with ease.


#### Experience a hassle-free way to manage your vehicle loans with GaadiFinance.

## Demo Video 
![Demo Video](assets/vehicle_loan_mgmt_demo.mp4)

---

## Project Setup Instructions

## 1. Clone the Repository

Start by cloning the repository to your local machine.

## 2. Import into STS

Add the cloned project to Spring Tool Suite (STS):
- Open STS.
- Go to `File` > `Open Project from file system...`.
- Browse to the location of your cloned repository and finish the import process.

## 3. Install MySQL Command Line Tool

Ensure you have MySQL installed on your machine. If not, download and install it from the [MySQL official website](https://dev.mysql.com/downloads/).

## 4. Access MySQL Command Line Tool

Open the MySQL command line tool. Enter your MySQL password when prompted and switch to the appropriate database using:

```
USE mysql;
```

## 5. Setup Database

Copy the SQL commands from `vloan_db_setup.sql` and paste them into the MySQL command line tool to set up the database.
To add VehicleLoanDataset into your Database make sure to follow the following instructions in vloan_setup_db.sql : 
`/*Post this just add the correct filepath of the dataset in dao/vehicle/ into dao/vehicle/VehicleDatasetReader.java and run that file as a Java Application.*/`

## 6. Database Configuration

The database should now be set up and ready to use. 

## 7. Configure Database Credentials

By default, the database username is `root` and the password is `password`. If your database credentials differ, update them in the `Database.java` file located in the `config` folder.

## 8. Update Maven Configuration

- Go to `Window` > `Preferences` in STS.
- Tick the following checkboxes:
  - `Download Artifact Sources`
  - `Download Artifact Javadoc`
  - `Download repository index updates on startup`
  - `Automatically update Maven projects configuration`
- Click `Apply` and then `Save`.

## 9. Download Dependencies

Open `pom.xml` in your project and press `Ctrl + S` to trigger Maven to download the necessary dependencies.

## 10. Run the Project

Finally, run the project as a Spring Boot application:
- Right-click on the project.
- Select `Run As` > `Spring Boot App`.
