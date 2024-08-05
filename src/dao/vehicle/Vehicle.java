package dao.vehicle;

public class Vehicle {
	int vehicleId;
	String vehicleMake;
	String vehicleType;
	double exShowroomPrice;
	double onRoadPrice;
	
	public Vehicle(int vehicleId, String vehicleMake, String vehicleType, double exShowroomPrice, double onRoadPrice) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleMake = vehicleMake;
		this.vehicleType = vehicleType;
		this.exShowroomPrice = exShowroomPrice;
		this.onRoadPrice = onRoadPrice;
	}

	public int getVehicleId() {
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

	public double getExShowroomPrice() {
		return exShowroomPrice;
	}

	public void setExShowroomPrice(double exShowroomPrice) {
		this.exShowroomPrice = exShowroomPrice;
	}

	public double getOnRoadPrice() {
		return onRoadPrice;
	}

	public void setOnRoadPrice(double onRoadPrice) {
		this.onRoadPrice = onRoadPrice;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vehicleMake=" + vehicleMake + ", vehicleType=" + vehicleType
				+ ", exShowroomPrice=" + exShowroomPrice + ", onRoadPrice=" + onRoadPrice + "]";
	}
	
	
}
