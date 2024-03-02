package com.volunteerManagement.service;
import org.apache.commons.math3.linear.*;
import org.springframework.stereotype.Component;

@Component

public class AHPProcessor{
	double date_row;
	double field_row;
	double exp_row;
	
	  public double getDateRow() {
	        return date_row;
	    }

	    public double getFieldRow() {
	        return field_row;
	    }

	    public double getExpRow() {
	        return exp_row;
	    }
	
	public void processAHP(String dateVsField,
			int criteriaValue1, String expVsField, 
			int criteriaValue2, String dateVsExp, int criteriaValue3) {
		double date_date_index=1;
		double date_field_index;
		double date_exp_index;
		double field_date_index;
		double field_field_index=1;
		double field_exp_index;
		double exp_date_index;
		double exp_field_index;
		double exp_exp_index=1;
		double date_col;
		double field_col;
		double exp_col;
		
		
		  if (dateVsField.equals("dateVsField_Date")) {
	            date_field_index=criteriaValue1;
	            field_date_index=1.0/criteriaValue1;
	        } else {
	        	    date_field_index=1.0/criteriaValue1;
		            field_date_index=criteriaValue1;
	        }
		  System.out.println("date_field_index: " + date_field_index);
		  System.out.println("field_date_index: " + field_date_index);
		

		  if (expVsField.equals("expVsField_Field")) {
	            exp_field_index=1.0/criteriaValue2;
	            field_exp_index=criteriaValue2;
	        } else {
	        	    exp_field_index=criteriaValue2;
		            field_exp_index=1.0/criteriaValue2;
	        }
		  
		  System.out.println("exp_field_index: " + exp_field_index);
		  System.out.println("field_exp_index: " + field_exp_index);
		  

		  if (dateVsExp.equals("dateVsExp_Date")) {
	            exp_date_index=1.0/criteriaValue3;
	            date_exp_index=criteriaValue3;
	        } else {
	        	    exp_date_index=criteriaValue3;
		            date_exp_index=1.0/criteriaValue3;
	        }
		  
		  System.out.println("date_exp_index: " + date_exp_index);
		  System.out.println("exp_date_index: " + exp_date_index);
		  
		  
		  double[][] pairwiseComparisonMatrix = {
	                {date_date_index, date_field_index, date_exp_index},
	                {field_date_index, field_field_index, field_exp_index},
	                {exp_date_index, exp_field_index, exp_exp_index}
	        };
	
	 
		  date_col=date_date_index+field_date_index+exp_date_index;
		  field_col=date_field_index+field_field_index+exp_field_index;
		  exp_col=date_exp_index+field_exp_index+exp_exp_index;
		  
		  System.out.println("date_col_sum " + date_col);
		  System.out.println("field_col_sum " + field_col);
		  System.out.println("exp_col_sum " + exp_col);
		  
		  
		  date_date_index=date_date_index/date_col;
		  field_date_index=field_date_index/date_col;
		  exp_date_index=exp_date_index/date_col;
		  
		  System.out.println("date_date_divided_resutl " + date_date_index);
		  System.out.println("field_date_divided_resutl " + field_date_index);
		  System.out.println("exp_date_divided_resutl " + exp_date_index);
		  
		  date_field_index=date_field_index/field_col;
		  field_field_index=field_field_index/field_col;
		  exp_field_index=exp_field_index/field_col;
		  
		  
		  System.out.println("date_field_divided_resutl " + date_field_index);
		  System.out.println("field_field_divided_resutl " + field_field_index);
		  System.out.println("exp_field_divided_resutl " + exp_date_index);
		  
		  
		  date_exp_index=date_exp_index/exp_col;
		  field_exp_index=field_exp_index/exp_col;
		  exp_exp_index=exp_exp_index/exp_col;
		  
		  System.out.println("date_exp_divided_resutl " + date_exp_index);
		  System.out.println("field_exp_divided_resutl " + field_exp_index);
		  System.out.println("exp_exp_divided_resutl " + exp_exp_index);
		  
		  date_row=(date_date_index+date_field_index+date_exp_index)/3.0;
		  field_row=(field_date_index+field_field_index+field_exp_index)/3.0;
		  exp_row=(exp_date_index+exp_field_index+exp_exp_index)/3.0;
		  
		  System.out.println("date_row_final_resutl " + date_row);
		  System.out.println("field_row_final_resutl " + field_row);
		  System.out.println("exp_row_final_resutl " + exp_row);
		  
		 
	
	
	}
}