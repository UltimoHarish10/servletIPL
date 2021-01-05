package com.har.ish.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.har.ish.extractors.ModelExtractor;
import com.har.ish.model.AddressModel;

@SuppressWarnings("deprecation")
public class AddressDao {
	
	public AddressModel getAddressDetailsById(Integer Id){
		Session session = null;
		Transaction tx =  null;
		AddressModel address = new AddressModel();
		try{
			hibernateInitiator hibe = new hibernateInitiator();
			session = hibe.creator();
			tx = session.beginTransaction();
			StringBuilder query = new StringBuilder("FROM address WHERE ID=:id AND isActive=:active");
			@SuppressWarnings("rawtypes")
			Query hqlQuery = session.createQuery(query.toString());
			hqlQuery.setParameter("id", Id);
			hqlQuery.setParameter("active", true);
			address = (AddressModel)hqlQuery.getSingleResult();
			tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return address;
	}
	
	public List<AddressModel> getAddressDetailsByPersonDetailsId(Integer personId){
		Session session = null;
		Transaction tx =  null;
		List<AddressModel> address = new ArrayList<>();
		try{
			hibernateInitiator hibe = new hibernateInitiator();
			session = hibe.creator();
			tx = session.beginTransaction();
			StringBuilder query = new StringBuilder("FROM address WHERE personalDetails.Id=:id AND isActive=:active");
			@SuppressWarnings("rawtypes")
			Query hqlQuery = session.createQuery(query.toString());
			hqlQuery.setParameter("id", personId);
			hqlQuery.setParameter("active", true);
			address = hqlQuery.list();
			tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return address;
	}
	
	public void saveAddresses(List<AddressModel> addresses){
		Session session = null;
		Transaction tx = null;
		try{
			hibernateInitiator in = new hibernateInitiator();
			session = in.creator();
			tx = session.beginTransaction();
			for(AddressModel address: addresses){
				Date date = new Date();
				if(address.getCreatedOn() == null){
					address.setCreatedOn(new Timestamp(date.getTime()));
				}
				address.setUpdatedOn(new Timestamp(date.getTime()));
				session.saveOrUpdate(address);
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