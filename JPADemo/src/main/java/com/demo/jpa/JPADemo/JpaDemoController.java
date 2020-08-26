package com.demo.jpa.JPADemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JpaDemoController {

    @GetMapping("/index")
    public String TestMethod(){
        return "Hello Spring Boot";
    }

}
