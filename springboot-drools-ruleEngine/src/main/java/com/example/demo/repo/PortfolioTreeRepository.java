package com.example.demo.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.PortfolioTree;
/**
 * @author NishigandhaomanwarOm
 *
 */
@Transactional
@Repository
public interface PortfolioTreeRepository extends JpaRepository<PortfolioTree, Long> {

//	@Query("SELECT p FROM PORTFOLIO p where p.id = :id ") 
//	public List<Portfolio> findRulesInRuleSet(@Param("id") Long ruleId);

}