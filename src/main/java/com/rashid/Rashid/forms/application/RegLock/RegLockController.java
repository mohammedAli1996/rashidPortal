package com.rashid.Rashid.forms.application.RegLock;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adminstration/regLock")
public class RegLockController {

	@Autowired
	private RegLockRepo regLockRepo;

	@PostConstruct
	private void checkLock() {
		if (regLockRepo.findAll().isEmpty()) {
			RegLock lock = new RegLock();
			lock.setCanRegister(true);
			regLockRepo.save(lock);
		}
	}

	@PostMapping
	public ResponseEntity<RegLock> flipLockState() {
		checkLock();
		RegLock lock = regLockRepo.findAll().get(0);
		lock.setCanRegister(!lock.isCanRegister());
		return new ResponseEntity<>(regLockRepo.save(lock), HttpStatus.CREATED);
	}

	
	@GetMapping
	public ResponseEntity<RegLock> getCurrLock() {
		checkLock();
		RegLock lock = regLockRepo.findAll().get(0);
		return new ResponseEntity<>(lock, HttpStatus.OK);
	}

}
