package com.spring.jpa.demojdbc.optimisticlock;

public class OptimisticLockException extends RuntimeException { 
    
    public OptimisticLockException(String description) {
        super(description);
    }
}
