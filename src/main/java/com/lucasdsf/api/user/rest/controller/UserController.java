package com.lucasdsf.api.user.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasdsf.api.user.client.AuthService;
import com.lucasdsf.api.user.domains.model.UserDto;
import com.lucasdsf.api.user.rest.resouces.UserReources;
import com.lucasdsf.api.user.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController implements UserReources{

	@Autowired
	private UserService userService;
	@Autowired
	private AuthService authService;
	@Override
	@PostMapping(value = "/users/")
	public ResponseEntity<UserDto> createUser(UserDto user) {
		return ResponseEntity.ok(userService.createUser(user));
	}
	
	@Override
	@GetMapping(value = "/me")
	public ResponseEntity<UserDto> getUser(@RequestHeader("Authorization") String authorization) {
		UserDto user = authService.getPrincipal(authorization);
		return ResponseEntity.ok(userService.getInfoUser(user));
	}
	
	@Override
	@GetMapping(value = "/users/")
	public ResponseEntity<List<UserDto>> listAll() {
		return ResponseEntity.ok(userService.listAll());
	}

	@Override
	@GetMapping(value = "/users/{id}")
	public ResponseEntity<UserDto> getInfoUser(@PathVariable("id")  Long id) {
		return ResponseEntity.ok(userService.getInfoUser(id));
	}

	@Override
	@PutMapping(value = "users/{id}")
	public ResponseEntity<UserDto> updateEmail(@PathVariable("id")Long id, @RequestBody  UserDto  userDto){
		return ResponseEntity.ok(userService.updateUserEmail(id, userDto));
	}
	
	@Override
	@DeleteMapping(value = "users/{id}")
	public ResponseEntity<UserDto> deleteUser(@PathVariable("id")Long id){
		return ResponseEntity.ok(userService.deleteUser(id));
	}
}
