package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Created by radugrig on 08/06/2018.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@Entity
@Table(name ="ASSETS", schema="prototypemora")
public class Asset extends StateAndAudit<Asset> {

    @Id
    private long id;
    private String currency;
    private BigDecimal amount;
    private String rating;
    private float percentage;
    private String institution;
    private ZonedDateTime snapshotDate;
    private String portfolio;
    private BigDecimal nominal;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Asset old;

}
