package com.rashid.Rashid.forms.application.Secutiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;  

	    
//	@GetMapping("/main") 
//	public ModelAndView mainPage() {    
//		ModelAndView mav = new ModelAndView("sysIndex");  
//		return mav ;   
//	}  
	
//	@GetMapping("/qtest") 
//	public ModelAndView qtest() {    
//		ModelAndView mav = new ModelAndView("qrtest");  
//		return mav ;   
//	}   
	                
	          
//	@GetMapping("/getNav")      
//	public ModelAndView getNav() {
////		ModelAndView mav = new ModelAndView("navNoOptions");
//		ModelAndView mav = new ModelAndView("nav");
//		return mav ; 
//	}  
// 
//	
//	@GetMapping("/index")
//	public ModelAndView index() {
//		ModelAndView mav = new ModelAndView("closed");  
//		return mav ; 
//	}
//	 
//	@GetMapping("/")
//	public ModelAndView root() {
//		ModelAndView mav = new ModelAndView("closed");
//		return mav ; 
//	}
	
//	@GetMapping("/login")
//    public ModelAndView login() {
//    	ModelAndView mav = new ModelAndView("login");  
//    	return mav; 
//    }
////    
//    @GetMapping("/forbidden")
//    public ModelAndView accessDenied() {
//    	ModelAndView mav = new ModelAndView("forbidden");
//    	return mav; 
//    }
    

    @RequestMapping(method = RequestMethod.GET , value = "/config/injectuser")
	private void injectUser() {
    	if(userRepository.findAll().size() == 0 ) {
    		Usersys user = new Usersys(); 
    		user.setUsername("Admin");
    		user.setPassword(new BCryptPasswordEncoder().encode("RS@Admin@123"));
    		user.addRole("owner");
    		user.addPermission("owner");
    		this.userRepository.save(user);
    	}
//    	
//    	Usersys user = new Usersys();
//		user.setUsername("Manager@CustomshowEmirates");
//		user.setPassword(new BCryptPasswordEncoder().encode("CustomShowManagement@3321123"));
//		user.addRole("manager");
//		user.addPermission("manager");
//		this.userRepository.save(user);
//		
//    	
//    	Usersys userJamal = new Usersys();
//		userJamal.setUsername("jamal@CustomshowEmirates.net");
//		userJamal.setPassword(new BCryptPasswordEncoder().encode("RV@623Jamal"));
//		userJamal.addRole("owner");
//		userJamal.addPermission("owner");
//		this.userRepository.save(userJamal);
//		
//    	Usersys userSaleh = new Usersys();
//		userSaleh.setUsername("saleh@CustomshowEmirates.net");
//		userSaleh.setPassword(new BCryptPasswordEncoder().encode("CSE@1935@Saleh"));
//		userSaleh.addRole("owner");
//		userSaleh.addPermission("owner");
//		this.userRepository.save(userSaleh);
//		
//		
//    	Usersys userSaqer = new Usersys();
//		userSaqer.setUsername("saqer@CustomshowEmirates");
//		userSaqer.setPassword(new BCryptPasswordEncoder().encode("S@1735-mngmt"));
//		userSaqer.addRole("owner");
//		userSaqer.addPermission("owner");
//		this.userRepository.save(userSaqer);
//    	
	}

    
}
