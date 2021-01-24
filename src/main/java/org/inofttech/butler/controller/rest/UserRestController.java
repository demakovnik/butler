package org.inofttech.butler.controller.rest;


import org.inofttech.butler.controller.common.AbstractController;
import org.inofttech.butler.entity.User;
import org.inofttech.butler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class UserRestController extends AbstractController<User, UserService> {

    public UserRestController(UserService service) {
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

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        service.save(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        service.deleteById(id);
        return "User with ID= " + id + " was deleted";
    }
}
