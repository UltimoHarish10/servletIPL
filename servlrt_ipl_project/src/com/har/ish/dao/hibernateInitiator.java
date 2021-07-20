package com.har.ish.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.har.ish.model.AddressModel;
import com.har.ish.model.CountryModel;
import com.har.ish.model.EmailModel;
import com.har.ish.model.GenderModel;
import com.har.ish.model.PersonalDetailsModel;
import com.har.ish.model.PhoneModel;
import com.har.ish.model.PositionTitleModel;
import com.har.ish.model.StateModel;
import com.har.ish.model.TeamModel;
import com.har.ish.model.passwordModel;
import com.har.ish.model.profileTypeModel;

public class hibernateInitiator {
	
	public static Session session = null;
	public static SessionFactory sf = null;
	
	public static Session creator(){
		Configuration con=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(passwordModel.class)
				.addAnnotatedClass(AddressModel.class)
				.addAnnotatedClass(CountryModel.class)
				.addAnnotatedClass(EmailModel.class)
				.addAnnotatedClass(GenderModel.class)
				.addAnnotatedClass(PersonalDetailsModel.class)
				.addAnnotatedClass(PhoneModel.class)
				.addAnnotatedClass(PositionTitleModel.class)
				.addAnnotatedClass(profileTypeModel.class)
				.addAnnotatedClass(StateModel.class)
				.addAnnotatedClass(TeamModel.class);
		sf = con.buildSessionFactory();
		if(session == null || !session.isOpen())
		session = sf.openSession();
		return session;		
	}
}
