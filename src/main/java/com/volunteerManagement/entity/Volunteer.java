package com.volunteerManagement.entity;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "volunteers")
public class Volunteer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Name is required")
	@Column(nullable = false)
	private String name;
	

   
    
	@NotBlank(message = "Gender is required")
    @Column(nullable = false)
    private String gender;
    
	@NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
	private String email;
    
	@NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Column(nullable = false)
    private String password;
    
	@NotBlank(message = "Phone is required")
    //@Pattern(regexp = "^(09|\\\\+?950?9|\\\\+?95950?9)?\\\\d{7,9}$", message = "Invalid phone number")
    @Column(nullable = false)
	private String phone;
	
    
    @ElementCollection(targetClass = DayOfWeek.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "availability", joinColumns = @JoinColumn(name = "volunteer_id"))
    @Column(name = "day_of_week")
    private Set<DayOfWeek> availability;
    
    
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "preferences", joinColumns = @JoinColumn(name = "volunteer_id"))
    @Column(name = "preference")
    private Set<Preference> preferences;
    
    public enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public enum Preference {
    	CULTURE,TECHNOLOGY , HEALTH, CLIMATE, EDUCATION, FOOD ,OTHER
    }
	
    @Column(name = "has_experience")
    private boolean hasExperience;

	public Volunteer(long id, String name,String gender, String email, String password,String phone,
			Set<DayOfWeek> availability, Set<Preference> preferences, boolean hasExperience) {
		super();
		this.id = id;
		this.name = name;
		//this.DateofBirth = DateofBirth;
		this.gender = gender;
		this.email = email;
		this.email=email;
		this.phone = phone;
		this.availability = availability;
		this.preferences = preferences;
		this.hasExperience = hasExperience;
	}

	public Volunteer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public String getDateofBirth() {
		return DateofBirth;
	}

	public void setDateofBirth(String DateofBirth) {
		this.DateofBirth = DateofBirth;
	}*/

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<DayOfWeek> getAvailability() {
		return availability;
	}

	public void setAvailability(Set<DayOfWeek> availability) {
		this.availability = availability;
	}

	public Set<Preference> getPreferences() {
		return preferences;
	}

	public void setPreferences(Set<Preference> preferences) {
		this.preferences = preferences;
	}

	public boolean isHasExperience() {
		return hasExperience;
	}

	public void setHasExperience(boolean hasExperience) {
		this.hasExperience = hasExperience;
	}
    
    
    
    
    
}
