package org.inofttech.butler.controller.rest;

import org.inofttech.butler.controller.common.AbstractController;
import org.inofttech.butler.entity.TemporaryDeviceDetails;
import org.inofttech.butler.request.DeviceRequest;
import org.inofttech.butler.service.TemporaryDeviceDetailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class DetailsRestController extends AbstractController<TemporaryDeviceDetails, TemporaryDeviceDetailService> {


    protected DetailsRestController(TemporaryDeviceDetailService service) {
        super(service);
    }

    @PostMapping("/details")
    public TemporaryDeviceDetails addNewDetails(@RequestBody DeviceRequest deviceRequest) {
        String modelNumber = deviceRequest.getModelNumber();
        Map<String, String> deviceData = deviceRequest.getDeviceData();
        return service.saveTemporaryDeviceDetails(modelNumber, deviceData);
    }

    @GetMapping("/details/devicemodelnumber/{modelnumber}")
    public List<TemporaryDeviceDetails> getDeviceDetailsByDeviceModelNumber(@PathVariable String modelnumber) {
        return service.getDetailsByDeviceModelNumber(modelnumber);
    }

    @GetMapping("/details/{id}")
    TemporaryDeviceDetails getDetailsById(@PathVariable long id) {
        TemporaryDeviceDetails temporaryDeviceDetails = service.getById(id);
        return temporaryDeviceDetails;
    }
}
