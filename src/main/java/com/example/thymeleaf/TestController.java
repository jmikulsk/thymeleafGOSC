package com.example.thymeleaf;

import com.example.thymeleaf.model.User;
import com.example.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    public static class NotFoundException extends RuntimeException {
    }

    @Autowired
    UserService userService;

    @GetMapping("/listUsers")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "list-users-view";
    }

    @GetMapping("/getUser/{id}")
    public String getUser(@PathVariable String id, Model model) {
        int i = Integer.parseInt(id);
        User user = userService.getUser(i);
        if(user == null) {
            throw new NotFoundException();
        }
        model.addAttribute("user", user);
        return "user-details";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/addUser")
    public String createUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "add-user";
        }

        userService.createUser(user.getImie(), user.getNazwisko(), user.getWiek());
        return "redirect:/listUsers";
    }

    @ExceptionHandler(NotFoundException.class)
    public String notFound() {
        return "404";
    }

}
