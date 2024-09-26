package com.rashid.Rashid.forms.application.Secutiry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Usersys")
public class Usersys {
	
	@Id
	private String id;

	private String password = " ";
	private String username ;
	private String UserPermissions = "none" ;
	private String UserRoles ="none";
	private String employeeName ; 
	private String judgeCode ; 
	private Date createdAt ; 
	private boolean Active = true ; 
	private String userSeq ; 
	private String signature ; 
	private String userImage ; 
	
	
	public Usersys() {
		this.createdAt = new Date();
		UserPermissions = " ";
		UserRoles = " ";
	}

	public Usersys( String password, String username, String userPermissions, String userRoles,
			String employeeName, Date createdAt, boolean active, String userSeq) {
		super();
		this.password = password;
		this.username = username;
		UserPermissions = userPermissions;
		UserRoles = userRoles;
		this.employeeName = employeeName;
		this.createdAt = createdAt;
		Active = active;
		this.userSeq = userSeq;
	}

	
	
	
	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String audit() {
		return "User : [ user Id : "+id+" , userName : "+username+" ,Employee Name : "+employeeName+" ,User Role : "+UserRoles+"]";
	}


	public String getJudgeCode(){
		return judgeCode ; 
	}
	
	public void setJudgeCode(String judgeCode) {
		this.judgeCode = judgeCode ; 
	}

	public String getId() {
		return id;
	}

	public void setId(String userID) {
		id = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPermissions() {
		return UserPermissions;
	}

	public void setUserPermissions(String userPermissions) {
		UserPermissions = userPermissions;
	}

	public String getUserRoles() {
		return UserRoles;
	}

	public void setUserRoles(String userRoles) {
		UserRoles = userRoles;
	}

	public boolean isActive() {
		return Active;
	}

	public void setActive(boolean active) {
		Active = active;
	}
	

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public boolean hasRole(String role) {
		if(this.UserRoles.equalsIgnoreCase(" ")) {
			return false ; 
		}
		else if (!this.convertRolesToList().contains(role)) {
			return false ;
		}
		return true ; 
	}
	
	public boolean hasPermission(String permission) {
		if(this.UserPermissions.equalsIgnoreCase(" ")) {
			return false ; 
		}
		else if (!this.convertPermissionsToList().contains(permission)) {
			return false  ; 
		}
		return true ; 
	}

	public void addRole(String role ) {
		this.UserRoles = role ; 
	}
	
	public void addPermission(String permission ) {
		this.UserPermissions = permission;
	}

	public List<String> convertPermissionsToList(){
		return new ArrayList<String>() ;
		/*
		if(this.UserPermissions.equalsIgnoreCase("")) {
			return null ; 
		}
		else {
			List<String> userPermissions = new ArrayList<String>() ;
			String[] permissions = this.UserPermissions.split(",");
			for(int i = 0 ; i<permissions.length ; i++) {
				userPermissions.add(permissions[i]);
			}
			return userPermissions ; 
		}*/
	}
	
	public List<String> convertRolesToList(){
		if(this.UserRoles.equalsIgnoreCase("")) {
			return null ; 
		}
		else {
			List<String> userRoles = new ArrayList<String>() ;
			String[] roles = this.UserRoles.split(",");
			for(int i =0 ; i < roles.length ; i++) {
				userRoles.add(roles[i]); 
			}
			return userRoles ; 
			
		}
	}
	
	public void revokeRoleFromUser(String role ) {
		List<String> userRoles = this.convertRolesToList() ; 
		if(userRoles.size() != 0 ) {
		if(userRoles.contains(role)) {
			userRoles.remove(userRoles.indexOf(role));
			this.UserRoles = "";
			for(String tempRole : userRoles) {
				this.addRole(tempRole);
				}
			}
		}
	}
	
	public void revokePermissionFromUser(String permission) {
		List<String> userPermissions = this.convertPermissionsToList();
		if(userPermissions.size() != 0 ) {
			if(userPermissions.contains(permission)) {
				userPermissions.remove(permission);
				this.UserPermissions = "";
				for(String tempPermission : userPermissions) {
					this.addPermission(tempPermission);
				}
			}
		}
	}

	public String getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}

	
	
	
}
