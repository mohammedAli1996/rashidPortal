package com.rashid.Rashid.forms.application;

public class ServiceException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;

	public ServiceException() {
    }

    public ServiceException(String s) {
        super(s);
    }

}