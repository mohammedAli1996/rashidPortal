package com.rashid.Rashid.forms.application.formPackages;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormPackageRepository  extends MongoRepository<FormPackage,String>{
	
	
	List<FormPackage> findByDeletedFalse();

}
