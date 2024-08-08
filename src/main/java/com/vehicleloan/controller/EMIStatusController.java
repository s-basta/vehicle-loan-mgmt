package com.vehicleloan.controller;

import com.vehicleloan.dao.emiStatus.EMIStatus;
import com.vehicleloan.dao.emiStatus.EMIStatusDAO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("emi-status")
public class EMIStatusController {

	private final EMIStatusDAO emiStatusDAO;

	public EMIStatusController(EMIStatusDAO emiStatusDAO) {
		this.emiStatusDAO = emiStatusDAO;
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
