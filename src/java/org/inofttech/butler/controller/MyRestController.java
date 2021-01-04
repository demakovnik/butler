package org.inofttech.butler.controller;


import org.inofttech.butler.entity.*;
import org.inofttech.butler.exception_handling.NoSuchItemException;
import org.inofttech.butler.request.DeviceRequest;
import org.inofttech.butler.service.DeviceService;
import org.inofttech.butler.service.EmployeeService;
import org.inofttech.butler.service.TemporaryDeviceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private TemporaryDeviceDetailsService temporaryDeviceDetailsService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/name/{name}")
    public List<Employee> showAllEmployeesByName(@PathVariable String name) {
        List<Employee> allEmployeesByName = employeeService.getAllByName(name);
        return allEmployeesByName;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);

        if (employee == null) {
            throw new NoSuchItemException("There is no employee with ID=" + id
                    + " in database");
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;

    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            throw new NoSuchItemException("There is no Employee with ID= " + id + " in database");
        }
        employeeService.deleteEmployeeById(id);
        return "Employee with ID= " + id + " was deleted";
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
        String linkAddress = deviceRequest.getLinkAddress();
        String modelNumber = deviceRequest.getModelNumber();
        Device device = deviceService.getDeviceByLinkAddressAndModelNumber(linkAddress, modelNumber);
        if (device == null) {
            throw new NoSuchItemException("There is no Device with Link address: " + linkAddress
                    + " and Model Number: " + modelNumber + " in database");
        }
        TemporaryDeviceDetails temporaryDeviceDetails = new TemporaryDeviceDetails();
        temporaryDeviceDetails.setDateTime(LocalDateTime.now());
        temporaryDeviceDetails.setDevice(device);
        List<Measurement> measurements = deviceRequest.getDeviceData().entrySet().stream().map(pair -> {
            String key = pair.getKey();
            String value = pair.getValue();
            Measurement measurement = new Measurement();
            measurement.setKey(key);
            measurement.setValue(value);
            measurement.setDetails(temporaryDeviceDetails);
            return measurement;
        }).collect(Collectors.toList());
        temporaryDeviceDetails.setMeasurements(measurements);
        temporaryDeviceDetailsService.saveTemporaryDeviceDetail(temporaryDeviceDetails);
        return temporaryDeviceDetails;
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
