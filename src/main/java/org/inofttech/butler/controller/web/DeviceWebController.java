package org.inofttech.butler.controller.web;

import org.inofttech.butler.controller.common.AbstractController;
import org.inofttech.butler.entity.Device;
import org.inofttech.butler.entity.to.DeviceDto;
import org.inofttech.butler.entity.to.converter.DeviceConverter;
import org.inofttech.butler.exception.IncorrectDataItemException;
import org.inofttech.butler.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeviceWebController extends AbstractController<Device, DeviceService> {


    protected DeviceWebController(DeviceService service) {
        super(service);
    }

    @GetMapping("/addDevice")
    public String addDeviceForm(Model model) {
        model.addAttribute("device", new DeviceDto());
        return "deviceForm";
    }

    @PostMapping("/addDevice")
    public String addNewDevice(@ModelAttribute("device") DeviceDto deviceDto, Model model) {
        try {
            DeviceConverter deviceConverter = new DeviceConverter();
            Device device = deviceConverter.getEntity(deviceDto);
            service.save(device);

            model.addAttribute("message", "Device with Model Serial Number: " + device.getModelNumber()
                    + " and Device Type: " + device.getDeviceType().getText() + " is already added");
            model.addAttribute("device", new DeviceDto());
        } catch (IncorrectDataItemException ex) {
            model.addAttribute("device", new DeviceDto());
            model.addAttribute("errorMessage", ex.getMessage());
        } finally {
            return "deviceForm";
        }
    }
}
