package com.har.ish.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "password")
public class passwordModel {
	@Id
	@Column(name="id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "Username",nullable = false)
	private String userName;
	@Column(name = "Password")
	private String Password;
	@Column(name="is_active",nullable = false)
	private Boolean is_active;
	@Column(name="Created_on",nullable=false)
	private String created_on;
	@Column(name="Updated_on",nullable=false)
	private String updated_on;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the is_active
	 */
	public Boolean getIs_active() {
		return is_active;
	}

	/**
	 * @param is_active the is_active to set
	 */
	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		Password = password;
	}

	/**
	 * @return the created_on
	 */
	public String getCreated_on() {
		return created_on;
	}

	/**
	 * @param created_on the created_on to set
	 */
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	/**
	 * @return the updated_on
	 */
	public String getUpdated_on() {
		return updated_on;
	}

	/**
	 * @param updated_on the updated_on to set
	 */
	public void setUpdated_on(String updated_on) {
		this.updated_on = updated_on;
	}

}
