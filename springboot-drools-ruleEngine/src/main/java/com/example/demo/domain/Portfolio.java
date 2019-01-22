/**
 * 
 */
package com.example.demo.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@Table(name = "PORTFOLIO", schema="prototypemora")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
@SequenceGenerator(name="PORTFOLIO_ID_GENERATOR", sequenceName="prototypemora.PORTFOLIO_SEQ")
public class Portfolio {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PORTFOLIO_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;
	@Column(name = "RAR_ID")
	private Long rarId;
	@Column(name = "PORTFOLIO_Name")
	private String name;
	@Column(name = "portfolio_code")
	private String portfolioCode;
	@Column(name = "CURRENCY")
	private String currency;
	@Column(name = "VALID_FROM")
	private Date validFrom;
	@Column(name = "VALID_TO")
	private Date validTo;
	@Column(name = "ACTIVE")
	private String active;
	@Column(name = "DESCRIPTION")
	private String description;
	@Transient
	private List<Portfolio> children;
	
	@Transient
	private Long parentId;

}
