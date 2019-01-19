package com.spring.jpa.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Bank_Account" )
public class BankAccount {
 
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANKACC_SEQ")
    @SequenceGenerator(sequenceName = "bankacc_seq", allocationSize = 1, name = "BANKACC_SEQ")
    private Long id;
 
    @Column(name = "Full_Name", length = 128, nullable = false)
    private String fullName;
 
    @Column(name = "Balance", nullable = false)
    private double balance;
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getFullName() {
        return fullName;
    }
 
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
 
    public double getBalance() {
        return balance;
    }
 
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
