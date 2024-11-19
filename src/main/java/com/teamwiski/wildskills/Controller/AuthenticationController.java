package com.teamwiski.wildskills.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamwiski.wildskills.Entity.AuthenticationEntity;
import com.teamwiski.wildskills.Service.AuthenticationService;


@RestController
@RequestMapping(method=RequestMethod.GET,path="/api/wildSkills/authentication")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController{
    @Autowired
    AuthenticationService authServ;


    //Create
    @PostMapping("/postAuthenticationDetails")
    public AuthenticationEntity postAuthenticationDetails (@RequestBody AuthenticationEntity authDetails){
        return authServ.postAuthenticationDetails(authDetails);
    }

    //Read
    @GetMapping("/getAuthenticationDetails")
    public AuthenticationEntity getAuthenticationDetails (@RequestParam int authId){
        return authServ.getAuthenticationDetails(authId);
    }

    //Update
    @PutMapping("/putAuthenticationDetails")
    public AuthenticationEntity putAuthenticationDetails(@RequestParam int authId, @RequestBody AuthenticationEntity authDetails){
        return authServ.putAuthenticationDetails(authId, authDetails);
    }


    @PutMapping("/putIncrementAuthenticationDetails")
    public AuthenticationEntity incrementAuthenticationDetails (@RequestParam int authId){
        return authServ.incrementAuthenticationDetails(authId);
    }

    //mu update sa auth status
    @PutMapping("/putUpdateAuthenticationStatus")
    public AuthenticationEntity updateAuthenticationStatus (@RequestParam int authId){
        return authServ.updateAuthenticationStatus(authId);
    }
    

    //Delete
    @DeleteMapping("/deleteStudentRecord/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public String deleteAuthenticationDetails(@PathVariable int authId){
        return authServ.deleteAuthenticationDetails(authId);
    }


}