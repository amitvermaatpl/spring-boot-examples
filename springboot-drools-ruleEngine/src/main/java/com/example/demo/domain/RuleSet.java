package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="RULESET", schema = "prototypemora")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SequenceGenerator(name="RULESET_ID_GENERATOR", sequenceName="RULESET_SEQ")
public class RuleSet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RULESET_ID_GENERATOR")
	@Column(name = "RULESET_ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "RULESET_DESC")
	private String rulesetDesc;
	
	@Column(name = "ACTIVE")
	private String activeStatus;  // CHAR type in DB
	
	@Column(name = "RULESET_MODE")
	private String rulesetMode;

	@Column(name = "VALID_FROM")
	@Temporal(TemporalType.DATE)
	private Date validFrom;

	@Column(name = "VALID_TO")
	@Temporal(TemporalType.DATE)
	private Date validTo;

	
}
