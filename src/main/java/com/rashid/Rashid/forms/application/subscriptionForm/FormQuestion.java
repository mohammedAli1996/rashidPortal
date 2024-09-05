package com.rashid.Rashid.forms.application.subscriptionForm;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Document(collection = "FormQuestion")
@Getter
@Setter
@ToString
@Accessors(chain=true)
public class FormQuestion {

	@Id
	private String id ; 
	
	private String parentFormId ; 
	
	private String question ;
	
	private QuestionType type ; 
	
	private int order ; 
	
	private List<String> options ; 
	
	
	private boolean requiredQ ; 
	
	private boolean deleted ; 

	
}
 