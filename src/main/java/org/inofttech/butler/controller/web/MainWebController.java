package org.inofttech.butler.controller.web;

import org.inofttech.butler.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainWebController {

    @Autowired
    AreaService areaService;

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/settings")
    public String getSettingsForm(Model model) {
        model.addAttribute("allAreas", areaService.getAll());
        return "settings";
    }


}
