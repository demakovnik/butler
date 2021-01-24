package org.inofttech.butler.dao;


import org.inofttech.butler.dao.common.CommonRepository;
import org.inofttech.butler.entity.Area;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends CommonRepository<Area> {

    public Area getByAddress(String address);
}
