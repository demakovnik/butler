package org.inofttech.butler.service;

import org.inofttech.butler.dao.TemporaryDeviceDetailsRepository;
import org.inofttech.butler.entity.TemporaryDeviceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemporaryDeviceDetailServiceImpl implements TemporaryDeviceDetailsService{

    @Autowired
    TemporaryDeviceDetailsRepository temporaryDeviceDetailsRepository;


    @Override
    public void saveTemporaryDeviceDetail(TemporaryDeviceDetails deviceDetails) {
        temporaryDeviceDetailsRepository.save(deviceDetails);
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
