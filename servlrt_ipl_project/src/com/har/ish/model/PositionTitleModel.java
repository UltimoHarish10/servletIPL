package com.har.ish.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "position_title")
@Table(name = "position_title")
public class PositionTitleModel {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	@Column(name = "POSITION_TITLE_NAME")
	private String positionTitleName;

	@ManyToOne
	@JoinColumn(name = "PROFILE_TYPE_ID")
	private profileTypeModel profType;

	@OneToMany(mappedBy = "postitle")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<PersonalDetailsModel> personalDetails;

	@Column(name = "IS_ACTIVE")
	private Boolean isActive;

	public List<PersonalDetailsModel> getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(List<PersonalDetailsModel> personalDetails) {
		this.personalDetails = personalDetails;
	}

	@Column(name = "CREATED_ON")
	private Timestamp createdOn;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getPositionTitleName() {
		return positionTitleName;
	}

	public void setPositionTitleName(String positionTitleName) {
		this.positionTitleName = positionTitleName;
	}

	public profileTypeModel getProfType() {
		return profType;
	}

	public void setProfType(profileTypeModel profType) {
		this.profType = profType;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
}
