package com.teamwiski.wildskills.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.teamwiski.wildskills.Entity.ScheduleEntity;
import com.teamwiski.wildskills.Service.ScheduleService;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/api/wildSkills/skillExchange")
public class ScheduleController {
	
	@Autowired
	ScheduleService sserv;
	
	//Create Schedule
	@PostMapping("/postScheduleRecord")
    public ScheduleEntity postScheduleRecord(@RequestBody ScheduleEntity schedule) {
        return sserv.postScheduleRecord(schedule);
    }
    
    //Read Schedule
	@GetMapping("/getAllSchedules")
    public List<ScheduleEntity> getAllSchedules() {
        return sserv.getAllSchedules();
    }
	
	//Update Schedule
	@PutMapping("/putScheduleDetails")
	public ScheduleEntity putScheduleDetails(@RequestParam int id, @RequestBody ScheduleEntity newSchedule) {
		return sserv.putScheduleDetails(id, newSchedule);
	}
	
	//Delete Schedule
	@DeleteMapping("/deleteSchedule/{id}")
    public String deleteSchedule(@PathVariable int id) {
		return sserv.deleteSchedule(id);
	}
	
}
