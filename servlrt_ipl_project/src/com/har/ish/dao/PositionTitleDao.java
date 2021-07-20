package com.har.ish.dao;



import java.util.ArrayList;
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
		Transaction tx = null;
		PositionTitleModel position = new PositionTitleModel();
		try(Session session = hibernateInitiator.creator()){
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
		return position;
	}
	
	public PositionTitleModel getPositionTitleById(Integer positionTitile){
		Transaction tx = null;
		PositionTitleModel position = new PositionTitleModel();
		try(Session session = hibernateInitiator.creator()){
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
		return position;
	}
	
	public Map<String,Integer> getprofileTypesIdForMap(List<String> profileTypeValues){
		
		Transaction tx = null;
		String query = new String();
		CommonMethods method = new CommonMethods();
		try(Session session = hibernateInitiator.creator()){
			tx=session.beginTransaction();
			StringBuilder queryString = new StringBuilder("SELECT ID,PROFILE_TYPE_NAME FROM PROFILE_TYPE WHERE LOWER(PROFILE_TYPE_NAME) IN(");
			for(String s : profileTypeValues){
				queryString.append("'");
				queryString.append(s);
				queryString.append("',");
			}
			if(profileTypeValues != null && !profileTypeValues.isEmpty()){
				 query = queryString.toString().substring(0, queryString.toString().length()-1);
			}
			if(query.length() ==  CommonMethods.ZERO){
				query = queryString.toString()+") AND IS_ACTIVE=1";
			}
			else{
				query = query+") AND IS_ACTIVE=1";
			}
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
		return null;
		
	}
	
	
public Map<String,Integer> getpositionTitlesIdForMap(List<String> positionTitleValues){
		
		Transaction tx = null;
		String query = new String();
		CommonMethods method = new CommonMethods();
		try(Session session = hibernateInitiator.creator()){
			tx=session.beginTransaction();
			StringBuilder queryString = new StringBuilder("SELECT ID,POSITION_TITLE_NAME FROM POSITION_TITLE WHERE LOWER(POSITION_TITLE_NAME) IN(");
			for(String s : positionTitleValues){
				queryString.append("'");
				queryString.append(s);
				queryString.append("',");
			}
			if(positionTitleValues != null && !positionTitleValues.isEmpty()){
				 query = queryString.toString().substring(0, queryString.toString().length()-1);
			}
			if(query.length() == CommonMethods.ZERO){
				query = queryString.toString()+") AND IS_ACTIVE=1";
			}
			else{
				query = query+") AND IS_ACTIVE=1";
			}
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
		return null;
		
	}
public List<Object[]> getAllPositionTitleValues(){
	Transaction tx = null;
	try (Session session = hibernateInitiator.creator()){
		tx = session.beginTransaction();
		StringBuilder query = new StringBuilder("SELECT POS.POSITION_TITLE_NAME,PT.PROFILE_TYPE_NAME FROM PROFILE_TYPE PT");
		query.append(" INNER JOIN POSITION_TITLE POS ON ");
		query.append("POS.PROFILE_TYPE_ID = PT.ID WHERE POS.IS_ACTIVE=1 AND PT.IS_ACTIVE=1");
		Query stringQuery = session.createSQLQuery(query.toString());
		List<Object[]> objs = stringQuery.list();
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
