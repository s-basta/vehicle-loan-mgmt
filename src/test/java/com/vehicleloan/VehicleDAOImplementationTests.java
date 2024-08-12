package com.vehicleloan;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vehicleloan.dao.vehicle.Vehicle;
import com.vehicleloan.dao.vehicle.VehicleDAOImplementation;

public class VehicleDAOImplementationTests {

	@InjectMocks
	private VehicleDAOImplementation vehicleDAO;

	@Mock
	private Connection conn;

	@Mock
	private Statement statement;

	@Mock
	private PreparedStatement preparedStatement;

	@Mock
	private ResultSet resultSet;

	@BeforeEach
	public void setUp() throws SQLException {
		MockitoAnnotations.openMocks(this);
		when(conn.createStatement()).thenReturn(statement);
		when(conn.prepareStatement(any(String.class))).thenReturn(preparedStatement);
	}

	@Test
	public void testGetAll() throws SQLException {
		List<Vehicle> expectedVehicles = Arrays.asList(new Vehicle(1, "Toyota", "Corolla", 20000.0),
				new Vehicle(2, "Honda", "Civic", 22000.0));

		when(statement.executeQuery("select * from vloanvehicle")).thenReturn(resultSet);
		when(resultSet.next()).thenReturn(true, true, false);
		when(resultSet.getInt("vehicleId")).thenReturn(1, 2);
		when(resultSet.getString("vehicleMake")).thenReturn("Toyota", "Honda");
		when(resultSet.getString("vehicleType")).thenReturn("Corolla", "Civic");
		when(resultSet.getDouble("ex_Showroom_Price")).thenReturn(20000.0, 22000.0);

		List<Vehicle> actualVehicles = vehicleDAO.getAll();
		assertEquals(expectedVehicles.size(), actualVehicles.size(), "The size of the vehicle lists should match.");
        for (int i = 0; i < expectedVehicles.size(); i++) {
            Vehicle expected = expectedVehicles.get(i);
            Vehicle actual = actualVehicles.get(i);
            assertEquals(expected.getVehicleId(), actual.getVehicleId());
            assertEquals(expected.getVehicleMake(), actual.getVehicleMake());
            assertEquals(expected.getVehicleModel(), actual.getVehicleModel());
            assertEquals(expected.getExShowroomPrice(), actual.getExShowroomPrice());
            assertEquals(expected.getOnRoadPrice(), actual.getOnRoadPrice());
        }
	}

	@Test
	public void testGetByVehicleId() throws SQLException {
		Vehicle expected = new Vehicle(1, "Toyota", "Corolla", 20000.0);

		when(statement.executeQuery("select * from vloanVehicle where vehicleid=1")).thenReturn(resultSet);
		when(resultSet.next()).thenReturn(true);
		when(resultSet.getInt("vehicleId")).thenReturn(1);
		when(resultSet.getString("vehicleMake")).thenReturn("Toyota");
		when(resultSet.getString("vehicleType")).thenReturn("Corolla");
		when(resultSet.getDouble("ex_Showroom_Price")).thenReturn(20000.0);

		Vehicle actual = vehicleDAO.getByVehicleId(1);
		assertEquals(expected.getVehicleId(), actual.getVehicleId());
		assertEquals(expected.getVehicleMake(), actual.getVehicleMake());
		assertEquals(expected.getVehicleModel(), actual.getVehicleModel());
		assertEquals(expected.getExShowroomPrice(), actual.getExShowroomPrice());
		assertEquals(expected.getOnRoadPrice(), actual.getOnRoadPrice());

	}

	@Test
	public void testGetVehicleMakes() throws SQLException {
		List<String> expectedMakes = Arrays.asList("Toyota", "Honda");

		when(statement.executeQuery("select distinct vehicleMake from vloanVehicle")).thenReturn(resultSet);
		when(resultSet.next()).thenReturn(true, true, false);
		when(resultSet.getString("vehicleMake")).thenReturn("Toyota", "Honda");

		List<String> actualMakes = vehicleDAO.getVehicleMakes();
		assertEquals(expectedMakes, actualMakes);
	}

	@Test
	public void testGetVehiclesByVehicleMake() throws SQLException {
		List<Vehicle> expectedVehicles = Collections.singletonList(new Vehicle(1, "Toyota", "Corolla", 20000.0));

		when(statement.executeQuery("select * from vloanvehicle where vehicleMake like 'Toyota'"))
				.thenReturn(resultSet);
		when(resultSet.next()).thenReturn(true, false);
		when(resultSet.getInt("vehicleId")).thenReturn(1);
		when(resultSet.getString("vehicleMake")).thenReturn("Toyota");
		when(resultSet.getString("vehicleType")).thenReturn("Corolla");
		when(resultSet.getDouble("ex_Showroom_Price")).thenReturn(20000.0);

		List<Vehicle> actualVehicles = vehicleDAO.getVehiclesByVehicleMake("Toyota");
		assertEquals(expectedVehicles.size(), actualVehicles.size(), "The size of the vehicle lists should match.");
        for (int i = 0; i < expectedVehicles.size(); i++) {
            Vehicle expected = expectedVehicles.get(i);
            Vehicle actual = actualVehicles.get(i);
            assertEquals(expected.getVehicleId(), actual.getVehicleId());
            assertEquals(expected.getVehicleMake(), actual.getVehicleMake());
            assertEquals(expected.getVehicleModel(), actual.getVehicleModel());
            assertEquals(expected.getExShowroomPrice(), actual.getExShowroomPrice());
            assertEquals(expected.getOnRoadPrice(), actual.getOnRoadPrice());
        }
	}

	@Test
	public void testGetPricesByMakeAndModel() throws SQLException {
		Map<String, Double> expectedPrices = new HashMap<>();
		expectedPrices.put("showroomPrice", 20000.0);
		expectedPrices.put("onRoadPrice", 22000.0);

		when(preparedStatement.executeQuery()).thenReturn(resultSet);
		when(resultSet.next()).thenReturn(true);
		when(resultSet.getDouble("ex_showroom_price")).thenReturn(20000.0);
		when(resultSet.getDouble("on_road_price")).thenReturn(22000.0);

		Map<String, Double> actualPrices = vehicleDAO.getPricesByMakeAndModel("Toyota", "Corolla");
		assertEquals(expectedPrices, actualPrices);
	}

	@Test
	public void testCreate() throws SQLException {
		Vehicle vehicle = new Vehicle(1, "Toyota", "Corolla", 20000.0);
		when(preparedStatement.executeUpdate()).thenReturn(1);

		boolean result = vehicleDAO.create(vehicle);
		assertTrue(result);
	}

	@Test
	public void testUpdate() throws SQLException {
		Vehicle vehicle = new Vehicle(1, "Toyota", "Corolla", 20000.0);
		when(preparedStatement.executeUpdate()).thenReturn(1);

		boolean result = vehicleDAO.update(vehicle);
		assertTrue(result);
	}

	@Test
	public void testDelete() throws SQLException {
		when(preparedStatement.executeUpdate()).thenReturn(1);

		boolean result = vehicleDAO.delete(1);
		assertTrue(result);
	}
}
