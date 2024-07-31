package com.baygrove.capstone.controller;

import com.baygrove.capstone.database.dao.UserDAO;
import com.baygrove.capstone.form.CreateUserFormBean;
import com.baygrove.capstone.security.AuthenticatedUserUtilities;
import com.baygrove.capstone.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import jakarta.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;


    @GetMapping("signup")
    public ModelAndView createAccount() {
        ModelAndView response = new ModelAndView("user/sign-up-form");

        return response;
    }

    @PostMapping("signup")
    public ModelAndView createAccountSubmit(@Valid CreateUserFormBean form, BindingResult bindingResult, HttpSession session) {

        ModelAndView response = new ModelAndView("user/sign-up-form");
        log.info("submit form: " + form.toString());

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info("Validation error : " + ((FieldError) error).getField() + " = " + error.getDefaultMessage());
            }

            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);

            return response;
        }

        userService.createUser(form);

        // Authenticate the user after creating account
        authenticatedUserUtilities.manualAuthentication(session, form.getEmail(), form.getPassword());

        // redirect to home page
        response.setViewName("redirect:/");

        return response;

    }

    @GetMapping("login")
    public ModelAndView getLogin() {
        ModelAndView response = new ModelAndView("user/log-in-form");

        return response;
    }


}
