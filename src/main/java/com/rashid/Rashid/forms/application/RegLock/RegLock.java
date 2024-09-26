package com.rashid.Rashid.forms.application.RegLock;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;



@Document(collection = "RegLock")
@Getter
@Setter
@ToString
@Accessors(chain=true)
public class RegLock {

	@Id
	private String id ; 
	
	private boolean canRegister ; 
	
}
