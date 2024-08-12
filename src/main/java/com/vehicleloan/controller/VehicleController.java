package com.vehicleloan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Vehicle>> getVehicles() {
		List<Vehicle> vehicles = vehicleDAO.getAll();
		return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
	}
	
	@GetMapping("/makes")
	public ResponseEntity<List<String>> getVehicleMakes(){
		List<String> vehicleMakes = vehicleDAO.getVehicleMakes();
		return new ResponseEntity<List<String>>(vehicleMakes, HttpStatus.OK);
	}

	@GetMapping("/vehiclesByMake/{vehicleMake}")
	public ResponseEntity<List<Vehicle>> getVehicleByVehicleMake(@PathVariable String vehicleMake){
		List<Vehicle> vehicles = vehicleDAO.getVehiclesByVehicleMake(vehicleMake);
		return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
	}
	
	@GetMapping("/vehicleModelsByMake/{vehicleMake}")
	public ResponseEntity<List<String>> getVehicleModelsByVehicleMake(@PathVariable String vehicleMake) {
	    // Fetch the list of vehicles by make
	    List<Vehicle> vehicles = vehicleDAO.getVehiclesByVehicleMake(vehicleMake);
	    
	    // Extract vehicle models from the list of vehicles
	    List<String> vehicleModels = new ArrayList<>();
	    for (Vehicle vehicle : vehicles) {
	        vehicleModels.add(vehicle.getVehicleModel());
	    }
	    
	    // Return the list of vehicle models with HTTP status OK
	    return new ResponseEntity<>(vehicleModels, HttpStatus.OK);
	}
	
	@GetMapping("/prices")
    public ResponseEntity<Map<String, Double>> getPricesByMakeAndModel(
            @RequestParam String vehicleMake,
            @RequestParam String vehicleModel) {
        Map<String, Double> prices = vehicleDAO.getPricesByMakeAndModel(vehicleMake, vehicleModel);
        if (prices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(prices, HttpStatus.OK);
    }

//	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
//	public ResponseEntity<List<Vehicle>> createVehicle(String vehicleMake){
//		List<Vehicle> vehicles = vehicleDAO.getVehiclesByVehicleMake(vehicleMake);
//		return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
//	}
}
