package org.inofttech.butler.controller;


import org.inofttech.butler.entity.Device;
import org.inofttech.butler.exception.IncorrectDataItemException;
import org.inofttech.butler.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplicationWebController {

    @Autowired
    DeviceService deviceService;

    @GetMapping("/addDevice")
    public String indexForm(Model model) {
        model.addAttribute("device", new Device());
        return "addDevice";
    }

    @PostMapping("/addDevice")
    public String addNewDevice(@ModelAttribute Device device, Model model) {
        try {
            deviceService.save(device);

            model.addAttribute("message", "Device with Model Serial Number: " + device.getModelNumber()
                    + " and Device Type: " + device.getDeviceType().getText() + " is already added");
            model.addAttribute("device", new Device());
        } catch (IncorrectDataItemException ex) {
            model.addAttribute("device", new Device());
            model.addAttribute("errorMessage",ex.getMessage());
        } finally {
            return "addDevice";
        }
    }
}
