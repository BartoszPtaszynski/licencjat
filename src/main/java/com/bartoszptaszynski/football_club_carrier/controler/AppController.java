package com.bartoszptaszynski.football_club_carrier.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.View;

@Controller
public class AppController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
