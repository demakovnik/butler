package org.inofttech.butler.controller.web.registration;


import org.inofttech.butler.controller.common.AbstractController;
import org.inofttech.butler.entity.User;
import org.inofttech.butler.entity.to.UserDto;
import org.inofttech.butler.exception.UserAlreadyExistException;
import org.inofttech.butler.service.UserService;
import org.inofttech.butler.validation.ValidationResponse;
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
public class RegistrationController extends AbstractController<User, UserService> {


    @Autowired
    PasswordEncoder passwordEncoder;

    public RegistrationController(UserService service) {
        super(service);
    }

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
    public ValidationResponse registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto,
                                                  BindingResult result) {
        ValidationResponse validationResponse = new ValidationResponse();
        try {
            if (result.hasErrors()) {
                Map<String, String> errors = result.getFieldErrors().stream()
                        .collect(
                                Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                        );

                validationResponse.setValidated(false);
                validationResponse.setErrorMessages(errors);
            } else {
                validationResponse.setValidated(true);
                service.save(userDto);
            }
        } catch (UserAlreadyExistException uaex) {
            Map<String, String> errors = new HashMap<>();
            errors.put("userExist", uaex.getMessage());
            validationResponse.setValidated(false);
            validationResponse.setErrorMessages(errors);
        }
        finally {
            return validationResponse;
        }
    }
}

