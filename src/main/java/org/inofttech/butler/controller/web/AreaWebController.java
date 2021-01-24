package org.inofttech.butler.controller.web;

import org.inofttech.butler.controller.common.AbstractController;
import org.inofttech.butler.entity.Area;
import org.inofttech.butler.entity.to.AreaDto;
import org.inofttech.butler.entity.to.converter.AreaConverter;
import org.inofttech.butler.exception.IncorrectDataItemException;
import org.inofttech.butler.service.AreaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class AreaWebController extends AbstractController<Area, AreaService> {


    protected AreaWebController(AreaService service) {
        super(service);
    }

    @GetMapping("/addArea")
    public String addAreaForm(Model model) {
            model.addAttribute("area", new AreaDto());

        return "areaForm";
    }

    @PostMapping("/addArea")
    public String addNewArea(@ModelAttribute("area") AreaDto areaDto, Model model) {
        try {
            AreaConverter areaConverter = new AreaConverter();
            Area area = areaConverter.getEntity(areaDto);
            service.save(area);

            model.addAttribute("message", "Area with address: " + area.getAddress()
                    + " added");
            model.addAttribute("allAreas", service.getAll());
        } catch (IncorrectDataItemException ex) {
            model.addAttribute("device", new AreaDto());
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("allAreas", service.getAll());
        } finally {
            return "settings";
        }
    }

    @PostMapping("/getArea")
    public String getArea(@RequestParam(value = "id", required = false) Long id,
                          Model model) {
        Area area = service.getById(id);
        AreaDto areaDto = new AreaDto();
        areaDto.setDescription(area.getDescription());
        areaDto.setAddress(area.getAddress());
        model.addAttribute("area", areaDto);
        model.addAttribute("action","/editArea");
        model.addAttribute("areaId",id);
        return "areaForm";
    }

    @PostMapping("/editArea")
    public String editArea(@ModelAttribute("area") @Valid AreaDto areaDto, @RequestParam("id") long id,
                           Model model) {
        Area area = service.getById(id);
        area.setAddress(areaDto.getAddress());
        area.setDescription(areaDto.getDescription());
        service.update(area);
        model.addAttribute("allAreas",service.getAll());
        return "settings";
    }

    @PostMapping("/deleteArea")
    public String deleteArea(@RequestParam("id") long id,
                           Model model) {
        service.deleteById(id);
        model.addAttribute("allAreas",service.getAll());
        return "settings";
    }
}
