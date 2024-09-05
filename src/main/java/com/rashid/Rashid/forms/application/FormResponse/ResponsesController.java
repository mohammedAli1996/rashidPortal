package com.rashid.Rashid.forms.application.FormResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/form-responses")
public class ResponsesController {

	@Autowired
	private ResponseService responseService ; 
	
    @PostMapping
    public ResponseEntity<FormResponse> submitResponse(@RequestBody FormResponse response) {
    	try {
    		return new ResponseEntity<>(responseService.submitResponse(response), HttpStatus.CREATED);	
    	}catch(Exception ex ) {
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ; 
    	}
    }

    
    
    
    
    
    @GetMapping("/markAsPaid")
    public ResponseEntity<FormResponse> markAsPaid(@RequestParam(name = "id") String id,@RequestParam(name = "state") boolean state) {
    	try {
            return new ResponseEntity<>(responseService.markAsPaid(id,state), HttpStatus.CREATED);	
    	}catch(Exception ex ) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND) ; 
    	}
    }
    
     
    
    @GetMapping
    public ResponseEntity<List<FormResponse>> getAllResponses() {
        
        try {
        	return new ResponseEntity<>(responseService.getAllResponses(), HttpStatus.OK);	
    	}catch(Exception ex ) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND) ; 
    	}
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<FormResponse> getResponseById(@PathVariable String id) {
    	try {
            return new ResponseEntity<>(responseService.getResponseById(id), HttpStatus.CREATED);	
    	}catch(Exception ex ) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND) ; 
    	}
    }
    
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<FormResponse> deleteResponse(@PathVariable String id) {
        try {
            return new ResponseEntity<>(responseService.deleteResponse(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
}
