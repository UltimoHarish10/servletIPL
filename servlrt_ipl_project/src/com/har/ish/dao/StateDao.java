package com.har.ish.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.har.ish.model.StateModel;
import com.har.ish.utilities.CommonMethods;

@SuppressWarnings("deprecation")
public class StateDao {
	
	public StateModel getStateById(Integer Id){
		Transaction tx = null;
		StateModel state = new StateModel();
		try(Session session = hibernateInitiator.creator()){
			tx = session.beginTransaction();
			StringBuilder query = new StringBuilder("FROM state WHERE id=:Id AND isActive=:active");
			@SuppressWarnings("rawtypes")
			Query hqlQuery = session.createQuery(query.toString());
			hqlQuery.setParameter("Id", Id);
			hqlQuery.setParameter("active", true);
			state = (StateModel)hqlQuery.uniqueResult();
			tx.commit();
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		return state;
	}
	
	public StateModel getStateByStateName(String StateName){
		Transaction tx = null;
		StateModel state = new StateModel();
		try(Session session = hibernateInitiator.creator()){
			tx = session.beginTransaction();
			StringBuilder query = new StringBuilder("FROM state s WHERE lower(s.stateName)=lower(:stateName) AND s.isActive=:active");
			@SuppressWarnings("rawtypes")
			Query hqlQuery = session.createQuery(query.toString());
			hqlQuery.setParameter("stateName", StateName);
			hqlQuery.setParameter("active", true);
			state = (StateModel)hqlQuery.uniqueResult();
			tx.commit();
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		return state;
	}
	
public Map<String,Integer> getCountryIdForMap(List<String> countryValues){
		
		Transaction tx = null;
		String query = new String();
		CommonMethods method = new CommonMethods();
		try(Session session = hibernateInitiator.creator()){
			tx=session.beginTransaction();
			StringBuilder queryString = new StringBuilder("SELECT ID,COUNTRY_NAME FROM COUNTRY WHERE LOWER(COUNTRY_NAME) IN(");
			for(String s : countryValues){
				queryString.append("'");
				queryString.append(s);
				queryString.append("',");
			}
			if(countryValues != null && !countryValues.isEmpty()){
				query = queryString.toString().substring(0, queryString.toString().length()-1);
			}
			if(query.length() == CommonMethods.ZERO){
				query = queryString.toString()+") AND IS_ACTIVE=1";
			}
			else{
				query = query+") AND IS_ACTIVE=1";
			}
			System.out.println("The query for team is "+ query.toString());
			Query hqlQuery = session.createSQLQuery(query);
			List<Object[]> objects = hqlQuery.list();
			tx.commit();
			return method.translateToMap(objects); 
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		return null;
	}
	public List<String> getAllCountries(){
		Transaction tx = null;
		try(Session session = hibernateInitiator.creator()){
			tx = session.beginTransaction();
			StringBuilder query = new StringBuilder("SELECT COUNTRY_NAME FROM COUNTRY WHERE IS_ACTIVE=1");
			Query stringQuery = session.createSQLQuery(query.toString());
			List<String> objs = stringQuery.list();
			tx.commit();
			return objs;
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		return null;
	}

}
