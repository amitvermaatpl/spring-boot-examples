package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Rules;
import com.example.demo.exception.LEDApplicationException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.RuleSetRepository;
import com.example.demo.repo.RulesRepository;

@Service
public class RuleService {
	
	private static final Logger log = LoggerFactory.getLogger(RuleService.class);
	@Autowired
	private RulesRepository rulesRepository;
	
	@Autowired
	private RuleSetRepository ruleSetRepository;
	
	@Autowired
	private RuleExecutor executor;

	public List<Rules> getAllRules() {				
	    return rulesRepository.findAllOrdered();
	}
	
	public Rules addRule(Long ruleSetId, Rules rule) {
		return ruleSetRepository.findById(ruleSetId).map(ruleSet -> {
			rule.setRuleSet(ruleSet);
			
			return rulesRepository.save(rule);
			}).orElseThrow(() -> new ResourceNotFoundException("RuleSetId " + ruleSetId + " not found"));
	    //return repository.save(rule);
	}

	public Rules fetchRuleById(Long ruleId) throws Exception {		
		return rulesRepository.findById(ruleId).orElseThrow(() -> new Exception("No rule found for this ID"));
	}

	public Rules updateRule(Long ruleId,	Rules ruleDescp) {
		Optional<Rules> optionalRule = rulesRepository.findById(ruleId);	
		
		Rules rule= null;
		if(optionalRule.isPresent()){
			rule =  optionalRule.get();						
		}
		if(ruleDescp.getRuleContent()!=null)
			rule.setRuleContent(ruleDescp.getRuleContent().trim());
		if(ruleDescp.getActiveStatus()!=null)
			rule.setActiveStatus(ruleDescp.getActiveStatus());
		if(ruleDescp.getRuleStep()!=null)
			rule.setRuleStep(ruleDescp.getRuleStep());
		
		Rules updatedRule = rulesRepository.save(rule);	    
	    return updatedRule;
	}
	
	public ResponseEntity<String> removeRule(Long ruleId) {			
		Optional<Rules> optionalRule = rulesRepository.findById(ruleId);
		Rules rule= null;
		
		if(optionalRule.isPresent()){
			rule =  optionalRule.get();
			rulesRepository.delete(rule);			
		}	    
	    return ResponseEntity.ok().build();
	}
	
	public Rules executeRule(Long ruleId) throws LEDApplicationException {	
		Rules rules = executor.executeRule(ruleId);
		
		log.info("getRuleId: "+rules.getId());
		log.info("getResult: "+rules.getResult());
		return rules;
	}

	public List<Rules> fetchRulesByRuleSetId(Long rulesetId) {		
		return rulesRepository.getRulesByRuleSetId(rulesetId);
	}
	
	public Rules createEmptyRule(Long ruleSetId) {
		Long ruleStep= rulesRepository.getRulesCount(ruleSetId) + 1L;
		Rules rule = new Rules();
		rule.setActiveStatus("Y");rule.setRuleContent("");rule.setRuleStep(ruleStep);
		return rule;
	}


}
