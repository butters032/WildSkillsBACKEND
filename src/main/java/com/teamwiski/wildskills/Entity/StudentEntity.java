package com.teamwiski.wildskills.Entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    @Column (name = "student_id")
    private int studentId;

    private String name;
    private LocalDate birthdate;
    private int age;
    private String email;
    private String password;
    private String gender;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ReviewEntity> reviews;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL)
    private List<SkillExchangeEntity> exchanges;

    @ManyToMany
    @JoinTable(name = "exchange_student",  
        joinColumns = @JoinColumn(name = "student_id"), 
        inverseJoinColumns = @JoinColumn(name = "skill_exchangeid"))
    private Set<SkillExchangeEntity> skillExchanges = new HashSet<>();


    //AUTHENTICATION & SESSION
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="authId", referencedColumnName="authId")
    @JsonManagedReference
    private AuthenticationEntity authKey;

    //MESSAGES OR CHATS
    @ManyToMany(mappedBy = "students")
    private Set<ChatEntity> chats = new HashSet<>();
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StudentEntity(){
        super();
    }

    public static int calculateAge(LocalDate birthdate, LocalDate currentDate) {
        Period period = Period.between(birthdate, currentDate);
        
          return period.getYears();
    }
    
    //REGISTRATION CONSTRUCTOR
    public StudentEntity(int studentId,String name,LocalDate birthdate,String email, String password, String gender){
        this.studentId=studentId;
        this.name=name;
        this.birthdate=birthdate;
        this.gender=gender;
        this.email=email;
        this.password=password;
        
        //this.age=calculateAge(birthdate, LocalDate.now());
    }

    //REVIEW CONSTRUCTOR
    public StudentEntity(int studentId,String name,LocalDate birthdate,String email, String password, String gender, List<ReviewEntity> reviews){
        this.studentId=studentId;
        this.name=name;
        this.birthdate=birthdate;
        this.gender=gender;
        this.email=email;
        this.password=password;
        //this.age=calculateAge(birthdate, LocalDate.now());
        
        this.reviews = reviews;
    }

    //CHAT CONSTRUCTOR
    public StudentEntity(Set<ChatEntity> chats, int studentId,String name,LocalDate birthdate,String email, String password, String gender){
        this.studentId=studentId;
        this.name=name;
        this.birthdate=birthdate;
        this.gender=gender;
        this.email=email;
        this.password=password;
        //this.age=calculateAge(birthdate, LocalDate.now());

        this.chats=chats;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) { 
        this.birthdate = birthdate; 
        this.age = calculateAge(birthdate, LocalDate.now());
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public AuthenticationEntity getAuthKey() {
        return authKey;
    }

    public void setAuthKey(AuthenticationEntity authKey) {
        this.authKey = authKey;
    }

    public Set<ChatEntity> getChat() {
        return chats;
    }

    public void setChat(Set<ChatEntity> chats) {
        this.chats = chats;
    }

    public Set<SkillExchangeEntity> getSkillExchanges() {
        return skillExchanges;
    }

    public void setSkillExchanges(Set<SkillExchangeEntity> skillExchanges) {
        this.skillExchanges = skillExchanges;
    }    
    
}
