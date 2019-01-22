package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Rules;
import com.example.demo.domain.RuleSet;

@Transactional
@Repository
public interface RuleSetRepository extends JpaRepository<RuleSet, Long> {

	@Query("SELECT r FROM Rules r where r.ruleSet.id = :id and r.activeStatus = 'Y' order by r.ruleStep asc") 
	public List<Rules> findRulesInRuleSet(@Param("id") Long ruleId);

	public Optional<RuleSet> findById(Long id);

}
