package ru.yastrebova.thebestrest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @PostMapping("/restaurant/create")
    public ResponseEntity createRestauratn() {
        return null;
    }
}
