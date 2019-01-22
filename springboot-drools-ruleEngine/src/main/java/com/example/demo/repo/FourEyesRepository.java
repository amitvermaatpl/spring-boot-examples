package com.example.demo.repo;

import com.example.demo.domain.FourEyeState;
import com.example.demo.domain.FourEyes;
import com.example.demo.domain.QFourEyes;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by radugrig on 28/05/2018.
 */
@Repository
public interface FourEyesRepository extends JpaRepository<FourEyes,Long> ,
        QuerydslPredicateExecutor<FourEyes>,QuerydslBinderCustomizer<QFourEyes> {


    Optional<FourEyes> findOneByIdAndState(long id,FourEyeState state);
    List<FourEyes> findAllByState(FourEyeState state);

    default void customize(QuerydslBindings bindings, QFourEyes fourEyes){
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }



}
