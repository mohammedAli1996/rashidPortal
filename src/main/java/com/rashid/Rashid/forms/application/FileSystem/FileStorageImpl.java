package com.rashid.Rashid.forms.application.FileSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

@Service
public class FileStorageImpl implements FileStorage {

	@Autowired
	private SequenceService sequenceService;

	@Value("${storage.path}")
	private String storageLoc;

	private Path rootLocation;

	@PostConstruct
	private void test() {
		rootLocation = Paths.get(storageLoc);
	}

	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	@Override
	public String store(MultipartFile file) {
		try {
			String fileName = sequenceService.getNextSequence() + "-" + file.getOriginalFilename().replace(" ", "-");
//			String fileName = sequenceService.getNextSequence() + "-" + new Date();
			Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}

	@Override
	public Resource loadFile(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error! -> message = " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}

	@Override
	public Stream<Path> loadFiles() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(this.rootLocation::relativize);
		} catch (IOException e) {
			throw new RuntimeException("\"Failed to read stored file");
		}
	}

	
}