package org.inofttech.butler.service;


import org.inofttech.butler.dao.AreaRepository;
import org.inofttech.butler.dao.DeviceRepository;
import org.inofttech.butler.entity.Area;
import org.inofttech.butler.entity.Device;
import org.inofttech.butler.entity.DeviceType;
import org.inofttech.butler.exception.IncorrectDataItemException;
import org.inofttech.butler.exception.NoSuchItemException;
import org.inofttech.butler.service.common.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AreaService extends AbstractService<Area, AreaRepository> {

    public AreaService(AreaRepository repository) {
        super(repository);
    }



    @Override
    public List<Area> getAll() {
        return repository.findAll();
    }

    @Override
    public Area save(Area area) throws IncorrectDataItemException {
        Area areaByAddress = repository.getByAddress(area.getAddress());
        if (areaByAddress != null) {
            throw new IncorrectDataItemException("Area with address: " +
                    area.getAddress() + " is already exists in database");
        }
        return repository.save(area);
    }

    public Area update(Area area) {
        return repository.save(area);
    }

    @Override
    public Area getById(long id) {

        Area area = null;
        Optional<Area> optionalArea = repository.findById(id);
        if (optionalArea.isPresent()) {
            area = optionalArea.get();
        } else {
            throw new NoSuchItemException("There is no area with ID=" + id + " in database");
        }
        return area;
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);

    }

    public Area getByAddress(String address) {
        return repository.getByAddress(address);
    }

}
