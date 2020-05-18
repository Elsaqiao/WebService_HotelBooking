package com.miage.bookingHotel.controller;

import com.miage.bookingHotel.model.Customer;
import com.miage.bookingHotel.model.Itinerary;
import com.miage.bookingHotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class personal_detailsController {

    @Autowired
    com.miage.bookingHotel.service.itineraryService itineraryService;
    @Autowired
    CustomerService customerService;

    @RequestMapping("/personal_details")
    public String personal_details(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session){
        if("admin".equals(username)&&"password".equals(password)){
            return "admin";
        }
        else {
            List<Customer> customers = customerService.getAllCustomers();
            boolean isUserExist = false;
            for (int i = 0; i < customers.size(); i++) {
                if (username.equals(customers.get(i).getCustomer_id())) {
                    isUserExist = true;
                }
//        if(!StringUtils.isEmpty(username) && "123456".equals(password)) {
//            session.setAttribute("loginUser",username);
//            System.out.println(username);
//            return "personal_details";
//        }
            }
            // Customer currentCustomer=customerService.findById(username).get();
            // System.out.println(currentCustomer.getPassword());
            if (isUserExist && customerService.findById(username).get().getPassword().equals(password)) {
                session.setAttribute("loginUser", username);
                model.addAttribute("currentCustomer", customerService.findById(username).get());
                List<Itinerary> itinerarys = itineraryService.findByCustomer_id(username);
                model.addAttribute("currentItinerary", itinerarys);
                System.out.println(username);
                return "personal_details";
            } else {
                model.addAttribute("msg", "User name or password error");
                return "login";
            }
        }
    }
}
