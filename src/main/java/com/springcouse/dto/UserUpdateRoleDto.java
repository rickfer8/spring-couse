package com.springcouse.dto;

import javax.validation.constraints.NotNull;

import com.springcouse.domain.enuns.Role;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserUpdateRoleDto {
	
	@NotNull(message = "Role required")
	private Role role;

}
