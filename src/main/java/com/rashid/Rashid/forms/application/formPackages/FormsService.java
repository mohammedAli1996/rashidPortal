package com.rashid.Rashid.forms.application.formPackages;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rashid.Rashid.forms.application.ServiceException;

@Service 
public class FormsService {
	
	@Autowired
	private FormPackageRepository formPackageRepository;
	
	
	// Create a new FormPackage
    public FormPackage createFormPackage(FormPackage formPackage) {
        formPackage.setCreateAt(new Date()); // Set the creation date
        return formPackageRepository.save(formPackage);
    }

    // Retrieve a FormPackage by id
    public FormPackage getFormPackageById(String id) {
    	Optional<FormPackage> optional = formPackageRepository.findById(id) ; 
    	if(optional.isPresent() && 
    			!optional.get().isDeleted()) {
    		return optional.get();
    	}
    	throw new ServiceException("Package not found");
    }

    // Retrieve all FormPackages
    public List<FormPackage> getAllFormPackages() {
        return formPackageRepository.findByDeletedFalse();
    }

    // Update an existing FormPackage
    public FormPackage updateFormPackage(String id, FormPackage formPackageDetails) {
        return formPackageRepository.findById(id).map(formPackage -> {
            formPackage.setPackageName(formPackageDetails.getPackageName());
            formPackage.setPackageDescription(formPackageDetails.getPackageDescription());
            formPackage.setPackagePrice(formPackageDetails.getPackagePrice());
            return formPackageRepository.save(formPackage);
        }).orElseThrow(() -> new ServiceException("Package not found"));
    }

    // Delete a FormPackage by id (soft delete)
    public FormPackage deleteFormPackage(String id) {
        return formPackageRepository.findById(id).map(formPackage -> {
            formPackage.setDeleted(true);
            return formPackageRepository.save(formPackage);
        }).orElseThrow(() -> new RuntimeException("FormPackage not found with id " + id));
    }

}
