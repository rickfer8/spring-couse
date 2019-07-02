package com.springcouse.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcouse.domain.Request;
import com.springcouse.domain.User;
import com.springcouse.dto.UserLoginDto;
import com.springcouse.dto.UserSaveDto;
import com.springcouse.dto.UserUpdateDto;
import com.springcouse.dto.UserUpdateRoleDto;
import com.springcouse.model.PageModel;
import com.springcouse.model.PageRequestModel;
import com.springcouse.service.RequestService;
import com.springcouse.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RequestService requestService;
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody @Valid UserSaveDto userDto){
		User user = userDto.transformToUser();
		User cretedUser = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(cretedUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @RequestBody @Valid UserUpdateDto userDto){
		User user = userDto.transformToUser();
		user.setId(id);
		User updateUser = userService.update(user);
		return ResponseEntity.ok(updateUser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable(name = "id") Long id){
		User user = userService.getById(id);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<PageModel<User>> listAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> pm = userService.lsitAllOnLazyMode(pr);		
		
		return ResponseEntity.ok(pm);
	}	
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody @Valid UserLoginDto user){
		User loggedUser = userService.login(user.getEmail(), user.getPassword());
		return ResponseEntity.ok(loggedUser);
	}
	
	@GetMapping("/{id}/requests")
	public ResponseEntity<PageModel<Request>> listAllRequestById(
			@PathVariable(name = "id") Long id,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listAllByOwnerIdOnLazyModel(id, pr);		
		
		return ResponseEntity.ok(pm);		
	}
	
	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRole(
			@PathVariable(name = "id") Long id, 
			@RequestBody @Valid UserUpdateRoleDto userDto) {
		User user = new User();
		user.setId(id);
		user.setRole(userDto.getRole());
		userService.updateRole(user);

		return ResponseEntity.ok().build();
	}

}
