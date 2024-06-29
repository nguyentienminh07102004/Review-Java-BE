package com.javaweb.convertor;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BuildingSearchBuilderConvertor {
    public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, String> params, List<String> typeCode) {
        return new BuildingSearchBuilder.Builder()
                .setName(MapUtil.getObject(params, "name", String.class))
                .setFloorArea(MapUtil.getObject(params, "floorArea", Integer.class))
                .setDistrictId(MapUtil.getObject(params, "districtId", Long.class))
                .setWard(MapUtil.getObject(params, "ward", String.class))
                .setStreet(MapUtil.getObject(params, "street", String.class))
                .setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Integer.class))
                .setDirection(MapUtil.getObject(params, "direction", String.class))
                .setLevel(MapUtil.getObject(params, "level", String.class))
                .setAreaFrom(MapUtil.getObject(params, "areaFrom", Integer.class))
                .setAreaTo(MapUtil.getObject(params, "areaTo", Integer.class))
                .setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", Integer.class))
                .setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", Integer.class))
                .setManagerName(MapUtil.getObject(params, "managerName", String.class))
                .setManagerPhoneNumber(MapUtil.getObject(params, "managerPhoneNumber", String.class))
                .setStaffId(MapUtil.getObject(params, "staffId", Long.class))
                .setTypeCode(typeCode)
                .build();
    }
}
