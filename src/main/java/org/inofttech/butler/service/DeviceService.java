package org.inofttech.butler.service;


import org.inofttech.butler.dao.DeviceRepository;
import org.inofttech.butler.dao.UserRepository;
import org.inofttech.butler.entity.Device;
import org.inofttech.butler.entity.DeviceType;
import org.inofttech.butler.entity.User;
import org.inofttech.butler.exception.IncorrectDataItemException;
import org.inofttech.butler.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService extends AbstractService<Device, DeviceRepository> {



    public DeviceService(DeviceRepository repository) {
        super(repository);
    }


    @Override
    public List<Device> getAll() {
        return repository.findAll();
    }

    @Override
    public void save(Device device) throws IncorrectDataItemException {
        Device deviceByModelNumber = repository.getDeviceByModelNumber(device.getModelNumber());
        if (deviceByModelNumber != null) {
            throw new IncorrectDataItemException("Device with Serial Model Number: " +
                    device.getModelNumber() + " is already exists in database");
        }
        repository.save(device);
    }

    @Override
    public Device getById(long id) {

        Device device = null;
        Optional<Device> optionalDevice = repository.findById(id);
        if (optionalDevice.isPresent()) {
            device = optionalDevice.get();
        }
        return device;
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);

    }

    public List<Device> getAllByDeviceType(DeviceType deviceType) {
        return repository.getAllByDeviceType(deviceType);
    }


    public Device getDeviceByLinkAddressAndModelNumber(String linkAddress, String modelNumber) {
        return repository.getDeviceByLinkAddressAndModelNumber(linkAddress,modelNumber);
    }
}
