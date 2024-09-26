package com.rashid.Rashid.forms.application.Secutiry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {

	private String username ; 
	
	private String employeeName ;
	
	private String judgeCode ; 
	
	private String password ;  
		
	private String signature ;
	
	private String userImage ;	
	
	private String role ;

	@Override
	public String toString() {
		return "AddUserRequest [username=" + username + ", employeeName=" + employeeName + ", judgeCode=" + judgeCode
				+ ", password=" + password + ", userImage=" + userImage + ", role=" + role + "]";
	} 

	
	
}
