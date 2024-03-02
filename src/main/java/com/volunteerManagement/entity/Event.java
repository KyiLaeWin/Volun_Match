package com.volunteerManagement.entity;

import com.volunteerManagement.service.VolunteerService;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
public class Event {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @NotBlank(message = "Date is required")
    private String date;
    
    @NotBlank(message = "Field is required")
    private String field;
    
    @NotNull(message = "Number of people needed is required")
    @Min(value = 1, message = "Number of people needed must be at least 1")
    private int numberOfPeopleNeeded;
    
    @NotBlank(message = "Please Choose Criteria")
    private String dateVsField;
    
    @NotNull(message = "Criteria Value  is required")
    @Min(value = 1, message = "Criteria Value  must be at least 1")
    private int CriteriaValue1;
    
    @NotBlank(message = "Please Choose Criteria")
    private String expVsField;
    
    @NotNull(message = "Criteria Value  is required")
    @Min(value = 1, message = "Criteria Value  must be at least 1")
    private int CriteriaValue2;
    
    
    @NotBlank(message = "Please Choose Criteria")
    private String dateVsExp;
    
    @NotNull(message = "Criteria Value  is required")
    @Min(value = 1, message = "Criteria Value  must be at least 1")
    private int CriteriaValue3;
    

     
    public String getDateVsField() {
		return dateVsField;
	}

	public void setDateVsField(String dateVsField) {
		this.dateVsField = dateVsField;
	}

	public String getExpVsField() {
		return expVsField;
	}

	public void setExpVsField(String expVsField) {
		this.expVsField = expVsField;
	}
	
	public String getDateVsExp() {
		return dateVsExp;
	}

	public void setDateVsExp(String dateVsExp) {
		this.dateVsExp = dateVsExp;
	}

	
	public int getCriteriaValue1() {
        return CriteriaValue1;
    }

    public void setCriteriaValue1(int CriteriaValue1) {
        this.CriteriaValue1 = CriteriaValue1;
    }

	public int getCriteriaValue2() {
		return CriteriaValue2;
	}

	public void setCriteriaValue2(int criteriaValue2) {
		CriteriaValue2 = criteriaValue2;
	}

	
	public int getCriteriaValue3() {
		return CriteriaValue3;
	}

	public void setCriteriaValue3(int criteriaValue3) {
		CriteriaValue3 = criteriaValue3;
	}

	public Event(String date, String field, int numberOfPeopleNeeded, String dateVsField, int CriteriaValue1,
			String expVsField, int CriteriaValue2, String dateVsExp, int CriteriaValue3) {
    	super();
        this.date = date;
        this.field = field;
        this.numberOfPeopleNeeded = numberOfPeopleNeeded;
        this.dateVsField=dateVsField;
        this.CriteriaValue1=CriteriaValue1;
        this.expVsField=expVsField;
        this.CriteriaValue2=CriteriaValue2;
        this.dateVsExp=dateVsExp;
        this.CriteriaValue3=CriteriaValue3;
        
        
    }

    public Event() {
       super();
    }

   

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getNumberOfPeopleNeeded() {
        return numberOfPeopleNeeded;
    }

    public void setNumberOfPeopleNeeded(int numberOfPeopleNeeded) {
        this.numberOfPeopleNeeded = numberOfPeopleNeeded;
    }
    
    
   
    

    // Other methods or annotations as needed
}
