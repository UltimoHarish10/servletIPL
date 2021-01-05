package com.har.ish.translators;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.har.ish.dao.EmailDao;
import com.har.ish.dto.AllPersonalDetailsDto;
import com.har.ish.model.EmailModel;
import com.har.ish.model.PersonalDetailsModel;

public class EmailTranslator {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailTranslator.class);
	
	public List<String> translateToEmails(PersonalDetailsModel per, List<String> Emails){
		logger.info("Inside translateToEmails Method is started");
		List<EmailModel> emails= per.getEmails();
		try{
			for(EmailModel email : emails){
				Emails.add(email.getEmailId());
			}
		}
		catch(Exception e){
			logger.error("Exception occured in the translateToEmails method : {}",e);
			e.printStackTrace();
		}
		logger.info("translateToEmails is completed");
		return Emails;
	}
	
	public List<EmailModel> emailModelTranslator(PersonalDetailsModel person,AllPersonalDetailsDto personDet){
		List<EmailModel> emails = new ArrayList<>();
		List<EmailModel> previousEmails = null;
		try{
			for(String emailss : personDet.getEmails()){
				EmailModel email = new EmailModel();
				email.setEmailId(emailss);
				email.setPersonalDetails(person);
				email.setIsActive(true);
				emails.add(email);
			}
			
			if(person.getId() != null){
				EmailDao emailDao = new EmailDao();
				previousEmails = emailDao.getEmailDetailsByPersonDetailsId(person.getId());
			}
			if(previousEmails != null){
				for(EmailModel emailModels : previousEmails){
					emailModels.setIsActive(false);
					emails.add(emailModels);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return emails;
	}

}
