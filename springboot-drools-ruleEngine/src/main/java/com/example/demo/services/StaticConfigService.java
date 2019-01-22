package com.example.demo.services;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.AssetRatingType;
import com.example.demo.domain.PercentagePerRating;
import com.example.demo.exception.LEDApplicationException;
import com.example.demo.repo.AssetRatingTyperepository;
import com.example.demo.repo.StaticConfigRepository;

@Service
public class StaticConfigService {
	
	private static final Logger log = LoggerFactory.getLogger(StaticConfigService.class);
	@Autowired
	private StaticConfigRepository repository;
	@Autowired
	private AssetRatingTyperepository assetRepository;
	
	public PercentagePerRating addRating(PercentagePerRating rating) throws LEDApplicationException {
		
		List<PercentagePerRating> allRatings = repository.findAll();
		List<PercentagePerRating> filteredRatings = allRatings.stream()
				.filter(p -> p.getRatingKey().getRating().equalsIgnoreCase(rating.getRatingKey().getRating()))
				.collect(Collectors.toList());
		
		if(filteredRatings.size() != 0) {
			throw new LEDApplicationException("Cannot add rating, Rating Already Exists", "LED113");
		} else {
			AssetRatingType assetRating = new AssetRatingType();
			assetRating.setRating(rating.getRatingKey().getRating());
			assetRating.setValidFrom(new Date());
			AssetRatingType persistedRating = assetRepository.save(assetRating);
			rating.setRatingKey(persistedRating);
			rating.setStatus("PENDING_APPROVAL");
			rating.setAppUserName(rating.getAppUserName());
			rating.setAppUserTimestamp((Instant.now()));
			rating.setValidFrom(new Date());
			return repository.save(rating);
		}
	}

	public PercentagePerRating editRating(PercentagePerRating rating) throws LEDApplicationException {
		// If already a rating is present in PENDING_APPROVAL status User cannot edit
		// the rating
		Optional<PercentagePerRating> ratingInDB = repository.getRatingToEdit(rating.getRatingKey().getRating());
		if (ratingInDB.isPresent()) {
			log.error("Cannot Edit the rating, Approve/Reject existing change for rating: " +rating.getRatingKey().getRating());
			throw new LEDApplicationException("Cannot Edit the rating, Approve/Reject existing change for rating",
					"LED107");
		}

		Optional<PercentagePerRating> ratingFromDB = repository.findById(rating.getId());
		PercentagePerRating dbRating = null;
		if (ratingFromDB.isPresent()) {
			dbRating = ratingFromDB.get();
		} 

		PercentagePerRating ratingToBeInserted = new PercentagePerRating();
		ratingToBeInserted = rating;
		ratingToBeInserted.setRatingKey(dbRating.getRatingKey());
		ratingToBeInserted.setStatus("PENDING_APPROVAL");
		ratingToBeInserted.setId(null);
		ratingToBeInserted.setValidFrom(new Date());
		ratingToBeInserted.setAppUserName(rating.getAppUserName());
		ratingToBeInserted.setAppUserTimestamp((Instant.now()));
		return repository.save(ratingToBeInserted);
	}

	public PercentagePerRating deleterating(Long ratingId) {
		Optional<PercentagePerRating> optionalRating = repository.findById(ratingId);
		PercentagePerRating ratingFromDB= null;
		if(optionalRating.isPresent()){
			ratingFromDB =  optionalRating.get();
			ratingFromDB.setAppUserTimestamp((Instant.now()));
			ratingFromDB.setValidFrom(new Date());
			ratingFromDB.setStatus("PENDING_DELETION");
		}	    
	    return repository.save(ratingFromDB);
	}

