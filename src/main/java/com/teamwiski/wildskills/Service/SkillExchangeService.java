package com.teamwiski.wildskills.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamwiski.wildskills.Entity.SkillExchangeEntity;
import com.teamwiski.wildskills.Entity.StudentEntity;
import com.teamwiski.wildskills.Repository.SkillExchangeRepository;
import com.teamwiski.wildskills.Repository.StudentRepository;

@Service
public class SkillExchangeService {
	
	@Autowired
	SkillExchangeRepository srepo;

	@Autowired
	StudentRepository strepo;
	
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
			skillExchange.setTitle(newSkillExchange.getTitle());
			skillExchange.setScheduledStart(newSkillExchange.getScheduledStart());
			skillExchange.setScheduledEnd(newSkillExchange.getScheduledEnd());
			
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
			msg="SKill Exchange not found!";
		}
		return msg;
	}

	//Read all SkillExchange by studentId
	/*public List<SkillExchangeEntity> getAllSkillExchange(int studentId) {
		List<SkillExchangeEntity> skillexchange =  new ArrayList<>();
		srepo.findByStudentStudentId(studentId).forEach(skillexchange::add);
		return skillexchange;
	}*/

	public Set<SkillExchangeEntity> getAllSKillExchanges(int studentId) {
		StudentEntity student = strepo.findById(studentId).orElseThrow();
		return student.getSkillExchanges();
	}

	//Read SkillExchange by studentId
	public SkillExchangeEntity getSkillExchange(int studentId) {
		return srepo.findById(studentId).orElseThrow();
	}

	//Create SkillExchange by studentId
	public SkillExchangeEntity postSkillExchange(SkillExchangeEntity skillExchange, int studentId) {
		StudentEntity student = strepo.findById(studentId).orElseThrow();
		skillExchange.setStudent(student);
		return srepo.save(skillExchange);
	}

	//Update SkillExchange
	@SuppressWarnings("finally")
	public SkillExchangeEntity putSkillExchange(int id, SkillExchangeEntity newSkillExchange, int studentId) {
		SkillExchangeEntity skillExchange = new SkillExchangeEntity();
		
		try {
			//search the id number
			skillExchange = srepo.findById(id).get();
			
			if (skillExchange.getStudent().getStudentId() != studentId){
				throw new SecurityException("Unauthorized Access!");
			} else {
				//if ID is found, the user can set new values to all fields
				skillExchange.setStatus(newSkillExchange.getStatus());
				skillExchange.setTitle(newSkillExchange.getTitle());
				skillExchange.setScheduledStart(newSkillExchange.getScheduledStart());
				skillExchange.setScheduledEnd(newSkillExchange.getScheduledEnd());
			}
			
		} catch(NoSuchElementException nex) {
			throw new NameNotFoundException ("Skill Exchange " + id + "not found");
		} finally {
			return srepo.save(skillExchange);
		}
	}

	//Delete SkillExchange by studentId
	public String deleteSkillExchange(int id, int studentId) {
		SkillExchangeEntity skillExchange = srepo.findById(id).orElseThrow();

		String msg="";
		if (srepo.findById(id)!=null && skillExchange.getStudent().getStudentId() == studentId) {
			srepo.deleteById(id);
			msg="SKill Exchange" + id + "successfully deleted!";
		} else if (skillExchange.getStudent().getStudentId() != studentId) {
			msg="Unauthorized Access!";
		} else {
			msg="SKill Exchange" + id + "not found!";
		}
		return msg;
	}
}
