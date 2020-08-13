package ru.yastrebova.thebestrest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/hello-world")
    public String sayHello() {
        return "hello_world";
    }
}
