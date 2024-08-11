package com.vehicleloan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vehicleloan.constant.LoanStatus;
import com.vehicleloan.constant.PaymentStatus;
import com.vehicleloan.dao.acceptedLoan.AcceptedLoan;
import com.vehicleloan.dao.acceptedLoan.AcceptedLoanDAO;
import com.vehicleloan.dao.applicant.Applicant;
import com.vehicleloan.dao.applicant.ApplicantDAO;
import com.vehicleloan.dao.emiStatus.EMIStatus;
import com.vehicleloan.dao.emiStatus.EMIStatusDAO;
import com.vehicleloan.utils.DateUtils;
import com.vehicleloan.utils.EMIUtils;

@RestController
@RequestMapping("applicant")
public class ApplicantController {
	private ApplicantDAO applicantDAO;
	private AcceptedLoanDAO acceptedLoanDAO;
	private EMIStatusDAO emiStatusDAO;

	public ApplicantController(ApplicantDAO applicantDAO , EMIStatusDAO emiStatusDAO , AcceptedLoanDAO acceptedLoanDAO) {
		this.applicantDAO = applicantDAO;
		this.acceptedLoanDAO = acceptedLoanDAO;
		this.emiStatusDAO = emiStatusDAO;
	}

	@GetMapping("/{applicantId}")
	public ResponseEntity<Applicant> getApplicantById(@PathVariable int applicantId) {
		Applicant applicant = applicantDAO.get(applicantId);

		if (applicant != null)
			return new ResponseEntity<Applicant>(applicant, HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping()
	public ResponseEntity<List<Applicant>> getApplicants(@RequestParam(required = false) Integer userId,
			@RequestParam(name = "status", required = false) LoanStatus loanStatus) {
		List<Applicant> applicants;

		applicants = applicantDAO.get(loanStatus, userId);

		return new ResponseEntity<List<Applicant>>(applicants, HttpStatus.OK);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> createApplicant(@RequestBody Applicant newApplicant) {
		Integer applicantId = applicantDAO.create(newApplicant);

		if (applicantId != null)
			return new ResponseEntity<>(HttpStatus.CREATED);
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@PutMapping()
	public ResponseEntity<?> updateApplicant(@RequestBody Applicant applicant) {
		boolean isUpdated = applicantDAO.update(applicant);

		if (isUpdated){
            if (applicant.getLoanStatus() == LoanStatus.APPROVED) {
                emiStatusDAO.deleteByApplicationId(applicant.getApplicationID());

                Applicant currentApplicant = applicantDAO.get(applicant.getApplicationID());
                
                Double emiAmount = EMIUtils.calculateEMI(currentApplicant.getLoanAmount(), currentApplicant.getRateOfInterest() / (double) 100, currentApplicant.getLoanTenure());
                
                AcceptedLoan acceptedLoan = acceptedLoanDAO.get(currentApplicant.getApplicationID());
                if(acceptedLoan == null)
                    acceptedLoanDAO.create(new AcceptedLoan(currentApplicant.getApplicationID(),
                            emiAmount,
                            currentApplicant.getLoanTenure(),
                            new java.util.Date(), DateUtils.addMonths(DateUtils.addMonths(new java.util.Date(), 1),
                                    currentApplicant.getLoanTenure() + 1)));
                else
                    acceptedLoanDAO.update(new AcceptedLoan(currentApplicant.getApplicationID(),
                    emiAmount,
                    currentApplicant.getLoanTenure(),
                    new java.util.Date(), DateUtils.addMonths(DateUtils.addMonths(new java.util.Date(), 1),
                            currentApplicant.getLoanTenure() + 1)));
                            
                int numberOfEMIs = currentApplicant.getLoanTenure();
                java.util.Date startDate = DateUtils.addMonths(new java.util.Date(), 1);

                for (int month = 1; month <= numberOfEMIs; month++) {
                    emiStatusDAO.create(
                            new EMIStatus(null, currentApplicant.getApplicationID(), PaymentStatus.PENDING,
                                    DateUtils.addMonths(startDate, month), null));
                }

            } else if (applicant.getLoanStatus() == LoanStatus.REJECTED) {
                acceptedLoanDAO.delete(applicant.getApplicationID());
            }

			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{applicantId}")
	public ResponseEntity<?> deleteApplicant(@PathVariable int applicantId) {
		boolean isDeleted = applicantDAO.delete(applicantId);

		if (isDeleted)
			return new ResponseEntity<Object>(HttpStatus.OK);
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}
}