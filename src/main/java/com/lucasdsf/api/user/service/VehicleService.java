package com.lucasdsf.api.user.service;

import java.util.List;

import com.lucasdsf.api.user.domains.model.VehicleDto;

public interface VehicleService {
	VehicleDto createVehicle(VehicleDto vehicle, String userLogin);
	VehicleDto getInfoVehicle(Long id);
	VehicleDto updateVehicle(Long id, VehicleDto vehicleDto);
	VehicleDto deleteVehicle(Long id);
	List<VehicleDto> listAll(String userLogin);
}
