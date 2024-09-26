package com.rashid.Rashid.forms.application.Secutiry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

	private String id ; 
	
	private String userName ; 
	
	private String employeeName ;
	
	private String role ; 

	private String judgeCode ; 
	
	private boolean active ; 
	
	public UserResponse() {}
		
	public UserResponse(String id, String userName, String employeeName, String role) {
		super();
		this.id = id;
		this.userName = userName;
		this.employeeName = employeeName;
		this.role = role;
	}
   
}
