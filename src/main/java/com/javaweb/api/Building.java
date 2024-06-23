package com.javaweb.api;

import com.javaweb.model.BuildingDTO;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
public class Building {

    @Autowired
    private IBuildingService buildingService;

    @GetMapping(value = "/api/building/")
    public List<BuildingDTO> findBuilding(@RequestParam Map<String, String> params, @RequestParam(value = "typeCode", required = false) List<String> typeCode) throws SQLException, ClassNotFoundException, NumberFormatException {
        return buildingService.findBuilding(params, typeCode);
    }
}
