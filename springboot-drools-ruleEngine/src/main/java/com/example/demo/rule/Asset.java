package com.example.demo.rule;

import java.math.BigDecimal;
import java.util.Date;

import lombok.*;

/**
 * Created by radugrig on 05/06/2018.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Asset {

    private long asset_key;

    private String currency;
    private double amount;
    private String rating;
    private float percentage;
    private String institution;
    private Date snapshotDate;
    private String portfolio;
    private double nominal;
}

