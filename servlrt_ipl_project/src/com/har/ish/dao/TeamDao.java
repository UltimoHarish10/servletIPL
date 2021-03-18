package com.har.ish.dao;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.har.ish.model.TeamModel;
import com.har.ish.utilities.CommonMethods;

public class TeamDao {
	
	private static final Logger logger = LoggerFactory.getLogger(TeamDao.class);
	
	public TeamModel getTeamDetailsByTeamFullname(String teamName){
		hibernateInitiator hibe = new hibernateInitiator();
		Session session = null;
		TeamModel team = new TeamModel();
		Transaction tx = null;
		try{
			session = hibe.creator();
			tx = session.beginTransaction();
			StringBuilder build = new StringBuilder("from team t Where lower(t.teamFullName)=lower(:teamName) AND t.isActive=:active");
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(build.toString());
			query.setParameter("teamName", teamName);
			query.setParameter("active", true);
			team = (TeamModel)query.uniqueResult();
			tx.commit();
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return team;
	}
	
	public TeamModel getTeamDetailsByTeamShortname(String teamName){
		hibernateInitiator hibe = new hibernateInitiator();
		Session session = null;
		TeamModel team = new TeamModel();
		Transaction tx = null;
		try{
			session = hibe.creator();
			tx = session.beginTransaction();
			StringBuilder build = new StringBuilder("from Team t Where lower(t.teamShortName)=lower(:teamName) AND t.isActive=:active");
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(build.toString());
			query.setParameter("teamName", teamName);
			query.setParameter("active",true);
			team = (TeamModel)query.uniqueResult();
			tx.commit();
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return team;
	}
	public TeamModel getTeamDetailsById(Integer Id){
		hibernateInitiator hibe = new hibernateInitiator();
		Session session = null;
		TeamModel team = new TeamModel();
		Transaction tx = null;
		try{
			session = hibe.creator();
			tx = session.beginTransaction();
			StringBuilder build = new StringBuilder("from Team t Where t.id=:id AND t.isActive=:active");
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(build.toString());
			query.setParameter("id", Id);
			query.setParameter("active", true);
			team = (TeamModel)query.uniqueResult();
			tx.commit();
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return team;
	}
	
public Map<String,Integer> getTeamIdForMap(List<String> teamValues){
		
		Session session = null;
		hibernateInitiator hibeInitiator = new hibernateInitiator();
		Transaction tx = null;
		CommonMethods method = new CommonMethods();
		try{
			session = hibeInitiator.creator();
			tx=session.beginTransaction();
			StringBuilder queryString = new StringBuilder("SELECT ID,TEAM_SHORT_NAME FROM TEAM WHERE LOWER(TEAM_SHORT_NAME) IN(");
			for(String s : teamValues){
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
public List<String> getAllActiveTeams(){
	hibernateInitiator in = new hibernateInitiator();
	Session session = null;
	Transaction tx = null;
	try{
		session = in.creator();
		tx = session.beginTransaction();
		StringBuilder fullQuery = new StringBuilder("SELECT TEAM_SHORT_NAME FROM TEAM WHERE IS_ACTIVE=1");
		Query stringQuery = session.createSQLQuery(fullQuery.toString());
		List<String> objs = stringQuery.list();
		tx.commit();
		return objs;
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
