package com.volunteerManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.volunteerManagement.entity.Admin;

import com.volunteerManagement.service.AdminService;

import jakarta.servlet.http.HttpSession;






@Controller
public class AdminController {

	@Autowired
    private  AdminService service;
	
	@GetMapping("/admin_login")
	public String volunHome() {
		return "admin_login";
	}
	

	@PostMapping("/admin_login")
	public String login(@RequestParam String email, @RequestParam String password, Model model) {
	    Admin admin = service.findByEmailAndPassword(email, password);
	    if (admin != null) {
	        // Login successful, redirect to home page or any other page
	        return "home";
	    } else {
	        // Login failed, display error message
	        model.addAttribute("errorMessage", "Invalid email or password");
	        return "admin_login"; // Assuming you have a login page named "volun_login"
	    }
	}
	
	@GetMapping("/admin_logout")
	public String logout(HttpSession session) {
	    // Invalidate the session or clear any session attributes if needed
	    session.invalidate(); // Invalidate the session

	    // Redirect to the login page
	    return "redirect:/admin_login";
	}
	
	
	

}
