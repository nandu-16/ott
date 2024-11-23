package com.example.text.model;
import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="Subscription")

public class UserSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
	private long sub_id;
    
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true) // Foreign key to the User entity
    private ApplicationUser user;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = true) // Foreign key to the Movies entity
    private PlanUser plan;

	public long getSub_id() {
		return sub_id;
	}

	public void setSub_id(long sub_id) {
		this.sub_id = sub_id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	 public void setUserId(Integer userId) {
	        this.user = new ApplicationUser();
	        this.user.setUserId(userId); // Assuming ApplicationUser has a method setId()
	    }

	    public void setPlanId(Long plan_id) {
	        this.plan = new PlanUser();
	        this.plan.setPlan_id(plan_id); // Assuming Movies has a method setId()
	    }
	    
	    public ApplicationUser getUser() {
	        return user;
	    }
	    
	    
	

}
