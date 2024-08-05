package dao.vehicle;

import java.util.List;

public interface VehicleDAO {
	Vehicle getByVehicleId(int vehicleId);
	List<String> getVehicleType();
	List<Vehicle> getVehiclesByVehicleType(String vehicleType);
	boolean create(Vehicle vehicle);
	boolean update(Vehicle vehicle);
	boolean delete(int vehicleId);
}