package org.inofttech.butler.controller;


import org.inofttech.butler.controller.common.AbstractController;
import org.inofttech.butler.entity.Device;
import org.inofttech.butler.entity.DeviceType;
import org.inofttech.butler.entity.TemporaryDeviceDetails;
import org.inofttech.butler.entity.User;
import org.inofttech.butler.exception.NoSuchItemException;
import org.inofttech.butler.request.DeviceRequest;
import org.inofttech.butler.service.DeviceService;
import org.inofttech.butler.service.TemporaryDeviceDetailsService;
import org.inofttech.butler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class ApplicationRestController extends AbstractController<User, UserService> {


    @Autowired
    private DeviceService deviceService;

    @Autowired
    private TemporaryDeviceDetailsService temporaryDeviceDetailsService;

    public ApplicationRestController(UserService service) {
        super(service);
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        List<User> allUsers = service.getAll();
        return allUsers;
    }

    @GetMapping("/users/name/{name}")
    public User getUserByName(@PathVariable String name) {
        User allUsersByName = service.getUserByName(name);
        return allUsersByName;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        service.save(user);
        return user;

    }

    @PutMapping("/employees")
    public User updateUser(@RequestBody User user) {
        service.save(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        service.deleteById(id);
        return "User with ID= " + id + " was deleted";
    }

    @GetMapping("/devices")
    public List<Device> showAllDevices() {
        return deviceService.getAll();
    }

    @GetMapping("/devices/type/{type}")
    public List<Device> getAllByType(@PathVariable String type) {
        DeviceType deviceType = DeviceType.valueOf(type);
        return deviceService.getAllByDeviceType(deviceType);
    }

    @GetMapping("/devices/{id}")
    public Device getDevice(@PathVariable int id) {
        Device device = deviceService.getById(id);

        if (device == null) {
            throw new NoSuchItemException("There is no device with ID=" + id + " in database");
        }
        return device;
    }

    @PostMapping("/devices")
    public Device addNewDevice(@RequestBody Device device) {
        deviceService.save(device);
        return device;
    }

    @PutMapping("/devices")
    public Device updateDevice(@RequestBody Device device) {
        deviceService.save(device);
        return device;
    }

    @DeleteMapping("/devices/{id}")
    public String deleteDevice(@PathVariable int id) {
        Device device = deviceService.getById(id);
        if (device == null) {
            throw new NoSuchItemException("There is no Device with ID = " + id + " in database");
        }
        deviceService.deleteById(id);
        return "Device with ID = " + id + " was deleted";
    }

    @PostMapping("/details")
    public TemporaryDeviceDetails addNewDetails(@RequestBody DeviceRequest deviceRequest) {
        String modelNumber = deviceRequest.getModelNumber();
        Map<String, String> deviceData = deviceRequest.getDeviceData();
        return temporaryDeviceDetailsService.saveTemporaryDeviceDetail(modelNumber, deviceData);
    }

    @GetMapping("/details/devicemodelnumber/{modelnumber}")
    public List<TemporaryDeviceDetails> getDeviceDetailsByDeviceModelNumber(@PathVariable String modelnumber) {
        return temporaryDeviceDetailsService.getDetailsByDeviceModelNumber(modelnumber);
    }

    @GetMapping("/details/{id}")
    TemporaryDeviceDetails getDetailsById(@PathVariable long id) {
        TemporaryDeviceDetails temporaryDeviceDetails = temporaryDeviceDetailsService.getTemporaryDeviceDetailsById(id);
        return temporaryDeviceDetails;
    }
}
