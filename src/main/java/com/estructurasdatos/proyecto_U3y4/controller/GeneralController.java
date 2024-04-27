package com.estructurasdatos.proyecto_U3y4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {
    
    @GetMapping("/")
    public String landingPage() {
        return "Landing";
    }

    
}
