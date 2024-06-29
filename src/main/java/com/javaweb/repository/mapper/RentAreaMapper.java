package com.javaweb.repository.mapper;

import com.javaweb.repository.entity.RentAreaEntity;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RentAreaMapper implements IRowMapper<RentAreaEntity> {
    @Override
    public RentAreaEntity rowMapper(ResultSet resultSet) {
        try {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setId(resultSet.getLong("id"));
            rentAreaEntity.setValue(resultSet.getInt("value"));
            rentAreaEntity.setBuildingId(resultSet.getLong("buildingId"));
            return rentAreaEntity;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
