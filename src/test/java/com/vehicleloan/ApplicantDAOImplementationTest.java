package com.vehicleloan;

import com.vehicleloan.constant.LoanStatus;
import com.vehicleloan.constant.TypeOfEmployment;
import com.vehicleloan.dao.applicant.Applicant;
import com.vehicleloan.dao.applicant.ApplicantDAO;
import com.vehicleloan.dao.applicant.ApplicantDAOImplementation;
import com.vehicleloan.dao.user.UserDAO;
import com.vehicleloan.dao.user.UserDAOImplementation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicantDAOImplementationTest {

    private ApplicantDAO applicantDAO;
    private UserDAO userDAO;
    private Applicant testApplicant;
    private Integer applicationID;

    @BeforeEach
    public void init() {
        userDAO = new UserDAOImplementation();
        applicantDAO = new ApplicantDAOImplementation(userDAO);

        testApplicant = new Applicant(
                1,
                3,
                "Toyota",
                "SUV",
                30000.0,
                32000.0,
                TypeOfEmployment.SALARIED,
                50000.0,
                2000.0,
                "1234567890",
                "test@example.com",
                "10",
                "Main Street",
                "City",
                "State",
                "123456",
                25000.0,
                12,
                7.5,
                LoanStatus.PENDING,
                new Date(System.currentTimeMillis()),
                "ABCDE1234F",
                "1234-5678-9012");

        applicationID = applicantDAO.create(testApplicant);
    }

    @AfterEach
    public void over() {
        // Cleanup code if needed, e.g., delete the applicant from the database
        if (applicationID != null) {
            applicantDAO.delete(applicationID);
        }
    }

    @Test
    public void testCreateApplicant() {
        // Verify applicant creation and retrieval
        assertNotNull(applicationID);
        Applicant retrievedApplicant = applicantDAO.get(applicationID);
        assertNotNull(retrievedApplicant);
        assertEquals(testApplicant.getVehicleMake(), retrievedApplicant.getVehicleMake());
    }

    @Test
    public void testGetApplicantById() {
        // Verify applicant retrieval by ID
        Applicant retrievedApplicant = applicantDAO.get(applicationID);
        assertNotNull(retrievedApplicant);
        assertEquals(testApplicant.getVehicleMake(), retrievedApplicant.getVehicleMake());
    }

    @Test
    public void testGetApplicantsByLoanStatus() {
        // Verify retrieval of applicants by loan status
        List<Applicant> applicants = applicantDAO.get(LoanStatus.PENDING, null);
        assertNotNull(applicants);
        assertTrue(applicants.size() > 0);
        for (Applicant applicant : applicants) {
            System.out.println(applicant);
        }
    }

    @Test
    public void testUpdateApplicant() {
        // Update the applicant's details
        Applicant updatedApplicant = new Applicant(applicationID, 3, "Honda", "Sedan", null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null);

        boolean isUpdated = applicantDAO.update(updatedApplicant);
        assertTrue(isUpdated);

        Applicant retrievedApplicant = applicantDAO.get(applicationID);
        assertNotNull(retrievedApplicant);
        assertEquals(updatedApplicant.getVehicleMake(), retrievedApplicant.getVehicleMake());
        assertEquals(updatedApplicant.getVehicleType(), retrievedApplicant.getVehicleType());
    }

    @Test
    public void testDeleteApplicant() {
        // Verify applicant deletion
        boolean isDeleted = applicantDAO.delete(applicationID);
        assertTrue(isDeleted);
        Applicant retrievedApplicant = applicantDAO.get(applicationID);
        assertNull(retrievedApplicant);
    }
}
