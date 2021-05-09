package com.auri.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auri.config.JwtTokenProvider;
import com.auri.dao.StaffRepo;
import com.auri.dao.StoreTableRepo;
import com.auri.entity.StaffTable;
import com.auri.entity.StoreTable;
import com.auri.model.ApiResponse;
import com.auri.model.LoginRequest;
import com.auri.model.RoleName;
import com.auri.model.SignUpRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	StaffRepo staffRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	StoreTableRepo store;
	
	private SimpMessagingTemplate temp = null;
	@Autowired
	void WebSocketController(SimpMessagingTemplate s){
		this.temp = s;
	}
	
	@PostMapping("/signin")
	public ResponseEntity<ApiResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
		System.out.println("sign in" + loginRequest.getUsername() + "   " + loginRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		System.out.println("1212 : " + authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("generate token  " + authentication);
		String jwt = tokenProvider.generateToken(authentication);
		System.out.println(jwt);
		return new ResponseEntity<ApiResponse>(new ApiResponse("100", jwt), HttpStatus.OK);
	}

	@GetMapping("/getstore")
	public ResponseEntity<StoreTable> test(@RequestParam int storeID) {
		StoreTable s = store.findByStoreId(storeID);
		if(s==null) {
			return new ResponseEntity<StoreTable>(s, HttpStatus.BAD_REQUEST);
		}
//		this.temp.convertAndSend("/topic/hi/110", new String("Hi, " + "" + "!"));
		return new ResponseEntity<StoreTable>(s, HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

		if (staffRepo.existsByStaffUserName(signUpRequest.getEmail())) {
			return new ResponseEntity<ApiResponse>(
					new ApiResponse("" + HttpStatus.BAD_REQUEST, "Username is already taken"), HttpStatus.BAD_REQUEST);

		}

		if (staffRepo.existsByStaffEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<ApiResponse>(
					new ApiResponse("" + HttpStatus.BAD_REQUEST, "Email Address already in use!"),
					HttpStatus.BAD_REQUEST);

		}
		System.out.println(staffRepo.existsByStaffUserName(signUpRequest.getEmail()));

		StaffTable result = new StaffTable();
		if (signUpRequest.getName().equalsIgnoreCase("ADMIN")) {
			StaffTable staff = new StaffTable();
			staff.setStaffUserName("admin");
			staff.setStaffPassword(passwordEncoder.encode(signUpRequest.getPassword()));
			staff.setStaffEmail(signUpRequest.getEmail());
			staff.setStaffName(signUpRequest.getName());
			staff.setStaffPermission("Settings,Management,Customer,Products");
			staff.setStaffJobTittle(RoleName.ROLE_ADMIN.toString());
			result = staffRepo.save(staff);
		}

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(result.getStaffUserName()).toUri();

		return new ResponseEntity<ApiResponse>(new ApiResponse("" + HttpStatus.OK, "admin registered successfully"),
				HttpStatus.OK);
	}
	
	

}
