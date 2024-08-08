package com.vehicleloan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicleloan.dao.vehicle.Vehicle;
import com.vehicleloan.dao.vehicle.VehicleDAO;

@RestController
@RequestMapping("vehicle")
public class VehicleController {
	private VehicleDAO vehicleDAO;
	
	public VehicleController(VehicleDAO vehicleDAO) {
		this.vehicleDAO = vehicleDAO;
	}

	@GetMapping()
	public ResponseEntity<List<Vehicle>> getVehicles() {
		List<Vehicle> vehicles = vehicleDAO.getAll();
		return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
	}
}
