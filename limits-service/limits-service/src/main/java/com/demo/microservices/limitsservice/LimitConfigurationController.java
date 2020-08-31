package com.demo.microservices.limitsservice;

import com.demo.microservices.limitsservice.bean.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromConfiguration(){
        return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }
    
    @GetMapping("/fault-tolerance-demo")
    @HystrixCommand(fallbackMethod = "fallbackRetrieveConfigruation")
    public LimitConfiguration retriveLimits() {
    	throw new RuntimeException("Service Not available");
    }
    
    public LimitConfiguration fallbackRetrieveConfigruation() {
    	return new LimitConfiguration(999,9);
    }

}
