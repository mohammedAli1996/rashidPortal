package com.rashid.Rashid.forms.application.FormResponse;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rashid.Rashid.forms.application.ServiceException;

@Service
public class ResponseService {

	@Autowired
	private ResponseRepository responseRepository ; 

    
    public FormResponse submitResponse(FormResponse response ) {
    	
    	response.setSubmittedAt(new Date())
    			.setDeleted(false);
    	
    	// Add payment info 
    	return responseRepository.save(response);
    }
    
  
    public List<FormResponse> getAllResponses(){
    	return responseRepository.findByDeletedFalse();
    }
    
 
    public FormResponse markAsPaid(String id , boolean paid) {
    	FormResponse response = getResponseById(id);
    	response.setPaid(paid);
    	return responseRepository.save(response);
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
