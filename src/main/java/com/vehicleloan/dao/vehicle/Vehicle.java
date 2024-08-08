package com.vehicleloan.dao.vehicle;

public class Vehicle {
	Integer vehicleId;
	String vehicleMake;
	String vehicleType;
	Double exShowroomPrice;
	Double onRoadPrice;
	
	public Vehicle(Integer vehicleId, String vehicleMake, String vehicleType, Double exShowroomPrice, Double onRoadPrice) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleMake = vehicleMake;
		this.vehicleType = vehicleType;
		this.exShowroomPrice = exShowroomPrice;
		this.onRoadPrice = onRoadPrice;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleMake() {
		return vehicleMake;
	}

	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Double getExShowroomPrice() {
		return exShowroomPrice;
	}

	public void setExShowroomPrice(Double exShowroomPrice) {
		this.exShowroomPrice = exShowroomPrice;
	}

	public Double getOnRoadPrice() {
		return onRoadPrice;
	}

	public void setOnRoadPrice(Double onRoadPrice) {
		this.onRoadPrice = onRoadPrice;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vehicleMake=" + vehicleMake + ", vehicleType=" + vehicleType
				+ ", exShowroomPrice=" + exShowroomPrice + ", onRoadPrice=" + onRoadPrice + "]";
	}
	
	
}
