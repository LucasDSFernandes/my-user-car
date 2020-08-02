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
import com.lucasdsf.api.user.domains.entity.ProfileUser;
import com.lucasdsf.api.user.domains.entity.User;
import com.lucasdsf.api.user.domains.model.UserDto;
import com.lucasdsf.api.user.domains.repository.ProfileRepository;
import com.lucasdsf.api.user.domains.repository.UserRepository;
import com.lucasdsf.api.user.enums.ProfileEnums;
import com.lucasdsf.api.user.rest.resouces.UserReources;
import com.lucasdsf.api.user.service.UserService;
import com.lucasdsf.api.user.service.impl.UserServiceImpl;
import com.lucasdsf.api.user.uteis.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
class UserControllerTest {
	@Inject
	UserReources resource;
	@Inject
	TestUtils utils;

	@Mock
	UserService userServiceMock;
	@MockBean
	private UserServiceImpl userService;
	@MockBean
	private AuthService authService;

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
		when(userServiceMock.createUser(Mockito.any(UserDto.class))).thenReturn(utils.generateOneUserDto());

		ResponseEntity<UserDto> response = this.resource.createUser(utils.generateOneUserDto());

		assertThat(response.getBody()== null, equalTo(true));
	}
	
	@Test
	void testFindAllUsers() {
		given( userRepositoryMock.findAll()).willReturn( utils.generateOneListOfUsersEntity() );
		given( userRepositoryMock.findAll()).willReturn( utils.generateOneListOfUsersEntity() );
		when( userServiceMock.listAll() ).thenReturn(utils.generateOneListOfUsers());
		
		ResponseEntity<List<UserDto>> response = this.resource.listAll();
		assertThat(!response.getBody().isEmpty(), equalTo(false));
	}
	
	@Test
	void testeFindInfoUser() {
		given( userRepositoryMock.findById(Mockito.anyLong())).willReturn(utils.generateOneUsertEntityOptional());
		when( userServiceMock.listAll() ).thenReturn(utils.generateOneListOfUsers());
		
		ResponseEntity<UserDto> response = this.resource.getInfoUser(utils.generateOneUserDto().getUserId());
		assertThat(response.getBody() == null, equalTo(true));
	}
	
	@Test
	void testeUpdateEmail() {
		given(userRepositoryMock.save(Mockito.any(User.class))).willReturn(utils.generateOneUsertEntity());
		when( userServiceMock.listAll() ).thenReturn(utils.generateOneListOfUsers());
		
		UserDto generateOneUserDto = utils.generateOneUserDto();
		ResponseEntity<UserDto> response = this.resource.updateEmail(generateOneUserDto.getUserId(), generateOneUserDto);
		assertThat(response.getBody() == null, equalTo(true));
	}
	
	@Test
	void testeDeleteUser() {
		Optional<User> generateOneUsertEntityOptional = utils.generateOneUsertEntityOptional();
		when( userRepositoryMock.findById(Mockito.anyLong())).thenReturn(generateOneUsertEntityOptional);

		ResponseEntity<UserDto> response = this.resource.deleteUser(generateOneUsertEntityOptional.get().getId());
		
//		Mockito.verify(userRepositoryMock, times(1)).delete(generateOneUsertEntityOptional.get());
		assertThat(response.getBody() == null, equalTo(true));
}
	
	
	@Test
	void testFindUser() {
		UserDto generateOneUserDto = utils.generateOneUserDto();
		given( authService.getPrincipal(Mockito.anyString())).willReturn(generateOneUserDto);
		when( userServiceMock.getInfoUser(Mockito.any(UserDto.class))).thenReturn(generateOneUserDto);
		
		ResponseEntity<UserDto> response = this.resource.getUser(Mockito.anyString());
		assertThat(response.getBody() != null, equalTo(false));
	}
}
