package com.vehicleloan.controller;

import com.vehicleloan.constant.PaymentStatus;
import com.vehicleloan.dao.emiStatus.EMIStatus;
import com.vehicleloan.dao.emiStatus.EMIStatusDAO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

import java.util.EnumSet;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("emi-status")
public class EMIStatusController {

	private final EMIStatusDAO emiStatusDAO;

	public EMIStatusController(EMIStatusDAO emiStatusDAO) {
		this.emiStatusDAO = emiStatusDAO;
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<EMIStatus>> getEMIStatusesByUserId(
	        @PathVariable Integer userId,
	        @RequestParam Optional<String> paymentStatus) {

	    // Fetch all EMI statuses for the user
	    List<EMIStatus> allStatuses = emiStatusDAO.getByUserId(userId);

	    List<EMIStatus> filteredStatuses;
	    if (paymentStatus.isPresent()) {
	        // Convert the query parameter to PaymentStatus enum
	        PaymentStatus statusFilter;
	        try {
	            statusFilter = PaymentStatus.valueOf(paymentStatus.get().toUpperCase());
	            System.out.println("Filter Status: " + statusFilter);
	        } catch (IllegalArgumentException e) {
	            // If the statusFilter is not a valid enum value, return bad request
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        // Filter the list to include only statuses that match the filter
	        filteredStatuses = allStatuses.stream()
	                .peek(status -> System.out.println("Status: " + status.getPaymentStatus())) // Debugging line
	                .filter(status -> status.getPaymentStatus().equals(statusFilter))
	                .collect(Collectors.toList());

	        System.out.println("Filtered Statuses: " + filteredStatuses);
	    } else {
	        filteredStatuses = allStatuses; // No filtering if no query parameter is provided
	    }

	    if (!filteredStatuses.isEmpty()) {
	        return new ResponseEntity<>(filteredStatuses, HttpStatus.OK);
	    }

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	@GetMapping("/{applicationId}")
	public ResponseEntity<List<EMIStatus>> getEMIStatusesByApplicationId(@PathVariable int applicationId,
			@RequestParam Optional<String> paymentStatus) {

		List<EMIStatus> allStatuses = emiStatusDAO.get(applicationId);

		List<EMIStatus> filteredStatuses;
		if (paymentStatus.isPresent()) {
			// Convert the query parameter to PaymentStatus enum
			PaymentStatus statusFilter;
			try {
				statusFilter = PaymentStatus.valueOf(paymentStatus.get().toUpperCase());
				System.out.println("Filter Status: " + statusFilter);
			} catch (IllegalArgumentException e) {
				// If the statusFilter is not a valid enum value, return bad request
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			filteredStatuses = allStatuses.stream()
					.peek(status -> System.out.println("Status: " + status.getPaymentStatus())) // Debugging line
					.filter(status -> status.getPaymentStatus().equals(statusFilter)).collect(Collectors.toList());

			System.out.println(statusFilter);
			System.out.println(filteredStatuses);
		} else {
			filteredStatuses = allStatuses; // No filtering if no query parameter is provided
		}

		if (!filteredStatuses.isEmpty()) {
			return new ResponseEntity<>(filteredStatuses, HttpStatus.OK);
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
