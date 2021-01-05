package com.har.ish.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="gender")
@Table(name="gender")
public class GenderModel {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(name="GENDER_NAME")
	private String genderName;
	
	@Column(name="IS_ACTIVE")
	private Boolean isActive;
	
	@Column(name="CREATED_ON")
	private Timestamp createdOn;
	
	@OneToMany(mappedBy="gender")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<PersonalDetailsModel> personalDetails;
	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
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

	public List<PersonalDetailsModel> getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(List<PersonalDetailsModel> personalDetails) {
		this.personalDetails = personalDetails;
	}
	
}
