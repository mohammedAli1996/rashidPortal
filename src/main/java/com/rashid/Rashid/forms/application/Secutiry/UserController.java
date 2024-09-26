package com.rashid.Rashid.forms.application.Secutiry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired 
	private UserService userService ; 
	
	@Autowired
	public MasterService masterService ;
	
	class Load{
		private String role ;
		public void setRole(String request ) {
			role = request ; 
		}
		public String getRole() {
			return role ; 
		}
	}  
	
	@GetMapping("/role")
	public Load getUserRole() {
		Load load = new Load();
		load.setRole(masterService.get_current_User().getUserRoles());
		return load;
	}
	
	@GetMapping("/add")  
	public ModelAndView getAddUser() {
		ModelAndView mav = new ModelAndView("users/adduser");
		mav.addObject("request", new AddUserRequest());
		mav.addObject("roles", userService.getCurrSystemRoles());
		return mav ; 
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addUser(@RequestBody AddUserRequest request ) {
		ApiResponse response = new ApiResponse() ; 
		try {
			response.setBody(userService.addUser(request));
			return ResponseEntity.ok(response);
		}catch(Exception ex) {
			response.setMsg(ex.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
	}
	
	
	@GetMapping("/getUser/{id}")
	public ModelAndView getUser(@PathVariable String id ) {
		ModelAndView mav = new ModelAndView("users/editUser");
		mav.addObject("id", id);
		return mav ;   
	}  
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<?> editUser(@RequestBody AddUserRequest request , @PathVariable String id ) {
		ApiResponse response = new ApiResponse() ; 
		try {
			response.setBody(userService.editUser(request,id));
			return ResponseEntity.ok(response);
		}catch(Exception ex) {
			response.setMsg(ex.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}  
	}
	  

	 
	@GetMapping("/list")
	public List<UserResponse> getUsersList(@RequestParam(name = "role") String role ){
		 return userService.getAllUsers(true, role);
	}
	
	@GetMapping("/allUsers")
	public List<UserResponse> getAllUsers(){
		return userService.getAllUsers();
	}  
	  
	
	@GetMapping("/usersView")
	public ModelAndView getUsersListView() {
		return new ModelAndView("users/userslist");  
	}  
	
	
	@PostMapping("/activate")
	public ResponseEntity<?> activateUserById(@RequestParam(name = "userId" , required = true ) String userId ) {
		ApiResponse response = new ApiResponse() ; 
		try {
			response.setBody(userService.activateUser(userId));
			return ResponseEntity.ok(response);
		}catch(Exception ex) {
			response.setMsg(ex.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
	}
	
	@PostMapping("/deActivateUser")
	public ResponseEntity<?> deActivateUserById(@RequestParam(name = "userId" , required = true ) String userId ) {
		ApiResponse response = new ApiResponse() ; 
		try {
			response.setBody(userService.deActivateUser(userId));
			return ResponseEntity.ok(response);
		}catch(Exception ex) {
			response.setMsg(ex.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
	}
	
//	@GetMapping("/all")
//	public ModelAndView getAllActiveUsers(){
//		ModelAndView mav = new ModelAndView("Users/all");
//		mav.addObject("users", userService.getAllUsers(true));
//		return mav;
//	}
//	
//	@GetMapping("/NA/all")
//	public ModelAndView getAllNonActiveUsers(){
//		ModelAndView mav = new ModelAndView("Users/nonActive");
//		mav.addObject("users", userService.getAllUsers(false));
//		return mav;
//	}
	
}
