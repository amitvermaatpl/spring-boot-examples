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
@Table(name = "ASSET_RATING_TYPE")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@SequenceGenerator(name = "ASSETRATINGTYPE_ID_GENERATOR", sequenceName = "prototypemora.ASSETRATINGTYPE_SEQ")
public class AssetRatingType {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSETRATINGTYPE_ID_GENERATOR")
	@Column(name = "RATING_KEY")
	private Long ratingKey;
	
	/*@Column(name = "ID")
	private Long id;*/
	
	@Column(name = "RATING")
	private String rating;
	
	@Column(name = "VALID_FROM")
	private Date validFrom;
	@Column(name = "VALID_TO")
	private Date validTo;
}
