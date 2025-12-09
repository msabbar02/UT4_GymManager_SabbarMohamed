package org.ut4.gymmanager.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeWebController {
    public HomeWebController() {
    }

    @GetMapping("/web")
    public String home(){
        return "home";
    }
}
