package org.inofttech.butler.service;

import org.inofttech.butler.entity.TemporaryDeviceDetails;

import java.util.List;

public interface TemporaryDeviceDetailsService {

    public void saveTemporaryDeviceDetail(TemporaryDeviceDetails deviceDetails);

    public TemporaryDeviceDetails getTemporaryDeviceDetailsById(long id);

    public void deleteTemporaryDeviceDetailById(long id);

    public List<TemporaryDeviceDetails> getDetailsByDeviceModelNumber(String modelNumber);
}
