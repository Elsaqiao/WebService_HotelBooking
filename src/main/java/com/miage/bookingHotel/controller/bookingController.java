package com.miage.bookingHotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class bookingController {
    @RequestMapping("/booking")
    public String booking(@RequestParam("currentId")String currentId, Model model)
    {
        System.out.println(currentId);
        model.addAttribute("currentId",currentId);
        return "booking";
    }
}
