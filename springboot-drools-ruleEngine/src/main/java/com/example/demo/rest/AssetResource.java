package com.example.demo.rest;

import com.example.demo.domain.Asset;
import com.example.demo.repo.AssetRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by radugrig on 08/06/2018.
 */
@RestController
@RequestMapping("/api/asset")
public class AssetResource {

    @Autowired
    private AssetRepository assetRepository;


    @GetMapping
    public Page<Asset> getAssetsFiltered(@QuerydslPredicate(root = Asset.class)Predicate predicate,
                                               @PageableDefault(sort = { "id", "amount" }) Pageable pageable){
        return assetRepository.findAll(predicate,pageable);
    }
}
