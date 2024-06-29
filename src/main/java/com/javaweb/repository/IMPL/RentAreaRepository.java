package com.javaweb.repository.IMPL;

import com.javaweb.repository.IRentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.repository.mapper.IRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RentAreaRepository extends AbstractRepository<RentAreaEntity> implements IRentAreaRepository {

    @Autowired
    private IRowMapper<RentAreaEntity> rowMapper;

    @Override
    public List<RentAreaEntity> findByBuildingId(Long id) {
        String sql = "SELECT * FROM rentArea WHERE buildingId = ?" ;
        return super.query(sql, rowMapper, id);
    }
}
