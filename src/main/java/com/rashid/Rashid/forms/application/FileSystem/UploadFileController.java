package com.rashid.Rashid.forms.application.FileSystem;


import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

  
@RestController
@RequestMapping("/api/fs")
public class UploadFileController { 
    
    @Autowired
    FileStorage fileStorage;
           
    @PostMapping("/filesUploader")    
    @ResponseBody
    public String uploadMultipartFile(@RequestParam("file") MultipartFile file) {
		try {
			return fileStorage.store(file);
		}catch(Exception ex) {
			return null ; 
		}
    } 
     
    
    @PostMapping("/multiFilesUploader")    
    @ResponseBody
    public List<String> uploadMultipartFiles(@RequestParam("files") MultipartFile[] files) {
		try {
			List<String> fileNames = new LinkedList<String>();
			for(MultipartFile file : files ) {
				fileNames.add(fileStorage.store(file));
			}
			return fileNames;
		}catch(Exception ex) {
			return null ; 
		}
    }
    
    
  
                      
       
     

}