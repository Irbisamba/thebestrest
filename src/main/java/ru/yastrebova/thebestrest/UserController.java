package ru.yastrebova.thebestrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yastrebova.thebestrest.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/hello-world")
    public String sayHello() {
        return "hello_world";
    }

    @GetMapping("/save")
    public ResponseEntity save(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        userService.save(name, email, password);
        return new ResponseEntity(HttpStatus.OK);
    }
}
