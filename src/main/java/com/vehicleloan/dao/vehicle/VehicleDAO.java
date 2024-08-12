package com.vehicleloan.dao.vehicle;

import java.util.List;
import java.util.Map;

public interface VehicleDAO {
	Vehicle getByVehicleId(int vehicleId);
	List<Vehicle> getAll();
	List<String> getVehicleMakes();
	List<Vehicle> getVehiclesByVehicleMake(String vehicleMake);
	Map<String, Double> getPricesByMakeAndModel(String make, String model);
	boolean create(Vehicle vehicle);
	boolean update(Vehicle vehicle);
	boolean delete(int vehicleId);
}