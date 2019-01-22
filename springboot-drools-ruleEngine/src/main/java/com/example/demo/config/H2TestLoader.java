package com.example.demo.config;

import com.example.demo.domain.Asset;
import com.example.demo.repo.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by radugrig on 08/06/2018.
 */
@Component
public class H2TestLoader {

    @Autowired
    private AssetRepository assetRepository;
    
    @PostConstruct
    public void init(){
        assetRepository.saveAll(
                Arrays.asList(
                        new Asset(1, "x1", BigDecimal.valueOf(1000000), "AAA", 0.2f, "1", ZonedDateTime.now(), "1", BigDecimal.valueOf(15000),null),
                        new Asset(2, "x1", BigDecimal.valueOf(1000000), "AA", 0.1f, "1", ZonedDateTime.now(), "1", BigDecimal.valueOf(17000), null),
                        new Asset(10, "x1", BigDecimal.valueOf(1000000), "AAA", 0.2f, "1", ZonedDateTime.now(), "2", BigDecimal.valueOf(0), null),
                        new Asset(11, "x1", BigDecimal.valueOf(1000000), "AA", 0.1f, "1", ZonedDateTime.now(), "2", BigDecimal.valueOf(0), null),
                        new Asset(3, "x1", BigDecimal.valueOf(1000000), "A", 0.0f, "1", ZonedDateTime.now(), "1", BigDecimal.valueOf(19000), null),
                        new Asset(12, "x1", BigDecimal.valueOf(1000000), "A", 0.0f, "1", ZonedDateTime.now(), "1", BigDecimal.valueOf(0), null),
                        new Asset(19, "x2", BigDecimal.valueOf(1000000), "AAA", 0.2f, "1", ZonedDateTime.now(), "2", BigDecimal.valueOf(0), null),
                        new Asset(20, "x2", BigDecimal.valueOf(1000000), "AA", 0.1f, "1", ZonedDateTime.now(), "2", BigDecimal.valueOf(0), null),
                        new Asset(21, "x2", BigDecimal.valueOf(1000000), "A", 0.0f, "1", ZonedDateTime.now(), "2", BigDecimal.valueOf(0), null),
                        new Asset(28, "x2", BigDecimal.valueOf(1000000), "AAA", 0.2f, "1", ZonedDateTime.now(), "2", BigDecimal.valueOf(0), null),
                        new Asset(29, "x2", BigDecimal.valueOf(1000000), "AA", 0.1f, "1", ZonedDateTime.now(), "2", BigDecimal.valueOf(0), null),
                        new Asset(30, "x2", BigDecimal.valueOf(1000000), "A", 0.0f, "1", ZonedDateTime.now(), "2", BigDecimal.valueOf(0), null))
        );
    }

}
