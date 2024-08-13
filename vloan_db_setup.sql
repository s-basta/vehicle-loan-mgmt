DROP TABLE `vloanuser`;
DROP TABLE `vloanvehicle`;
DROP TABLE `vloanapplicant`;
DROP TABLE `vloanacceptedloan`;
DROP TABLE `vloanemistatus`;
	
CREATE TABLE `vloanuser` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(255) NOT NULL,
  `lastName` VARCHAR(255) NOT NULL,
  `dateOfBirth` DATE NOT NULL,
  `gender` ENUM('MALE', 'FEMALE', 'OTHER') NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `mobile` VARCHAR(20) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `isAdmin` TINYINT(1) NOT NULL,
  `typeOfEmployment` ENUM('SALARIED', 'SELF_EMPLOYED', 'PENSIONER', 'HOUSEWIFE', 'STUDENT') DEFAULT NULL,
  `salary` DECIMAL(15,2) DEFAULT NULL,
  `panCardNumber` VARCHAR(20) DEFAULT NULL,
  `aadharNumber` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
);

CREATE TABLE `vloanvehicle` (
  `VehicleID` INT NOT NULL AUTO_INCREMENT,
  `VehicleMake` VARCHAR(50) DEFAULT NULL,
  `VehicleType` VARCHAR(50) DEFAULT NULL,
  `Ex_Showroom_Price` DOUBLE DEFAULT NULL,
  `On_Road_Price` DOUBLE DEFAULT NULL,
  PRIMARY KEY (`VehicleID`)
);

/*Post this just add the correct filepath of the dataset in dao/vehicle/ into dao/vehicle/VehicleDatasetReader.java and run that file as a Java Application.*/

CREATE TABLE `vloanapplicant` (
  `applicationID` INT NOT NULL AUTO_INCREMENT,
  `userID` INT NOT NULL,
  `vehicleMake` VARCHAR(255) NOT NULL,
  `vehicleType` VARCHAR(255) NOT NULL,
  `exShowroomPrice` DECIMAL(10,2) NOT NULL,
  `onRoadPrice` DECIMAL(10,2) NOT NULL,
  `typeOfEmployment` VARCHAR(255) NOT NULL,
  `yearlySalary` DECIMAL(15,2) NOT NULL,
  `existingEMI` DECIMAL(15,2) NOT NULL,
  `mobileNumber` VARCHAR(15) NOT NULL,
  `emailID` VARCHAR(255) NOT NULL,
  `houseNumber` VARCHAR(50) NOT NULL,
  `streetName` VARCHAR(255) NOT NULL,
  `city` VARCHAR(100) NOT NULL,
  `state` VARCHAR(100) NOT NULL,
  `pinCode` VARCHAR(10) NOT NULL,
  `loanAmount` DECIMAL(15,2) NOT NULL,
  `loanTenure` INT NOT NULL,
  `rateOfInterest` DECIMAL(5,2) NOT NULL,
  `loanStatus` ENUM('PENDING', 'APPROVED', 'REJECTED') NOT NULL,
  `applicationDate` DATE NOT NULL,
  PRIMARY KEY (`applicationID`),
  FOREIGN KEY (`userID`) REFERENCES `vloanuser`(`userId`)
);

CREATE TABLE `vloanacceptedloan` (
  `applicationID` INT NOT NULL,
  `emiAmount` DECIMAL(10,2) DEFAULT NULL,
  `totalEMIs` INT DEFAULT NULL,
  `loanStartDate` DATE DEFAULT NULL,
  `loanEndDate` DATE DEFAULT NULL,
  PRIMARY KEY (`applicationID`),
  FOREIGN KEY (`applicationID`) REFERENCES `vloanapplicant`(`applicationID`)
);

CREATE TABLE `vloanemistatus` (
  `statusId` INT NOT NULL AUTO_INCREMENT,
  `applicationId` INT NOT NULL,
  `paymentStatus` ENUM('PENDING', 'COMPLETED') NOT NULL,
  `scheduledPaymentDate` DATE NOT NULL,
  `actualPaymentDate` DATE DEFAULT NULL,
  PRIMARY KEY (`statusId`),
  FOREIGN KEY (`applicationId`) REFERENCES `vloanacceptedloan`(`applicationID`)
);
