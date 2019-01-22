package com.example.demo.rest;

import com.example.demo.domain.FourEyes;
import com.example.demo.repo.FourEyesRepository;
import com.example.demo.services.FourEyesService;
import com.example.demo.rest.error.BadRequestExceprion;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by radugrig on 29/05/2018.
 */
@RestController
@RequestMapping("/api/foureye")
public class FourEyesResource {

    @Autowired
    private FourEyesRepository repository;

    @Autowired
    private FourEyesService fourEyesService;

    @GetMapping
    public ResponseEntity<Page<FourEyes>> getEntries(
            @QuerydslPredicate(root = FourEyes.class) Predicate predicate,
            @PageableDefault(sort = { "id" }) Pageable pageable){
        return ResponseEntity.ok(fourEyesService.getEntries(predicate,pageable));
    }

    @PostMapping
    public ResponseEntity<FourEyes> addNewEntry(@RequestBody FourEyes newEntry) throws URISyntaxException {
        if (newEntry.getId()!=null) throw new BadRequestExceprion("bla bla go to update");
        FourEyes entry = repository.save(newEntry);
        return ResponseEntity.created(new URI("/api/foureye/"+entry.getId())).body(entry);
    }

    @PutMapping
    public ResponseEntity<FourEyes> updateEntry(@RequestBody FourEyes updateEntry){
        if(updateEntry.getId()==null) throw new BadRequestExceprion("need id");
        return fourEyesService.updateEntity(updateEntry)
                .map(fourEyes -> ResponseEntity.ok(fourEyes))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("approve")
    public ResponseEntity<Page<FourEyes>> getApproveEntries(@QuerydslPredicate(root = FourEyes.class) Predicate predicate,
                                                            @PageableDefault(sort = { "id" }) Pageable pageable){
        return ResponseEntity.ok(fourEyesService.getEntriesForApproval(predicate,pageable));
    }

    @PostMapping("approve")
    public ResponseEntity approve(Long id){
        fourEyesService.approve(id);
        return ResponseEntity.accepted().build();
    }


    @DeleteMapping("approve")
    public ResponseEntity reject(Long id){
        fourEyesService.reject(id);
        return ResponseEntity.accepted().build();
    }





}