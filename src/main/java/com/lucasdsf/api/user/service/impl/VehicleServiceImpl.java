package com.lucasdsf.api.user.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lucasdsf.api.user.builder.VehicleDtoBuilder;
import com.lucasdsf.api.user.builder.VehicleEntityBuilder;
import com.lucasdsf.api.user.domains.entity.User;
import com.lucasdsf.api.user.domains.entity.Vehicle;
import com.lucasdsf.api.user.domains.model.VehicleDto;
import com.lucasdsf.api.user.domains.repository.UserRepository;
import com.lucasdsf.api.user.domains.repository.VehicleRepository;
import com.lucasdsf.api.user.exception.ErrorException;
import com.lucasdsf.api.user.service.VehicleService;

@Component
public class VehicleServiceImpl implements VehicleService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public VehicleDto createVehicle(VehicleDto vehicleDto, String userLogin) {
		checkPlateExist(vehicleDto);
		User user = findUser(userLogin);
		
		logger.info("Inserindo veiculo. Modelo: {}, Placa: {}", vehicleDto.getModel(), vehicleDto.getLicensePlate());
		this.vehicleRepository.save(VehicleEntityBuilder.vehicleEntityBuilder()
										.color(vehicleDto.getColor())
										.licensePlate(vehicleDto.getLicensePlate())
										.model(vehicleDto.getModel())
										.year(vehicleDto.getYear())
										.user(user)
										.build());
		return new VehicleDto("Veiculo cadastrado com sucesso!", true);
	}

	private User findUser(String userLogin) {
		User user = userRepository.findByUserLogin(userLogin)
					.orElseThrow(()-> new ErrorException("Login already exists"));
		return user;
	}

	private void checkPlateExist(VehicleDto vehicleDto) {
		if(vehicleRepository.findByLicensePlate(vehicleDto.getLicensePlate()).isPresent())
			throw new ErrorException("License plate already exists");
	}
	
	@Override
	public VehicleDto getInfoVehicle(Long id) {

		Vehicle vehicleEntity = findVehicle(id);	
		return VehicleDtoBuilder.vehicleDTOBuilder()
				.color(vehicleEntity.getColor())
				.licensePlate(vehicleEntity.getLicensePlate())
				.model(vehicleEntity.getModel())
				.year(vehicleEntity.getYear())
				.build();
	}

	@Override
	public VehicleDto updateVehicle(Long id, VehicleDto vehicleDto) {
		Vehicle vehicleEntity = findVehicle(id);

		vehicleEntity.setLicensePlate(vehicleDto.getLicensePlate());
		vehicleEntity.setModel(vehicleDto.getModel());
		vehicleEntity.setYear(vehicleEntity.getYear());
		logger.info("Atualizando veiculo: {}", vehicleDto);
		vehicleRepository.save(vehicleEntity);

		return new VehicleDto("Veiculo alterado com suceso", true);
	}

	@Override
	public List<VehicleDto> listAll(String userLogin) {
		User user = findUser(userLogin);

		List<VehicleDto> vehicleList = new ArrayList<>();
		List<Vehicle> vehicleEntityList = vehicleRepository.findAllByUser(user);
		vehicleEntityList.stream()
				.sorted(Comparator.comparing(Vehicle::getModel, Comparator.nullsLast(Comparator.naturalOrder())))
				.forEach(vehicle ->  
					vehicleList.add(VehicleDtoBuilder.vehicleDTOBuilder()
							.id(vehicle.getId())
							.color(vehicle.getColor())
							.licensePlate(vehicle.getLicensePlate())
							.model(vehicle.getModel())
							.year(vehicle.getYear())
							.build())
				);
		return vehicleList;
	}

	private Vehicle findVehicle(Long id) {
		return vehicleRepository.findById(id)
				.orElseThrow(() -> new ErrorException("Veiculo não encontrado."));
	}

	@Override
	public VehicleDto deleteVehicle(Long id) {
		vehicleRepository.deleteById(id);
		return new VehicleDto("Veiculo deletado com sucesso!", true);
	}
}