package com.teamwiski.wildskills.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamwiski.wildskills.entity.ScheduleEntity;
import com.teamwiski.wildskills.repository.ScheduleRepository;

@Service
public class ScheduleService {
	
	@Autowired
	ScheduleRepository srepo;
	
	public ScheduleService() {
		super();
	}
	
	//Create Schedule
    public ScheduleEntity postScheduleRecord(ScheduleEntity schedule) {
        return srepo.save(schedule);
    }
    
    //Read Schedule
    public List<ScheduleEntity> getAllSchedules() {
        return srepo.findAll();
    }
	
    //Update Schedule
    @SuppressWarnings("finally")
    public ScheduleEntity putScheduleDetails(int id, ScheduleEntity newSchedule) {
        ScheduleEntity schedule = new ScheduleEntity();
        try {
            //search the id number
            schedule = srepo.findById(id).get();
            
            //if ID is found, the user can set new values to all fields
            schedule.setTime(newSchedule.getTime());
            schedule.setDate(newSchedule.getDate());
            
        } catch(NoSuchElementException nex) {
            throw new NameNotFoundException("Schedule " + id + " not found");
        } finally {
            return srepo.save(schedule);
        }
    }
    
    //Delete Schedule
    public String deleteSchedule(int id) {
        String msg="";
        if (srepo.findById(id)!=null) {
            srepo.deleteById(id);
            msg="Schedule " + id + " successfully deleted!";
        } else {
            msg="Schedule " + id + " not found!";
        }
        return msg;
    }
}
