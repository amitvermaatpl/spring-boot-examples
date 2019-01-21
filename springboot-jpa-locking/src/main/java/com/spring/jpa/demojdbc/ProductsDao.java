package com.spring.jpa.demojdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.jpa.demojdbc.optimisticlock.OptimisticlyLocked;

@Repository
public class ProductsDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;
   
    public Product findOne(Long id) {
        return this.jdbcTemplate.queryForObject("select * from product where id = ?", new Object[]{id},
            new RowMapper<Product>() {
                public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Product product = new Product();
                    product.setId(rs.getLong("id"));
                    product.setVersion(rs.getLong("version"));
                    product.setName(rs.getString("name"));
                    product.setUpc(rs.getLong("upc"));

                    return product;
                }
            });
    }
    
    @OptimisticlyLocked
    public Product save(Product product) {
        this.jdbcTemplate.update("update product set version = ? , name = ?, upc = ? where id = ?",
            product.getVersion(), product.getName(), product.getUpc(), product.getId());

        return this.findOne(product.getId());
    }
}
