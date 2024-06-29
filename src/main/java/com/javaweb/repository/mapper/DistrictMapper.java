package com.javaweb.repository.mapper;

import com.javaweb.repository.entity.DistrictEntity;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DistrictMapper implements IRowMapper<DistrictEntity> {
    @Override
    public DistrictEntity rowMapper(ResultSet resultSet) {
        try {
            DistrictEntity districtEntity = new DistrictEntity();
            districtEntity.setId(resultSet.getLong("id"));
            districtEntity.setCode(resultSet.getString("name"));
            districtEntity.setName(resultSet.getString("name"));
            return districtEntity;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
