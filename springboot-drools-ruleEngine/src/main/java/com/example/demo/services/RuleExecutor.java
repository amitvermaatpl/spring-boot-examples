package com.example.demo.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Rules;
import com.example.demo.exception.LEDApplicationException;
import com.example.demo.repo.RuleSetRepository;
import com.example.demo.repo.RulesRepository;
import com.example.demo.rule.Asset;
import com.example.demo.services.util.ConsoleOutputCapturer;
/**
 * Created by radugrig on 01/06/2018.
 */
@Service
public class RuleExecutor {
	private static final Logger log = LoggerFactory.getLogger(RuleExecutor.class);
	
	@Autowired
	private RuleSetRepository ruleSetRepository;
	
	@Autowired
	private RulesRepository ruleRepository;
	
	@Autowired
	ConsoleOutputCapturer captureConsole;
	
	public List<Rules> executeRuleSet(Long ruleSetId) throws LEDApplicationException {
		List<Rules> ruleResults = new ArrayList<Rules>();
		// Fetch List of rules having this ruleSetId
		// Execute the rules in loop
		List<Rules> rules = ruleSetRepository.findRulesInRuleSet(ruleSetId);
		if (!rules.isEmpty()) {
			for (Rules r : rules) {
				if (r.getRuleContent() != null || !"".equals(r.getRuleContent().trim())) {
					r.setResult(fireRule(r).getResult());
					ruleResults.add(r);
				} else {
					r.setResult("ERROR : Rule Content defined is not Valid");
				}

			}
		} else {

			throw new LEDApplicationException("No Rules defined for ruleset: " + ruleSetId, "LED113");
		}
		return ruleResults;
	}
    
    public Rules executeRule(Long ruleId) throws LEDApplicationException{
    	Optional<Rules> rule = ruleRepository.findById(ruleId); 
    	if(rule.isPresent() && rule.get().getActiveStatus().equalsIgnoreCase("y")) {
	    	Rules rulefromDb = rule.get();
	    	if(rulefromDb.getRuleContent() != null || !"".equals(rulefromDb.getRuleContent().trim())) {
	    		rulefromDb.setResult(fireRule(rulefromDb).getResult());
	    	}else {
	    		rulefromDb.setResult("Valid Rule/Content not found or Rule is Inactive");
	    	}
	    	
    	return rulefromDb;
    	} else {
    		throw new LEDApplicationException("Valid Rule/Content not found or Rule is Inactive", "LED101");
    	}
    }
    
	public Rules fireRule(Rules rule) throws LEDApplicationException {
		KieServices kieServices = KieServices.Factory.get();
		KieHelper kieHelper = new KieHelper();
		log.info("Firing rule Id: " + rule.getId() + "Rule Content" + rule.getRuleContent());
		if(rule != null && !"".equals(rule.getRuleContent().trim())) {
			if(!rule.getRuleContent().startsWith("package")) {
				rule.setResult("ERROR : Rule Content defined is not Valid");
			}
			byte[] b1 = rule.getRuleContent().getBytes();
			Resource resource1 = kieServices.getResources().newByteArrayResource(b1);
			kieHelper.addResource(resource1, ResourceType.DSLR);
			FileInputStream fis1;
			try {
				String webAppPath = System.getProperty( "catalina.base" );
				String ruleFilePath = webAppPath+"/app/WEB-INF/classes/rules/assets.dsl";
				log.info("***assets.dsl file path: ==> "+ruleFilePath);
				fis1 = new FileInputStream("/home/vcap/app/WEB-INF/classes/rules/assets.dsl");
//				Local Workspace path
//				fis1 = new FileInputStream("src/main/resources/rules/assets.dsl");
				Resource resource2 = kieServices.getResources().newInputStreamResource(fis1);
	
				kieHelper.addResource(resource2, ResourceType.DSL);
				KieBase kieBase = kieHelper.build();
				KieSession kieSession = kieBase.newKieSession();
	
				Arrays.asList(new Asset(1, "x1", 1000000, "AAA", 0.2f, "1", new Date(118, 5, 15), "1", 15000),
						new Asset(2, "x1", 1000000, "AA", 0.1f, "1", new Date(118, 5, 15), "1", 17000),
						new Asset(10, "x1", 1000000, "AAA", 0.2f, "1", new Date(118, 5, 15), "2", 0),
						new Asset(11, "x1", 1000000, "AA", 0.1f, "1", new Date(118, 5, 15), "2", 0),
						new Asset(3, "x1", 1000000, "A", 0.0f, "1", new Date(118, 5, 15), "1", 19000),
						new Asset(12, "x1", 1000000, "A", 0.0f, "1", new Date(118, 5, 15), "1", 0),
						new Asset(19, "x2", 1000000, "AAA", 0.2f, "1", new Date(118, 5, 15), "2", 0),
						new Asset(20, "x2", 1000000, "AA", 0.1f, "1", new Date(118, 5, 15), "2", 0),
						new Asset(21, "x2", 1000000, "A", 0.0f, "1", new Date(118, 5, 15), "2", 0),
						new Asset(28, "x2", 1000000, "AAA", 0.2f, "1", new Date(118, 5, 15), "2", 0),
						new Asset(29, "x2", 1000000, "AA", 0.1f, "1", new Date(118, 5, 15), "2", 0),
						new Asset(30, "x2", 1000000, "A", 0.0f, "1", new Date(118, 5, 15), "2", 0))
						.forEach(kieSession::insert);
				captureConsole.start();
				int numberOfRulesFired = kieSession.fireAllRules();
				String capturedValue = captureConsole.stop();
				log.info("Number of rules fired " + numberOfRulesFired);
				log.info("Value captured from Console ------------ " + capturedValue);
				rule.setResult(capturedValue);
				kieSession.dispose();
			
			} catch (FileNotFoundException e) {
				rule.setResult("Rule Error");
				e.printStackTrace();
			} catch (Exception e) {
				rule.setResult("Rule Error");
				e.printStackTrace();
			}
		}else {
			log.debug("Rule or Rule Content is empty: ");
			rule.setResult("Rule or Rule Content is empty");
		}
		
		return rule;
	}
    
}
