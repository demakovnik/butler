package org.inofttech.butler.dao;



import org.inofttech.butler.entity.Device;
import org.inofttech.butler.entity.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {

    public List<Device> getAllByDeviceType(DeviceType deviceType);
    public Device getDeviceByLinkAddressAndModelNumber(String linkAddress, String modelNumber);
    public Device getDeviceByModelNumber(String modelNumber);
}
