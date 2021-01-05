package com.har.ish.translators;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.har.ish.model.PhoneModel;
import com.har.ish.dao.PhoneDao;
import com.har.ish.dto.AllPersonalDetailsDto;
import com.har.ish.model.PersonalDetailsModel;

public class PhoneTranslator {
	private static final Logger logger = LoggerFactory.getLogger(PhoneTranslator.class);

	public List<Long> phoneNumberTranslator(PersonalDetailsModel per, List<Long> phoneNumbers) {
		logger.info("PhoneTranslator method is started");
		try {
			List<PhoneModel> phones = per.getPhones();
			for (PhoneModel phone : phones) {
				phoneNumbers.add(phone.getPhoneNumber());
			}
		} catch (Exception e) {
			logger.error("Exception occured in the PhoneTranslator method : {}",e);
			e.printStackTrace();
		}
		logger.info("PhoneTranslator method is completed");
		return phoneNumbers;
	}
	
	public List<PhoneModel> PhoneModelsTranslator(PersonalDetailsModel person, AllPersonalDetailsDto personDetDto) {
		List<PhoneModel> phoneModels = new ArrayList<>();
		List<PhoneModel> previousPhones = null;
		try {
			for (Long Phones : personDetDto.getPhoneNumbers()) {
				PhoneModel phones = new PhoneModel();
				phones.setPhoneNumber(Phones);
				phones.setIsActive(true);
				phones.setPersonalDetails(person);
				phoneModels.add(phones);
			}
			PhoneDao phoneDao = new PhoneDao();
			if(person.getId() != null){
				previousPhones = phoneDao.getPhoneModelByPersonDetailsId(person.getId());
			}
			if(previousPhones != null){
				for(PhoneModel phoneModel : previousPhones){
					phoneModel.setIsActive(false);
					phoneModels.add(phoneModel);
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return phoneModels;
	}

}
