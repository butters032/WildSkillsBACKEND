package com.teamwiski.wildskills.Controller;

import java.util.List;
import java.util.Set;

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

import com.teamwiski.wildskills.Entity.SkillExchangeEntity;
import com.teamwiski.wildskills.Service.SkillExchangeService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(method = RequestMethod.GET, path="/api/wildSkills/skillExchange")
public class SkillExchangeController {
	
	@Autowired
	SkillExchangeService sserv;
	
	//Create SkillExchange
	@PostMapping("/postSkillExchangeRecord")
	public SkillExchangeEntity postSkillExchangeRecord(@RequestBody SkillExchangeEntity skillExchange) {
		return sserv.postSkillExchangeRecord(skillExchange);
	}
	
	//Read SkillExchange
	@GetMapping("/getAllSkillExchange")
	public List<SkillExchangeEntity> getAllSkillExchange() {
		return sserv.getAllSkillExchange();
	}
	
	//Update SkillExchange
	@PutMapping("/putSkillExchangeDetails")
	public SkillExchangeEntity putSkillExchangeDetails(@RequestParam int id, @RequestBody SkillExchangeEntity newSkillExchange) {
		return sserv.putSkillExchangeDetails(id, newSkillExchange);
	}
	
	//Delete SkillExchange
	@DeleteMapping("/deleteSkillExchange/{id}")
	public String deleteSkillExchange(@PathVariable int id) {
		return sserv.deleteSkillExchange(id);
	}
	
	//Read all SkillExchange by Student
	@GetMapping("/student/{studentId}")
	public Set<SkillExchangeEntity> getAllSKillExchanges(@PathVariable int studentId) {
		return sserv.getAllSKillExchanges(studentId);
	}

	//Read all Completed
	@GetMapping("/student/{studentId}/completed")
	public Set<SkillExchangeEntity> getSkillExchangeCompleted(@PathVariable int studentId) {
		return sserv.getSkillExchangeCompleted(studentId);
	}

	//Read all Ongoing
	@GetMapping("/student/{studentId}/ongoing")
	public Set<SkillExchangeEntity> getSkillExchangeOngoing(@PathVariable int studentId) {
		return sserv.getSkillExchangeOngoing(studentId);
	}

	//Read SkillExchange by Student
	/*@GetMapping("/student/{studentId}/exchange/{id}")
	public SkillExchangeEntity getSkillExchange(@PathVariable int id) {
		return sserv.getSkillExchange(id);
	}*/

	//Create SkillExchange by Student
	@PostMapping("/student/{studentId}/postSkillExchange/{skillOfferingId}")
	public SkillExchangeEntity postSkillExchange(@RequestBody SkillExchangeEntity skillExchange, @PathVariable int studentId, @PathVariable int skillOfferingId) {
		return sserv.postSkillExchange(skillExchange, studentId, skillOfferingId);
	}

	//Update SkillExchange by Student
	@PutMapping("/student/{studentId}/putSkillExchangeDetails")
	public SkillExchangeEntity putSkillExchange(@RequestParam int id, @RequestBody SkillExchangeEntity newSkillExchange, @PathVariable int studentId) {
		return sserv.putSkillExchange(id, newSkillExchange, studentId);
	}
	
	//Delete SkillExchange
	@DeleteMapping("/student/{studentId}/deleteSkillExchange/{id}")
	public String deleteSkillExchange(@PathVariable int studentId, @PathVariable int id) {
		return sserv.deleteSkillExchange(studentId, id);
	}
	
	@GetMapping("/countAllExchange")
	public int searchTotalExchanges() {
		return sserv.searchTotalExchanges();
	}
	
	@GetMapping("/getRecentExchanges")
	public List<SkillExchangeEntity> searchRecentExchanges(){
		return sserv.searchRecentExchanges();
	}

	@GetMapping("/findChatter") 
	public int findChatter(@RequestParam int exchangeId, @RequestParam int reviewerId) { 
		return sserv.findChatter(exchangeId, reviewerId); 
	}
}
