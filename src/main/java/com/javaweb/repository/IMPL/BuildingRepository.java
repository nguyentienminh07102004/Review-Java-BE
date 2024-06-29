package com.javaweb.repository.IMPL;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.convertor.BuildingSearchBuilderConvertor;
import com.javaweb.repository.IBuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.mapper.IRowMapper;
import com.javaweb.utils.validateDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BuildingRepository extends AbstractRepository<BuildingEntity> implements IBuildingRepository {

    @Autowired
    private IRowMapper<BuildingEntity> rowMapper;

    @Override
    public List<BuildingEntity> findBuilding(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.districtId, b.street, b.floorArea, b.ward, b.numberOfBasement, b.managerName, b.managerPhoneNumber, ");
        sql.append("b.rentPrice, b.serviceFee, b.brokerageFee FROM building b ");
        joinTable(buildingSearchBuilder, sql);
        where_condition_normal(buildingSearchBuilder, sql);
        where_condition_special(sql, buildingSearchBuilder);
        System.out.println(sql.toString());
        return query(sql.toString(), rowMapper);
    }

    private void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
        if(buildingSearchBuilder.getTypeCode() != null && !buildingSearchBuilder.getTypeCode().isEmpty()) {
            sql.append(" INNER JOIN buildingRentType ON buildingRentType.buildingId = b.id ");
            sql.append(" INNER JOIN rentType ON rentType.id = buildingRentType.rentTypeId ");
        }
        if(buildingSearchBuilder.getStaffId() != null) {
            sql.append(" INNER JOIN assignmentBuilding ON b.id = assignmentBuilding.buildingId ");
        }
    }

    private void where_condition_normal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
        sql.append(" WHERE 1 = 1 ");
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if(!fieldName.equalsIgnoreCase("typeCode") && !fieldName.startsWith("area")
                        && !fieldName.equalsIgnoreCase("staffId") && !fieldName.startsWith("rentPrice")) {
                    String value = field.get(buildingSearchBuilder) == null ? "" : field.get(buildingSearchBuilder).toString();
                    if(!validateDataInput.isNumber(value)) {
                        if(validateDataInput.StringValidate(value))
                            sql.append(" AND b.").append(fieldName).append(" LIKE '%").append(value).append("%' ");
                    } else {
                        sql.append(" AND b.").append(fieldName).append(" = ").append(value);
                    }
                }
            }
        } catch (SecurityException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    private void where_condition_special(StringBuilder sql, BuildingSearchBuilder buildingSearchBuilder) {
        if(buildingSearchBuilder.getAreaFrom() != null || buildingSearchBuilder.getAreaTo() != null) {
            sql.append(" AND EXISTS ( SELECT * FROM rentArea WHERE rentArea.buildingId = b.id ");
            if (buildingSearchBuilder.getAreaFrom() != null) {
                sql.append(" AND rentArea.value >= ").append(buildingSearchBuilder.getAreaFrom());
            }
            if (buildingSearchBuilder.getAreaTo() != null) {
                sql.append(" AND rentArea.value <= ").append(buildingSearchBuilder.getAreaTo());
            }
            sql.append(" ) ");
        }
        if(buildingSearchBuilder.getRentPriceFrom() != null) {
            sql.append(" AND b.rentPrice >= ").append(buildingSearchBuilder.getRentPriceFrom());
        }
        if(buildingSearchBuilder.getRentPriceTo() != null) {
            sql.append(" AND b.rentPrice <= ").append(buildingSearchBuilder.getRentPriceTo());
        }
        if(buildingSearchBuilder.getStaffId() != null) {
            sql.append(" AND assignmentBuilding.staffId = ").append(buildingSearchBuilder.getStaffId());
        }
        sql.append(" AND ( ");
        sql.append(buildingSearchBuilder.getTypeCode().stream().map(item -> " rentType.code = '" + item + "' ").collect(Collectors.joining(" OR ")));
        sql.append(" ) GROUP BY b.id");
    }
}
