package com.springcouse.dto;

import javax.validation.constraints.NotNull;

import com.springcouse.domain.Request;
import com.springcouse.domain.RequestStage;
import com.springcouse.domain.User;
import com.springcouse.domain.enuns.RequestState;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestStageSaveDto {
	
	
	private String description;
	
	@NotNull(message = "State Requeride")
	private RequestState state;
	
	@NotNull(message = "Request Requeride")
	private Request request;
	
	@NotNull(message = "Owner Requeride")
	private User owner;
	
	public RequestStage transformToRequestStage() {
		RequestStage stage = new RequestStage(null, null, this.description, this.state, this.request, this.owner);
		return stage;
	}

}
