package com.javaweb.repository.mapper;

import com.javaweb.repository.entity.BuildingEntity;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BuildingMapper implements IRowMapper<BuildingEntity> {
    @Override
    public BuildingEntity rowMapper(ResultSet resultSet) throws SQLException {
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity.setId(resultSet.getLong("id"));
        buildingEntity.setName(resultSet.getString("name"));
        buildingEntity.setStreet(resultSet.getString("street"));
        buildingEntity.setWard(resultSet.getString("ward"));
        buildingEntity.setServiceFee(resultSet.getString("servicefee"));
        buildingEntity.setBrokerageFee(resultSet.getString("brokeragefee"));
        buildingEntity.setDistrictId(resultSet.getLong("districtid"));
        buildingEntity.setRentPrice(resultSet.getInt("rentprice"));
        buildingEntity.setFloorArea(resultSet.getInt("floorArea"));
        buildingEntity.setManagerName(resultSet.getString("managername"));
        buildingEntity.setManagerPhoneNumber(resultSet.getString("managerphonenumber"));
        return buildingEntity;
    }
}
