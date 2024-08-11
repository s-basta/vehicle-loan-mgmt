package com.vehicleloan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vehicleloan.dao.acceptedLoan.AcceptedLoan;
import com.vehicleloan.dao.acceptedLoan.AcceptedLoanDAO;

@RestController
@RequestMapping("accepted-loan")
public class AcceptedLoanController {
	private AcceptedLoanDAO acceptedLoanDAO;

	public AcceptedLoanController(AcceptedLoanDAO acceptedLoanDAO) {
		this.acceptedLoanDAO = acceptedLoanDAO;
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

	@PutMapping()
	public ResponseEntity<?> updateAcceptedLoan(@RequestBody AcceptedLoan acceptedLoan) {
		boolean isUpdated = acceptedLoanDAO.update(acceptedLoan);

		if (isUpdated)
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
