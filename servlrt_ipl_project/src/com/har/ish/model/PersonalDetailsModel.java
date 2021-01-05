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

@Entity(name="personal_details")
@Table(name="personal_details")
public class PersonalDetailsModel {
	
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@ManyToOne
	@JoinColumn(name="GENDER_ID")
	private GenderModel gender;
	
	@Column(name="AGE")
	private Integer age;
	
	@ManyToOne
	@JoinColumn(name="TEAM_ID")
	private TeamModel teams;
	
	@ManyToOne
	@JoinColumn(name="POSITION_TITLE_ID")
	private PositionTitleModel postitle;
	
	@OneToMany(mappedBy="personalDetails")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<AddressModel> addresses;
	
	@Column(name="IS_ACTIVE")
	private Boolean isActive;
	
	@Column(name="CREATED_ON")
	private Timestamp createdOn;
	
	@Column(name="UPDATED_ON")
	private Timestamp updatedOn;
	
	@OneToMany(mappedBy="personalDetails")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<PhoneModel> phones;
	
	@OneToMany(mappedBy="personalDetails")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<EmailModel> emails;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public GenderModel getGender() {
		return gender;
	}

	public void setGender(GenderModel gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public TeamModel getTeams() {
		return teams;
	}

	public void setTeams(TeamModel teams) {
		this.teams = teams;
	}

	public PositionTitleModel getPostitle() {
		return postitle;
	}

	public void setPostitle(PositionTitleModel postitle) {
		this.postitle = postitle;
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

	public List<PhoneModel> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneModel> phones) {
		this.phones = phones;
	}

	public List<EmailModel> getEmails() {
		return emails;
	}

	public void setEmails(List<EmailModel> emails) {
		this.emails = emails;
	}

	public List<AddressModel> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressModel> addresses) {
		this.addresses = addresses;
	}
}
