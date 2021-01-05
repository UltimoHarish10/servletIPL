package com.har.ish.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity(name="team")
@Table(name="team")
public class TeamModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="TEAM_SHORT_NAME")
	private String teamShortName;
	
	@Column(name="TEAM_FULL_NAME")
	private String teamFullName;
	
	@Column(name="IS_ACTIVE")
	private Boolean isActive;
	
	@Column(name="CREATED_ON")
	private Timestamp createdOn;
	
	@Column(name="UPDATED_ON")
	private Timestamp updatedOn;
	
	@OneToMany(mappedBy="teams")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<PersonalDetailsModel> personalDetails;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the teamShortName
	 */
	public String getTeamShortName() {
		return teamShortName;
	}

	/**
	 * @param teamShortName the teamShortName to set
	 */
	public void setTeamShortName(String teamShortName) {
		this.teamShortName = teamShortName;
	}

	/**
	 * @return the teamFullName
	 */
	public String getTeamFullName() {
		return teamFullName;
	}

	/**
	 * @param teamFullName the teamFullName to set
	 */
	public void setTeamFullName(String teamFullName) {
		this.teamFullName = teamFullName;
	}

	/**
	 * @return the createdOn
	 */
	public Timestamp getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the updatedOn
	 */
	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<PersonalDetailsModel> getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(List<PersonalDetailsModel> personalDetails) {
		this.personalDetails = personalDetails;
	}
	
}
