package com.rashid.Rashid.forms.application.FileSystem;

public class FileInfo {
    private String filename;
    private String url;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public FileInfo(String filename, String url) {
		super();
		this.filename = filename;
		this.url = url;
	}
    
    
    
}

