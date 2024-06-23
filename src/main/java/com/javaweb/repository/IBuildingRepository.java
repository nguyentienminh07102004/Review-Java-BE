package com.javaweb.repository;

import com.javaweb.repository.entity.BuildingEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IBuildingRepository {
    List<BuildingEntity> findBuilding(Map<String, String> params, List<String> typeCode) throws SQLException, ClassNotFoundException, NumberFormatException;
}
