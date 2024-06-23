package com.javaweb.repository.IMPL;

import com.javaweb.repository.IBuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.mapper.IRowMapper;
import com.javaweb.utils.validateDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BuildingRepository extends AbstractRepository<BuildingEntity> implements IBuildingRepository {

    @Autowired
    private IRowMapper<BuildingEntity> rowMapper;

    @Override
    public List<BuildingEntity> findBuilding(Map<String, String> params, List<String> typeCode) throws SQLException, ClassNotFoundException, NumberFormatException {
        StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.districtid, b.street, b.floorarea, b.ward, b.numberOfBasement, b.managerName, b.managerPhoneNumber, " +
                "b.rentprice, b.servicefee, b.brokeragefee FROM building b ");
        joinTable(params, typeCode, sql);
        where_condition(params, typeCode, sql);
        System.out.println(sql.toString());
        return query(sql.toString(), rowMapper);
    }

    private void joinTable(Map<String, String> params, List<String> typeCode, StringBuilder sql) {
        if(typeCode != null && !typeCode.isEmpty()) {
            sql.append(" INNER JOIN buildingrenttype ON buildingrenttype.buildingId = b.id ");
            sql.append(" INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeId ");
        }
        if(validateDataInput.StringValidate(params.get("staffId"))) {
            sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
        }
        if(validateDataInput.StringValidate(params.get("areaFrom")) || validateDataInput.StringValidate(params.get("areaTo"))) {
            sql.append(" INNER JOIN rentarea ON b.id = rentarea.buildingid ");
        }
    }

    private void where_condition(Map<String, String> params, List<String> typeCode, StringBuilder sql) {
        sql.append(" WHERE 1 = 1 ");
        for(Map.Entry<String, String> param : params.entrySet()) {
            String key = param.getKey();
            if(validateDataInput.StringValidate(key)) {
                if(key.equals("numberOfBasement")) {
                    Integer.parseInt(param.getValue());
                    sql.append(" AND b.numberofbasement = ").append(param.getValue());
                }
                else if(key.startsWith("area")) {
                    Integer.parseInt(param.getValue());
                    if(key.endsWith("From")) {
                        sql.append(" AND rentarea.value >= ").append(param.getValue());
                    } else {
                        sql.append(" AND rentarea.value <= ").append(param.getValue());
                    }
                }
                else if(key.equals("staffId")) {
                    sql.append(" AND assignmentbuilding.staffid = ").append(param.getValue());
                }
                else if(key.startsWith("rentPrice")) {
                    Integer.parseInt(param.getValue());
                    if(key.endsWith("From")) {
                        sql.append(" AND b.rentPrice >= ").append(param.getValue());
                    } else {
                        sql.append(" AND b.rentPrice <= ").append(param.getValue());
                    }
                }
                else {
                    sql.append(" AND ").append(key).append(" LIKE '%").append(param.getValue()).append("%'");
                }
            }
        }
        if(typeCode != null && !typeCode.isEmpty()){
            List<String> codes = new ArrayList<String>();
            for(String code : typeCode) {
                codes.add("'" + code + "'");
            }
            sql.append(" AND renttype.code IN ( " + String.join(", ", codes) + " ) ");
        }
        sql.append(" GROUP BY b.id");
    }
}
