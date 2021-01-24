package org.inofttech.butler.controller.rest;

import org.inofttech.butler.controller.common.AbstractController;
import org.inofttech.butler.entity.Device;
import org.inofttech.butler.entity.DeviceType;
import org.inofttech.butler.entity.to.DeviceDto;
import org.inofttech.butler.entity.to.converter.DeviceConverter;
import org.inofttech.butler.exception.NoSuchItemException;
import org.inofttech.butler.service.DeviceService;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class DeviceRestController extends AbstractController<Device, DeviceService> {

    protected DeviceRestController(DeviceService service) {
        super(service);
    }

    @GetMapping(value = "/csrf-token")
    public @ResponseBody
    String getCsrfToken(HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        return token.getToken();
    }

    @GetMapping("/devices")
    public List<Device> showAllDevices() {
        return service.getAll();
    }

    @GetMapping("/devices/type/{type}")
    public List<Device> getAllByType(@PathVariable String type) {
        DeviceType deviceType = DeviceType.valueOf(type);
        return service.getAllByDeviceType(deviceType);
    }

    @GetMapping("/devices/{id}")
    public Device getDevice(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping("/devices")
    public Device addNewDevice(@RequestBody DeviceDto deviceDto) {
        DeviceConverter deviceConverter = new DeviceConverter();
        Device device = deviceConverter.getEntity(deviceDto);
        service.save(device);
        return device;
    }

    @PutMapping("/devices")
    public Device updateDevice(@RequestBody Device device) {
        service.save(device);
        return device;
    }

    @DeleteMapping("/devices/{id}")
    public String deleteDevice(@PathVariable int id) {
        Device device = service.getById(id);
        if (device == null) {
            throw new NoSuchItemException("There is no Device with ID = " + id + " in database");
        }
        service.deleteById(id);
        return "Device with ID = " + id + " was deleted";
    }

}
