package com.mye030.world_data_visualizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mye030.world_data_visualizer.model.CountryMetadata;


@Repository
public interface CountryMetadataRepository extends JpaRepository<CountryMetadata, Integer>{
	
	@Query("SELECT id FROM CountryMetadata WHERE name = :name")
	Integer findIdByName(@Param("name") String name);

	@Query("SELECT DISTINCT name FROM CountryMetadata")
	List<String> findAllNames();
	
	
}
