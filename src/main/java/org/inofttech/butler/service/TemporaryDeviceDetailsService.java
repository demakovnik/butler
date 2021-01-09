package org.inofttech.butler.service;




import org.inofttech.butler.entity.TemporaryDeviceDetails;
import org.inofttech.butler.exception.NoSuchItemException;

import java.util.List;
import java.util.Map;

public interface TemporaryDeviceDetailsService {

    public TemporaryDeviceDetails saveTemporaryDeviceDetail(String modelNumber, Map<String,String> deviceData) throws NoSuchItemException;

    public TemporaryDeviceDetails getTemporaryDeviceDetailsById(long id);

    public void deleteTemporaryDeviceDetailById(long id);

    public List<TemporaryDeviceDetails> getDetailsByDeviceModelNumber(String modelNumber);
}
