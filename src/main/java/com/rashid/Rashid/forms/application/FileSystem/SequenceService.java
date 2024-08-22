package com.rashid.Rashid.forms.application.FileSystem;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SequenceService {

	
	@Autowired
	private FilesSequenceRepository sequenceRepository ; 
	
	private static int currentSequence ; 
	
	@PostConstruct
	private void initSequencer() {
		if(sequenceRepository.findAll().size()== 0 ) {
			FilesSequencer sequence = new FilesSequencer(); 
			sequence.setFileSequence(1);
			currentSequence = 1 ; 
			sequenceRepository.save(sequence);
		}else {
			currentSequence = sequenceRepository.findAll().get(0).getFileSequence();
		}
	} 
	
	
	public int getNextSequence() {
		currentSequence++ ; 
		FilesSequencer sequence = sequenceRepository.findAll().get(0) ; 
		sequence.setFileSequence(currentSequence);
		sequenceRepository.save(sequence);
		return currentSequence ; 
	}
	
}
