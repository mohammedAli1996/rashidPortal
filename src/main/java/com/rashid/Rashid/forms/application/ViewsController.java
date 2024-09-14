package com.rashid.Rashid.forms.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewsController {
	
	
	@GetMapping
	public String rootG() {
		return "site/index";
	}
	
	
	@GetMapping("/index")
	public String index() {
		return "site/index";
	}
	
	
	@GetMapping("/package1")
	public String package1() {
		return "site/package1";
	}
	
	@GetMapping("/package2")
	public String package2() {
		return "site/package2";
	}
	
	@GetMapping("/package3")
	public String package3() {
		return "site/package3";
	}
	
	
	@GetMapping("/subscribe")
	public String subscribe(@RequestParam(name = "packageType") String packageType, 
			@RequestParam(name = "packageName") String packageName) {
		return "site/subscribe";
	}
	
	 
	
	@GetMapping("/login")
	public String login() {
		return "admin/login";
	}
	
	@GetMapping("/adminstration/packageType")
	public String getPackageTypes() {
		return "admin/packages";
	}
	
	@GetMapping("/adminstration/responsesAll")
	public String responsesAll() {
		return "admin/responsesAll";
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
 