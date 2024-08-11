package com.vehicleloan.dao.vehicle;

public class Vehicle {
	Integer vehicleId;
	String vehicleMake;
	String vehicleModel;
	Double exShowroomPrice;
	Double onRoadPrice;
	Double roadTax = 1.11; // Assuming 11% roadTax for vehicles priced upto 10 lakhs 

	public Vehicle(Integer vehicleId, String vehicleMake, String vehicleModel, Double exShowroomPrice) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleMake = vehicleMake;
		this.vehicleModel = vehicleModel;
		this.exShowroomPrice = exShowroomPrice;
		// Using data for road tax in Mumbai
		// The tax rate is 11% of the vehicle cost for vehicles priced up to 10 lakhs. 
		// For vehicles costing between ₹10 lakh and ₹20 lakh, the tax rate is 12%. 
		// Meanwhile, for vehicles costing more than ₹20 lakh, the tax rate is 13%.
		if(this.exShowroomPrice <= 1000000) {
			this.onRoadPrice = this.exShowroomPrice*this.roadTax;
		}
		else if (this.exShowroomPrice <= 2000000) {
			this.onRoadPrice = this.exShowroomPrice*1.12;
		}
		else {
			this.onRoadPrice = this.exShowroomPrice*1.13;
		}
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

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
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
		return "Vehicle [vehicleId=" + vehicleId + ", vehicleMake=" + vehicleMake + ", vehicleType=" + vehicleModel
				+ ", exShowroomPrice=" + exShowroomPrice + ", onRoadPrice=" + onRoadPrice + "]";
	}
	
	
}
