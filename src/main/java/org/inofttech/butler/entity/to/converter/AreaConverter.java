package org.inofttech.butler.entity.to.converter;

import org.inofttech.butler.entity.Area;
import org.inofttech.butler.entity.Device;
import org.inofttech.butler.entity.to.AreaDto;
import org.inofttech.butler.entity.to.DeviceDto;

public class AreaConverter extends DtoToEntityConverter {

    @Override
    public Area getEntity(Object dtoObject) {
        AreaDto areaDto = (AreaDto) dtoObject;
        Area area = new Area();
        area.setAddress(areaDto.getAddress());
        area.setDescription(areaDto.getDescription());
        return area;
    }
}
