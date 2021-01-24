package org.inofttech.butler.controller.web;

import org.inofttech.butler.controller.common.AbstractController;
import org.inofttech.butler.entity.Area;
import org.inofttech.butler.entity.Building;
import org.inofttech.butler.entity.Device;
import org.inofttech.butler.entity.to.BuildingDto;
import org.inofttech.butler.entity.to.converter.BuildingConverter;
import org.inofttech.butler.entity.to.converter.DeviceConverter;
import org.inofttech.butler.exception.IncorrectDataItemException;
import org.inofttech.butler.service.AreaService;
import org.inofttech.butler.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BuildingWebController extends AbstractController<Building, BuildingService> {

    @Autowired
    AreaService areaService;

    public BuildingWebController(BuildingService service) {
        super(service);
    }

    @GetMapping("/addBuilding")
    public String addAreaForm(Model model) {
        model.addAttribute("building", new BuildingDto());
        model.addAttribute("allAreas", areaService.getAll());
        return "buildingForm";
    }

    @PostMapping("/addBuilding")
    public String addNewBuilding(@RequestParam("address") String address,
                                 @RequestParam("description") String description,
                                 Model model) {
        List<Area> areaList = areaService.getAll();
        try {
            Area area = areaService.getByAddress(address);
            Building building = new Building();
            building.setDescription(description);
            building.setArea(area);
            service.save(building);

            model.addAttribute("message", "Building with address: " + building.getArea().getAddress()
                    + " and description: " + building.getDescription() + " is already added");
            model.addAttribute("building", new BuildingDto());
            model.addAttribute("allAreas", areaList);
        } catch (IncorrectDataItemException ex) {
            model.addAttribute("building", new BuildingDto());
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("allAreas", areaList);
        } finally {
            return "buildingForm";
        }
    }
}
