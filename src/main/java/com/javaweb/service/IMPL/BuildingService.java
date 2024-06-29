package com.javaweb.service.IMPL;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.convertor.BuildingConverter;
import com.javaweb.convertor.BuildingSearchBuilderConvertor;
import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.IBuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private IBuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private BuildingSearchBuilderConvertor buildingSearchBuilderConvertor;

    @Override
    public List<BuildingDTO> findBuilding(Map<String, String> params, List<String> typeCode) {
        List<BuildingEntity> buildingEntityList = buildingRepository.findBuilding(buildingSearchBuilderConvertor.toBuildingSearchBuilder(params, typeCode));
        List<BuildingDTO> result = new ArrayList<>();
        for(BuildingEntity buildingEntity : buildingEntityList) {
            BuildingDTO building = buildingConverter.EntityToDTO(buildingEntity);
            result.add(building);
        }
        return result;
    }
}
