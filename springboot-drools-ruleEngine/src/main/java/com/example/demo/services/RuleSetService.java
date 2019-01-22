package com.example.demo.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.RuleSet;
import com.example.demo.domain.Rules;
import com.example.demo.exception.LEDApplicationException;
import com.example.demo.repo.RuleSetRepository;

@Service
public class RuleSetService {
	@Autowired
	private RuleSetRepository repository;
	
	@Autowired
	private RuleExecutor executor;
	
	public List<RuleSet> getAllRuleSet() {				
	    return repository.findAll();
	}
	
	public RuleSet addRuleSet(RuleSet ruleset) {		
		return repository.save(ruleset);
	}

	public void deleteRuleSet(Long ruleSetId) {
		Optional<RuleSet> optionalRuleSet = repository.findById(ruleSetId);
		RuleSet ruleSet= null;
		
		if(optionalRuleSet.isPresent()){
			ruleSet =  optionalRuleSet.get();
			repository.delete(ruleSet);			
		}
	}
	
	public List<Rules> executeRuleSet(Long ruleSetId) throws LEDApplicationException {		
		return executor.executeRuleSet(ruleSetId);
	}

	public Long findById(Long ruleSetId) {
		Optional<RuleSet> optionalRuleSet = repository.findById(ruleSetId);
		if(optionalRuleSet.isPresent()){
			return optionalRuleSet.get().getId();
		}
		return null;
	}

	public RuleSet fetchRuleSetById(Long ruleSetId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findById(ruleSetId).orElseThrow(() -> new Exception("No ruleset found for this ID"));
	}

}
