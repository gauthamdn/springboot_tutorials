package com.example.swagger.demoswagger.restservicesdemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class restservicesController {

@GetMapping(path="/hello-swagger")
public String helloSwagger() {
	return "Hello Swagger";
}
	

}
