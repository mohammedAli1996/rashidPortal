package com.rashid.Rashid.forms.application.FormResponse;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rashid.Rashid.forms.application.ServiceException;
import com.rashid.Rashid.forms.application.formPackages.FormPackage;
import com.rashid.Rashid.forms.application.formPackages.FormsService;
import com.rashid.Rashid.forms.application.subscriptionForm.SubscriptionForm;
import com.rashid.Rashid.forms.application.subscriptionForm.SubscriptionFormService;

@Service
public class ResponseService {

	@Autowired
	private ResponseRepository responseRepository ; 

	@Autowired
	private SubscriptionFormService subscriptionFormService ;
	
    @Autowired
    private FormsService formsService;    
    
    public FormResponse submitResponse(FormResponse response ) {
    	formsService.getFormPackageById(response.getFormPackageId());
    	FormPackage formPackage =  formsService.getFormPackageById(response.getFormPackageId()) ;
    	SubscriptionForm subscriptionForm = subscriptionFormService.getSubFormById(response.getSubscriptionFormId());
    	
    	response.setSubmittedAt(new Date())
    			.setFormPackage(formPackage)
    			.setSubscriptionForm(subscriptionForm)
    			.setPaymentAmount(formPackage.getPackagePrice())
    			.setDeleted(false);
    	
    	// Add payment info 
    	return responseRepository.save(response);
    }
    
  
    public List<FormResponse> getAllResponses(){
    	return responseRepository.findByDeletedFalse();
    }
    
 
    
    public FormResponse getResponseById(String id ) {
    	Optional<FormResponse> optional = responseRepository.findById(id);
    	if(optional.isPresent() && !optional.get().isDeleted()) {
    		return optional.get() ; 
    		
    	}
    	throw new ServiceException("Response not found");
    }
    
    public FormResponse deleteResponse(String id ) {
    	FormResponse response = getResponseById(id);
    	response.setDeleted(true);
    	return responseRepository.save(response);
    }
    
    
	
}
