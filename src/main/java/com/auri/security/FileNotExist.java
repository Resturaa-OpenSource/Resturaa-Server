package com.auri.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =HttpStatus.NOT_FOUND , reason="To show an example of a custom message")
public class FileNotExist extends RuntimeException {
	
}
