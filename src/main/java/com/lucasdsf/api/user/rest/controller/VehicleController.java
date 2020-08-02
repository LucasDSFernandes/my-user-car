package com.lucasdsf.api.user.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasdsf.api.user.client.AuthService;
import com.lucasdsf.api.user.domains.model.UserDto;
import com.lucasdsf.api.user.domains.model.VehicleDto;
import com.lucasdsf.api.user.rest.resouces.VehicleReources;
import com.lucasdsf.api.user.service.VehicleService;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "*")
public class VehicleController implements VehicleReources{

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private AuthService authService;
	
	@Override
	@PostMapping(value = "/")
	public ResponseEntity<VehicleDto> createVehicle(@RequestHeader("Authorization") String authorization,VehicleDto vehicle) {
		UserDto user = authService.getPrincipal(authorization);
		return ResponseEntity.ok(vehicleService.createVehicle(vehicle, user.getLogin()));
	}
	
	@Override
	@GetMapping(value = "/")
	public ResponseEntity<List<VehicleDto>> listAll(@RequestHeader("Authorization") String authorization) {
		UserDto user = authService.getPrincipal(authorization);
		return ResponseEntity.ok(vehicleService.listAll(user.getLogin()));
	}

	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<VehicleDto> getInfoCar(@RequestHeader("Authorization") String authorization,@PathVariable("id")  Long id) {
		authService.getPrincipal(authorization);
		return ResponseEntity.ok(vehicleService.getInfoVehicle(id));
	}

	@Override
	@PutMapping(value = "/{id}")
	public ResponseEntity<VehicleDto> updateVehicle(@RequestHeader("Authorization") String authorization,@PathVariable("id")Long id, @RequestBody  VehicleDto  vehicleDto){
		authService.getPrincipal(authorization);
		return ResponseEntity.ok(vehicleService.updateVehicle(id, vehicleDto));
	}
	
	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<VehicleDto> deleteVehicle(@RequestHeader("Authorization") String authorization,@PathVariable("id")Long id){
		authService.getPrincipal(authorization);
		return ResponseEntity.ok(vehicleService.deleteVehicle(id));
	}
}
