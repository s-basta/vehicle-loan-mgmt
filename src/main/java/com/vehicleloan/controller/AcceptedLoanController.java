package com.vehicleloan.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.vehicleloan.constant.PaymentStatus;
import com.vehicleloan.dao.acceptedLoan.AcceptedLoan;
import com.vehicleloan.dao.acceptedLoan.AcceptedLoanDAO;
import com.vehicleloan.dao.emiStatus.EMIStatus;
import com.vehicleloan.dao.emiStatus.EMIStatusDAO;
import com.vehicleloan.utils.DateUtils;

@RestController
@RequestMapping("accepted-loan")
public class AcceptedLoanController {
	private AcceptedLoanDAO acceptedLoanDAO;
	private EMIStatusDAO emiStatusDAO;

	public AcceptedLoanController(AcceptedLoanDAO acceptedLoanDAO, EMIStatusDAO emiStatusDAO) {
		this.acceptedLoanDAO = acceptedLoanDAO;
		this.emiStatusDAO = emiStatusDAO;
	}

	@GetMapping("/{applicationId}")
	public ResponseEntity<AcceptedLoan> getAcceptedLoanByApplicationId(@PathVariable int applicationId) {
		AcceptedLoan acceptedLoan = acceptedLoanDAO.get(applicationId);

		if (acceptedLoan != null)
			return new ResponseEntity<AcceptedLoan>(acceptedLoan, HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping()
	public ResponseEntity<List<AcceptedLoan>> getAcceptedLoans(@RequestParam(required = false) Integer userId) {
		List<AcceptedLoan> acceptedLoans = acceptedLoanDAO.getAll(userId);

		return new ResponseEntity<List<AcceptedLoan>>(acceptedLoans, HttpStatus.OK);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> createAcceptedLoan(@RequestBody AcceptedLoan acceptedLoan) {
		boolean isCreated = acceptedLoanDAO.create(acceptedLoan);

		if (isCreated) {
			int numberOfEMIs = acceptedLoan.getTotalEMIs();
			Date startDate = acceptedLoan.getLoanStartDate();

			for (int month = 1; month <= numberOfEMIs; month++) {
				emiStatusDAO.create(
						new EMIStatus(null, acceptedLoan.getApplicationID(), PaymentStatus.PENDING, DateUtils.addMonths(startDate, month), null));
			}

			return new ResponseEntity<>(HttpStatus.CREATED);
		}

		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@PutMapping()
	public ResponseEntity<?> updateAcceptedLoan(@RequestBody AcceptedLoan acceptedLoan) {
		boolean isUpdated = acceptedLoanDAO.update(acceptedLoan);

		if (isUpdated)
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{applicationId}")
	public ResponseEntity<?> deleteAcceptedLoan(@PathVariable int applicationId) {
		boolean isDeleted = acceptedLoanDAO.delete(applicationId);

		if (isDeleted) {
			emiStatusDAO.deleteByApplicationId(applicationId);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}
}
