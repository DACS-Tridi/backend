package com.dacs.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dacs.backend.dto.UserDTO;
import com.dacs.backend.exeptions.ResourceNotFoundException;
import com.dacs.backend.model.entity.User;
import com.dacs.backend.service.UserService;



@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("")
	public ResponseEntity<List<UserDTO>> getAll() {
		List<User> users = userService.getAll();
		List<UserDTO> data = users.stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<UserDTO>>(data, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		Optional<User> user = userService.getById(id);

		if (user.isEmpty()) {
			throw new ResourceNotFoundException("");
		}
		UserDTO data = modelMapper.map(user.get(), UserDTO.class);
		return new ResponseEntity<UserDTO>(data, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO UserDTO) {
		User user = modelMapper.map(UserDTO, User.class);
		UserDTO data = modelMapper.map(userService.save(user), UserDTO.class);
		return new ResponseEntity<UserDTO>(data, HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO UserDTO) throws ResourceNotFoundException {

		if (UserDTO.getId() == null || !userService.existById(UserDTO.getId())) {
			throw new ResourceNotFoundException("");
		}

		User user = modelMapper.map(UserDTO, User.class);
		UserDTO data = modelMapper.map(userService.save(user), UserDTO.class);
		return new ResponseEntity<UserDTO>(data, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {

		if (id == null || !userService.existById(id)) {
			throw new ResourceNotFoundException("");
		}

		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
