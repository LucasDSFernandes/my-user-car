package com.lucasdsf.api.user.builder;

import com.lucasdsf.api.user.domains.entity.User;
import com.lucasdsf.api.user.domains.entity.Vehicle;

public class VehicleEntityBuilder {
	private Long id;
	private Long year;
	private String licensePlate;
	private String model;
	private String color;
	private User user;
	
	public static VehicleEntityBuilder vehicleEntityBuilder() {
		return new VehicleEntityBuilder();
	}
	
	public VehicleEntityBuilder id(Long id) {
		this.id = id;
		return this;		
	}
	public VehicleEntityBuilder year(Long year) {
		this.year = year;
		return this;		
	}
	public VehicleEntityBuilder licensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
		return this;		
	}
	public VehicleEntityBuilder model(String model) {
		this.model = model;
		return this;		
	}
	public VehicleEntityBuilder color(String color) {
		this.color = color;
		return this;		
	}
	public VehicleEntityBuilder user(User user) {
		this.user = user;
		return this;		
	}

	public Vehicle build() {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(id);
		vehicle.setLicensePlate(licensePlate);
		vehicle.setModel(model);
		vehicle.setYear(year);
		vehicle.setColor(color);
		vehicle.setUser(user);
		return vehicle;
	} 
}
