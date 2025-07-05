package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.error.IdInvalidException;

@RestController
public class HelloController {
    @GetMapping("/")
    public String hello() throws IdInvalidException {
        if (true) {
            throw new IdInvalidException("check mate hoidanit");
        }
        return "hello";
    }
}
