package com.volunteerManagement.entity;

public class VolunteerScore {
    private Long volunteerId;
    private double score;
	public Long getVolunteerId() {
		return volunteerId;
	}
	public void setVolunteerId(Long volunteerId) {
		this.volunteerId = volunteerId;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public VolunteerScore(Long volunteerId, double score) {
		super();
		this.volunteerId = volunteerId;
		this.score = score;
	}
	public VolunteerScore() {
		super();
		// TODO Auto-generated constructor stub
	}

    // constructors, getters, and setters
}

