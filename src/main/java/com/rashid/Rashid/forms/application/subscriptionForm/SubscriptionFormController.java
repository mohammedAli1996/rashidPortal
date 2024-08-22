package com.rashid.Rashid.forms.application.subscriptionForm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscription-form")
public class SubscriptionFormController {

	@Autowired
	private SubscriptionFormService subscriptionFormService ;
	
	 
    // Create a new subscription form   
    @PostMapping
    public ResponseEntity<SubscriptionForm> createForm(@RequestBody SubscriptionForm subscriptionForm) {
        return new ResponseEntity<>(subscriptionFormService.createForm(subscriptionForm), HttpStatus.CREATED);
    }
 
    // Get a FormPackage by id
    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionForm> getSubFormById(@PathVariable String id) {
    	try {
            return new ResponseEntity<>(subscriptionFormService.getSubFormById(id), HttpStatus.CREATED);	
    	}catch(Exception ex ) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND) ; 
    	}
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionForm>> getAllForms() {
        return new ResponseEntity<>(subscriptionFormService.getAllForms(), HttpStatus.OK);
    }

    
    
    @PostMapping("/addQuestion")
    public ResponseEntity<FormQuestion> addQuestionToForm(@RequestBody FormQuestion formQuestion) {
        try {
            return new ResponseEntity<>(subscriptionFormService.addQuestionToForm(formQuestion), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    
    
    @PutMapping("/updateQuestion")
    public ResponseEntity<FormQuestion> updateQuestion(@RequestBody FormQuestion formQuestion ) {
        try {
            return new ResponseEntity<>(subscriptionFormService.updateQuestion(formQuestion), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/question/{id}")
    public ResponseEntity<FormQuestion> getQuestionById(@PathVariable String id) {
    	try {
            return new ResponseEntity<>(subscriptionFormService.getQuestionById(id), HttpStatus.CREATED);	
    	}catch(Exception ex ) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND) ; 
    	}
    }

    // Soft delete a FormPackage by id
    @DeleteMapping("/removeQuestion/{id}")
    public ResponseEntity<FormQuestion> removeQuestionFromForm(@PathVariable String id) {
        try {
            return new ResponseEntity<>(subscriptionFormService.removeQuestionFromForm(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	
	
	
}
