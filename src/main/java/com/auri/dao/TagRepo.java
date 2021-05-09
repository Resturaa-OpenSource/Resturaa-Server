package com.auri.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.auri.entity.TagTable;

public interface TagRepo extends CrudRepository<TagTable, Integer>  {

	
	@Modifying
	@Transactional
	@Query("delete from TagTable u where u.tagID = ?1")
	int deleteBytagID(int tagID);
	
	TagTable findByTagID(int tagid);
	
//	@Query( "select o from TagTable o where o.tagid in :ids" )
//	List<TagTable> findAllByTagIDs(@Param("ids") List<Integer> tagid);
//	
//
	
}
