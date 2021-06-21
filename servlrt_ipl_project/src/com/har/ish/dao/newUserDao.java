package com.har.ish.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.har.ish.extractors.ModelExtractor;
import com.har.ish.model.passwordModel;

@SuppressWarnings("deprecation")
public class newUserDao {
	private static final Logger logger = LoggerFactory.getLogger(newUserDao.class);
	
	public void addUser(String User, String Password) {
		logger.info("Inside add user method of the Dao class newUserDao");
		System.out.println("Inside addUser DAO");
		Transaction tx = null;
		try(Session b=hibernateInitiator.creator()){
			tx=b.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query query = b.createSQLQuery("CALL addNewUser(?,?,?)");
			query.setParameter(1, null);
			query.setParameter(2, User);
			query.setParameter(3, Password);
			query.executeUpdate();
			tx.commit();
			logger.info("add user method of newUserDao completed successfully");
		}
		catch(Exception e){
			logger.error("Exception occured in the dao method addUser : {}",e);
			tx.rollback();
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public passwordModel getUser(String User,String Password){
		logger.info("Inside get user method of the dao class newUserDao");
		System.out.println("Inside getUser DAO");
		passwordModel modelPassword = new passwordModel();
		ModelExtractor extractor = new ModelExtractor();
		Transaction tx = null;
		try(Session b=hibernateInitiator.creator()){
			tx=b.beginTransaction();
			Query query = b.createSQLQuery("SELECT * FROM PASSWORD WHERE USERNAME = :username and PASSWORD = aes_encrypt(:password,'key1') and is_active=1");
			query.setParameter("username", User);
			query.setParameter("password", Password);
			Object[] object=(Object[]) query.getSingleResult();
			modelPassword=extractor.passwordExtractor(object,modelPassword);
			Query query1 = b.createSQLQuery("SELECT CAST(aes_decrypt(password,'key1') AS CHAR(10000) CHARACTER SET utf8) FROM PASSWORD WHERE id=:id");
			query1.setParameter("id", modelPassword.getId());
			Object obj = query1.getSingleResult();
			modelPassword.setPassword(obj.toString());
			tx.commit();		}
		catch(Exception e){
			logger.error("Exception occured in the getUser method of the dao class newUserDao : {}",e);
			tx.rollback();
			e.printStackTrace();
		}
		return modelPassword;
	}

}
