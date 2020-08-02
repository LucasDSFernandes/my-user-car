package com.lucasdsf.api.user.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.lucasdsf.api.user.domains.entity.ProfileUser;
import com.lucasdsf.api.user.domains.entity.User;
import com.lucasdsf.api.user.domains.model.UserDto;
import com.lucasdsf.api.user.domains.repository.ProfileRepository;
import com.lucasdsf.api.user.domains.repository.UserRepository;
import com.lucasdsf.api.user.enums.ProfileEnums;
import com.lucasdsf.api.user.service.UserService;
import com.lucasdsf.api.user.uteis.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
class UserServiceImplTest {
	@Inject
	TestUtils utils;

	@Inject
	private UserService userService;

	@MockBean
	private UserRepository userRepositoryMock;
	@MockBean
	private ProfileRepository profileRepository;
	
	@Test
	void testCreateUserSucess(){
		given(profileRepository.findByDescriptionProfile(ProfileEnums.ROLE_USER)).willReturn(utils.generateOneProfileEntity());
		given(profileRepository.save(Mockito.any(ProfileUser.class))).willReturn(utils.generateOneProfileEntity());
		given(userRepositoryMock.findByUserLogin(Mockito.anyString())).willReturn( Optional.empty());
		given(userRepositoryMock.findByEmail(Mockito.anyString())).willReturn( Optional.empty());
		given(userRepositoryMock.save(Mockito.any(User.class))).willReturn(utils.generateOneUsertEntity());

		UserDto response = this.userService.createUser(utils.generateOneUserDto());

		assertThat(response.getMessage() != null, equalTo(false));
	}
	@Test
	void testCreateUserLoginExist(){
		given(profileRepository.findByDescriptionProfile(ProfileEnums.ROLE_USER)).willReturn(utils.generateOneProfileEntity());
		given(profileRepository.save(Mockito.any(ProfileUser.class))).willReturn(utils.generateOneProfileEntity());
		Optional<User> generateOneUsertEntityOptional = utils.generateOneUsertEntityOptional();
		given(userRepositoryMock.findByUserLogin(Mockito.anyString())).willReturn(generateOneUsertEntityOptional);
		given(userRepositoryMock.findByEmail(Mockito.anyString())).willReturn( Optional.empty());
		given(userRepositoryMock.save(Mockito.any(User.class))).willReturn(generateOneUsertEntityOptional.get());
		
		Exception exception =
				assertThrows(Exception.class, () -> 
				this.userService.createUser(utils.generateOneUserDto())
						);
		assertTrue(exception.getMessage().equalsIgnoreCase("Login already exists"));
	}
	@Test
	void testCreateUserEmailExist(){
		given(profileRepository.findByDescriptionProfile(ProfileEnums.ROLE_USER)).willReturn(utils.generateOneProfileEntity());
		given(profileRepository.save(Mockito.any(ProfileUser.class))).willReturn(utils.generateOneProfileEntity());
		Optional<User> generateOneUsertEntityOptional = utils.generateOneUsertEntityOptional();
		given(userRepositoryMock.findByUserLogin(Mockito.anyString())).willReturn( Optional.empty());
		given(userRepositoryMock.findByEmail(Mockito.anyString())).willReturn(generateOneUsertEntityOptional);
		given(userRepositoryMock.save(Mockito.any(User.class))).willReturn(generateOneUsertEntityOptional.get());
		
		Exception exception =
				assertThrows(Exception.class, () -> 
				this.userService.createUser(utils.generateOneUserDto())
						);
		assertTrue(exception.getMessage().equalsIgnoreCase("Email already exists"));
	}
	
	@Test
	void testFindAllUsers() {
		given( userRepositoryMock.findAll()).willReturn( utils.generateOneListOfUsersEntity() );
		given( userRepositoryMock.findAll()).willReturn( utils.generateOneListOfUsersEntity() );
		
		List<UserDto> response = this.userService.listAll();
		assertThat(response.isEmpty(), equalTo(false));
	}
	
	@Test
	void testeFindInfoUser() {
		given( userRepositoryMock.findById(Mockito.anyLong())).willReturn(utils.generateOneUsertEntityOptional());
		
		UserDto response = this.userService.getInfoUser(utils.generateOneUserDto().getUserId());
		assertThat(response != null, equalTo(true));
	}
	
	@Test
	void testeUpdateEmail() {
		given( userRepositoryMock.findById(Mockito.anyLong())).willReturn(utils.generateOneUsertEntityOptional());
		given(userRepositoryMock.save(Mockito.any(User.class))).willReturn(utils.generateOneUsertEntity());
		
		UserDto generateOneUserDto = utils.generateOneUserDto();
		UserDto response = this.userService.updateUserEmail(generateOneUserDto.getUserId(), generateOneUserDto);
		assertThat(response != null, equalTo(true));
	}
	
	@Test
	void testeDeleteUser() {
		Optional<User> generateOneUsertEntityOptional = utils.generateOneUsertEntityOptional();
		given( userRepositoryMock.findById(Mockito.anyLong())).willReturn(utils.generateOneUsertEntityOptional());

		UserDto response = this.userService.deleteUser(generateOneUsertEntityOptional.get().getId());
		
		assertThat(response != null, equalTo(true));
	}

}
