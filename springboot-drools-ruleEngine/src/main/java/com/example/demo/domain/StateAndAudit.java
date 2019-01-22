package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by radugrig on 14/06/2018.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class StateAndAudit<T extends StateAndAudit> {

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private FourEyeState state =FourEyeState.ACTUAL;

    public abstract T getOld();
    public abstract void setOld(T old);

}
