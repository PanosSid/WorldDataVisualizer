package com.mye030.world_data_visualizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mye030.world_data_visualizer.model.IndicatorMetadata;

@Repository
public interface IndicatorMetadataRepository  extends JpaRepository<IndicatorMetadata, Integer>{
	
	@Query("SELECT id FROM IndicatorMetadata WHERE name = :name")
	Integer findIdByName(@Param("name") String name);
	
	@Query("SELECT DISTINCT name FROM IndicatorMetadata")
	List<String> findAllNames();
}
