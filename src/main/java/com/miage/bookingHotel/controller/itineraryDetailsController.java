package com.miage.bookingHotel.controller;

import com.miage.bookingHotel.model.Itinerary;
import com.miage.bookingHotel.service.itineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class itineraryDetailsController {

    @Autowired
    itineraryService itineraryService;

    @PostMapping("/itinerary")
    public String addItinerary(Itinerary itinerary,Model model){
        itinerary.setReferenceNumber(itineraryService.generateReference_num(itinerary.getHotelName(),itinerary.getCustomer_id()));
        itineraryService.saveItinerary(itinerary);
        model.addAttribute("msg","Order created successfully");
        model.addAttribute("myReferenceNumber",itinerary.getReferenceNumber());
        return "booking";
    }
}
