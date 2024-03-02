package com.volunteerManagement.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.volunteerManagement.entity.Event;
import com.volunteerManagement.entity.Volunteer;
import com.volunteerManagement.service.AHPProcessor;
import com.volunteerManagement.service.MatchingService;
import com.volunteerManagement.service.VolunteerService;

import jakarta.validation.Valid;

@Controller
public class EventController {
	@Autowired
	private VolunteerService service;
	
	
	
	@Autowired
	private MatchingService matchingservice;
	
	@Autowired
	private AHPProcessor ahpprocessor;
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	
	@GetMapping("/list")
	public ModelAndView getAllVolunteer() {
		List<Volunteer>list=service.getAllVolunteer();
//		ModelAndView m=new ModelAndView();
//		m.setViewName("list");
//		m.addObject("Volunteer",list);
		return new ModelAndView("list","Volunteer",list);
	}
	
	@RequestMapping("/select")
    public String showForm(Model model) {
        // Add an empty eventForm object to the model
        model.addAttribute("eventForm", new Event());
        int maxVolunteers = service.getNumberOfVolunteers();
        model.addAttribute("maxVolunteers", maxVolunteers);
        return "select"; // Assuming "select" is your template name
    }
	
	@PostMapping("/submit-event")
	public String submitEvent(@Valid @ModelAttribute("eventForm") Event event, BindingResult result,Model model) {
		
		if (result.hasErrors()) {
			//model.addAttribute("volunteer", v);
	        //model.addAttribute("bindingResult", result);
			 model.addAttribute("eventForm", event);
			return "select";
		}
	    // Process the submitted event (save to the database, perform business logic, etc.)
	    // Here, you can access event.getDate(), event.getField(), event.getNumberOfPeopleNeeded() for processing.
	    // You might want to save it to the database or perform other actions.
		ahpprocessor.processAHP(event.getDateVsField(),
				event.getCriteriaValue1(), event.getExpVsField(),event.getCriteriaValue2(),
				event.getDateVsExp(),event.getCriteriaValue3());
		 double dateRow = ahpprocessor.getDateRow();
	        double fieldRow = ahpprocessor.getFieldRow();
	        double expRow = ahpprocessor.getExpRow();
		       
		
		List<Long> allbestcandidatesId = matchingservice.CalculateScore(event.getDate(),event.getField(),
	        event.getNumberOfPeopleNeeded(),dateRow,fieldRow,expRow);
		List<Volunteer> bestVolunteers = service.getVolunteersByIds(allbestcandidatesId);
	    // For simplicity, let's assume you want to show the submitted event data in the view.
	    model.addAttribute("submittedEvent", event);
	    
	    model.addAttribute("dateRow", dateRow);
	    model.addAttribute("fieldRow", fieldRow);
	    model.addAttribute("expRow", expRow);
	    
	    model.addAttribute("submittedresultID",allbestcandidatesId);
	    model.addAttribute("bestVolunteers",bestVolunteers);
	    
	    
	    // Return to the "select" view after processing.
	    return "select";
	}


}
