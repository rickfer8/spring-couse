package com.springcouse.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.springcouse.domain.enuns.RequestState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Request {

	private Long id;
	private String subject;
	private String description;
	private Date criationDate;
	private RequestState state;
	private User user;
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	
}
