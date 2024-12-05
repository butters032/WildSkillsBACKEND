package com.teamwiski.wildskills.Entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblSkillExchange")
public class SkillExchangeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int SkillExchangeID;
	
	@Column
	private String status; //Completed - Cancelled - Ongoing
	private String title; //Title sa exchange para header sa exchange menu
	private LocalDateTime scheduledStart;
	private LocalDateTime scheduledEnd;

	@ManyToOne
	@JoinColumn (name = "skill_offering_id", referencedColumnName = "skill_offering_id")
	//@JsonIgnore
	private SkillOfferingEntity offering;

	@ManyToOne /*(cascade = CascadeType.ALL)*/
	@JoinColumn (name = "student_id", referencedColumnName = "student_id")
	@JsonIgnore
	private StudentEntity student;

	@JsonIgnore
	@ManyToMany (mappedBy = "skillExchanges")
    private Set<StudentEntity> students = new HashSet<>();

	@JsonIgnore
	@OneToOne(mappedBy="user")
    private ChatEntity chat;


	public SkillExchangeEntity() {
		super();
	}
	
	public SkillExchangeEntity(int SkillExchangeID, String status, String title, LocalDateTime scheduledStart, LocalDateTime scheduledEnd, StudentEntity student, SkillOfferingEntity offering) {
		this.SkillExchangeID = SkillExchangeID;
		this.status = status;
		this.title = title;
		this.scheduledStart = scheduledStart;
		this.scheduledEnd = scheduledEnd;
		this.student = student;
		this.offering = offering;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getScheduledStart() {
		return scheduledStart;
	}

	public void setScheduledStart(LocalDateTime scheduledStart) {
		this.scheduledStart = scheduledStart;
	}

	public LocalDateTime getScheduledEnd() {
		return scheduledEnd;
	}

	public void setScheduledEnd(LocalDateTime scheduledEnd) {
		this.scheduledEnd = scheduledEnd;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public SkillOfferingEntity getOffering() {
		return offering;
	}

	public void setOffering(SkillOfferingEntity offering) {
		this.offering = offering;
	}

	public Set<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentEntity> students) {
		this.students = students;
	}

	public ChatEntity getChat() {
		return chat;
	}

	public void setChat(ChatEntity chat) {
		this.chat = chat;
	}
	
}
