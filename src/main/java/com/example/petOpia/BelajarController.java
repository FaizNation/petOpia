package com.example.petOpia;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BelajarController {
     @GetMapping("/")
    public String hello() {
        return"Hello Faiz!";
    }
    
    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable String name) {
        return "hello " + name;
    }
}
