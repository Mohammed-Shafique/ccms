package com.shafique.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @author mohammedshafique
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome(){
        return "index";
    }
}
