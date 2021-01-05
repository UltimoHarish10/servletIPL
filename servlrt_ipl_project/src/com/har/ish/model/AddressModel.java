package com.har.ish.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity(name="address")
@Table(name="address")
public class AddressModel {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(name="STREET_VALUE")
	private String streetVal;
	
	@Column(name="DISTRICT_NAME")
	private String districtName;
	
	@ManyToOne
	@JoinColumn(name="STATE_ID")
	private StateModel states;
	
	@Column(name="PINCODE")
	private Integer pincode;
	
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

	public String getStreetVal() {
		return streetVal;
	}

	public void setStreetVal(String streetVal) {
		this.streetVal = streetVal;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public StateModel getStates() {
		return states;
	}

	public void setStates(StateModel states) {
		this.states = states;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
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
