package com.javaweb.repository;

import com.javaweb.repository.entity.RentAreaEntity;

import java.util.List;

public interface IRentAreaRepository {
    List<RentAreaEntity> findByBuildingId(Long id);
}
