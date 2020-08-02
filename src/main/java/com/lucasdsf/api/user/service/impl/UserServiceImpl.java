package com.lucasdsf.api.user.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lucasdsf.api.user.builder.ProfileUserBuilder;
import com.lucasdsf.api.user.builder.UserDtoBuilder;
import com.lucasdsf.api.user.builder.UserEntityBuilder;
import com.lucasdsf.api.user.builder.VehicleDtoBuilder;
import com.lucasdsf.api.user.domains.entity.ProfileUser;
import com.lucasdsf.api.user.domains.entity.User;
import com.lucasdsf.api.user.domains.entity.Vehicle;
import com.lucasdsf.api.user.domains.model.UserDto;
import com.lucasdsf.api.user.domains.model.VehicleDto;
import com.lucasdsf.api.user.domains.repository.ProfileRepository;
import com.lucasdsf.api.user.domains.repository.UserRepository;
import com.lucasdsf.api.user.enums.ProfileEnums;
import com.lucasdsf.api.user.exception.ErrorException;
import com.lucasdsf.api.user.service.UserService;
import com.lucasdsf.api.user.util.PasswordUtil;

@Component
public class UserServiceImpl implements UserService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		ProfileUser profile=null;

		checkLoginExist(userDto);
		checkEmailExist(userDto);
			
		profile = profileRepository.findByDescriptionProfile(ProfileEnums.ROLE_USER);
		
		if(!Optional.ofNullable(profile).isPresent()) {
			profile = ProfileUserBuilder.userDTOBuilder()
											.profile(ProfileEnums.ROLE_USER).build();
			logger.info("Inserindo perfil {} ao usuario {}", userDto.getLogin(), ProfileEnums.ROLE_USER);
			profileRepository.save(profile);
		}

		logger.info("Inserindo usuario. Login: {}, nome: {}", userDto.getLogin(), userDto.getFirstName());
		this.userRepository.save(UserEntityBuilder.userBuilder()
										.email(userDto.getEmail())
										.login(userDto.getLogin())
										.profile(profile)
										.password(PasswordUtil.generateBCrypt(userDto.getPassword()))
										.firstName(userDto.getFirstName())
										.lastName(userDto.getLastName())
										.phoneNumber(userDto.getPhone())
										.birthday(userDto.getBirthday())
										.build());
		return userDto;
	}

	private void checkEmailExist(UserDto userDto) {
		if(userRepository.findByEmail(userDto.getEmail()).isPresent()) {
			 throw new ErrorException("Email already exists");
		}
	}

	private void checkLoginExist(UserDto userDto) {
		if(userRepository.findByUserLogin(userDto.getLogin()).isPresent()) {
			 throw new ErrorException("Login already exists");
		}
	}
	
	@Override
	public UserDto getInfoUser(UserDto user) {
		return UserDtoBuilder.userDTOBuilder()
				.id(user.getUserId())
				.email(user.getEmail())
				.login(user.getLogin())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.phoneNumber(user.getPhone())
				.birthday(user.getBirthday())
				.createdAt(user.getCreatedAt())
				.lastLogin(user.getLastLogin())
				.vehicles(user.getCars())
				.build();
	}
	@Override
	public UserDto getInfoUser(Long id) {
		User user = findUser(id);
		
		return UserDtoBuilder.userDTOBuilder()
				.id(user.getId())
				.email(user.getEmail())
				.login(user.getUserLogin())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.phoneNumber(user.getPhoneNumber())
				.birthday(user.getBirthday())
				.createdAt(user.getCreatedAt())
				.lastLogin(user.getLastLogin())
				.vehicles(getBuildLisVehicle(user))
				.build();
	}

	private List<VehicleDto> getBuildLisVehicle(User user) {
		List<VehicleDto> vehicleList = new ArrayList<>();

		List<Vehicle> vehicles = user.getVehicle();
		vehicles.forEach(vehicle ->{
			vehicleList.add(VehicleDtoBuilder.vehicleDTOBuilder()
									.color(vehicle.getColor())
									.licensePlate(vehicle.getLicensePlate())
									.model(vehicle.getModel())
									.year(vehicle.getYear())
									.build());
				
		});
		return vehicleList;
	}
	@Override
	public UserDto updateUserEmail(Long id, UserDto userDto) {
		User userEntity = findUser(id);

		userEntity.setEmail(userDto.getEmail());
		logger.info("Atualizando email do login: {}, email: {}", userDto.getLogin(), userDto.getEmail());
		userRepository.save(userEntity);
		
		return new UserDto("Email alterado para "+userDto.getEmail(), true);
	}

	@Override
	public List<UserDto> listAll() {
		List<UserDto> userList = new ArrayList<>();
		List<User> userEntity = userRepository.findAll();
		userEntity.stream()
				.sorted(Comparator.comparing(User::getFirstName, Comparator.nullsLast(Comparator.naturalOrder())))
				.forEach(user ->  
					{
						userList.add(UserDtoBuilder.userDTOBuilder()
								.id(user.getId())
								.email(user.getEmail())
								.login(user.getUserLogin())
								.firstName(user.getFirstName())
								.lastName(user.getLastName())
								.phoneNumber(user.getPhoneNumber())
								.birthday(user.getBirthday())
								.createdAt(user.getCreatedAt())
								.lastLogin(user.getLastLogin())
								.vehicles(getBuildLisVehicle(user))
								.build());
					}
				);
		return userList;
	}

	private User findUser(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ErrorException("Usuario não encontrado."));
	}

	@Override
	public UserDto deleteUser(Long id) {
		userRepository.deleteById(id);
		return new UserDto("Usuário Deletado com sucesso!", true);
	}

	
}