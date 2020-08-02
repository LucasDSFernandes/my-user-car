package com.lucasdsf.api.user.builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.lucasdsf.api.user.domains.entity.Vehicle;
import com.lucasdsf.api.user.domains.model.UserDto;
import com.lucasdsf.api.user.domains.model.VehicleDto;

public class UserDtoBuilder {
	private Long id;
	private String login;
	private String email;
	private String firstName;
	private String lastName;
	private Long phoneNumber;
	private LocalDate birthday;
	private List<VehicleDto> vehicles;
	private LocalDateTime lastLogin;
	private LocalDateTime createdAt;
	
	public static UserDtoBuilder userDTOBuilder() {
		return new UserDtoBuilder();
	}
	
	public UserDtoBuilder id(Long id) {
		this.id = id;
		return this;		
	}
	public UserDtoBuilder firstName(String firtName) {
		this.firstName = firtName;
		return this;		
	}
	public UserDtoBuilder lastName(String lastName) {
		this.lastName = lastName;
		return this;		
	}
	public UserDtoBuilder login(String login) {
		this.login = login;
		return this;		
	}
	public UserDtoBuilder email(String email) {
		this.email = email;
		return this;		
	}
	public UserDtoBuilder phoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;		
	}
	public UserDtoBuilder birthday(LocalDate birthday) {
		this.birthday = birthday;
		return this;		
	}
	public UserDtoBuilder vehicles(List<VehicleDto> vehicles) {
		this.vehicles = vehicles;
		return this;		
	}
	public UserDtoBuilder lastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
		return this;		
	}
	public UserDtoBuilder createdAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		return this;		
	}
	
	public UserDto build() {
		UserDto userDto = new UserDto();
		userDto.setUserId(id);
		userDto.setEmail(email);
		userDto.setLogin(login);
		userDto.setFirstName(firstName);
		userDto.setLastName(lastName);
		userDto.setPhone(phoneNumber);
		userDto.setBirthday(birthday);
		userDto.setCars(vehicles);
		userDto.setLastLogin(lastLogin);
		userDto.setCreatedAt(createdAt);
		return userDto;
	} 

}
