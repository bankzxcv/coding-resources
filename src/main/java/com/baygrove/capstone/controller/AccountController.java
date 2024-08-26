package com.baygrove.capstone.controller;


import com.baygrove.capstone.database.dao.UserDAO;
import com.baygrove.capstone.database.entity.User;
import com.baygrove.capstone.form.EditUserFormBean;
import com.baygrove.capstone.form.UserFormBean;
import com.baygrove.capstone.security.AuthenticatedUserUtilities;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/account")
public class AccountController {

    private final AuthenticatedUserUtilities authenticatedUserUtilities;
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountController(AuthenticatedUserUtilities authenticatedUserUtilities, UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.authenticatedUserUtilities = authenticatedUserUtilities;
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/settings")
    public ModelAndView getAccountSettings() {
        ModelAndView response = new ModelAndView("user/account-settings");
        User user = authenticatedUserUtilities.getCurrentUser();
        response.addObject("user", user);
        return response;
    }

    @GetMapping("/settings/update")
    public ModelAndView getAccountSettingsForm() {
        ModelAndView response = new ModelAndView("user/account-settings-form");

        User user = authenticatedUserUtilities.getCurrentUser();
        response.addObject("user", user);

        UserFormBean form = new UserFormBean();
        form.setId(user.getId());
        form.setEmail(user.getEmail());
        form.setUsername(user.getUsername());

        response.addObject("form", form);
        return response;
    }

    @PostMapping("/settings/update/submit")
    public ModelAndView submitAccountSettingsUpdate(@Valid EditUserFormBean form, BindingResult bindingResult) {
        ModelAndView response = new ModelAndView("user/account-settings-form");

        User currentUser = authenticatedUserUtilities.getCurrentUser();

        // check if username is unique
        User userByUsername = userDAO.findByUsernameIgnoreCase(form.getUsername());


        if (userByUsername != null && !userByUsername.getUsername().equals(currentUser.getUsername())) {
            bindingResult.rejectValue("username", "username", "This username is already in use.");
        }

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info("Validation error : " + ((FieldError) error).getField() + " = " + error.getDefaultMessage());
            }

            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);

            return response;
        }


        currentUser.setUsername(form.getUsername());

        String encryptedPassword = passwordEncoder.encode(form.getPassword());

        currentUser.setPassword(encryptedPassword);

        userDAO.save(currentUser);

        response.setViewName("redirect:/account/settings");

        return response;
    }
}
