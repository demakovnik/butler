package org.inofttech.butler.dao;

import org.inofttech.butler.entity.TemporaryDeviceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemporaryDeviceDetailsRepository extends JpaRepository<TemporaryDeviceDetails,Long> {

    List<TemporaryDeviceDetails> getTemporaryDeviceDetailsByDevice_ModelNumber(String modelNumber);

}
