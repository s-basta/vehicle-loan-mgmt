package com.vehicleloan.dao.vehicle;

import java.util.List;

public interface VehicleDAO {
	Vehicle getByVehicleId(int vehicleId);
	List<Vehicle> getAll();
	List<String> getVehicleMakes();
	List<Vehicle> getVehiclesByVehicleMake(String vehicleMake);
	boolean create(Vehicle vehicle);
	boolean update(Vehicle vehicle);
	boolean delete(int vehicleId);
}