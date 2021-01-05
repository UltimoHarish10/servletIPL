package com.har.ish.translators;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.har.ish.dao.AddressDao;
import com.har.ish.dao.StateDao;
import com.har.ish.dto.AddressDto;
import com.har.ish.dto.AllPersonalDetailsDto;
import com.har.ish.model.AddressModel;
import com.har.ish.model.PersonalDetailsModel;

public class AddressTranslators {
	public static final Logger logger = LoggerFactory.getLogger(AddressTranslators.class);
	
	public List<AddressDto> translateToAddress(PersonalDetailsModel per,List<AddressDto> addresses){
		logger.info("translateToAddress method is started");
		try{
			List<AddressModel> Addresses = per.getAddresses(); 
			for(AddressModel address : Addresses){
				AddressDto addressDto = new AddressDto();
				addressDto.setDistrictName(address.getDistrictName());
				addressDto.setStreetName(address.getStreetVal());
				addressDto.setStateName(address.getStates().getStateName());
				addressDto.setPincode(address.getPincode());
				addresses.add(addressDto);
			}
		}
		catch(Exception e){
			logger.error("Exception occured in the translateToAddress method : {}",e);
			e.printStackTrace();
		}
		logger.info("translateToAddress method is completed");
		return addresses;
	}
	
	public List<AddressModel> translateToAddressModel(AllPersonalDetailsDto personalDet,PersonalDetailsModel persons){
		List<AddressModel> addresss = new ArrayList<>();
		try{
		if(personalDet.getAddresses()!=null){
			StateDao stats = new StateDao();
			for(AddressDto address : personalDet.getAddresses()){
				AddressModel addresses = new AddressModel();
				addresses.setStreetVal((address.getStreetName()));
				addresses.setDistrictName(address.getDistrictName());
				addresses.setStates(stats.getStateByStateName(address.getStateName()));
				addresses.setPincode(address.getPincode());
				addresses.setIsActive(true);
				addresses.setPersonalDetails(persons);
				addresss.add(addresses);
			}
			
		}
		AddressDao addressDao = new AddressDao();
		List<AddressModel> previousAddress = null;
		if(persons.getId() != null){
			 previousAddress = addressDao.getAddressDetailsByPersonDetailsId(persons.getId());
		}
		if(previousAddress != null){
			for(AddressModel addressModel : previousAddress){
				addressModel.setIsActive(false);
				addresss.add(addressModel);
			}
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return addresss;
	}

}
