package com.rashid.Rashid.forms.application.formPackages;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Document(collection = "FormPackage")
@Getter
@Setter
@ToString
@Accessors(chain=true)
public class FormPackage {

	@Id
	private String id ; 
	
	private String packageName ;
	
	private String packageDescription ; 
	
	private double packagePrice ; 
	
	private Date createAt ; 
	
	private boolean deleted ; 
	
	
}
