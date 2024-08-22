package com.rashid.Rashid.forms.application.FormResponse;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResponseRepository extends MongoRepository<FormResponse,String> {

	List<FormResponse> findByDeletedFalse();
	
}
