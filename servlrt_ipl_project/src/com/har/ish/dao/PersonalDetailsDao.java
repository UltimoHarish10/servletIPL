package com.har.ish.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.har.ish.dto.AllPersonalDetailsDto;
import com.har.ish.dto.FilterDto;
import com.har.ish.model.PersonalDetailsModel;
import com.har.ish.translators.CommonTranslator;

public class PersonalDetailsDao {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonalDetailsDao.class);
	
	public PersonalDetailsModel getPersonalDetails(Integer personId){
		logger.info("getPersonalDetails method is started");
		PersonalDetailsModel pers = new PersonalDetailsModel(); 
		hibernateInitiator in = new hibernateInitiator();
		Session b = null;
		Transaction tx = null;
		try{
			b=in.creator();
			tx=b.beginTransaction();
			pers = b.get(PersonalDetailsModel.class, personId);
			System.out.println(pers.getFirstName());
			System.out.println(pers.getLastName());
			System.out.println(pers.getAddresses());
			tx.commit();
		}
		catch(Exception e){
			logger.error("Exception occured in the getPersonalDetails : {}",e);
			e.printStackTrace();
		}
		finally{
			b.close();
		}
		logger.info("getPersonalDetails method is completed");
		return pers;
	}

	public List<Object[]> getAllPersonalDetails(AllPersonalDetailsDto personDto) {
		logger.info("getAllPersonalDetails method is started");
		hibernateInitiator in = new hibernateInitiator();
		Session b = null;
		Transaction tx = null;
		List<Object[]> objects = new ArrayList<>();
		try {
			b = in.creator();
			tx = b.beginTransaction();
			Query query = b.getNamedQuery("GET_ALL_PERSONAL_DETAILS");
			objects = query.list();
			tx.commit();
		} catch (Exception e) {
			logger.error("Exception occured in the getAllPersonalDetails : {}",e);
			tx.rollback();
			e.printStackTrace();
		} finally {
			b.close();
		}
		logger.info("getAllPersonalDetails method is completed");
		return objects;
	}
	
	public void savePersonDetails(List<PersonalDetailsModel> persons){
		Session session = null;
		Transaction tx = null;
		try{
			hibernateInitiator in = new hibernateInitiator();
			session = in.creator();
			tx=session.beginTransaction();
			for(PersonalDetailsModel person : persons){
				Date date = new Date();
				if(person.getCreatedOn()== null){
					person.setCreatedOn(new Timestamp(date.getTime()));
				}
				person.setUpdatedOn(new Timestamp(date.getTime()));
				session.saveOrUpdate(person);
				/*session.flush();*/
			}
			tx.commit();
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
	}
	
	public List<Object[]> getPersonalDetailsByFilters(Map<String,Map<String,Integer>>filterMap,FilterDto filterDto) {
		logger.info("getPersonalDetailsByFilters mthod is started");
		Session b = null;
		Transaction tx = null;
		hibernateInitiator in = new hibernateInitiator();
		List<Object[]> objects = new ArrayList<>();
		CommonTranslator cmnTranslator = new CommonTranslator();
		String query  = null;
		try {
			b = in.creator();
			tx = b.beginTransaction();
			StringBuilder sqlQuery = new StringBuilder(b.getNamedQuery("GET_ALL_PERSONAL_DETAILS").getQueryString());
			if(filterDto.getAge() != null){
				sqlQuery.append(" AND PD.AGE = :age");
			}
			if(filterMap != null && filterMap.get(cmnTranslator.COUNTRY) != null){
				sqlQuery.append(" AND CO.ID IN (");
				for(Map.Entry<String,Integer> mapings : filterMap.get(cmnTranslator.COUNTRY).entrySet()){
					sqlQuery.append(mapings.getValue());
					sqlQuery.append(",");
				}
				query = sqlQuery.toString().substring(0, sqlQuery.length()-1);
			}
			if(query != null){
				sqlQuery = new StringBuilder(query);
				sqlQuery.append(")");
			}
			if(filterMap != null && filterMap.get(cmnTranslator.POSITION_TITLE) != null){
				sqlQuery.append(" AND POS.ID IN (");
				for(Map.Entry<String,Integer> mapings : filterMap.get(cmnTranslator.POSITION_TITLE).entrySet()){
					sqlQuery.append(mapings.getValue());
					sqlQuery.append(",");
				}
				query = sqlQuery.toString().substring(0, sqlQuery.length()-1);
			}
			if(query != null){
				sqlQuery = new StringBuilder(query);
				sqlQuery.append(")");
			}
			if(filterMap != null && filterMap.get(cmnTranslator.PROFILE_TYPE) != null){
				sqlQuery.append(" AND PT.ID IN (");
				for(Map.Entry<String,Integer> mapings : filterMap.get(cmnTranslator.PROFILE_TYPE).entrySet()){
					sqlQuery.append(mapings.getValue());
					sqlQuery.append(",");
				}
				query = sqlQuery.toString().substring(0, sqlQuery.length()-1);
			}
			if(query != null){
				sqlQuery = new StringBuilder(query);
				sqlQuery.append(")");
			}
			if(filterMap != null && filterMap.get(cmnTranslator.TEAMS) != null){
				sqlQuery.append(" AND TM.ID IN (");
				for(Map.Entry<String,Integer> mapings : filterMap.get(cmnTranslator.TEAMS).entrySet()){
					sqlQuery.append(mapings.getValue());
					sqlQuery.append(",");
				}
				query = sqlQuery.toString().substring(0, sqlQuery.length()-1);
			}
			if(query != null){
				sqlQuery = new StringBuilder(query);
				sqlQuery.append(")");
			}
			System.out.println("Sql Query is "+sqlQuery.toString());
			logger.debug("The query used here is : {}",sqlQuery.toString());
			Query queries = b.createSQLQuery(sqlQuery.toString());
			if(filterDto.getAge() != null){
				queries.setParameter("age", filterDto.getAge());
			}
			objects = queries.list();
			tx.commit();
		} catch (Exception e) {
			logger.error("Exception occured in the getPersonalDetailsByFilters method : {}",e);
			tx.rollback();
			e.printStackTrace();
		} finally {
			b.close();
		}
		return objects;
	}

}
