package com.har.ish.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="email")
@Table(name="email")
public class EmailModel {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(name="EMAIL_ID")
	private String emailId;
	
	@ManyToOne
	@JoinColumn(name="PERSON_ID")
	private PersonalDetailsModel personalDetails;
	
	@Column(name="IS_ACTIVE")
	private Boolean isActive;
	
	@Column(name="CREATED_ON")
	private Timestamp createdOn;
	
	@Column(name="UPDATED_ON")
	private Timestamp updatedOn;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public PersonalDetailsModel getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetailsModel personalDetails) {
		this.personalDetails = personalDetails;
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

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}
}
