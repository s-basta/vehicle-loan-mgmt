package com.vehicleloan.dao.vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.vehicleloan.config.Database;

@Service
public class VehicleDAOImplementation implements VehicleDAO {
	Connection conn;
	
	public VehicleDAOImplementation() {
		conn = Database.getConnection();
	}
	
	private Vehicle resultSetToVehicleConvertor(ResultSet result) throws SQLException{
		return new Vehicle(
				result.getInt("vehicleId"),
				result.getString("vehicleMake"),
				result.getString("vehicleModel"),
				result.getDouble("ex_Showroom_Price"));		
	}
	
	@Override
	public List<Vehicle> getAll() {
		List<Vehicle> vehicles = new ArrayList<>();
		
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from vloanvehicle");
			
			while(result.next()) {
				vehicles.add(resultSetToVehicleConvertor(result));
			}
		}catch(SQLException e) { 
			e.printStackTrace();
		}
		
		return vehicles;
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
	public List<String> getVehicleMakes() {
		List<String> vehicleMakes = new ArrayList<>();
		
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select distinct vehicleMake from vloanVehicle");
			
			while(result.next()) {
				vehicleMakes.add(result.getString("vehicleMake"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vehicleMakes;
	}

	@Override
	public List<Vehicle> getVehiclesByVehicleMake(String vehicleMake) {
		List<Vehicle> vehicles = new ArrayList<>();
		
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from vloanvehicle where vehicleMake like '" + vehicleMake + "'");
			
			while(result.next()) {
				vehicles.add(resultSetToVehicleConvertor(result));
			}
		}catch(SQLException e) { 
			e.printStackTrace();
		}
		
		return vehicles;
	}
	
	// Method to get showroom and on-road prices based on make and model
	@Override
	public Map<String, Double> getPricesByMakeAndModel(String make, String model) {
	    Map<String, Double> prices = new HashMap<>();
	    
	    String sql = "SELECT ex_showroom_price, on_road_price FROM vloanVehicle WHERE vehicleMake = ? AND vehicleModel = ?";
	    
	    try (PreparedStatement pst = conn.prepareStatement(sql)) {
	        pst.setString(1, make);
	        pst.setString(2, model);
	        
	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	                double showroomPrice = rs.getDouble("ex_showroom_price");
	                double onRoadPrice = rs.getDouble("on_road_price");
	                
	                prices.put("showroomPrice", showroomPrice);
	                prices.put("onRoadPrice", onRoadPrice);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return prices;
	}


	@Override
	public boolean create(Vehicle vehicle) {
		// TODO Auto-generated method stub
		try {
			String sql = "INSERT INTO `vloanVehicle` (" +
	                "`vehicleMake`, `vehicleModel`, `ex_showroom_price`, `on_road_price`) " +
	                "VALUES (?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
					
			pst.setString(1, vehicle.getVehicleMake());
            pst.setString(2, vehicle.getVehicleModel());
            pst.setDouble(3, vehicle.getExShowroomPrice());
            pst.setDouble(4, vehicle.getOnRoadPrice());
            
            int rows = pst.executeUpdate();
            if(rows > 0) return true;
        }catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean update(Vehicle vehicle) {
		if(vehicle == null || vehicle.getVehicleId() == null) {
			throw new IllegalArgumentException("Vehicle and VehicleId must not be null");
		}
		
        List<String> setClauses = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();
        
        if (vehicle.getVehicleMake() != null) {
            setClauses.add("vehicleMake = ? ");
            parameters.add(vehicle.getVehicleMake());
        }
        if (vehicle.getVehicleModel() != null) {
            setClauses.add("vehicleModel = ? ");
            parameters.add(vehicle.getVehicleModel());
        }
        if (vehicle.getExShowroomPrice() != null) {
            setClauses.add("ex_showroom_price = ? ");
            parameters.add(vehicle.getExShowroomPrice());
        }
        if (vehicle.getOnRoadPrice() != null) {
            setClauses.add("on_road_price = ? ");
            parameters.add(vehicle.getOnRoadPrice());
        }
        
        String sql = "UPDATE `vloanvehicle` SET " + String.join(", ", setClauses) + " WHERE vehicleId = ?";
        parameters.add(vehicle.getVehicleId());

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                pst.setObject(i + 1, parameters.get(i));
            }

            int rows = pst.executeUpdate();
            if(rows > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
	}

	@Override
	public boolean delete(int vehicleId) {
		try {
			PreparedStatement pst = conn.prepareStatement("delete from vloanvehicle where vehicleId=?");
			pst.setInt(1,vehicleId);
			int rows = pst.executeUpdate();
			if(rows > 0) return true;		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}