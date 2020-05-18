package com.miage.bookingHotel.controller;

import com.miage.bookingHotel.model.Customer;
import com.miage.bookingHotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class customerDetailsController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/customer")
    public String addCustomer(Customer customer, Model model){
        customer.setCustomer_id(customerService.generateCustomer_id(customer.getLastName()));
        customerService.saveCustomer(customer);
        model.addAttribute("msg","Registered successfully");
        model.addAttribute("myCustomerID",customer.getCustomer_id());
        return "registration";
    }
}
