package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

/**
 * Created by radugrig on 28/05/2018.
 */
@Getter
@Setter
@Builder
@Entity
@Table(name = "FOUR_EYES", schema="prototypemora")
@NoArgsConstructor
@AllArgsConstructor
public class FourEyes extends StateAndAudit<FourEyes> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private FourEyes old;

    @Column(name = "NAME")
    private String name;

    @Column(name="OCCUPATION")
    private String occupation;

    @Column(name="AGE")
    private Integer age;


}
