/**
 * 
 */
package com.example.demo.domain;

import java.util.Date;
import java.time.Instant;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name = "PERC_PER_RATING")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@SequenceGenerator(name = "PERCENTAGERATING_ID_GENERATOR", sequenceName = "prototypemora.PERCENTAGERATING_SEQ")
public class PercentagePerRating {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERCENTAGERATING_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "RATING_KEY")
	private AssetRatingType ratingKey;
	@Column(name = "PERCENTAGE")
	private Float percentage;
	@Column(name = "VALID_FROM")
	private Date validFrom;
	@Column(name = "VALID_TO")
	private Date validTo;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "app_user")
	private String appUserName;
	@Column(name = "app_TIMESTAMP")
	private Instant appUserTimestamp;
	@Transient
	private Float previousLatestPercentage;
}
