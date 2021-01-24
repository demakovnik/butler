package org.inofttech.butler.dao;


import org.inofttech.butler.dao.common.CommonRepository;
import org.inofttech.butler.entity.Device;
import org.inofttech.butler.entity.DeviceType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends CommonRepository<Device> {

    public List<Device> getAllByDeviceType(DeviceType deviceType);
    public Device getDeviceByLinkAddressAndModelNumber(String linkAddress, String modelNumber);
    public Device getDeviceByModelNumber(String modelNumber);
}
