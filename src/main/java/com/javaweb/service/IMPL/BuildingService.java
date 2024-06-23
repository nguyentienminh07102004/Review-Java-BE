package com.javaweb.service.IMPL;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.IBuildingRepository;
import com.javaweb.repository.IDistrictRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
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
    private IDistrictRepository districtRepository;

    @Override
    public List<BuildingDTO> findBuilding(Map<String, String> params, List<String> typeCode) throws ClassNotFoundException, SQLException, NumberFormatException {
        List<BuildingEntity> buildingEntityList = buildingRepository.findBuilding(params, typeCode);
        List<BuildingDTO> result = new ArrayList<>();
        for(BuildingEntity buildingEntity : buildingEntityList) {
            BuildingDTO building = new BuildingDTO();
            building.setName(buildingEntity.getName());
            building.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + districtRepository.findById(buildingEntity.getDistrictId()).getName());
            building.setBrokerageFee(buildingEntity.getBrokerageFee());
            building.setServiceFee(buildingEntity.getServiceFee());
            building.setFloorArea(buildingEntity.getFloorArea());
            building.setManagerName(buildingEntity.getManagerName());
            building.setManagerPhoneNumber(buildingEntity.getManagerPhoneNumber());
            building.setRentPrice(buildingEntity.getRentPrice());
            building.setNumberOfBasement(buildingEntity.getNumberOfBasement());
            result.add(building);
        }
        return result;
    }
}
