package hu.unideb.inf.survey.web.registration.controller;

import hu.unideb.inf.survey.service.UserService;
import hu.unideb.inf.survey.service.UserValidationService;
import hu.unideb.inf.survey.service.domain.UserDomain;
import hu.unideb.inf.survey.service.exception.UserRegistrationException;
import hu.unideb.inf.survey.web.registration.model.UserRegistrationDto;
import hu.unideb.inf.survey.web.registration.transformer.UserRegistrationDtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private static final String REQUEST_MAPPING = "/registration";

    private UserService userService;

    private UserRegistrationDtoTransformer userRegistrationDtoTransformer;

    private UserValidationService userValidationService;

    @Autowired
    public RegistrationController(UserService userService, UserRegistrationDtoTransformer userRegistrationDtoTransformer, UserValidationService userValidationService) {
        this.userService = userService;
        this.userRegistrationDtoTransformer = userRegistrationDtoTransformer;
        this.userValidationService = userValidationService;
    }

    @GetMapping(REQUEST_MAPPING)
    public String registrationPageLoaderController(Model model) {
        if (!model.containsAttribute("userRegistrationDto")){
            model.addAttribute("userRegistrationDto", new UserRegistrationDto());
        }
        model.addAttribute("errorMsg", "");
        return "registration";
    }

    @PostMapping(REQUEST_MAPPING)
    public String registrationController(@ModelAttribute(name = "userRegistrationDto") UserRegistrationDto userRegistrationDto, Model model) {
        UserDomain userDomain = userRegistrationDtoTransformer.from(userRegistrationDto);
        String errorMsg = null;
        try {
            userValidationService.checkForEmpties(userDomain);
            userValidationService.validateUserData(userRegistrationDto.getName());
            userValidationService.validatePasswordComplexity(userRegistrationDto.getPassword());
            userValidationService.validatePasswordConfirmation(userRegistrationDto.getPassword(), userRegistrationDto.getPasswordConfirmation());
            userService.registerUser(userDomain);
        } catch (UserRegistrationException e) {
            errorMsg = e.getMessage();
            model.addAttribute("errorMsg", errorMsg);
        }

        return errorMsg == null ? "redirect:login?registratedSuccesfully=false" : "registration";
    }
}
