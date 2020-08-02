package com.lucasdsf.api.user.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.lucasdsf.api.user.client.AuthService;
import com.lucasdsf.api.user.domains.entity.User;
import com.lucasdsf.api.user.domains.entity.Vehicle;
import com.lucasdsf.api.user.domains.model.UserDto;
import com.lucasdsf.api.user.domains.model.VehicleDto;
import com.lucasdsf.api.user.domains.repository.UserRepository;
import com.lucasdsf.api.user.domains.repository.VehicleRepository;
import com.lucasdsf.api.user.rest.resouces.VehicleReources;
import com.lucasdsf.api.user.service.VehicleService;
import com.lucasdsf.api.user.uteis.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
class VehicleServiceImplTest {
	@Inject
	TestUtils utils;
	@Inject
	private VehicleService vehicleService;
	
	@MockBean
	private VehicleRepository vehicleRepositoryMock;
	@MockBean
	private UserRepository userRepositoryMock;
	@Test
	void testCreateVehiclePlateExist(){
		Optional<User> generateOneUser = utils.generateOneUsertEntityOptional();
		given(userRepositoryMock.findByUserLogin(Mockito.anyString())).willReturn(generateOneUser);
		given(vehicleRepositoryMock.findByLicensePlate(Mockito.anyString())).willReturn(utils.generateOneVehicleEntityOptional());
		given(vehicleRepositoryMock.save(Mockito.any(Vehicle.class))).willReturn(utils.generateOneVehicleEntity());
		
		Exception exception =
				assertThrows(Exception.class, () -> 
				this.vehicleService.createVehicle(utils.generateOneVehicleDto(),  Mockito.anyString())
						);
		assertTrue(exception.getMessage().equalsIgnoreCase("License plate already exists"));
	}
	@Test
	void testCreateVehicleSucess(){
		Optional<User> generateOneUser = utils.generateOneUsertEntityOptional();
		given(userRepositoryMock.findByUserLogin(Mockito.anyString())).willReturn(generateOneUser);
		given(vehicleRepositoryMock.findByLicensePlate(Mockito.anyString())).willReturn( Optional.empty());
		given(vehicleRepositoryMock.save(Mockito.any(Vehicle.class))).willReturn(utils.generateOneVehicleEntity());
		
		VehicleDto response = this.vehicleService.createVehicle(utils.generateOneVehicleDto(),  Mockito.anyString());
		
		assertThat(response!= null, equalTo(true));
	}
	
	@Test
	void testFindAllVehicles() {
		Optional<User> generateOneUser = utils.generateOneUsertEntityOptional();
		List<Vehicle> generateOneListVehicleEntity = utils.generateOneListVehicleEntity();
		given(userRepositoryMock.findByUserLogin(Mockito.anyString())).willReturn(generateOneUser);
		given( vehicleRepositoryMock.findAllByUser(generateOneUser.get())).willReturn( generateOneListVehicleEntity);
		
		List<VehicleDto> response = this.vehicleService.listAll(generateOneUser.get().getUserLogin());
		assertThat(response.isEmpty(), equalTo(false));
	}
	
	@Test
	void testFindAllVehiclesUserNotFound() {
		given( vehicleRepositoryMock.findAllByUser(Mockito.any(User.class))).willReturn( utils.generateOneListVehicleEntity());
		
		Exception exception =
				assertThrows(Exception.class, () -> 
				this.vehicleService.listAll( Mockito.anyString())
						);
		assertTrue(exception.getMessage().equalsIgnoreCase("User not Found"));
	}
	
	@Test
	void testeFindInfoVehicle() {
		Optional<Vehicle> generateOneVehicleEntityOptional = utils.generateOneVehicleEntityOptional();
		given( vehicleRepositoryMock.findById(Mockito.anyLong())).willReturn(generateOneVehicleEntityOptional);
		
		VehicleDto response = this.vehicleService.getInfoVehicle(generateOneVehicleEntityOptional.get().getId());
		assertThat(response!= null, equalTo(true));
	}
	
	@Test
	void testeUpdateEmail() {
		VehicleDto generateOneVehicleDto = utils.generateOneVehicleDto();
		given( vehicleRepositoryMock.findById(Mockito.anyLong())).willReturn(utils.generateOneVehicleEntityOptional());
		given(vehicleRepositoryMock.save(Mockito.any(Vehicle.class))).willReturn(utils.generateOneVehicleEntity());
		
		VehicleDto response = this.vehicleService.updateVehicle( generateOneVehicleDto.getId(), generateOneVehicleDto);
		assertThat(response!= null, equalTo(true));
	}
	
	@Test
	void testeDeleteVehicle() {
		Optional<Vehicle> generateOneVehicletEntityOptional = utils.generateOneVehicleEntityOptional();
		given( vehicleRepositoryMock.findById(Mockito.anyLong())).willReturn(generateOneVehicletEntityOptional);

		VehicleDto response = this.vehicleService.deleteVehicle( generateOneVehicletEntityOptional.get().getId());
		
		assertThat(response!= null, equalTo(true));
	}
}
