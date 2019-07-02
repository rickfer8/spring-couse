package com.springcouse.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.springcouse.domain.Request;
import com.springcouse.domain.RequestStage;
import com.springcouse.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestSaveDto {
	
	@NotBlank(message = "Subject Required")
	private String subject;
	
	private String description;
	
	@NotNull(message = "Owner Required")
	private User owner;
	
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	
	public Request transformToRequest() {
		Request request = new Request(null,this.subject,this.description,null,null,this.owner,this.stages);
		return request;
	}

}
