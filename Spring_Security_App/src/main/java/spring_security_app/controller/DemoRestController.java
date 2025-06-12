package spring_security_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {

    @GetMapping("/getMsg")
    public String getMessage() {
        return "Welcome, Ramkrishna! You are logged in successfully.";
    }
}
