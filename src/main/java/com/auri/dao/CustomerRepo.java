package com.auri.dao;

import com.auri.entity.CustomerTable;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepo extends CrudRepository<CustomerTable, Integer> {

	CustomerTable findByCustID(int id);

	CustomerTable findByCustPhone(String custPhone);

	List<CustomerTable> findAllByCustSex(String custSex);

	CustomerTable findByCustSexAndCustPincode(String custsex, int pincode);

	CustomerTable findByCustPincode(int pincode);

	List<CustomerTable> findAllByCustID(int[] custID);

	@Modifying
	@Transactional
	@Query("delete from CustomerTable u where u.custID = ?1")
	@Cascade(CascadeType.DELETE)
	int deleteByCustID(int firstName);
	
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CustomerTable c WHERE c.custID = :code")
    boolean existsByName(@Param("code") String code);

}
