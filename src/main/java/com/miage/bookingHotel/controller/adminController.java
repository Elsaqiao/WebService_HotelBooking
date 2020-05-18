package com.miage.bookingHotel.controller;

import com.miage.bookingHotel.model.Customer;
import com.miage.bookingHotel.model.Itinerary;
import com.miage.bookingHotel.service.CustomerService;
import com.miage.bookingHotel.service.itineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class adminController {
    @Autowired
    CustomerService customerService;
    @Autowired
    itineraryService itineraryService;

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/customer_list")
    public String customer_list(Model model){
        List<Customer> customers=customerService.getAllCustomers();
        model.addAttribute("customers",customers);
        return "customer_list";
    }
    @RequestMapping("/Itinerary_list")
    public String Itinerary_list(Model model){
        List<Itinerary> itineraries=itineraryService.getAllItineraries();
        model.addAttribute("itineraries",itineraries);
        return "Itinerary_list";
    }
    @GetMapping("/itinerary/{referenceNumber}")
    public String update_Itinerary(@PathVariable("referenceNumber")String referenceNumber, Model model){
        Optional<Itinerary> itinerary=itineraryService.findByReferenceNumber(referenceNumber);
        model.addAttribute("itinerary",itinerary);
        return "update_itinerary";
    }
    @PostMapping("/updateItinerary")
    public String toUpdate_itinerary(Itinerary itinerary){
        itineraryService.updateItinerary(itinerary.getReferenceNumber(),itinerary);
        System.out.println(itinerary.getCheck_in_date());
        return "redirect:/Itinerary_list";
    }
	@RequestMapping("/deleteItinerary/{referenceNumber}")
    public String toDelete_itinerary(@PathVariable("referenceNumber")String referenceNumber){
        itineraryService.deleteItineraryByReferenceNumber(referenceNumber);
        return "redirect:/Itinerary_list";
    }
    @GetMapping("customer/{customer_id}")
    public String update_Customer(@PathVariable("customer_id")String customer_id,Model model){
        Optional<Customer> customer=customerService.findById(customer_id);
        model.addAttribute("customer",customer);
        return "update_customer";
    }
    @PostMapping("/updateCustomer")
    public String toUpdate_customer(Customer customer){
        customerService.updateCustomer(customer.getCustomer_id(),customer);
        return "redirect:/customer_list";
    }
    @RequestMapping("/deleteCustomer/{customer_id}")
    public String toDelete_customer(@PathVariable("customer_id")String customer_id){
        customerService.deleteCustomerById(customer_id);
        return "redirect:/customer_list";
    }
    @RequestMapping("/toaddItinerary")
    public String toAdd_itinerary(){
        return "add_itinerary";
    }
    @PostMapping("/addItinerary")
    public String Add_itinerary(Itinerary itinerary,Model model){
        itinerary.setReferenceNumber(itineraryService.generateReference_num(itinerary.getHotelName(),itinerary.getCustomer_id()));
        itineraryService.saveItinerary(itinerary);
        return "redirect:/Itinerary_list";
    }
    @RequestMapping("/toaddCustomer")
    public String toAdd_customer(){
        return "add_customer";
    }
    @PostMapping("/addCustomer")
    public String Add_customer(Customer customer,Model model){
        customer.setCustomer_id(customerService.generateCustomer_id(customer.getLastName()));
        customerService.saveCustomer(customer);
        return "redirect:/customer_list";
    }
    @RequestMapping("/searchCustomer")
    public String searchCustomer(@RequestParam("customer_id")String customer_id,Model model){
        Optional<Customer> customer=customerService.findById(customer_id);
        Customer customer1=customer.get();
        if(customer.get()!=null){
            model.addAttribute("cid",customer1);
            return "search_customer";
        }
        else{
            model.addAttribute("msg","Please enter the id that exists");
            Customer customer2=new Customer();
            model.addAttribute("cid",customer2);
            return "search_customer";
        }
    }
    @RequestMapping("/searchItinerary")
    public String searchItinerary(@RequestParam("referenceNumber")String referenceNumber,Model model){
        Optional<Itinerary> itinerary=itineraryService.findByReferenceNumber(referenceNumber);
        Itinerary itinerary1=itinerary.get();
        if(itinerary.get()!=null){
            model.addAttribute("iid",itinerary1);
            return "search_itinerary";
        }
        else{
            model.addAttribute("msg","Please enter the reference number that exists");
            Itinerary itinerary2=new Itinerary();
            model.addAttribute("iid",itinerary2);
            return "search_customer";
        }
    }
}

