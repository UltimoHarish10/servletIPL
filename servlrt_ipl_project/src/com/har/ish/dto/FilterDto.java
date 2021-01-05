package com.har.ish.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FilterDto {

	public List<String> profileTypes;
	public List<String> positionTitle;
	public Integer Age;
	public List<String> teams;
	public List<String> countries;
	public List<String> getProfileTypes() {
		return profileTypes;
	}
	public void setProfileTypes(List<String> profileTypes) {
		this.profileTypes = profileTypes;
	}
	public List<String> getPositionTitle() {
		return positionTitle;
	}
	public void setPositionTitle(List<String> positionTitle) {
		this.positionTitle = positionTitle;
	}
	public Integer getAge() {
		return Age;
	}
	public void setAge(Integer age) {
		Age = age;
	}
	public List<String> getTeams() {
		return teams;
	}
	public void setTeams(List<String> teams) {
		this.teams = teams;
	}
	public List<String> getCountries() {
		return countries;
	}
	public void setCountries(List<String> countries) {
		this.countries = countries;
	}	
}
