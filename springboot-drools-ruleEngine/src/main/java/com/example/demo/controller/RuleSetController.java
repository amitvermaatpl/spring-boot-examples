package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.RuleSet;
import com.example.demo.domain.Rules;
import com.example.demo.exception.LEDApplicationException;
import com.example.demo.services.RuleSetService;

@CrossOrigin(origins = "*", maxAge = 5000, methods ={RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.DELETE}, allowedHeaders = {"Content-Type", "Accept", "X-Requested-With", "remember-me","Origin","Last-Modified", "Content-Disposition", "attachment"})
@RestController
@RequestMapping("/ruleset")
public class RuleSetController {
	
	private static final Logger log = LoggerFactory.getLogger(RuleSetController.class);
	
	@Autowired
	private RuleSetService rulesetService;	
	
	// *** FETCH RULE By ID *** //
	@GetMapping("/getbyid/{id}")
	public RuleSet getRuleById(@PathVariable(value = "id") Long ruleSetId) throws Exception {
		log.info("Fetching rulest by id= "+ruleSetId);		
		return rulesetService.fetchRuleSetById(ruleSetId);
	}
	
	@GetMapping("/execute/{id}")
	public List<Rules> executeRule(@PathVariable(value = "id") Long ruleSetId) throws LEDApplicationException {	
		log.info("executing all rules from databse.");	
        List<Rules> ruleList = rulesetService.executeRuleSet(ruleSetId);
		return ruleList;
	}
	
	// *** FETCH ALL RULES *** //
	// @RequestMapping(value="/rules", method=RequestMethod.GET) 
	@GetMapping("/fetchAll")
	public List<RuleSet> getAllRulSet() {	
		log.info("Fetching all rulset from databse.");			
	    return rulesetService.getAllRuleSet();
	}	
	
	// *** ADD RULESET *** //	
	@PostMapping("/add")	
	public RuleSet addRuleSet(@Valid @RequestBody RuleSet ruleset) {
		log.info("Adding new RuleSet.");
	    return rulesetService.addRuleSet(ruleset);
	}

	// *** DELETE RULESET By ID *** //
		@DeleteMapping("/delete/{id}")
		public boolean deleteRule(@PathVariable(value = "id") Long ruleSetId) {
			log.info("Deleting rule from database.");		
			rulesetService.deleteRuleSet(ruleSetId);
			
			if(rulesetService.findById(ruleSetId)==null){
				return true;
			}else{
				return false;
			}
		}
	
	
}
