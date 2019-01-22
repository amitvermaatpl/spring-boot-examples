package com.example.demo.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Portfolio;
/**
 * @author NishigandhaomanwarOm
 *
 */
@Transactional
@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {


	@Query("select a.id from Portfolio a where a.id not in (select b.childPortfolioId from PortfolioTree b) and exists (select 1 from PortfolioTree c where a.id=c.parentPortfolioId)")
	public Long getRootportfolioID() ;
}