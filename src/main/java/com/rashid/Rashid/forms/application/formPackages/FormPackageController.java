package com.rashid.Rashid.forms.application.formPackages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rashid.Rashid.forms.application.RegLock.RegLock;
import com.rashid.Rashid.forms.application.RegLock.RegLockRepo;

import java.util.List;

@RestController
@RequestMapping("/api/form-packages")
public class FormPackageController {

    @Autowired
    private FormsService formsService;

    @Autowired
	private RegLockRepo regLockRepo;
    
    
    
    @GetMapping("/lckState")
    public ResponseEntity<RegLock> getLockState() {
    	try {
            return new ResponseEntity<>(regLockRepo.findAll().get(0), HttpStatus.CREATED);	
    	}catch(Exception ex ) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND) ; 
    	}
    }
    
    
    
    // Create a new FormPackage
    @PostMapping
    public ResponseEntity<FormPackage> createFormPackage(@RequestBody FormPackage formPackage) {
        FormPackage createdFormPackage = formsService.createFormPackage(formPackage);
        return new ResponseEntity<>(createdFormPackage, HttpStatus.CREATED);
    }

    // Get a FormPackage by id
    @GetMapping("/{id}")
    public ResponseEntity<FormPackage> getFormPackageById(@PathVariable String id) {
    	try {
    		FormPackage formPackage = formsService.getFormPackageById(id);
            return new ResponseEntity<>(formPackage, HttpStatus.CREATED);	
    	}catch(Exception ex ) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND) ; 
    	}
    }

    // Get all FormPackages
    @GetMapping
    public ResponseEntity<List<FormPackage>> getAllFormPackages() {
        List<FormPackage> formPackages = formsService.getAllFormPackages();
        return new ResponseEntity<>(formPackages, HttpStatus.OK);
    }

    // Update a FormPackage by id
    @PutMapping("/{id}")
    public ResponseEntity<FormPackage> updateFormPackage(@PathVariable String id, @RequestBody FormPackage formPackageDetails) {
        try {
            FormPackage updatedFormPackage = formsService.updateFormPackage(id, formPackageDetails);
            return new ResponseEntity<>(updatedFormPackage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Soft delete a FormPackage by id
    @DeleteMapping("/{id}")
    public ResponseEntity<FormPackage> deleteFormPackage(@PathVariable String id) {
        try {
            FormPackage deletedFormPackage = formsService.deleteFormPackage(id);
            return new ResponseEntity<>(deletedFormPackage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
