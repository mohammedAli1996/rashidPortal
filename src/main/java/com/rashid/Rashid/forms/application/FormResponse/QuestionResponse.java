package com.rashid.Rashid.forms.application.FormResponse;

import java.util.List;

import com.rashid.Rashid.forms.application.subscriptionForm.QuestionType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


@Getter
@Setter
@ToString
@Accessors(chain=true)
public class QuestionResponse {
	
	private String questionId ; 
	
	private String question ; 
	
	private QuestionType questionType ; 
	
	private String answers ; 
	
	private List<String> answersList ; 

	
}
