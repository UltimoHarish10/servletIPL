package com.har.ish.dao;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.har.ish.model.PositionTitleModel;
import com.har.ish.utilities.CommonMethods;

@SuppressWarnings("deprecation")
public class PositionTitleDao {
	
	public PositionTitleModel getPositionTitleByName(String positionTitile){
		Session session = null;
		Transaction tx = null;
		PositionTitleModel position = new PositionTitleModel();
		try{
			hibernateInitiator hibe = new hibernateInitiator();
			session = hibe.creator();
			tx = session.beginTransaction();
			StringBuilder query = new StringBuilder("From position_title pos WHERE lower(pos.positionTitleName) = lower(:positiontitle) And isActive=:active");
			@SuppressWarnings("rawtypes")
			Query hqlQuery = session.createQuery(query.toString());
			hqlQuery.setParameter("positiontitle", positionTitile);
			hqlQuery.setParameter("active", true);
			position = (PositionTitleModel)hqlQuery.uniqueResult();
			tx.commit();
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return position;
	}
	
	public PositionTitleModel getPositionTitleById(Integer positionTitile){
		Session session = null;
		Transaction tx = null;
		PositionTitleModel position = new PositionTitleModel();
		try{
			hibernateInitiator hibe = new hibernateInitiator();
			session = hibe.creator();
			tx = session.beginTransaction();
			StringBuilder query = new StringBuilder("From position_title pos WHERE pos. = :positiontitle And isActive=:active");
			@SuppressWarnings("rawtypes")
			Query hqlQuery = session.createQuery(query.toString());
			hqlQuery.setParameter("positiontitle", positionTitile);
			hqlQuery.setParameter("active", true);
			position = (PositionTitleModel)hqlQuery.uniqueResult();
			tx.commit();
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return position;
	}
	
	public Map<String,Integer> getprofileTypesIdForMap(List<String> profileTypeValues){
		
		Session session = null;
		hibernateInitiator hibeInitiator = new hibernateInitiator();
		Transaction tx = null;
		CommonMethods method = new CommonMethods();
		try{
			session = hibeInitiator.creator();
			tx=session.beginTransaction();
			StringBuilder queryString = new StringBuilder("SELECT ID,PROFILE_TYPE_NAME FROM PROFILE_TYPE WHERE LOWER(PROFILE_TYPE_NAME) IN(");
			for(String s : profileTypeValues){
				queryString.append("'");
				queryString.append(s);
				queryString.append("',");
			}
			String query = queryString.toString().substring(0, queryString.toString().length()-1);
			query = query+") AND IS_ACTIVE=1";
			System.out.println("The query for profile type is "+ query.toString());
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
	
	
public Map<String,Integer> getpositionTitlesIdForMap(List<String> positionTitleValues){
		
		Session session = null;
		hibernateInitiator hibeInitiator = new hibernateInitiator();
		Transaction tx = null;
		CommonMethods method = new CommonMethods();
		try{
			session = hibeInitiator.creator();
			tx=session.beginTransaction();
			StringBuilder queryString = new StringBuilder("SELECT ID,POSITION_TITLE_NAME FROM POSITION_TITLE WHERE LOWER(POSITION_TITLE_NAME) IN(");
			for(String s : positionTitleValues){
				queryString.append("'");
				queryString.append(s);
				queryString.append("',");
			}
			String query = queryString.toString().substring(0, queryString.toString().length()-1);
			query = query+") AND IS_ACTIVE=1";
			System.out.println("The query for postion title is "+ query.toString());
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
