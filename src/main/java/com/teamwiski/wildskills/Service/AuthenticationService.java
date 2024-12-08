package com.teamwiski.wildskills.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamwiski.wildskills.Entity.AuthenticationEntity;
import com.teamwiski.wildskills.Repository.AuthenticationRepository;

@Service
public class AuthenticationService{
    @Autowired
    AuthenticationRepository authRepo;

    public AuthenticationService(){
        super();
    }

    //Create
    public AuthenticationEntity postAuthenticationDetails (AuthenticationEntity authDetails){
        return authRepo.save(authDetails);
    }

    //Read
    public AuthenticationEntity getAuthenticationDetails (int authId){
        return authRepo.findById(authId).get();
    }
    

    //Update
    @SuppressWarnings("finally")
    public AuthenticationEntity putAuthenticationDetails (int authId, AuthenticationEntity newAuthDetails){
        AuthenticationEntity updatedAuthDetails = new AuthenticationEntity();
        try {
            updatedAuthDetails=authRepo.findById(authId).get();
            updatedAuthDetails.setAuthStatus(newAuthDetails.getAuthStatus());
            updatedAuthDetails.setSessionDurationOn(newAuthDetails.getSessionDurationOn());
            updatedAuthDetails.setSessionDurationEnd(newAuthDetails.getSessionDurationEnd());
        } catch(NoSuchElementException n){
            throw new NameNotFoundException("Auth Id: " + authId+ " not found.");
        }finally{
            return authRepo.save(updatedAuthDetails);
        }
    }

    public AuthenticationEntity incrementAuthenticationDetails (int authId){
        AuthenticationEntity authEntity = new AuthenticationEntity(); 
        authEntity=authRepo.findById(authId).get();
        //authEntity.setAuthStatus(true);
        authEntity.setSessionDurationOn(LocalDateTime.now()); 
        authEntity.setSessionDurationEnd(LocalDateTime.now().plusMinutes(10));

        return authRepo.save(authEntity);
    }

    public AuthenticationEntity updateAuthenticationStatus (int authId, boolean currStatus){
        AuthenticationEntity authEntity = new AuthenticationEntity(); 
        authEntity=authRepo.findById(authId).get();
        authEntity.setAuthStatus(currStatus);

        return authRepo.save(authEntity);
    }

    //Delete
    public String deleteAuthenticationDetails(int authId){
        String msg = "";
        if(authRepo.findById(authId).isPresent()){
            authRepo.deleteById(authId);
            msg = "Authentication Succesfully Deleted";
        }
        else{
            msg = "Authentication Record Not Found";
        }
        return msg;
    }
}