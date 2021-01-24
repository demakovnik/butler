package org.inofttech.butler.service;


import org.inofttech.butler.dao.BuildingRepository;
import org.inofttech.butler.entity.Area;
import org.inofttech.butler.entity.Building;
import org.inofttech.butler.exception.IncorrectDataItemException;
import org.inofttech.butler.exception.NoSuchItemException;
import org.inofttech.butler.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BuildingService extends AbstractService<Building, BuildingRepository> {


    public BuildingService(BuildingRepository repository) {
        super(repository);
    }

    @Override
    public Building save(Building building) {
        Area area = building.getArea();
        String description = building.getDescription();
        Building byAreaAndDescription = repository.getByAreaAndDescription(area, description);
        if (byAreaAndDescription != null) {
            throw new IncorrectDataItemException("Building with address: " +
                    area.getAddress() + " and description: " + description + " is already exists in database");
        }
        return repository.save(building);
    }

    @Override
    public List<Building> getAll() {
        return repository.findAll();
    }

    @Override
    public Building getById(long id) {

        Building building = null;
        Optional<Building> optionalBuilding = repository.findById(id);
        if (optionalBuilding.isPresent()) {
            building = optionalBuilding.get();
        } else {
            throw new NoSuchItemException("There is no area with ID=" + id + " in database");
        }
        return building;
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

}
