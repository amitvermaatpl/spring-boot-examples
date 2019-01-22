/**
 * 
 */
package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author NishigandhaomanwarOm
 *
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PORTFOLIOTREE",schema="prototypemora")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SequenceGenerator(name="TreeIdGenerator", sequenceName="prototypemora.PORTFOLIOTREE_SEQ", initialValue = 1)
public class PortfolioTree {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TreeIdGenerator")
	@Column(name = "ID")
	private Long id;

	@Column(name = "RAR_ID")
	private Long rarId;
	
	@Column(name = "PORTFOLIO_Name")
	private String portfolioName;

	@Column(name = "Parent_FK")
	private Long parentPortfolioId;
	
	@Column(name = "CHILD_FK")
	private Long childPortfolioId;
	
	@Column(name = "VALID_TO")
	private Date validTo;
	
	@Column(name = "VALID_FROM")
	private Date validFrom;

}
