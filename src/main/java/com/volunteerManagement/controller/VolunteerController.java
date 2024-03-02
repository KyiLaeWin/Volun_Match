package com.volunteerManagement.controller;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.volunteerManagement.entity.Volunteer;
import com.volunteerManagement.service.VolunteerService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.*;
import org.springframework.validation.FieldError;

@Controller
public class VolunteerController {
	
	@Autowired
	private VolunteerService service;
	
	/*@GetMapping("/")
	public String home() {
		return "home";
	}*/
	
	@GetMapping("/volun_login")
	public String volunLogin() {
		return "volun_login";
	}
	
	@GetMapping("/leave_confirmation")
	public String volunLeave() {
		return "leave_confirmation";
	}
	
	@GetMapping("/volun_home/{volunteerId}")
	public String volunteerHome(@PathVariable Long volunteerId, Model model) {
	    Volunteer volunteer = service.getVolunteerById(volunteerId); // Assuming you have a method to retrieve volunteer by ID
	    if (volunteer != null) {
	        model.addAttribute("volunteer", volunteer);
	        return "volun_home"; // Assuming your home page is named "volun_home.html"
	    } else {
	        // Handle case where volunteer with given ID is not found
	        return "redirect:/volun_login"; // Redirect to login page
	    }
	}
	
	@GetMapping("/volun_update/{volunteerId}")
	public String showUpdateProfilePage(Model model, @PathVariable Long volunteerId) {
	    Volunteer volunteer = service.getVolunteerById(volunteerId);
	    model.addAttribute("volunteer", volunteer);
	    return "volun_update";
	}
	
	
	@PostMapping("/update_profile")
	public String updateProfile(@ModelAttribute("volunteer") Volunteer volunteer) {
	    // Ensure that the volunteer object contains a valid ID
	    if (volunteer.getId() <= 0) {
	        // Handle case where ID is missing or invalid
	        throw new IllegalArgumentException("Invalid volunteer ID");
	    }

	    // Perform update operation in the service layer
	    service.updateVolunteerProfile(volunteer);

	    // Redirect to a confirmation page or any other appropriate page
	    return "redirect:/volun_home/" + volunteer.getId();
	}
	
	@GetMapping("/volun_leave/{volunteerId}")
	public String showLeaveConfirmationPage(Model model, @PathVariable Long volunteerId) {
	    Volunteer volunteer = service.getVolunteerById(volunteerId);
	    model.addAttribute("volunteer", volunteer);
	    return "volun_leave";
	}
	
	@PostMapping("/leave")
	public String deleteProfile(@ModelAttribute("volunteer") Volunteer volunteer) {
	    // Ensure that the volunteer object contains a valid ID
	    if (volunteer.getId() <= 0) {
	        // Handle case where ID is missing or invalid
	        throw new IllegalArgumentException("Invalid volunteer ID");
	    }

	    // Perform update operation in the service layer
	    service.deleteProfile(volunteer);

	    // Redirect to a confirmation page or any other appropriate page
	    return "redirect:/leave_confirmation";
	}

	
	
	@GetMapping("/volun_register")
	public String volunRegister(Model model) {
		 model.addAttribute("volunteer",new Volunteer());
		return "volun_register";
	}
	
	
	/*@GetMapping("/list")
	public ModelAndView getAllVolunteer() {
		List<Volunteer>list=service.getAllVolunteer();
//		ModelAndView m=new ModelAndView();
//		m.setViewName("list");
//		m.addObject("Volunteer",list);
		return new ModelAndView("list","Volunteer",list);
	}*/
	
	@PostMapping("/save")
	public String addVolunteer(@Valid @ModelAttribute Volunteer volunteer, BindingResult result,Model model)  {
		// System.out.println("Request Payload: " + v);
		//String hashedPassword = new BCryptPasswordEncoder().encode(v.getPassword());
	    //v.setPassword(hashedPassword);
		if (result.hasErrors()) {
			//model.addAttribute("volunteer", v);
	        //model.addAttribute("bindingResult", result);
			 model.addAttribute("volunteer", volunteer);
			return "volun_register";
		}
		
		Volunteer existingVolunteer = service.findByEmail(volunteer.getEmail());
		    if (existingVolunteer != null) {
		        model.addAttribute("errorMessage", "Email already exists");
		        return "volun_register";
		    }
		    
		    
		String hashedPassword = BCrypt.hashpw(volunteer.getPassword(), BCrypt.gensalt());
	    volunteer.setPassword(hashedPassword);
		service.save(volunteer);
		return "redirect:/volun_login";
	}
	
	/*@GetMapping("/select")
	public String volunSelection() {
		return "select";
	}*/
	
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, Model model) {
	    Volunteer volunteer = service.findByEmailAndPassword(email, password);
	    
	    if (volunteer != null) {
	        // Login successful, redirect to home page or any other page
	    	
	    	 return "redirect:/volun_home/" + volunteer.getId();
	    } else {
	        // Login failed, display error message
	        model.addAttribute("errorMessage", "Invalid email or password");
	        return "redirect:/volun_login"; // Assuming you have a login page named "volun_login"
	    }
	}

	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    // Invalidate the session or clear any session attributes if needed
	    session.invalidate(); // Invalidate the session

	    // Redirect to the login page
	    return "redirect:/volun_login";
	}

	
	
	
	

}
