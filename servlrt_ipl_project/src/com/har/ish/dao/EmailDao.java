package com.har.ish.dao;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.har.ish.extractors.ModelExtractor;
import com.har.ish.model.EmailModel;

public class EmailDao {
	
	@SuppressWarnings("deprecation")
	public EmailModel getEmailDetailsById(Integer Id){
		Transaction tx = null;
		EmailModel emails = new EmailModel();
		try(Session session = hibernateInitiator.creator()){
			tx = session.beginTransaction();
			StringBuilder query = new StringBuilder("FROM email e WHERE e.Id=:Id AND e.isActive=:active");
			@SuppressWarnings("rawtypes")
			Query hqlQuery = session.createQuery(query.toString());
			hqlQuery.setParameter("Id", Id);
			hqlQuery.setParameter("active", true);
			emails = (EmailModel)hqlQuery.uniqueResult();
			tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return emails;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<EmailModel> getEmailDetailsByPersonDetailsId(Integer Id){
		Transaction tx = null;
		List<EmailModel> emails = new ArrayList<>();
		try(Session session = hibernateInitiator.creator()){
			tx = session.beginTransaction();
			StringBuilder query = new StringBuilder("FROM email e WHERE e.personalDetails.Id=:Id AND e.isActive=:active");
			@SuppressWarnings("rawtypes")
			Query hqlQuery = session.createQuery(query.toString());
			hqlQuery.setParameter("Id", Id);
			hqlQuery.setParameter("active", true);
			emails = hqlQuery.list();
			tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return emails;
	}
	
	
	public void saveEmailModel(List<EmailModel> emails){
		Transaction tx = null;
		try(Session session = hibernateInitiator.creator()){
			tx = session.beginTransaction();
			for(EmailModel email : emails){
				Date date = new Date();
				if(email.getCreatedOn()== null){
					email.setCreatedOn(new Timestamp(date.getTime()));
				}
				email.setUpdatedOn(new Timestamp(date.getTime()));
				session.saveOrUpdate(email);
				/*session.flush();*/
			}
			tx.commit();
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
	}
	
}
