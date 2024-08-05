package dao.vehicle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.Database;

public class VehicleDAOImplementation implements VehicleDAO {
	Connection conn;
	
	public VehicleDAOImplementation() {
		conn = Database.getConnection();
	}
	
	private Vehicle resultSetToVehicleConvertor(ResultSet result) throws SQLException{
		return new Vehicle(
				result.getInt("vehicleId"),
				result.getString("vehicleMake"),
				result.getString("vehicleType"),
				result.getDouble("ex_Showroom_Price"),
				result.getDouble("on_Road_Price"));				
	}
	
	@Override
	public Vehicle getByVehicleId(int vehicleId) {
		Vehicle vehicle = null;

		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from vloanVehicle where vehicleid=" + vehicleId);
			if(result.next()) {
				 vehicle = resultSetToVehicleConvertor(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicle;	
	}

	@Override
	public List<String> getVehicleType() {
		Vehicle vehicle = null;
		List<String> vehicleTypes = new ArrayList<>();
		
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select distinct vehicleType from vloanVehicle");
			
			while(result.next()) {
				vehicleTypes.add(result.getString("vehicleType"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vehicleTypes;
	}

	@Override
	public List<Vehicle> getVehiclesByVehicleType(String vehicleType) {
		List<Vehicle> vehicles = new ArrayList<>();
		
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from vloanvehicle where vehicletype like '" + vehicleType + "'");
			
			while(result.next()) {
				vehicles.add(resultSetToVehicleConvertor(result));
			}
		}catch(SQLException e) { 
			e.printStackTrace();
		}
		
		return vehicles;
	}
}