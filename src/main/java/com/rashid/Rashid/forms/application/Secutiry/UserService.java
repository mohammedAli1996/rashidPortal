package com.rashid.Rashid.forms.application.Secutiry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rashid.Rashid.forms.application.ServiceException;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository ; 
	
	@Autowired
	public MasterService masterService ;
	
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private List<String> currSystemRoles = new ArrayList<String>();
	
	@PostConstruct
	private void initRolesList() {
		currSystemRoles.add("Admin");
		currSystemRoles.add("Employee");
		currSystemRoles.add("manager");
		currSystemRoles.add("JUDGE");
		currSystemRoles.add("master");
		currSystemRoles.add("PHOTOGRAPHER");
		currSystemRoles.add("CALLOFFICER");
		currSystemRoles.add("ENTRY");
	}
	
	/*add user */
	
	public boolean addUser(AddUserRequest request ) {
		
		
		Usersys user = new Usersys() ; 
		user.setActive(true);
		user.setEmployeeName(request.getEmployeeName());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setUsername(request.getUsername());
		user.setUserRoles(request.getRole());
		user.setUserPermissions(request.getRole());
		user.setJudgeCode(request.getJudgeCode());
//		user.setUserImage(request.getUserImage());
//		user.setSignature(request.getSignature());
		user = userRepository.save(user);
		
		return true ; 
	}
	

	public boolean editUser(AddUserRequest request , String id  ) {
		
		Usersys user = getUser(id); 
		

		user.setEmployeeName(request.getEmployeeName());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setUsername(request.getUsername());
		user.setUserRoles(request.getRole());
		user.setUserPermissions(request.getRole());
		user.setJudgeCode(request.getJudgeCode());
		user.setUserImage(request.getUserImage());
		user.setSignature(request.getSignature());
		user = userRepository.save(user);
		
		
		return true ; 
	}
	
	
	
	
	
	/*Getters*/
	
	public List<UserResponse> getAllUsers(boolean active,String role ){
		List<UserResponse> users = new ArrayList<UserResponse>();
		masterService.get_current_User() ; 
		for(Usersys user : userRepository.findAll()) {
			if(user.isActive() != active) {
				continue ;
			}
			if(!user.getUserRoles().equals(role)) {
				continue ; 
			}
			UserResponse response = new UserResponse();
			response.setEmployeeName(user.getEmployeeName());
			response.setId(user.getId());
			response.setJudgeCode(user.getJudgeCode());
			users.add(response);
		}
		return users ; 
	}
	
	
	public List<UserResponse> getAllUsers(){
		List<UserResponse> users = new ArrayList<UserResponse>(); 
		for(Usersys user : userRepository.findAll()) {
			if(user.getUsername().equalsIgnoreCase("Admin")) {
				continue;
			}
			UserResponse response = new UserResponse();
			response.setJudgeCode(user.getJudgeCode());
			response.setEmployeeName(user.getEmployeeName());
			response.setUserName(user.getUsername());
			response.setRole(user.getUserRoles());
			response.setActive(user.isActive());
			response.setId(user.getId());
			users.add(response);
		}
		return users ; 
	}
	
	public Usersys getUser(String id ) {
		Optional<Usersys> optional = this.userRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		throw new ServiceException("User not found");
	}

	
	
	/*Active state control*/
	public boolean activateUser(String userId ) {
		 Usersys user = getUser(userId);
		 if(user.getUsername().equalsIgnoreCase("Admin")) {
			return false ; 
		 }
		 user.setActive(true);
//		 try {
//				logService.addLog("User activated", userId, false);
//			}catch(Exception ex ) { 
//				ex.printStackTrace();
//			}
		 return this.userRepository.save(user).isActive();
	}
	
	public boolean deActivateUser(String userId ) {
		 Usersys user = getUser(userId);
		 if(user.getUsername().equalsIgnoreCase("Admin")) {
				return false ; 
		 }
		 user.setActive(false);
//		 try {
//				logService.addLog("User de-activated", userId, false);
//			}catch(Exception ex ) { 
//				ex.printStackTrace();
//			}
		 return this.userRepository.save(user).isActive();
	}
	
	
	/*Sys roles*/
	
	public List<String> getCurrSystemRoles() {
		return currSystemRoles;
	}

	public void setCurrSystemRoles(List<String> currSystemRoles) {
		this.currSystemRoles = currSystemRoles;
	}
	
}
