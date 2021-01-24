package org.inofttech.butler.controller.web;



import org.inofttech.butler.dao.UserRepository;
import org.inofttech.butler.entity.Role;
import org.inofttech.butler.entity.User;
import org.inofttech.butler.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String users(Model model) {
        List<User> users = (List<User>) userRepository.findAll();
        model.addAttribute("users", users.stream().sorted(Comparator.comparing(User::getUsername)).collect(Collectors.toList()));
        return "users";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(@RequestParam(name = "userId") User user,
                           @RequestParam Map<String, String> formdata) {
        user.getRoles().clear();
        String username = formdata.get("username");
        User anotherUser = userRepository.findByUsername(username);
        if (anotherUser != null && anotherUser.getId() != user.getId()) {
            throw new UserAlreadyExistException("User with name " + username + " is already exists!");
        }
        Set<Role> userRoles = user.getRoles();
        for (Role role : Role.values()) {
            if (formdata.containsKey(role.name())) {
                userRoles.add(role);
            }
        }
        user.setUsername(username);
        user.setRoles(userRoles);
        userRepository.save(user);

        return "redirect:/user";
    }

    @GetMapping("{user}/delete")
    public String userDelete(@PathVariable User user,
                             @AuthenticationPrincipal User currentUser) {
        if (user.getUsername().equals(currentUser.getUsername())) {
            throw new UserAlreadyExistException("You cannot delete yourself!");
        }
        userRepository.delete(user);
        return "redirect:/user";
    }
}