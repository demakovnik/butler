package org.inofttech.butler.service;


import org.inofttech.butler.dao.DeviceRepository;
import org.inofttech.butler.dao.TemporaryDeviceDetailsRepository;
import org.inofttech.butler.entity.Device;
import org.inofttech.butler.entity.Measurement;
import org.inofttech.butler.entity.TemporaryDeviceDetails;
import org.inofttech.butler.exception.NoSuchItemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TemporaryDeviceDetailServiceImpl implements TemporaryDeviceDetailsService{

    @Autowired
    TemporaryDeviceDetailsRepository temporaryDeviceDetailsRepository;
    @Autowired
    DeviceRepository deviceRepository;


    @Override
    public TemporaryDeviceDetails saveTemporaryDeviceDetail(String modelNumber, Map<String,String> deviceData) throws NoSuchItemException {
        Device device = deviceRepository.getDeviceByModelNumber(modelNumber);
        if (device == null) {
            throw new NoSuchItemException("There is no Device with  Model Number: " + modelNumber
                    + " in database");
        }
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
        return temporaryDeviceDetailsRepository.save(deviceDetails);
    }

    @Override
    public TemporaryDeviceDetails getTemporaryDeviceDetailsById(long id) {
        TemporaryDeviceDetails temporaryDeviceDetails = null;
        Optional<TemporaryDeviceDetails> optionalDeviceDetailsById = temporaryDeviceDetailsRepository.findById(id);
        if (optionalDeviceDetailsById.isPresent()) {
            temporaryDeviceDetails =  optionalDeviceDetailsById.get();
        }
        return temporaryDeviceDetails;
    }

    @Override
    public void deleteTemporaryDeviceDetailById(long id) {
        temporaryDeviceDetailsRepository.deleteById(id);
    }

    @Override
    public List<TemporaryDeviceDetails> getDetailsByDeviceModelNumber(String modelNumber) {
        return temporaryDeviceDetailsRepository.getTemporaryDeviceDetailsByDevice_ModelNumber(modelNumber);
    }
}
