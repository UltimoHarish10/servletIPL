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
		Session session = null;
		Transaction tx = null;
		StateModel state = new StateModel();
		try{
			hibernateInitiator hibe = new hibernateInitiator();
			session = hibe.creator();
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
		finally{
			session.close();
		}
		return state;
	}
	
	public StateModel getStateByStateName(String StateName){
		Session session = null;
		Transaction tx = null;
		StateModel state = new StateModel();
		try{
			hibernateInitiator hibe = new hibernateInitiator();
			session = hibe.creator();
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
		finally{
			session.close();
		}
		return state;
	}
	
public Map<String,Integer> getCountryIdForMap(List<String> countryValues){
		
		Session session = null;
		hibernateInitiator hibeInitiator = new hibernateInitiator();
		Transaction tx = null;
		CommonMethods method = new CommonMethods();
		try{
			session = hibeInitiator.creator();
			tx=session.beginTransaction();
			StringBuilder queryString = new StringBuilder("SELECT ID,COUNTRY_NAME FROM COUNTRY WHERE LOWER(COUNTRY_NAME) IN(");
			for(String s : countryValues){
				queryString.append("'");
				queryString.append(s);
				queryString.append("',");
			}
			String query = queryString.toString().substring(0, queryString.toString().length()-1);
			query = query+") AND IS_ACTIVE=1";
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
		finally{
			session.close();
		}
		return null;
		
	}

}
