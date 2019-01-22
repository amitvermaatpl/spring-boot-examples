package com.example.demo.repo;

import com.example.demo.domain.Asset;
import com.example.demo.domain.QAsset;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by radugrig on 08/06/2018.
 */
@Repository
@Transactional(readOnly = true)
public interface AssetRepository extends JpaRepository<Asset,Long>,QuerydslPredicateExecutor<Asset>,QuerydslBinderCustomizer<QAsset> {
    default void customize(QuerydslBindings bindings, QAsset asset){
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }
}