	public List<PercentagePerRating> getAllStaticConfigurations() throws LEDApplicationException {
		
		List<PercentagePerRating> allRatings = repository.findAllRatingsToDisplay();
//		For UI tooltip need to show latest percentage value and Previous value
		for (PercentagePerRating rating : allRatings) {
			if("PENDING_APPROVAl".equalsIgnoreCase(rating.getStatus())) {
				List<PercentagePerRating> previousRatingPerKey = allRatings.stream()
						.filter(r -> r.getRatingKey().getRating().equals(rating.getRatingKey().getRating()))
						.collect(Collectors.toList()).stream().filter(k -> "APPROVED".equals(k.getStatus()))
						.collect(Collectors.toList());

				if (!previousRatingPerKey.isEmpty() && previousRatingPerKey.size() > 1) {
					throw new LEDApplicationException("More than one rating found in APPROVED status for rating key"
							+ rating.getRatingKey().getRating(), "LED112");
				} else if (!previousRatingPerKey.isEmpty() && previousRatingPerKey.get(0) != null){
					rating.setPreviousLatestPercentage(previousRatingPerKey.get(0).getPercentage());
				}
				
			}
		}
		return allRatings;
	}
	
	@Transactional
	public PercentagePerRating approveRating(Long ratingId) throws LEDApplicationException {
		Optional<PercentagePerRating>  ratingFromDB = repository.findById(ratingId);
		PercentagePerRating ratingToBeUpdated = null;
		if(ratingFromDB.isPresent() && "PENDING_APPROVAL".equalsIgnoreCase(ratingFromDB.get().getStatus())) {
			ratingToBeUpdated = ratingFromDB.get();
		} else {
			log.error("Rating not Found or not in PENDING_APPROAL state " +ratingId);
			throw new LEDApplicationException("Rating not Found or not in PENDING_APPROAL state", "LED106");
		}
//		set existing one as Inactive.
		Optional<PercentagePerRating> ratingFromDb = repository.getRatingToInactivate(ratingToBeUpdated.getRatingKey().getRating());
		if(ratingFromDb.isPresent()) {
		PercentagePerRating ratingToInactivate = ratingFromDb.get();
		ratingToInactivate.setStatus("INACTIVE");	
		repository.save(ratingToInactivate);
		}
		ratingToBeUpdated.setValidFrom(new Date());
		ratingToBeUpdated.setStatus("APPROVED");
		ratingToBeUpdated.setAppUserTimestamp((Instant.now()));
		return repository.save(ratingToBeUpdated);
	}
	
	public PercentagePerRating approveDelete(Long ratingId) throws LEDApplicationException {
		Optional<PercentagePerRating>  ratingFromDB = repository.findById(ratingId);
		PercentagePerRating ratingToBeUpdated = null;
		if(ratingFromDB.isPresent() && "PENDING_DELETION".equalsIgnoreCase(ratingFromDB.get().getStatus())) {
			ratingToBeUpdated = ratingFromDB.get();
		} else {
			log.error("Rating not Found or not in PENDING_DELETION state " +ratingId);
			throw new LEDApplicationException("Rating not Found or not in PENDING_DELETION state", "LED108");
		}
		ratingToBeUpdated.setValidFrom(new Date());
		ratingToBeUpdated.setStatus("INACTIVE");
		ratingToBeUpdated.setAppUserTimestamp((Instant.now()));
	    return repository.save(ratingToBeUpdated);
	}
	
	public PercentagePerRating rejectRating(Long ratingId) {
		Optional<PercentagePerRating> optionalRating = repository.findById(ratingId);
		PercentagePerRating ratingFromDB = null;
		if (optionalRating.isPresent() && "PENDING_APPROVAL".equalsIgnoreCase(optionalRating.get().getStatus())) {
			ratingFromDB = optionalRating.get();
			ratingFromDB.setAppUserTimestamp((Instant.now()));
			ratingFromDB.setStatus("REJECTED");
			ratingFromDB.setValidFrom(new Date());
		} else if(optionalRating.isPresent() && "PENDING_DELETION".equalsIgnoreCase(optionalRating.get().getStatus())) {
			ratingFromDB = optionalRating.get();
			ratingFromDB.setAppUserTimestamp((Instant.now()));
			ratingFromDB.setStatus("APPROVED");
		}
		return repository.save(ratingFromDB);
	}

}
