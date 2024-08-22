package com.rashid.Rashid.forms.application.subscriptionForm;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


@Document(collection = "SubscriptionForm")
@Getter
@Setter
@ToString
@Accessors(chain=true)
public class SubscriptionForm {

	@Id
	private String id ; 
	
	private String formtitle ; 
	
	private String formDescription ; 
	
	@DBRef
	private List<FormQuestion> questions ; 

	
	private boolean deleted ; 

}
