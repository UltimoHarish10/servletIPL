package com.har.ish.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.har.ish.model.GenderModel;

public class GenderDao {
	
	@SuppressWarnings("deprecation")
	public GenderModel getGenderDetailByGenderName(String GenderName){
		GenderModel gen = new GenderModel();
		hibernateInitiator in = new hibernateInitiator();
		Session session = null;
		Transaction tx = null;
		try{
			session = in.creator();
			tx = session.beginTransaction();
			StringBuilder query = new StringBuilder();
			query.append("FROM gender G WHERE lower(G.genderName) = lower(:genName) AND isActive=:active");
			@SuppressWarnings("rawtypes")
			Query hqlQuery = session.createQuery(query.toString());
			hqlQuery.setString("genName", GenderName);
			hqlQuery.setParameter("active", true);
			gen = (GenderModel)hqlQuery.uniqueResult();
			tx.commit();
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return gen;
	}
	
	public GenderModel getGenderDetailsById(Integer Id){
		GenderModel gen = new GenderModel();
		hibernateInitiator in = new hibernateInitiator();
		Session session = null;
		Transaction tx = null;
		try{
			session = in.creator();
			tx = session.beginTransaction();
			StringBuilder query = new StringBuilder();
			query.append("FROM gender G WHERE G.Id = :id AND isActive=:active");
			@SuppressWarnings("rawtypes")
			Query hqlQuery = session.createQuery(query.toString());
			hqlQuery.setParameter("id", Id);
			hqlQuery.setParameter("active", true);
			gen = (GenderModel)hqlQuery.uniqueResult();
			tx.commit();
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return gen;
	}

}
