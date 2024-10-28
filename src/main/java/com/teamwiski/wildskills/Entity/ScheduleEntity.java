package com.teamwiski.wildskills.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblSchedule")
public class ScheduleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ScheduleID;
	
	@Column
	private java.sql.Time time; //Scheduled Time sa nasabutan
	private java.sql.Date date; //Scheduled Date sa nasabutan
	
	public ScheduleEntity() {
		super();
	}
	
	public ScheduleEntity(int ScheduleID, java.sql.Time time, java.sql.Date date) {
		this.ScheduleID = ScheduleID;
		this.time = time;
		this.date = date;
	}
	
	public int getScheduleID() {
		return ScheduleID;
	}
	public java.sql.Time getTime() {
		return time;
	}

	public void setTime(java.sql.Time time) {
		this.time = time;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	
}
