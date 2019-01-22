package com.example.demo.services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.domain.Rules;

@RunWith(SpringJUnit4ClassRunner.class)
public class RuleServiceTest {
	
	@Autowired
	RuleService ruleService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllRules() {
		List<Rules> rules=ruleService.getAllRules();
		assertNotNull(rules);
	}

}
