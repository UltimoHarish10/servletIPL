package com.har.ish.extractors;

import java.sql.Timestamp;
import java.util.List;

import com.har.ish.model.AddressModel;
import com.har.ish.model.EmailModel;
import com.har.ish.model.PersonalDetailsModel;
import com.har.ish.model.PhoneModel;
import com.har.ish.model.StateModel;
import com.har.ish.model.passwordModel;

public class ModelExtractor {

	public passwordModel passwordExtractor(Object[] model, passwordModel pass) {
		if (model != null) {
			try {
				pass.setId((int) model[0]);
				pass.setUserName((String) model[1]);
				pass.setIs_active((Boolean) model[3]);
				pass.setCreated_on( model[4].toString());
				pass.setUpdated_on( model[5].toString());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return pass;
	}
	
	public List<AddressModel> addressExtractor(List<Object[]> objs,List<AddressModel> address){
		
		try{
			for(Object[] objects : objs){
				AddressModel addresses = new AddressModel();
				addresses.setId((Integer)objects[0]);
				addresses.setStreetVal(objects[1].toString());
				addresses.setDistrictName(objects[2].toString());
				addresses.setStates((StateModel)objects[3]);
				addresses.setPincode((Integer)objects[4]);
				addresses.setPersonalDetails((PersonalDetailsModel)objects[5]);
				addresses.setIsActive((boolean)objects[6]);
				addresses.setCreatedOn((Timestamp)objects[7]);
				addresses.setUpdatedOn((Timestamp)objects[8]);
				address.add(addresses);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return address;
	}
	
	public List<PhoneModel> phoneExtractor(List<Object[]>objs,List<PhoneModel> phones){
		try{
			
			for(Object[] objects :  objs){
				PhoneModel phoneModel = new PhoneModel();
				phoneModel.setId((Integer)objects[0]);
				phoneModel.setPhoneNumber((Long)objects[1]);
				phoneModel.setPersonalDetails((PersonalDetailsModel)objects[2]);
				phoneModel.setIsActive((boolean)objects[3]);
				phoneModel.setCreatedOn((Timestamp)objects[4]);
				phoneModel.setUpdatedOn((Timestamp)objects[5]);
				phones.add(phoneModel);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return phones;
	}
	
	public List<EmailModel> emailExtractor(List<Object[]>objs,List<EmailModel> emails){
		try{
			
			for(Object[] objects :  objs){
				EmailModel emailModel = new EmailModel();
				emailModel.setId((Integer)objects[0]);
				emailModel.setEmailId(objects[1].toString());
				emailModel.setPersonalDetails((PersonalDetailsModel)objects[2]);
				emailModel.setIsActive((boolean)objects[3]);
				emailModel.setCreatedOn((Timestamp)objects[4]);
				emailModel.setUpdatedOn((Timestamp)objects[5]);
				emails.add(emailModel);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return emails;
	}
	
}
