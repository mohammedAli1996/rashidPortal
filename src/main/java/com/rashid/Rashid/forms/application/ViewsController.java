package com.rashid.Rashid.forms.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewsController {
	
	@GetMapping("/login")
	public String login() {
		return "admin/login";
	}
	
	@GetMapping("/adminstration/packageType")
	public String getPackageTypes() {
		return "admin/packages";
	}
	
	
	
	@GetMapping("/adminstration/subscriptionForms")
	public String getSubscriptionForms() {
		return "admin/subscriptionForms";
	}
	
	
	@GetMapping("/adminstration/viewForm")
	public String getViewSubscriptionForms(@RequestParam(name = "id") String id ) {
		return "admin/viewSubscriptionForms";
	}
	
	 
}
 