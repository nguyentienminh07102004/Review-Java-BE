package com.javaweb.service;

import com.javaweb.model.BuildingDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IBuildingService {
    List<BuildingDTO> findBuilding(Map<String, String> params, List<String> typeCode) throws SQLException, ClassNotFoundException, NumberFormatException;
}
