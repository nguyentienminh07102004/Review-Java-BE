package com.javaweb.repository;

import com.javaweb.repository.entity.DistrictEntity;

import java.sql.SQLException;

public interface IDistrictRepository {
    DistrictEntity findById(Long id) throws SQLException, ClassNotFoundException;
}
