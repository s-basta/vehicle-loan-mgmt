package com.vehicleloan.dao.vehicle;

import java.util.List;

public interface VehicleDAO {
	Vehicle getByVehicleId(int vehicleId);
	List<Vehicle> getAll();
	List<String> getVehicleType();
	List<Vehicle> getVehiclesByVehicleType(String vehicleType);
	Integer create(Vehicle vehicle);
	boolean update(Vehicle vehicle);
	boolean delete(int vehicleId);
}