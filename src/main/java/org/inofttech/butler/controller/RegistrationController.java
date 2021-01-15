package org.inofttech.butler.controller;


import org.inofttech.butler.entity.to.UserDto;
import org.inofttech.butler.exception.UserAlreadyExistException;
import org.inofttech.butler.service.UserService;
import org.inofttech.butler.validation.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    @PostMapping(value = "/registration", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public UserResponse registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto,
                                            BindingResult result) {
        UserResponse userResponse = new UserResponse();
        try {
            if (result.hasErrors()) {
                Map<String, String> errors = result.getFieldErrors().stream()
                        .collect(
                                Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                        );

                userResponse.setValidated(false);
                userResponse.setErrorMessages(errors);
            } else {
                userResponse.setValidated(true);
                userService.saveUser(userDto);
            }
        } catch (UserAlreadyExistException uaex) {
            Map<String, String> errors = new HashMap<>();
            errors.put("userExist", uaex.getMessage());
            userResponse.setValidated(false);
            userResponse.setErrorMessages(errors);
        }
        finally {
            return userResponse;
        }
    }
   /* public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid final UserDto userDto,
            final BindingResult result,
            HttpServletRequest httpServletRequest,
            Errors errors) {
        UserResponse response = new UserResponse();

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
    }*/

}

