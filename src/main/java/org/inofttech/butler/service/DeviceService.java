package org.inofttech.butler.service;





import org.inofttech.butler.entity.Device;
import org.inofttech.butler.entity.DeviceType;
import org.inofttech.butler.exception.IncorrectDataItemException;

import java.util.List;

public interface DeviceService {

    public List<Device> getAllByDeviceType(DeviceType deviceType);

    public List<Device> getAllDevices();

    public void saveDevice(Device device) throws IncorrectDataItemException;

    public Device getDeviceById(int id);

    void deleteDeviceById(int id);

    public Device getDeviceByLinkAddressAndModelNumber(String linkAddress, String modelNumber);
}
