
package com.auri.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.auri.dao.CustomerRepo;
import com.auri.dao.ProductRepo;
import com.auri.dao.StaffRepo;
import com.auri.dao.StoreTableRepo;

@Service
public class StorageService {

	@Autowired
	StoreTableRepo store;
	@Autowired
	CustomerRepo customer;
	@Autowired
	StaffRepo staffRepo;
	@Autowired
	ProductRepo productRepo;

	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	private final Path rootLocation = Paths.get("upload-dir");

	public void store(MultipartFile file) {

		uploadFile(file);
		
		/// for local storage
		
//		try {
//			File f = new File(rootLocation + "/" + file.getOriginalFilename());
//			if (f.exists()) {
//				
//				f.delete();
//				
//			}
//			f = null;
//			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
//			file.getInputStream().close();
//			
//		} catch (Exception e) {
//			
//			System.out.println("Faild");
//			throw new RuntimeException("FAIL!");
//		
//		}
	}

	public InputStream loadFile(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {

				resource.getInputStream().close();
				return resource.getInputStream();

			} else {
				throw new ResourceNotFoundException(filename);
			}
		} catch (MalformedURLException e) {
			throw new ResourceNotFoundException("MalformedURLException");
		} catch (IOException e) {
			throw new ResourceNotFoundException("IOException" + e);
		}
	}

	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	public void init() {
		try {
			if (!Files.exists(rootLocation)) {
				System.out.println("file system created");
				Files.createDirectory(rootLocation);
			} else {
				System.out.println("is exist");
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}

	@Autowired
	private AmazonS3 s3client;

	@Value("${jsa.s3.bucket}")
	private String bucketName;

	public InputStream downloadFileS3(String keyName) {

		try {

			System.out.println("Downloading an object");
			S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName, keyName));
			System.out.println("Content-Type: " + s3object.getObjectMetadata().getContentType());
//	            Utility.displayText(s3object.getObjectContent());
			InputStream is = s3object.getObjectContent();

			System.out.println("===================== Import File - Done! =====================");
			return is;

		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException from GET requests, rejected reasons:");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException: ");
			System.out.println("Error Message: " + ace.getMessage());
		}
		return null;
	}

//	 public void uploadFile(String keyName, String uploadFilePath) {
//	 
//	 try {
//	 
//	 File file = new File(uploadFilePath);
//	         s3client.putObject(new PutObjectRequest(bucketName, keyName, file));
//	         System.out.println("===================== Upload File - Done! =====================");
//	         
//	 } catch (AmazonServiceException ase) {
//	 System.out.println("Caught an AmazonServiceException from PUT requests, rejected reasons:");
//	 System.out.println("Error Message:    " + ase.getMessage());
//	 System.out.println("HTTP Status Code: " + ase.getStatusCode());
//	 System.out.println("AWS Error Code:   " + ase.getErrorCode());
//	 System.out.println("Error Type:       " + ase.getErrorType());
//	 System.out.println("Request ID:       " + ase.getRequestId());
//	        } catch (AmazonClientException ace) {
//	            System.out.println("Caught an AmazonClientException: ");
//	            System.out.println("Error Message: " + ace.getMessage());
//	        }
//	 }

	private void uploadFileTos3bucket(String fileName, File file) {
		s3client.putObject(
				new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	public String uploadFile(MultipartFile multipartFile) {

		String fileUrl = "";
		try {
			File file = convertMultiPartToFile(multipartFile);

			fileUrl = "/" + bucketName + "/" + multipartFile.getOriginalFilename();
			uploadFileTos3bucket(multipartFile.getOriginalFilename(), file);
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileUrl;
	}

}