package com.example.thymeleaf.skierowanie.controller;

import com.example.thymeleaf.TestController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralControllerAdvice {


    @ExceptionHandler(TestController.NotFoundException.class)
    public String notFound() {
        return "404";
    }

}
