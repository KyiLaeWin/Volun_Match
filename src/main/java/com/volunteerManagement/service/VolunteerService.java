package com.volunteerManagement.service;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volunteerManagement.entity.Volunteer;
import com.volunteerManagement.repository.volunteerRepository;

@Service
public class VolunteerService {
	
	@Autowired
	private volunteerRepository vrepo;
	
public void save(Volunteer volunteer) {
	vrepo.save(volunteer);	
}

public List<Volunteer> getAllVolunteer(){
	return vrepo.findAll();
}

public Volunteer findByEmail(String email) {
    return vrepo.findByEmail(email);
}

public Volunteer getVolunteerById(Long id) {
    Optional<Volunteer> optionalVolunteer = vrepo.findById(id);
    return optionalVolunteer.orElse(null);
}

public void updateVolunteerProfile(Volunteer updatedVolunteer) {
    // Retrieve the existing volunteer entity from the database
    Volunteer existingVolunteer = vrepo.findById(updatedVolunteer.getId()).orElse(null);

    if (existingVolunteer != null) {
        // Update the specific fields that need to be changed
        existingVolunteer.setAvailability(updatedVolunteer.getAvailability());
        existingVolunteer.setPreferences(updatedVolunteer.getPreferences());
        existingVolunteer.setHasExperience(updatedVolunteer.isHasExperience());

        // Save the updated volunteer entity back to the database
        vrepo.save(existingVolunteer);
    } else {
        // Handle the case where the volunteer with the given ID is not found
        throw new IllegalArgumentException("Volunteer not found with ID: " + updatedVolunteer.getId());
    }
}

public Volunteer findByEmailAndPassword(String email, String password) {
    // Find volunteer by email
    Volunteer volunteer = vrepo.findByEmail(email);
    
    // Check if volunteer exists and password matches
    if (volunteer != null && BCrypt.checkpw(password, volunteer.getPassword())) {
        return volunteer;
    } else {
        return null; // Return null if no matching volunteer found or password doesn't match
    }
}

public void deleteProfile(Volunteer volunteer) {
    // Ensure that the volunteer object contains a valid ID
    if (volunteer.getId() <= 0) {
        // Handle case where ID is missing or invalid
        throw new IllegalArgumentException("Invalid volunteer ID");
    }

    // Delete the volunteer's account from the database
    vrepo.deleteById(volunteer.getId());
}

public int getNumberOfVolunteers() {
    return vrepo.findAll().size();
}

public List<Volunteer> getVolunteersByIds(List<Long> volunteerIds) {
    List<Volunteer> volunteers = new ArrayList<>();
    for (Long id : volunteerIds) {
        Optional<Volunteer> optionalVolunteer = vrepo.findById(id);
        optionalVolunteer.ifPresent(volunteers::add);
    }
    return volunteers;
}



}
