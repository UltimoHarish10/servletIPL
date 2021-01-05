package com.har.ish.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity(name="state")
@Table(name="state")
public class StateModel {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="STATE_NAME")
	private String stateName;
	
	@ManyToOne
	@JoinColumn(name="COUNTRY_ID")
	private CountryModel countries;
	
	@Column(name="IS_ACTIVE")
	private Boolean isActive;
	
	@Column(name="CREATED_ON")
	private Timestamp createdOn;
	
	@OneToMany(mappedBy="states")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<AddressModel> addresses;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public CountryModel getCountries() {
		return countries;
	}

	public void setCountries(CountryModel countries) {
		this.countries = countries;
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

	public List<AddressModel> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressModel> addresses) {
		this.addresses = addresses;
	}
}
