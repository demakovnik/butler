package org.inofttech.butler.controller;


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
public class ApplicationRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private TemporaryDeviceDetailsService temporaryDeviceDetailsService;

    @GetMapping("/users")
    public List<User> showAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return allUsers;
    }

    @GetMapping("/users/name/{name}")
    public User getUserByName(@PathVariable String name) {
        User allUsersByName = userService.getgetUserByName(name);
        return allUsersByName;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;

    }

    @PutMapping("/employees")
    public User updateUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return "User with ID= " + id + " was deleted";
    }

    @GetMapping("/devices")
    public List<Device> showAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/devices/type/{type}")
    public List<Device> getAllByType(@PathVariable String type) {
        DeviceType deviceType = DeviceType.valueOf(type);
        return deviceService.getAllByDeviceType(deviceType);
    }

    @GetMapping("/devices/{id}")
    public Device getDevice(@PathVariable int id) {
        Device device = deviceService.getDeviceById(id);

        if (device == null) {
            throw new NoSuchItemException("There is no device with ID=" + id + " in database");
        }
        return device;
    }

    @PostMapping("/devices")
    public Device addNewDevice(@RequestBody Device device) {
        deviceService.saveDevice(device);
        return device;
    }

    @PutMapping("/devices")
    public Device updateDevice(@RequestBody Device device) {
        deviceService.saveDevice(device);
        return device;
    }

    @DeleteMapping("/devices/{id}")
    public String deleteDevice(@PathVariable int id) {
        Device device = deviceService.getDeviceById(id);
        if (device == null) {
            throw new NoSuchItemException("There is no Device with ID = " + id + " in database");
        }
        deviceService.deleteDeviceById(id);
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
