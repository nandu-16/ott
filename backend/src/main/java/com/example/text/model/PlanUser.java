package com.example.text.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
@Entity
@Table(name="plans")

public class PlanUser {
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long plan_id;
	    private String name;
		private String description;
	    private String price;
	    private String duration;
	    private String isenabled;
	    
	    @Transient
	    private String remaining_days;
	    
	    
	    
	    public String getRemaining_day() {
			return remaining_days;
		}
		public void setRemaining_day(String remaining_day) {
			this.remaining_days = remaining_days;
		}
		public Long getPlan_id() {
			return plan_id;
		}
		public void setPlan_id(Long plan_id) {
			this.plan_id = plan_id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		public String getDuration() {
			return duration;
		}
		public void setDuration(String duration) {
			this.duration = duration;
		}
		public String getIsenabled() {
			return isenabled;
		}
		public void setIsenabled(String isenabled) {
			this.isenabled = isenabled;
		}
		public char[] get() {
			// TODO Auto-generated method stub
			return null;
		}
	
	   
}
