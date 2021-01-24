package org.inofttech.butler.dao;


import org.inofttech.butler.dao.common.CommonRepository;
import org.inofttech.butler.entity.Area;
import org.inofttech.butler.entity.Building;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends CommonRepository<Building> {

    public List<Building> getAllByArea_Address(String address);

    public Building getByAreaAndDescription(Area area, String description);
}
