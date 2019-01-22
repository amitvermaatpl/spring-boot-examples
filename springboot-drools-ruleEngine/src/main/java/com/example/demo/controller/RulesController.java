package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Rules;
import com.example.demo.exception.LEDApplicationException;
import com.example.demo.services.RuleService;

@CrossOrigin(origins = "*", maxAge = 5000, methods ={RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.DELETE}, allowedHeaders = {"Content-Type", "Accept", "X-Requested-With", "remember-me","Origin","Last-Modified", "Content-Disposition", "attachment"})
@RestController
@RequestMapping("/rule")
public class RulesController {
	
	private static final Logger log = LoggerFactory.getLogger(RulesController.class);
	
	@Autowired
	private RuleService ruleService;	
	
		// *** FETCH ALL RULES *** //
		// @RequestMapping(value="/rules", method=RequestMethod.GET) 
		@GetMapping("/fetchAll")
		public List<Rules> getAllRules() {	
			log.info("Fetching all rules from databse.");	
			
		    return ruleService.getAllRules();
		}	

		// *** ADD RULE *** //		
		@GetMapping("/addWithRuleSetId/{ruleSetId}")	
		public Rules createRule(@PathVariable (value = "ruleSetId") Long ruleSetId) {
			log.info("Adding new rule in database.");
		    return ruleService.addRule(ruleSetId, ruleService.createEmptyRule(ruleSetId));
		}
		/*@PostMapping("/addWithRuleSetId/{ruleSetId}")	
		public Rule createRule(@PathVariable (value = "ruleSetId") Long ruleSetId, @Valid @RequestBody Rule rule) {
			log.info("Adding new rule in database.");
		    return ruleService.addRule(ruleSetId, rule);
		}*/
		
		// *** FETCH RULE By ID *** //
		@GetMapping("/getbyid/{id}")
		public Rules getRuleById(@PathVariable(value = "id") Long ruleId) throws Exception {
			log.info("Fetching rule by id= "+ruleId);		
			return ruleService.fetchRuleById(ruleId);
		}
		
		// *** FETCH RULE By RuleSet-ID ***//
		@GetMapping("/getbyrulesetid/{rulesetid}")
		public List<Rules> getRulesByRuleSetId(@PathVariable(value = "rulesetid") Long rulesetId) throws Exception {
			log.info("Fetching rules by id= "+rulesetId);		
			return ruleService.fetchRulesByRuleSetId(rulesetId);
		}
		
		// *** UPDATE RULE By ID *** //
		@PutMapping("/updatebyid/{id}")
		public Rules updateRule(@PathVariable(value = "id") Long ruleId,
		                                        @Valid @RequestBody Rules ruleDescp) {

			log.info("Updating rule in database.");	    
		    return ruleService.updateRule(ruleId, ruleDescp);
		}
		
		// *** DELETE RULE By ID *** //
		@DeleteMapping("/deletebyid/{id}")
		public ResponseEntity<String> deleteRule(@PathVariable(value = "id") Long ruleId) {
			log.info("Deleting rule from database.");		
		    
		    return ruleService.removeRule(ruleId);
		}
	
		@GetMapping("/executerule/{id}")
		public Rules executeRule(@PathVariable(value = "id") Long ruleId) throws LEDApplicationException {
			log.info("executeRule(): ruleId: "+ruleId+ " | Response: " +ruleService.executeRule(ruleId));
			return ruleService.executeRule(ruleId);
		}
		
}
