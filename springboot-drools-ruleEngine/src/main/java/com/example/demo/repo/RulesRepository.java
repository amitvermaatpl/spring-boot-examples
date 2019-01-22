package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.domain.Rules;

@Transactional
@Repository
public interface RulesRepository extends JpaRepository<Rules, Long> {

	@Query("SELECT r FROM Rules r where r.id = :id") 
	public Optional<Rules> findById(@Param("id") Long ruleId);
	
	//@Query("SELECT r.activeStatus,r.id,r.ruleContent,r.ruleStep FROM Rule r, RuleSet rs where r.ruleSet.id = ?1") 
	//@Query("SELECT r FROM Rule r, RuleSet rs where r.ruleSet.id = ?1")
	
	@Query(value= "select * from prototypemora.Rules a where a.RULESET_ID= ?1 order by a.RULE_STEP asc", nativeQuery = true)
	public List<Rules> getRulesByRuleSetId(Long rulesetId);	
	
	Page<Rules> findByRuleSetId(Long ruleSetId, Pageable pageable);
	
	@Query(value= "Select count(*) from prototypemora.rules r where r.RULESET_ID= ?1", nativeQuery = true)
	public Long getRulesCount(Long ruleSetId);

	@Query(value= "Select r from Rules r order by r.id asc")
	public List<Rules> findAllOrdered();
}
