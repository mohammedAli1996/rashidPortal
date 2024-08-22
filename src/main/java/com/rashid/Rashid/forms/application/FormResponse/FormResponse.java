package com.rashid.Rashid.forms.application.FormResponse;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.rashid.Rashid.forms.application.formPackages.FormPackage;
import com.rashid.Rashid.forms.application.subscriptionForm.SubscriptionForm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Document(collection = "FormResponse")
@Getter
@Setter
@ToString
@Accessors(chain=true)
public class FormResponse {

	@Id
	private String id ; 
	
	private String subscriptionFormId ; 
	
	private SubscriptionForm subscriptionForm ; 
	
	private String formPackageId ; 
	
	private FormPackage formPackage ;
	
	private Date submittedAt ;
	
	private double paymentAmount ;
	
	private List<QuestionResponse> responses ;   
	
	
	private String paymentLink ; 
	
	private String paymentStatus ; 
	
	private Date paymentDate ; 
	
	private boolean deleted ; 
	
	
}
