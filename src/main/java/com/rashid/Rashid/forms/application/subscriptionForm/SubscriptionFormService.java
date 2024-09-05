package com.rashid.Rashid.forms.application.subscriptionForm;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rashid.Rashid.forms.application.ServiceException;

@Service
public class SubscriptionFormService {
	
	@Autowired
	private SubscriptionFormRepository subscriptionFormRepository ; 
	
	
	@Autowired
	private FormQuestionRepository formQuestionRepository ; 
	
	
	
	public SubscriptionForm createForm(SubscriptionForm form) {
//		FormPackage formPackage = formsService.getFormPackageById(formPackageId);
		form.setQuestions(new LinkedList<>()).setDeleted(false);
		return subscriptionFormRepository.save(form);
	}
	
	
	public SubscriptionForm getSubFormById(String formId ) {
		Optional<SubscriptionForm> optional = subscriptionFormRepository.findById(formId);
		if(optional.isPresent()) {
			return optional.get();
		}
		throw new ServiceException("Subscription form not found");
	}
	
	
	public List<SubscriptionForm> getAllForms() {
		return subscriptionFormRepository.findAll() ; 
	}
	
	
	public FormQuestion addQuestionToForm(FormQuestion question) {
		SubscriptionForm form = getSubFormById(question.getParentFormId());
		question = formQuestionRepository.save(question);
		form.getQuestions().add(question);
		subscriptionFormRepository.save(form);
		return question;
	}
	
	
	public FormQuestion getQuestionById(String questionId ) {
		Optional<FormQuestion> optional = formQuestionRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		throw new ServiceException("Question not found");
	}
	
	public FormQuestion removeQuestionFromForm(String questionId) {
		FormQuestion question = getQuestionById(questionId);
		SubscriptionForm form = getSubFormById(question.getParentFormId());
		for(int i = 0 ; i < form.getQuestions().size() ; i ++) {
			if(form.getQuestions().get(i).getId().equals(questionId)) {
				form.getQuestions().remove(i);
				subscriptionFormRepository.save(form);
				question.setDeleted(true);
				question = formQuestionRepository.save(question);
				return question; 
			}
		}
		throw new ServiceException("Question not found"); 
	}


	public FormQuestion updateQuestion(FormQuestion formQuestion) {
		FormQuestion question = getQuestionById(formQuestion.getId());
		question.setQuestion(formQuestion.getQuestion())
				.setType(formQuestion.getType())
				.setOptions(formQuestion.getOptions())
				.setRequiredQ(formQuestion.isRequiredQ());
		return formQuestionRepository.save(question);
	}
	
	
	
}
