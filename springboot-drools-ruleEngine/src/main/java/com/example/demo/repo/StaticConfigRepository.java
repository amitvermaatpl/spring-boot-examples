package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.PercentagePerRating;
/**
 * @author NishigandhaomanwarOm
 *
 */

@Repository
@Transactional
public interface StaticConfigRepository extends JpaRepository<PercentagePerRating, Long> {

	@Query("SELECT p FROM PercentagePerRating p where p.ratingKey.rating = :rating and p.status != 'INACTIVE'") 
	public List<PercentagePerRating> findExistingPercRatingsByRating(@Param("rating") String rating);

	@Query("SELECT p FROM PercentagePerRating p where p.status != 'REJECTED' order by p.ratingKey.rating asc") 
	public List<PercentagePerRating> findAllRatingsToDisplay();
	
	@Query("SELECT p FROM PercentagePerRating p where p.ratingKey.rating = :rating and p.status = 'APPROVED'") 
	public Optional<PercentagePerRating> getRatingToInactivate(@Param("rating") String rating);
	
	@Query("SELECT p FROM PercentagePerRating p where p.ratingKey.rating = :rating and p.status = 'PENDING_APPROVAL'") 
	public Optional<PercentagePerRating> getRatingToEdit(@Param("rating") String rating);
	
}