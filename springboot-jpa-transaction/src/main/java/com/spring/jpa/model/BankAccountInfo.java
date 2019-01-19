package com.spring.jpa.model;


/* 
 * While an  Entity class represents for the data of a record of a table, a Model class represents for
 * the data of one record of a query statement (joined from one or many tables). You use the Model class 
 * when you are interested in some columns of one or many tables. 
 *  
 */
public class BankAccountInfo {
	 
	   private Long id;
	   private String fullName;
	   private double balance;
	 
	   public BankAccountInfo() {
	 
	   }
	 
	   // Used in JPA query.
	   public BankAccountInfo(Long id, String fullName, double balance) {
	      this.id = id;
	      this.fullName = fullName;
	      this.balance = balance;
	   }
	 
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
