package com.springcouse.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.springcouse.domain.Request;
import com.springcouse.domain.RequestStage;
import com.springcouse.domain.User;
import com.springcouse.domain.enuns.Role;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserSaveDto {
	
	@NotBlank(message = "Name required")
	private String name;
	
	@Email(message = "Email required")
	private String email;

	@Size(min = 7, max = 99, message = "Password must be between 7 and 99")
	private String password;
	
	@NotNull(message = "Role Required")
	private Role role;
	
	private List<Request> requets = new ArrayList<Request>();
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	
	public User transformToUser() {
		User user = new User(null,this.name,this.email,this.password,this.role,this.requets,this.stages);
		return user;
	}

}
