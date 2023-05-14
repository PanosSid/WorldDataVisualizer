package com.mye030.world_data_visualizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mye030.world_data_visualizer.model.IndicatorValue;
import com.mye030.world_data_visualizer.model.IndicatorValueId;

@Repository
public interface IndicatorValueRepository extends JpaRepository<IndicatorValue, IndicatorValueId>{
	
	@Query("SELECT iv.year, iv.value FROM IndicatorValue iv WHERE iv.countryId = :countryId AND iv.indicatorId = :indicatorId")
    List<Object[]> findYearsAndValuesByCountryAndIndicator(@Param("countryId") int countryId, @Param("indicatorId") int indicatorId);
}
