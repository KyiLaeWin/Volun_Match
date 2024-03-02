package com.volunteerManagement.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.apache.commons.math3.linear.RealVector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volunteerManagement.entity.Volunteer;
import com.volunteerManagement.repository.volunteerRepository;

import com.volunteerManagement.entity.Event;
import com.volunteerManagement.repository.eventRepository;


import com.volunteerManagement.entity.VolunteerScore;
import com.volunteerManagement.entity.Volunteer.DayOfWeek;
import com.volunteerManagement.entity.Volunteer.Preference;



@Service

public class MatchingService {
	 @Autowired
	    private volunteerRepository volunteerRepository;

	    @Autowired
	    private eventRepository eventRepository;
	    
	    
	    
	    

	    public List<Long> CalculateScore(String eventDate, String eventField, int numberOfPeopleNeeded
	    		,double dateRow, double fieldRow, double expRow) {
	    	List<Volunteer> volunteers = volunteerRepository.findAll();
	    	
	        // Implementation to calculate scores for volunteers based on eventDate, eventField, and numberOfPeopleNeeded

	        // Perform the matching and scoring logic here...

	        // Create a list to store the best candidates
	    	
	    	
	        List<VolunteerScore> bestCandidateswithScores = new ArrayList<>();
	        
	        int volunteerCount = 0;
	        for (Volunteer volunteer : volunteers) {
	            double score = calculateOverallScore(volunteer, eventDate, eventField, dateRow, fieldRow, expRow);
	            VolunteerScore volunteerwithScore = new VolunteerScore(volunteer.getId(), score);
	            bestCandidateswithScores.add(volunteerwithScore);
	            System.out.println("Score of volunteer " + volunteerCount + ": " + score);
	            volunteerCount++;
	            System.out.flush();
	        }
	        System.out.println("Total volunteers processed: " + volunteerCount);


	        // Add volunteers to the bestCandidates list based on the scoring logic
	        
	        Collections.sort(bestCandidateswithScores, Comparator.comparingDouble(VolunteerScore::getScore).reversed());

	        List<Long> topVolunteerIds = new ArrayList<>();

	        int count = 0;
	        for (VolunteerScore volunteerScore : bestCandidateswithScores) {
	            topVolunteerIds.add(volunteerScore.getVolunteerId());
	            count++;
	            if (count == numberOfPeopleNeeded) {
	                break; // Exit the loop after adding the top N Volunteer IDs
	            }
	        }

	        return topVolunteerIds;

	        // Return the list of best candidates
	        
	    }        
	        
	        
	        
	    public double calculateOverallScore(Volunteer volunteer, String adminInputDate, 
	    		String adminPreference,double dateRow,double fieldRow,double expRow) {
	    	 

            double dateScore = calculateMatchingDateScore(adminInputDate, volunteer.getAvailability());
            double preferenceScore = calculatePreferenceFieldScore(adminPreference, volunteer.getPreferences());
            double experienceScore = calculateExperienceScore(volunteer.isHasExperience());
            System.out.print("Date value");
            System.out.print(dateScore);
            System.out.print("\n");
            System.out.print("Preference value");
            System.out.print(preferenceScore);
            System.out.print("\n");
            System.out.print("Exp value");
            System.out.print(experienceScore);
            System.out.print("\n");
            
            
          
            
            return (dateScore * dateRow) + (preferenceScore * fieldRow) + (experienceScore * expRow);
        }
	    
	    public double calculateMatchingDateScore(String adminInputDate, Set<DayOfWeek> candidateDate) {
	        for (DayOfWeek day : candidateDate) {
	            if (adminInputDate.equalsIgnoreCase(day.toString())) {
	                return 9;
	            }
	        }

	        return 0;
	    }


	    public double calculatePreferenceFieldScore(String adminPreference, Set<Preference> candidatePreference) {
	        for (Preference preference : candidatePreference) {
	            if (adminPreference.equalsIgnoreCase(preference.toString())) {
	                return 9;
	            }
	        }

	        for (Preference preference : candidatePreference) {
	            if ("OTHER".equalsIgnoreCase(preference.toString())) {
	                return 7;
	            }
	        }

	        return 0;
	    }

        public double calculateExperienceScore(boolean hasExperience) {
            return hasExperience ? 9 : 5;
        }
        
}
