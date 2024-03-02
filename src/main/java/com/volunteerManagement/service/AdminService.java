package com.volunteerManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.volunteerManagement.entity.Admin;
import com.volunteerManagement.repository.adminRepository;


@Service
public class AdminService {

	@Autowired
	private adminRepository adminrepo;
	


public Admin findByEmailAndPassword(String email, String password) {
    // Find volunteer by email
    Admin admin = adminrepo.findByEmail(email);
    
    // Check if volunteer exists and password matches
    if (admin != null && admin.getPassword().equals(password)) {
        return admin;
    } else {
        return null; // Return null if no matching volunteer found or password doesn't match
    }
}
}
