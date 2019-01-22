package com.example.demo.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.AssetRatingType;
/**
 * @author NishigandhaomanwarOm
 *
 */
@Transactional
@Repository
public interface AssetRatingTyperepository extends JpaRepository<AssetRatingType, Long> {

}