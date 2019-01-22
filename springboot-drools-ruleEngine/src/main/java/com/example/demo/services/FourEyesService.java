package com.example.demo.services;

import com.example.demo.domain.FourEyeState;
import com.example.demo.domain.FourEyes;
import com.example.demo.domain.QFourEyes;
import com.example.demo.repo.FourEyesRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.demo.domain.FourEyeState.*;

/**
 * Created by radugrig on 28/05/2018.
 * TO DO make this generic
 *
 */
@Service
public class FourEyesService {

    @Autowired
    private FourEyesRepository fourEyesRepository;

    @Transactional
    public Optional<FourEyes> updateEntity(FourEyes toUpdate){
        return fourEyesRepository.findOneByIdAndState(toUpdate.getId(),ACTUAL)
                .map(oldValue-> {
                    oldValue.setState(HISTORY);
                    toUpdate.setId(null);
                    toUpdate.setOld(oldValue);
                    toUpdate.setState(UPDATED);
            return fourEyesRepository.save(toUpdate);
        });
    }

    public void approve(long id){
        fourEyesRepository.findOneByIdAndState(id,UPDATED).ifPresent(fourEyes -> {
            fourEyes.setState(ACTUAL);
            fourEyes.getOld().setState(HISTORY);
            // add approves name;
            fourEyesRepository.save(fourEyes);
        });
    }

    public void reject(long id){
        fourEyesRepository.findOneByIdAndState(id,UPDATED).ifPresent(rejected -> {
            FourEyes old =rejected.getOld();
            old.setState(ACTUAL);
            rejected.setOld(null);
            fourEyesRepository.save(old);
            fourEyesRepository.delete(rejected);
        });
    }

    public Page<FourEyes> getEntries(Predicate predicate, Pageable pageable){
        return getFourEyesByState( predicate, pageable, ACTUAL);
    }

    public Page<FourEyes> getEntriesForApproval(Predicate predicate, Pageable pageable) {
        return getFourEyesByState( predicate, pageable, UPDATED);
    }

    private Page<FourEyes> getFourEyesByState(Predicate predicate, Pageable pageable, FourEyeState state) {
        return fourEyesRepository.findAll(QFourEyes.fourEyes.state.eq(state).and(predicate),pageable);
    }


}
