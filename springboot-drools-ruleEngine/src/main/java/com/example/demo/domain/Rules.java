package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@Table(name="RULES",schema = "prototypemora" )
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SequenceGenerator(name="RULE_ID_GENERATOR", sequenceName="RULE_SEQ")
public class Rules {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RULE_ID_GENERATOR")
	@Column(name = "RULE_ID")
	private Long id;
	
	//CascadeType.ALL
	@ManyToOne(fetch = FetchType.LAZY) // cascade=CascadeType.REMOVE
	@JoinColumn(name = "RULESET_ID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private RuleSet ruleSet;  // nullable-false //
	
	@Column(name = "RULE_STEP")
	private Long ruleStep;

	@Column(name = "CONTENT")
	private String ruleContent;

	@Column(name = "ACTIVE")
	private String activeStatus;  // CHAR //
	
	@Transient
	private String result;
	
}
