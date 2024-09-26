package com.rashid.Rashid.forms.application.Secutiry;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<Usersys,String>{

	public Usersys findByUsername(String username);

	public Usersys findByEmployeeName(String employeeName);
	
}
