package org.inofttech.butler;

import org.inofttech.butler.fileoperator.ObjectToByteStreamOperator;
import org.inofttech.butler.model.Device;
import org.inofttech.butler.model.TempSensor;
import org.inofttech.butler.model.User;
import org.inofttech.butler.storage.PathStorage;
import org.inofttech.butler.storage.Storage;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Storage<Device> devicePathStorage = new PathStorage<>(new File("storage/devices"),new ObjectToByteStreamOperator());
        Storage<User> userStorage = new PathStorage<>(new File("storage/users"),new ObjectToByteStreamOperator());
        Device device = new TempSensor("name","dfgdfgdfg","dfgdfgfdfgdfgdfhhh");
        User user = new User("Sergey");
        devicePathStorage.save(device);
        userStorage.save(user);
        List<Device> deviceList = devicePathStorage.getAllSorted();

        System.out.println(deviceList.size());
    }
}
