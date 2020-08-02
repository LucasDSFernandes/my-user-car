package com.lucasdsf.api.user.rest.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.lucasdsf.api.user.client.AuthService;
import com.lucasdsf.api.user.domains.entity.User;
import com.lucasdsf.api.user.domains.entity.Vehicle;
import com.lucasdsf.api.user.domains.model.VehicleDto;
import com.lucasdsf.api.user.domains.repository.UserRepository;
import com.lucasdsf.api.user.domains.repository.VehicleRepository;
import com.lucasdsf.api.user.rest.resouces.VehicleReources;
import com.lucasdsf.api.user.service.VehicleService;
import com.lucasdsf.api.user.service.impl.VehicleServiceImpl;
import com.lucasdsf.api.user.uteis.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
class VehicleControllerTest {

	@Inject
	VehicleReources resource;
	@Inject
	TestUtils utils;

	@Mock
	VehicleService vehicleServiceMock;
	@MockBean
	private VehicleServiceImpl vehicleService;
	@MockBean
	private AuthService authService;
	@MockBean
	private VehicleRepository vehicleRepositoryMock;
	@MockBean
	private UserRepository userRepositoryMock;
	
	@Test
	void testCreateVehicleSucess(){
		given( authService.getPrincipal(Mockito.anyString())).willReturn(utils.generateOneUserDto());
		given(userRepositoryMock.findByUserLogin(Mockito.anyString())).willReturn( Optional.empty());
		given(vehicleRepositoryMock.findByLicensePlate(Mockito.anyString())).willReturn( Optional.empty());
		given(vehicleRepositoryMock.save(Mockito.any(Vehicle.class))).willReturn(utils.generateOneVehicleEntity());
		when(vehicleServiceMock.createVehicle(Mockito.any(VehicleDto.class), Mockito.anyString())).thenReturn(utils.generateOneVehicleDto());

		ResponseEntity<VehicleDto> response = this.resource.createVehicle( Mockito.anyString(), utils.generateOneVehicleDto());

		assertThat(response.getBody()== null, equalTo(true));
	}
	
	@Test
	void testFindAllVehicles() {
		given( authService.getPrincipal(Mockito.anyString())).willReturn(utils.generateOneUserDto());
		given( vehicleRepositoryMock.findAllByUser(Mockito.any(User.class))).willReturn( utils.generateOneListVehicleEntity());
		when( vehicleServiceMock.listAll( Mockito.anyString())).thenReturn(utils.generateOneListVehicleDto());
		
		ResponseEntity<List<VehicleDto>> response = this.resource.listAll( Mockito.anyString());
		assertThat(!response.getBody().isEmpty(), equalTo(false));
	}
	
	@Test
	void testeFindInfoVehicle() {
		given( authService.getPrincipal(Mockito.anyString())).willReturn(utils.generateOneUserDto());
		given( vehicleRepositoryMock.findById(Mockito.anyLong())).willReturn(utils.generateOneVehicleEntityOptional());
		when( vehicleServiceMock.listAll( Mockito.anyString())).thenReturn(utils.generateOneListVehicleDto());
		
		ResponseEntity<VehicleDto> response = this.resource.getInfoCar(Mockito.anyString(),
				utils.generateOneVehicleDto().getId());
		assertThat(response.getBody() == null, equalTo(true));
	}
	
	@Test
	void testeUpdateEmail() {
		given( authService.getPrincipal(Mockito.anyString())).willReturn(utils.generateOneUserDto());
		given( vehicleRepositoryMock.findById(Mockito.anyLong())).willReturn(utils.generateOneVehicleEntityOptional());
		given(vehicleRepositoryMock.save(Mockito.any(Vehicle.class))).willReturn(utils.generateOneVehicleEntity());
		
		VehicleDto generateOneVehicleDto = utils.generateOneVehicleDto();
		ResponseEntity<VehicleDto> response = this.resource.updateVehicle( Mockito.anyString(), generateOneVehicleDto.getId(), generateOneVehicleDto);
		assertThat(response.getBody() == null, equalTo(true));
	}
	
	@Test
	void testeDeleteVehicle() {
		given( authService.getPrincipal(Mockito.anyString())).willReturn(utils.generateOneUserDto());

		Optional<Vehicle> generateOneVehicletEntityOptional = utils.generateOneVehicleEntityOptional();
		when( vehicleRepositoryMock.findById(Mockito.anyLong())).thenReturn(generateOneVehicletEntityOptional);

		ResponseEntity<VehicleDto> response = this.resource.deleteVehicle( Mockito.anyString(),generateOneVehicletEntityOptional.get().getId());
		
		assertThat(response.getBody() == null, equalTo(true));
	}

}
