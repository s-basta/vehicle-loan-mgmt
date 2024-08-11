package com.vehicleloan.controller;

import com.vehicleloan.dao.emiStatus.EMIStatus;
import com.vehicleloan.dao.emiStatus.EMIStatusDAO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("emi-status")
public class EMIStatusController {

	private final EMIStatusDAO emiStatusDAO;

	public EMIStatusController(EMIStatusDAO emiStatusDAO) {
		this.emiStatusDAO = emiStatusDAO;
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<EMIStatus>> getEMIStatusesByUserId(@PathVariable Integer userId) {
		List<EMIStatus> emiStatuses = emiStatusDAO.getByUserId(userId);
		if(!emiStatuses.isEmpty()) {
			return new ResponseEntity<>(emiStatuses , HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

	@GetMapping("/{applicationId}")
	public ResponseEntity<List<EMIStatus>> getEMIStatusesByApplicationId(@PathVariable int applicationId) {
		List<EMIStatus> emiStatuses = emiStatusDAO.get(applicationId);

		if (!emiStatuses.isEmpty()) {
			return new ResponseEntity<>(emiStatuses, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping()
	public ResponseEntity<?> updateEMIStatus(@RequestBody EMIStatus emiStatus) {
		boolean isUpdated = emiStatusDAO.update(emiStatus);

		if (isUpdated) {
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
