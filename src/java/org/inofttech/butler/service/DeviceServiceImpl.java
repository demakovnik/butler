package org.inofttech.butler.service;


import org.inofttech.butler.dao.DeviceRepository;
import org.inofttech.butler.entity.Device;
import org.inofttech.butler.entity.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;


    @Override
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public void saveDevice(Device device) {
        deviceRepository.save(device);

    }

    @Override
    public Device getDeviceById(int id) {

        Device device = null;
        Optional<Device> optionalDevice = deviceRepository.findById(id);
        if (optionalDevice.isPresent()) {
            device = optionalDevice.get();
        }
        return device;
    }

    @Override
    public void deleteDeviceById(int id) {
        deviceRepository.deleteById(id);

    }

    @Override
    public List<Device> getAllByDeviceType(DeviceType deviceType) {
        return deviceRepository.getAllByDeviceType(deviceType);
    }

    @Override
    public Device getDeviceByLinkAddressAndModelNumber(String linkAddress, String modelNumber) {
        return deviceRepository.getDeviceByLinkAddressAndModelNumber(linkAddress,modelNumber);
    }
}
