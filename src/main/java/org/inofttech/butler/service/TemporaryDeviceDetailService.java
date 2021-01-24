package org.inofttech.butler.service;


import org.inofttech.butler.dao.DeviceRepository;
import org.inofttech.butler.dao.TemporaryDeviceDetailsRepository;
import org.inofttech.butler.entity.Device;
import org.inofttech.butler.entity.Measurement;
import org.inofttech.butler.entity.TemporaryDeviceDetails;
import org.inofttech.butler.exception.NoSuchItemException;
import org.inofttech.butler.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TemporaryDeviceDetailService extends AbstractService<TemporaryDeviceDetails, TemporaryDeviceDetailsRepository> {

    @Autowired
    DeviceRepository deviceRepository;

    public TemporaryDeviceDetailService(TemporaryDeviceDetailsRepository repository) {
        super(repository);
    }



    public TemporaryDeviceDetails saveTemporaryDeviceDetails(String modelNumber, Map<String,String> deviceData) throws NoSuchItemException {
        Device device = getDeviceByModelNumber(modelNumber);
        TemporaryDeviceDetails deviceDetails = new TemporaryDeviceDetails();
        deviceDetails.setDateTime(LocalDateTime.now());
        deviceDetails.setDevice(device);
        List<Measurement> measurements = deviceData.entrySet().stream().map(pair -> {
            String key = pair.getKey();
            String value = pair.getValue();
            Measurement measurement = new Measurement();
            measurement.setKey(key);
            measurement.setValue(value);
            measurement.setDetails(deviceDetails);
            return measurement;
        }).collect(Collectors.toList());
        deviceDetails.setMeasurements(measurements);
        return repository.save(deviceDetails);
    }



    public List<TemporaryDeviceDetails> getDetailsByDeviceModelNumber(String modelNumber) {
        return repository.getTemporaryDeviceDetailsByDevice_ModelNumber(modelNumber);
    }

    @Override
    public TemporaryDeviceDetails save(TemporaryDeviceDetails entity) {
        String modelNumber = entity.getDevice().getModelNumber();
        Device device = getDeviceByModelNumber(modelNumber);
        entity.setDateTime(LocalDateTime.now());
        entity.setDevice(device);
        return repository.save(entity);
    }

    private Device getDeviceByModelNumber(String modelNumber) {
        Device device = deviceRepository.getDeviceByModelNumber(modelNumber);
        if (device == null) {
            throw new NoSuchItemException("There is no Device with  Model Number: " + modelNumber
                    + " in database");
        }
        return device;
    }

    @Override
    public List<TemporaryDeviceDetails> getAll() {
        return repository.findAll();
    }

    @Override
    public TemporaryDeviceDetails getById(long id) {
        TemporaryDeviceDetails temporaryDeviceDetails = null;
        Optional<TemporaryDeviceDetails> optionalDeviceDetailsById = repository.findById(id);
        if (optionalDeviceDetailsById.isPresent()) {
            temporaryDeviceDetails =  optionalDeviceDetailsById.get();
        }
        return temporaryDeviceDetails;
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);

    }
}
