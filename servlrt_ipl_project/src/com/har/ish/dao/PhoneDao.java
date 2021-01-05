package com.har.ish.dao;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.har.ish.extractors.ModelExtractor;
import com.har.ish.model.PhoneModel;

public class PhoneDao {
	
	@SuppressWarnings("deprecation")
	public PhoneModel getPhoneModelById(Integer Id){
		Session session = null;
		Transaction tx = null;
		PhoneModel models = new PhoneModel();
		try{
			hibernateInitiator hibe = new hibernateInitiator();
			session = hibe.creator();
			tx = session.beginTransaction();
			StringBuilder query = new StringBuilder("From phone p WHERE p.Id=:Id AND p.isActive =:active");
			@SuppressWarnings("rawtypes")
			Query hqlQuery = session.createQuery(query.toString());
			hqlQuery.setParameter("Id", Id);
			hqlQuery.setParameter("active", true);
			models = (PhoneModel)hqlQuery.uniqueResult();
			tx.commit();
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return models;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<PhoneModel> getPhoneModelByPersonDetailsId(Integer Id){
		Session session = null;
		Transaction tx = null;
		List<PhoneModel> models = new ArrayList<>();
		try{
			hibernateInitiator hibe = new hibernateInitiator();
			session = hibe.creator();
			tx = session.beginTransaction();
			StringBuilder query = new StringBuilder("From phone p WHERE p.personalDetails.Id=:Id AND p.isActive =:active");
			@SuppressWarnings("rawtypes")
			Query hqlQuery = session.createQuery(query.toString());
			hqlQuery.setParameter("Id", Id);
			hqlQuery.setParameter("active", true);
			models =hqlQuery.list();
			tx.commit();
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return models;
	}
	
	public void savePhoneModel(List<PhoneModel> phones){
		
		System.out.println("Inside Save methods of Phone Model");
		Session session = null;
		Transaction tx = null;
		try{
			hibernateInitiator in = new hibernateInitiator();
			session = in.creator();
			tx = session.beginTransaction();
			for(PhoneModel phone : phones){
				Date date = new Date();
				if(phone.getCreatedOn()== null){
					phone.setCreatedOn(new Timestamp(date.getTime()));
				}
				phone.setUpdatedOn(new Timestamp(date.getTime()));
				session.saveOrUpdate(phone);
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

}
