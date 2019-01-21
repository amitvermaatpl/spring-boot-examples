package com.spring.jpa.demojpa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("JpaProductsController")
@RequestMapping("/jpa-products")
public class ProductsController {
    
    @Autowired
    private ProductsRepository repo;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Product> findOne(@PathVariable Long id) {
       return repo.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Product save(@RequestBody Product product) {
       return repo.save(product);
    }
}
