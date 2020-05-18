package com.miage.bookingHotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class hotelController {
    @RequestMapping("/hotel")
    public String hotel(@RequestParam("city")String city, Model model){
        model.addAttribute("city",city);
        //model.addAttribute("checkin_date",checkin_date);
        // model.addAttribute("checkout_date",checkout_date);
        return "hotel";
    }
}
