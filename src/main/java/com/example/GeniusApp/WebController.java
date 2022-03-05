package com.example.GeniusApp;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {
    @GetMapping("/songs")
    public String allsongs(){
        return "Portal";
    }
}
