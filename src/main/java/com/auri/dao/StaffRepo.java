package com.auri.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.auri.entity.StaffTable;

public interface StaffRepo extends CrudRepository<StaffTable, Integer>  {
	
	Optional<StaffTable> findByStaffID(Long staffID);
	Optional<StaffTable> findByStaffUserName(String staffUsername);
	Optional<StaffTable> findByStaffUserNameOrStaffEmail(String staffName,String StaffEmail);
	
	Boolean existsByStaffEmail(String email);
	Boolean existsByStaffUserName(String staffUserName);
	
	@Modifying
	@Transactional
	@Query("delete from StaffTable u where u.staffID = ?1")
	int deleteByStaffID(Long StaffID);
}
