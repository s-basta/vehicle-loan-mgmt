package com.vehicleloan;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vehicleloan.dao.vehicle.Vehicle;
import com.vehicleloan.dao.vehicle.VehicleDAO;
import com.vehicleloan.dao.vehicle.VehicleDAOImplementation;

import org.junit.jupiter.api.Assertions;

import java.util.List;

public class VehicleDAOImplementationTest {

    VehicleDAO vehicleDAO;
    Vehicle testVehicle;
    Integer vehicleId;

    @BeforeEach
    public void init() {
        vehicleDAO = new VehicleDAOImplementation();

        // Setup a test vehicle
        testVehicle = new Vehicle(null, "Toyota", "Sedan", 20000.0, 22000.0);
    }

    @AfterEach
    public void cleanUp() {
        if (vehicleId != null) {
            vehicleDAO.delete(vehicleId);
        }
    }

    @Test
    public void testCreateVehicle() {
        // Create Vehicle Test
        vehicleId = vehicleDAO.create(testVehicle);
        Assertions.assertNotNull(vehicleId, "Vehicle ID should not be null");

        // Retrieve the vehicle to validate creation
        Vehicle retrievedVehicle = vehicleDAO.getByVehicleId(vehicleId);
        Assertions.assertNotNull(retrievedVehicle, "Retrieved vehicle should not be null");
        Assertions.assertEquals(testVehicle.getVehicleMake(), retrievedVehicle.getVehicleMake());
        Assertions.assertEquals(testVehicle.getVehicleType(), retrievedVehicle.getVehicleType());
        Assertions.assertEquals(testVehicle.getExShowroomPrice(), retrievedVehicle.getExShowroomPrice());
        Assertions.assertEquals(testVehicle.getOnRoadPrice(), retrievedVehicle.getOnRoadPrice());
    }

    @Test
    public void testGetByVehicleId() {
        // Create a vehicle and get its ID
        vehicleId = vehicleDAO.create(testVehicle);
        Assertions.assertNotNull(vehicleId);

        // Test the retrieval by ID
        Vehicle createdVehicle = vehicleDAO.getByVehicleId(vehicleId);
        Assertions.assertNotNull(createdVehicle);
        Assertions.assertEquals(testVehicle.getVehicleMake(), createdVehicle.getVehicleMake());
    }

    @Test
    public void testGetVehicleType() {
        // Create a vehicle to ensure there is at least one vehicleType
        vehicleDAO.create(testVehicle);

        // Test getting vehicle types
        List<String> vehicleTypes = vehicleDAO.getVehicleType();
        Assertions.assertFalse(vehicleTypes.isEmpty(), "Vehicle types should not be empty");
        Assertions.assertTrue(vehicleTypes.contains(testVehicle.getVehicleType()), "Vehicle type should be in the list");
    }

    @Test
    public void testGetVehiclesByVehicleType() {
        // Create a test vehicle and ensure it can be retrieved by type
        vehicleId = vehicleDAO.create(testVehicle);
        Assertions.assertNotNull(vehicleId);

        List<Vehicle> vehicles = vehicleDAO.getVehiclesByVehicleType(testVehicle.getVehicleType());
        Assertions.assertNotNull(vehicles);
        Assertions.assertTrue(vehicles.size() > 0);
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    @Test
    public void testUpdateVehicle() {
        // Create a vehicle and then update it
        vehicleId = vehicleDAO.create(testVehicle);
        Assertions.assertNotNull(vehicleId);

        Vehicle updatedVehicle = new Vehicle(vehicleId, "Honda", "SUV", 25000.0, 27000.0);
        boolean isUpdated = vehicleDAO.update(updatedVehicle);
        Assertions.assertTrue(isUpdated, "Vehicle should be updated successfully");

        // Retrieve the updated vehicle
        Vehicle retrievedVehicle = vehicleDAO.getByVehicleId(vehicleId);
        Assertions.assertNotNull(retrievedVehicle, "Updated vehicle should not be null");
        Assertions.assertEquals(updatedVehicle.getVehicleMake(), retrievedVehicle.getVehicleMake());
        Assertions.assertEquals(updatedVehicle.getVehicleType(), retrievedVehicle.getVehicleType());
        Assertions.assertEquals(updatedVehicle.getExShowroomPrice(), retrievedVehicle.getExShowroomPrice());
        Assertions.assertEquals(updatedVehicle.getOnRoadPrice(), retrievedVehicle.getOnRoadPrice());
    }

    @Test
    public void testDeleteVehicle() {
        // Create a vehicle and get its ID
        vehicleId = vehicleDAO.create(testVehicle);
        Assertions.assertNotNull(vehicleId);

        // Delete the vehicle
        boolean isDeleted = vehicleDAO.delete(vehicleId);
        Assertions.assertTrue(isDeleted, "Vehicle should be deleted successfully");

        // Verify the vehicle is deleted
        Vehicle retrievedVehicle = vehicleDAO.getByVehicleId(vehicleId);
        Assertions.assertNull(retrievedVehicle, "Deleted vehicle should be null");
    }
}
