package com.vehicleloan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicleloan.controller.model.user.request.LoginRequestModel;
import com.vehicleloan.dao.user.User;
import com.vehicleloan.dao.user.UserDAO;

@RestController
@RequestMapping("user")
public class UserController {
	private UserDAO userDAO;

	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable int userId) {
		User user = userDAO.get(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userDAO.getAll();

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody LoginRequestModel loginRequestModel) {
		User user = userDAO.get(loginRequestModel.getUsername(), loginRequestModel.getPassword());
		System.out.println(user);
		if (user == null)
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> createUser(@RequestBody User newUser) {
		boolean isCreated = userDAO.create(newUser);

		if (isCreated)
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		boolean isUpdated = userDAO.update(user);

		if (isUpdated)
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable int userId) {
		boolean isDeleted = userDAO.deleteById(userId);

		if (isDeleted)
			return new ResponseEntity<Object>(HttpStatus.OK);
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}
}
