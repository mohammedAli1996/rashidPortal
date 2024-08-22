package com.rashid.Rashid.forms.application.FileSystem;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "FilesSequencer")
public class FilesSequencer {

	
	@Id
	private String id ; 
	
	private int fileSequence ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getFileSequence() {
		return fileSequence;
	}

	public void setFileSequence(int fileSequence) {
		this.fileSequence = fileSequence;
	} 
	
	
}
