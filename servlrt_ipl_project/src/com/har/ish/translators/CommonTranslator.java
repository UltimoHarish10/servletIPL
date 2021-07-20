package com.har.ish.translators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.har.ish.dao.PositionTitleDao;
import com.har.ish.dao.StateDao;
import com.har.ish.dao.TeamDao;
import com.har.ish.dto.FilterDto;

public class CommonTranslator {
	
	public static final Logger logger = LoggerFactory.getLogger(CommonTranslator.class);
	
	public static final String PROFILE_TYPE = "profile types";
	public static final String POSITION_TITLE = "position titles";
	public static final String TEAMS = "teams";
	public static final String COUNTRY = "country";
	
	public Map<String,Map<String,Integer>> translateToFilterMap(FilterDto filterDto){
		logger.info("translateToFilterMap to filter map method is started");
		PositionTitleDao posDao = new PositionTitleDao();
		TeamDao teamDao = new TeamDao();
		StateDao stateDao = new StateDao();
		Map<String,Map<String,Integer>> filterMap = new HashMap<>();
		Map<String,Integer>supportFiltermap = new HashMap<>();
		try{
			if(filterDto.getProfileTypes() != null && !filterDto.getProfileTypes().isEmpty()){
				supportFiltermap.putAll(posDao.getprofileTypesIdForMap(filterDto.getProfileTypes()));
				filterMap.put(PROFILE_TYPE,supportFiltermap);
			}
			supportFiltermap = new HashMap<>();
			if(filterDto.getPositionTitle() != null && !filterDto.getPositionTitle().isEmpty()){
				supportFiltermap.putAll(posDao.getpositionTitlesIdForMap(filterDto.getPositionTitle()));
				filterMap.put(POSITION_TITLE, supportFiltermap);
			}
			supportFiltermap = new HashMap<>();
			if(filterDto.getTeams() != null && !filterDto.getTeams().isEmpty()){
				supportFiltermap.putAll(teamDao.getTeamIdForMap(filterDto.getTeams()));
				filterMap.put(TEAMS, supportFiltermap);
			}
			supportFiltermap = new HashMap<>();
			if(filterDto.getCountries() != null && !filterDto.getCountries().isEmpty()){
				supportFiltermap.putAll(stateDao.getCountryIdForMap(filterDto.getCountries()));
				filterMap.put(COUNTRY, supportFiltermap);
			}
		}
		catch(Exception e){
			logger.error("Exception occured in the translateToFilterMap method : {}",e);
			e.printStackTrace();
		}
		logger.info("translateToFilterMap to filter map method is completed");
		return filterMap;
	}

}
