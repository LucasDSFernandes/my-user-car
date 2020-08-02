package com.lucasdsf.api.user.domains.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
	
	@JsonProperty("id")
	private Long userId;
	
	@NotNull()
	private String lastName;
	@NotNull()
	private String firstName;
	@NotNull()
	private String email;
	@NotNull()
	private Long phone;
	@NotNull()
	private LocalDate birthday;
	
	private String password;
	@NotNull()
	private String login;
	
	private LocalDateTime lastLogin;
	
	private LocalDateTime createdAt;
		
	private List<VehicleDto> cars;

	private String message;
	
	private boolean sucess;
	
	public UserDto() {}
	public UserDto(String message, boolean sucess) {
		this.message = message;
		this.sucess = sucess;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstame) {
		this.firstName = firstame;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSucess() {
		return sucess;
	}
	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phoneNumber) {
		this.phone = phoneNumber;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public List<VehicleDto> getCars() {
		return cars;
	}
	public void setCars(List<VehicleDto> cars) {
		this.cars = cars;
	}
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
