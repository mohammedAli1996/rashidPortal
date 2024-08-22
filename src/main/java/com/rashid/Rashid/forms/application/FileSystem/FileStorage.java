package com.rashid.Rashid.forms.application.FileSystem;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;


public interface FileStorage {
	String store(MultipartFile file );

    Resource loadFile(String filename);

    void deleteAll();

    void init();

    Stream<Path> loadFiles();
	
}
