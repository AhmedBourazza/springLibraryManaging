package com.library.ensaf.projet.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/sign")
    public String sign() {
        return "signUser";
    }

/*
    @GetMapping("/espaceClient")
    public String aboutPage() {
        return "espaceClient"; 

    
    

    }*/
    /*@GetMapping("/Admin")
    public String BorrowPage() {
        return "Admin"; 


    }
  */
}
