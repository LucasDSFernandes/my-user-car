package com.lucasdsf.api.user.builder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.lucasdsf.api.user.domains.entity.ProfileUser;
import com.lucasdsf.api.user.domains.entity.User;

public class UserEntityBuilder {
	
	private Long userId;
	private String email;
	private String password;
	private ProfileUser profile;
	private String login;
	private String firstName;
	private String lastName;
	private Long phoneNumber;
	private LocalDate birthday;
	
	public static UserEntityBuilder userBuilder() {
		return new UserEntityBuilder();
	}
	
	public UserEntityBuilder userId(Long userId) {
		this.userId = userId;
		return this;
	}
	
	public UserEntityBuilder lastName(String lastName) {
		this.lastName = lastName;
		return this;		
	}
	public UserEntityBuilder firstName(String firstName) {
		this.firstName = firstName;
		return this;		
	}
	public UserEntityBuilder email(String email) {
		this.email = email;
		return this;		
	}
	public UserEntityBuilder phoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;		
	}
	public UserEntityBuilder password(String password) {
		this.password = password;
		return this;		
	}
	public UserEntityBuilder profile(ProfileUser profile) {
		this.profile = profile;
		return this;		
	}
	public UserEntityBuilder login(String login) {
		this.login = login;
		return this;		
	}
	public UserEntityBuilder birthday(LocalDate birthday) {
		this.birthday = birthday;
		return this;		
	}
	
	public User build() {
		User user = new User();
		user.setId(userId);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setBirthday(birthday);
		user.setPhoneNumber(phoneNumber);
		user.setUserLogin(login);
		user.setPassword(password);
		user.setProfile(profile);
		user.setCreatedAt(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toLocalDateTime());
		return user;
	} 

}
