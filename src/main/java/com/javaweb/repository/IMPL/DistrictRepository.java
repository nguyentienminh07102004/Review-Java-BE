package com.javaweb.repository.IMPL;

import com.javaweb.repository.IDistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.mapper.IRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class DistrictRepository extends AbstractRepository<DistrictEntity> implements IDistrictRepository {

    @Autowired
    private IRowMapper<DistrictEntity> rowMapper;

    @Override
    public DistrictEntity findById(Long id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT d.id, d.name, d.code FROM district d WHERE id = ?";
        List<DistrictEntity> districtEntity = query(sql, rowMapper, id);
        if(districtEntity != null || !districtEntity.isEmpty()) {
            return districtEntity.get(0);
        }
        return null;
    }
}
