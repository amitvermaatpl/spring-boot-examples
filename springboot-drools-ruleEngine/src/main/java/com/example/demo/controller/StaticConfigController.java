/**
 * 
 */
package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.PercentagePerRating;
import com.example.demo.exception.LEDApplicationException;
import com.example.demo.services.StaticConfigService;

/**
 * @author NishigandhaomanwarOm
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = {"Content-Type", "Accept", "X-Requested-With", "remember-me","Origin","Last-Modified", "Content-Disposition", "attachment"})
@RestController
@RequestMapping("/staticConfig")
public class StaticConfigController {

private static final Logger log = LoggerFactory.getLogger(StaticConfigController.class);
	
	@Autowired
	private StaticConfigService saticConfigService;	
	
	@GetMapping("/fetchAll")
	public List<PercentagePerRating> getAllStaticConfigurations() throws LEDApplicationException {	
		log.info("Fetching all static configurations from databse.");		
	    return saticConfigService.getAllStaticConfigurations();
	}	
	
	@GetMapping("/deletebyid/{id}")
	public PercentagePerRating deleteRating(@PathVariable(value = "id") Long ratingId) {
		log.info("Deleting rating from database.");		
	    return saticConfigService.deleterating(ratingId);
	}
	
	@PostMapping("/addRating")	
	public PercentagePerRating addRating(@Valid @RequestBody PercentagePerRating rating) throws LEDApplicationException {
		log.info("Adding new rating.");
	    return saticConfigService.addRating(rating);
	}
	
	@PutMapping("/editRating")
	public PercentagePerRating editRating(@Valid @RequestBody PercentagePerRating rating) throws LEDApplicationException {
		log.info("Updating Rating in database.");	
	    return saticConfigService.editRating(rating);
	}
	
	@GetMapping("/approveRating/{id}")
	public PercentagePerRating approveRating(@PathVariable (value = "id") Long ratingId) throws LEDApplicationException {
		log.info("Approving  Rating in database with ratingKey: " +ratingId);	    
	    return saticConfigService.approveRating(ratingId);
	}

	@GetMapping("/rejectRating/{id}")
	public PercentagePerRating rejectRating(@PathVariable (value = "id") Long ratingId) throws LEDApplicationException {
		log.info("Rejecting  Rating in database with ratingKey: " +ratingId);	    
	    return saticConfigService.rejectRating(ratingId);
	}
	
	@GetMapping("/approveDeleteRating/{id}")
	public PercentagePerRating approveDeleteRating(@PathVariable (value = "id") Long ratingId) throws LEDApplicationException {
		log.info("Approving Delete of Rating in database with ratingKey: " +ratingId);	    
	    return saticConfigService.approveDelete(ratingId);
	}
	
}
