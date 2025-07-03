package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;

@RestController
public class HelloController {
    @GetMapping("/")
    public String hello() {
        
        return "hello";
    }
}
