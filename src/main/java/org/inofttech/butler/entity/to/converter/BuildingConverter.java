package org.inofttech.butler.entity.to.converter;

import org.inofttech.butler.entity.Building;
import org.inofttech.butler.entity.to.BuildingDto;

public class BuildingConverter extends DtoToEntityConverter {

    @Override
    public Building getEntity(Object dtoObject) {
        BuildingDto buildingDto = (BuildingDto) dtoObject;
        Building building = new Building();
        building.setArea(buildingDto.getArea());
        building.setDescription(buildingDto.getDescription());
        return building;
    }
}
