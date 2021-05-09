package com.auri.controller;

import java.io.IOException;
import java.io.InputStream;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.auri.service.StorageService;

@RestController
@RequestMapping("/api/files")
public class FileController {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	StorageService storageService;

	@RequestMapping(value = "/download/{fileName:.+}", method = RequestMethod.GET)
	public void getImage(HttpServletResponse response, @PathVariable String fileName) throws IOException {

//		InputStream file = storageService.loadFile(fileName);
		InputStream file = storageService.downloadFileS3(fileName);
//		System.out.println("file "+file);
		if (file != null) {
//			System.out.println(file);
			MediaType mediatype = getMediaTypeForFileName(this.servletContext, fileName);
			response.setContentType(mediatype.getType() + "/" + mediatype.getSubtype());
			StreamUtils.copy(file, response.getOutputStream());
			
			response.getOutputStream().close();
			file.close();
			System.out.println("closing      ");
			response.flushBuffer();
		}
	}


	@PostMapping("/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		
		String message = "";
		System.out.println("Upload File   ");

		try {
			storageService.store(file);
			System.out.println(" message ");
			return new ResponseEntity<String>(message,HttpStatus.OK);
		
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return  new ResponseEntity<String>(message,HttpStatus.NOT_ACCEPTABLE);
		
		}

	}

	public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
		String mineType = servletContext.getMimeType(fileName);
		try {
			MediaType mediaType = MediaType.parseMediaType(mineType);
			return mediaType;
		} catch (Exception e) {
			return MediaType.APPLICATION_OCTET_STREAM;
		}
	}
}
