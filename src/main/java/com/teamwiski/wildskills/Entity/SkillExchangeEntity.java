package com.teamwiski.wildskills.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblSkillExchange")
public class SkillExchangeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int SkillExchangeID;
	
	@Column
	private String status; //Completed - Cancelled - Ongoing
	private String description; //Description sa exchange para header sa exchange menu
	
	public SkillExchangeEntity() {
		super();
	}
	
	public SkillExchangeEntity(int SkillExchangeID, String status, String description) {
		this.SkillExchangeID = SkillExchangeID;
		this.status = status;
		this.description = description;
	}
	
	public int getSkillExchangeID() {
		return SkillExchangeID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
