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
import com.vehicleloan.dao.applicant.Applicant;
import com.vehicleloan.dao.applicant.ApplicantDAO;

@RestController
@RequestMapping("applicant")
public class ApplicantController {
	private ApplicantDAO applicantDAO;

	public ApplicantController(ApplicantDAO applicantDAO) {
		this.applicantDAO = applicantDAO;
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
		boolean isCreated = applicantDAO.create(newApplicant);

		if (isCreated)
			return new ResponseEntity<>(HttpStatus.CREATED);
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@PutMapping()
	public ResponseEntity<?> updateApplicant(@RequestBody Applicant applicant) {
		boolean isUpdated = applicantDAO.update(applicant);

		if (isUpdated)
			return new ResponseEntity<>(HttpStatus.OK);
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
