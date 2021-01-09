package org.inofttech.butler.controller;



import org.inofttech.butler.entity.User;
import org.inofttech.butler.entity.to.UserDto;
import org.inofttech.butler.exception.UserAlreadyExistException;
import org.inofttech.butler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping("/login")
    String getLoginForm(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        model.addAttribute("errorLogin", error != null);
        model.addAttribute("logout", logout != null);
        model.addAttribute("user", new UserDto());
        return "login";
    }

    @PostMapping("/registration")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid final UserDto userDto,
            final BindingResult result,
            HttpServletRequest httpServletRequest,
            Errors errors) {

        try {
            if (result.hasErrors()) {
                ModelAndView modelAndView = new ModelAndView("login", "user", userDto);
                return modelAndView;
            }
            User registered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException uaeEx) {
            ModelAndView mav = new ModelAndView("login", "user", userDto);
            mav.addObject("message", "An account for that username already exists.");
            return mav;
        }

        return new ModelAndView("login", "user", userDto);
    }

}

