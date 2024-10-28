package com.teamwiski.wildskills.Service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamwiski.wildskills.Entity.SkillExchangeEntity;
import com.teamwiski.wildskills.Repository.SkillExchangeRepository;

@Service
public class SkillExchangeService {
	
	@Autowired
	SkillExchangeRepository srepo;
	
	public SkillExchangeService() {
		super();
	}
	
	//Create SkillExchange
	public SkillExchangeEntity postSkillExchangeRecord(SkillExchangeEntity skillExchange) {
		return srepo.save(skillExchange);
	}
	
	//Read SkillExchange
	public List<SkillExchangeEntity> getAllSkillExchange() {
		return srepo.findAll();
	}
	
	//Update SkillExchange
	@SuppressWarnings("finally")
	public SkillExchangeEntity putSkillExchangeDetails(int id, SkillExchangeEntity newSkillExchange) {
		SkillExchangeEntity skillExchange = new SkillExchangeEntity();
		try {
			//search the id number
			skillExchange = srepo.findById(id).get();
			
			//if ID is found, the user can set new values to all fields
			skillExchange.setStatus(newSkillExchange.getStatus());
			skillExchange.setDescription(newSkillExchange.getDescription());
			
		} catch(NoSuchElementException nex) {
			throw new NameNotFoundException ("Skill Exchange " + id + "not found");
		} finally {
			return srepo.save(skillExchange);
		}
	}
	
	//Delete SkillExchange
	public String deleteSkillExchange(int id) {
		String msg="";
		if (srepo.findById(id)!=null) {
			srepo.deleteById(id);
			msg="SKill Exchange" + id + "successfully deleted!";
		} else {
			msg="SKill Exchange" + id + "not found!";
		}
		return msg;
	}
}
