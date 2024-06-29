package com.javaweb.repository;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.repository.entity.BuildingEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IBuildingRepository {
    List<BuildingEntity> findBuilding(BuildingSearchBuilder buildingSearchBuilder);
}
