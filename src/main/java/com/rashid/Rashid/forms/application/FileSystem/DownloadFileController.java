package com.rashid.Rashid.forms.application.FileSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/dfs")
public class DownloadFileController {

    @Autowired
    FileStorage fileStorage;
   
    /*  
     * Retrieve Files' Information  
     */
//    @GetMapping("/getFilesListLocal")  
//    public String getListFiles(Model model) {    
//        List<FileInfo> fileInfos = fileStorage.loadFiles().map(     
//                path -> {
//                    String filename = path.getFileName().toString();  
//                    String url = MvcUriComponentsBuilder.fromMethodName(DownloadFileController.class,  
//                            "downloadFile", path.getFileName().toString()).build().toString();   
//                    return new FileInfo(filename, url);
//                }
//        ) 
//                .collect(Collectors.toList());
//
//        model.addAttribute("files", fileInfos);   
//        return "listfiles";
//    }   

    /*
     * Download Files
     */
    @GetMapping("/getLocalFile/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
    	try {
            Resource file = fileStorage.loadFile(filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                    .body(file);
    	}catch(Exception ex ) {
    		return ResponseEntity.notFound().headers(HttpHeaders.EMPTY).build();
    	}
    }
    

}