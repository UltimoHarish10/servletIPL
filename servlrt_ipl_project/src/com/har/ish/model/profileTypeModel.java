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

@Entity(name="profile_type")
@Table(name="profile_type")
public class profileTypeModel {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="PROFILE_TYPE_NAME")
	private String profileTypeName;
	
	@Column(name="IS_ACTIVE")
	private Boolean isActive;
	
	@Column(name="CREATED_ON")
	private Timestamp createdOn;
	
	@OneToMany(mappedBy = "profType")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<PositionTitleModel> postitle;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProfileTypeName() {
		return profileTypeName;
	}

	public void setProfileTypeName(String profileTypeName) {
		this.profileTypeName = profileTypeName;
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

	public List<PositionTitleModel> getPostitle() {
		return postitle;
	}

	public void setPostitle(List<PositionTitleModel> postitle) {
		this.postitle = postitle;
	}	
}
