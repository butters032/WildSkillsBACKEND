package com.teamwiski.wildskills.Controller;

import java.util.List;

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
	
}
