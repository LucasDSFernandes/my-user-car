package com.lucasdsf.api.user.uteis;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.lucasdsf.api.user.domains.entity.ProfileUser;
import com.lucasdsf.api.user.domains.entity.User;
import com.lucasdsf.api.user.domains.entity.Vehicle;
import com.lucasdsf.api.user.domains.model.UserDto;
import com.lucasdsf.api.user.domains.model.VehicleDto;
import com.lucasdsf.api.user.enums.ProfileEnums;

	
@Component
public class TestUtils {

	private static final long USER_ID = 1l;
	private static final String EMAIL = "pitag@teste.com";
	private static final String LOGIN = "pitangAdmin";
	private static final String FIRSTNAME = "pitang";
	private static final String LASTNAME = "empresa";
	private static final Long PHONENUMBER = 81999229238l;
	private static int year = 2020;
	private static int dayOfMonth =1;
	private static int minutes =18;
	private static int hora=10;
	private static final LocalDate BIRTHDAY = LocalDate.of(year, Month.AUGUST, dayOfMonth);
	private static final LocalDateTime LASTLOGIN =  LocalDateTime.of(year, Month.AUGUST, dayOfMonth,hora, minutes);
	private static final LocalDateTime CREATEDAT =  LocalDateTime.of(year, Month.AUGUST, dayOfMonth,hora, minutes+10);
	
	private static final Long VEHICLE_ID = 2l;
	private static final String LICENSEPLATE = "AAA9999";
	private static final String MODEL = "HONDA";
	private static final Long YEAR = 2020L;
	private static final String COLOR = "BRANCO";

	public UserDto generateOneUserDto() {
		UserDto userDto = new UserDto();
		userDto.setUserId(USER_ID);
		userDto.setEmail(EMAIL);
		userDto.setLogin(LOGIN);
		userDto.setFirstName(FIRSTNAME);
		userDto.setLastName(LASTNAME);
		userDto.setPhone(PHONENUMBER);
		userDto.setBirthday(BIRTHDAY);
		userDto.setCars(generateOneListVehicleDto());
		userDto.setLastLogin(LASTLOGIN);
		userDto.setCreatedAt(CREATEDAT);
		return userDto;
	}
	
	public User generateOneUsertEntity() {
		User user = new User();
		user.setId(USER_ID);
		user.setEmail(EMAIL);
		user.setUserLogin(LOGIN);
		user.setFirstName(FIRSTNAME);
		user.setLastName(LASTNAME);
		user.setPhoneNumber(PHONENUMBER);
		user.setBirthday(BIRTHDAY);
		user.setVehicle(generateOneListVehicleEntity());
		user.setLastLogin(LASTLOGIN);
		user.setCreatedAt(CREATEDAT);
		return user;
	}
	public Optional<User> generateOneUsertEntityOptional() {
		Optional<User> user = Optional.of(generateOneUsertEntity());
		return user;
	}
	
	public List<UserDto> generateOneListOfUsers() {
		return Arrays.asList(
							generateOneUserDto(),
							generateOneUserDto(),
							generateOneUserDto(),
							generateOneUserDto()
							 );
	}
	public List<User> generateOneListOfUsersEntity() {
		return Arrays.asList(
				generateOneUsertEntity(),
				generateOneUsertEntity(),
				generateOneUsertEntity(),
				generateOneUsertEntity()
				);
	}
	
	public VehicleDto generateOneVehicleDto() {
		VehicleDto vehicleDto = new VehicleDto();
		vehicleDto.setId(VEHICLE_ID);
		vehicleDto.setLicensePlate(LICENSEPLATE);
		vehicleDto.setModel(MODEL);
		vehicleDto.setYear(YEAR);
		vehicleDto.setColor(COLOR);
		return vehicleDto;
	}

	public Optional<Vehicle> generateOneVehicleEntityOptional() {
		Optional<Vehicle> vehicle = Optional.of(generateOneVehicleEntity());
		return vehicle;
	}
	public Vehicle generateOneVehicleEntity() {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(VEHICLE_ID);
		vehicle.setLicensePlate(LICENSEPLATE);
		vehicle.setModel(MODEL);
		vehicle.setYear(YEAR);
		vehicle.setColor(COLOR);
//		vehicle.setUser(generateOneUsertEntity());
		return vehicle;
	}
	
	public List<VehicleDto> generateOneListVehicleDto() {
		return Arrays.asList(
								generateOneVehicleDto(),
								generateOneVehicleDto(),
								generateOneVehicleDto()
							);
	}

	public List<Vehicle> generateOneListVehicleEntity() {
		return Arrays.asList(
				generateOneVehicleEntity(), 
				generateOneVehicleEntity(), 
				generateOneVehicleEntity(), 
				generateOneVehicleEntity(), 
				generateOneVehicleEntity()
				);
	}
	
	public ProfileUser generateOneProfileEntity() {
		ProfileUser profile = new ProfileUser();
		profile.setId(1L);
		profile.setDescriptionProfile(ProfileEnums.ROLE_USER);
		return profile;
	}
}
