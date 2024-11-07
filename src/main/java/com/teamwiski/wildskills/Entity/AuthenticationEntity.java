package com.teamwiski.wildskills.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tblauth")
public class AuthenticationEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int authId;

    private boolean authStatus;
    private LocalDateTime sessionDurationOn;
    private LocalDateTime sessionDurationEnd;

    @OneToOne(mappedBy="authKey")
    @JsonBackReference
    private StudentEntity user;


    public AuthenticationEntity(){

    }

    public AuthenticationEntity(int authId,LocalDateTime sessionDurationOn, LocalDateTime sessionDurationEnd){
        this.authId=authId;
        this.sessionDurationOn=sessionDurationOn;
        this.sessionDurationEnd=sessionDurationEnd;
    }   

    public int getAuthId() {
        return authId;
    }


    public boolean getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(boolean authStatus) {
        this.authStatus = authStatus;
    }

    public LocalDateTime getSessionDurationOn() {
        return sessionDurationOn;
    }

    public void setSessionDurationOn(LocalDateTime sessionDurationOn) {
        this.sessionDurationOn = sessionDurationOn;
    }

    public LocalDateTime getSessionDurationEnd() {
        return sessionDurationEnd;
    }

    public void setSessionDurationEnd(LocalDateTime sessionDurationEnd) {
        this.sessionDurationEnd = sessionDurationEnd;
    }

    public StudentEntity getUser() {
        return user;
    }

    public void setUser(StudentEntity user) {
        this.user = user;
    }

}
