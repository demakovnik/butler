package org.inofttech.butler.entity.to.converter;

import org.inofttech.butler.entity.Device;
import org.inofttech.butler.entity.to.DeviceDto;

public class DeviceConverter extends DtoToEntityConverter {

    @Override
    public Device getEntity(Object dtoObject) {
        DeviceDto deviceDto = (DeviceDto) dtoObject;
        Device device = new Device();
        device.setModelNumber(deviceDto.getModelNumber());
        device.setDescription(deviceDto.getDescription());
        device.setLinkAddress(deviceDto.getLinkAddress());
        device.setDeviceType(deviceDto.getDeviceType());
        device.setDetails(deviceDto.getDetails());
        return device;
    }
}
