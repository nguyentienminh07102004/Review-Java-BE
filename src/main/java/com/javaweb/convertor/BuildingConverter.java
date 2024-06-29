package com.javaweb.convertor;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.IDistrictRepository;
import com.javaweb.repository.IRentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {

    @Autowired
    private IDistrictRepository districtRepository;

    @Autowired
    private IRentAreaRepository rentAreaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BuildingDTO EntityToDTO(BuildingEntity buildingEntity) {
        BuildingDTO building = modelMapper.map(buildingEntity, BuildingDTO.class);
        building.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + districtRepository.findById(buildingEntity.getDistrictId()).getName());
        List<RentAreaEntity> list = rentAreaRepository.findByBuildingId(buildingEntity.getId());
        String rentAreaValue = list.stream().map(RentAreaEntity::getValue).map(String::valueOf).collect(Collectors.joining(","));
        building.setRentArea(rentAreaValue);
        return building;
    }

}
