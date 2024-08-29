package com.rashid.Rashid.forms.application.FormResponse;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
	
	private String packageName ; 
	
	private Date submittedAt ;
	
	private double paymentAmount ;
	
	private List<QuestionResponse> responses ;   

	
	private boolean deleted ; 
	
	
}
  