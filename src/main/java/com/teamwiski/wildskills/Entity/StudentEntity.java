package com.teamwiski.wildskills.Entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// Changes:
// Gi connect and review ug student
// gi add ang list sa review sulod sa constructor
// pero wala gihapon mugana

@Entity
@Table(name="tblstudent")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    private String name;
    private Date birthdate;
    private int age;
    private String email;
    private String password;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SkillExchangeEntity> exchanges;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ReviewEntity> reviews;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StudentEntity(){
        super();
    }

    public StudentEntity(int studentId,String name,Date birthdate,int age,String email, String password, List<ReviewEntity> reviews){
        this.studentId=studentId;
        this.name=name;
        this.birthdate=birthdate;
        this.age=age;
        this.email=email;
        this.password=password;
        
        this.reviews = reviews;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
