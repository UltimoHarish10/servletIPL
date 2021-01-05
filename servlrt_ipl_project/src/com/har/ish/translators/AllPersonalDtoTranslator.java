package com.har.ish.translators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.har.ish.dao.GenderDao;
import com.har.ish.dao.PersonalDetailsDao;
import com.har.ish.dao.PositionTitleDao;
import com.har.ish.dao.StateDao;
import com.har.ish.dao.TeamDao;
import com.har.ish.dto.AddressDto;
import com.har.ish.dto.AllPersonalDetailsDto;
import com.har.ish.model.AddressModel;
import com.har.ish.model.PersonalDetailsModel;
import com.har.ish.translators.PhoneTranslator;
import com.har.ish.utilities.CommonMethods;


public class AllPersonalDtoTranslator {
	private static final Logger logger = LoggerFactory.getLogger(AllPersonalDtoTranslator.class);

	public AllPersonalDetailsDto translateToPersonalDto(PersonalDetailsModel per, AllPersonalDetailsDto pers) {
		logger.info("translateToPersonalDto method is started here");
		PhoneTranslator phones = new PhoneTranslator();
		EmailTranslator Email = new EmailTranslator();
		AddressTranslators address = new AddressTranslators();
		try {
			pers.setId(per.getId());
			pers.setFirstName(per.getFirstName());
			pers.setLastName(per.getLastName());
			pers.setGender(per.getGender().getGenderName());
			pers.setAge(per.getAge());
			pers.setTeamShortName(per.getTeams().getTeamShortName());
			pers.setTeamFullName(per.getTeams().getTeamFullName());
			pers.setPositionTitle(per.getPostitle().getPositionTitleName());
			pers.setProfileType(per.getPostitle().getProfType().getProfileTypeName());
			List<Long> phoneNumbers = new ArrayList<>();
			phoneNumbers = phones.phoneNumberTranslator(per, phoneNumbers);
			pers.setPhoneNumbers(phoneNumbers);
			List<String> Emails = new ArrayList<>();
			Emails = Email.translateToEmails(per, Emails);
			pers.setEmails(Emails);
			List<AddressDto> addresses = new ArrayList<>();
			addresses = address.translateToAddress(per, addresses);
			pers.setAddresses(addresses);
		} catch (Exception e) {
			logger.error("Exception occured in the translateToPersonalDto method : {}",e);
			e.printStackTrace();
		}
		return pers;
	}

	public List<AllPersonalDetailsDto> translateToAllPersonalDto(List<Object[]> per, List<AllPersonalDetailsDto> pers) {
		logger.info("translateToAllPersonalDto is started");
		CommonMethods common = new CommonMethods();
		try {
			Map<Integer, List<AllPersonalDetailsDto>> personalDetails = new HashMap<>();
			for (Object[] obj : per) {
				AllPersonalDetailsDto personaldto = new AllPersonalDetailsDto();
				personaldto.setId((Integer) obj[0]);
				personaldto.setFirstName(obj[1].toString());
				personaldto.setLastName(obj[2].toString());
				personaldto.setGender(obj[3].toString());
				personaldto.setAge((Integer) obj[4]);
				List<Long> longs = new ArrayList<>();
				String phone = obj[5].toString();
				Long phn = Long.parseLong(phone);
				longs.add(phn);
				personaldto.setPhoneNumbers(longs);
				List<String> emails = new ArrayList<>();
				String Emails = obj[6].toString();
				emails.add(Emails);
				personaldto.setEmails(emails);
				personaldto.setTeamShortName(obj[7].toString());
				personaldto.setTeamFullName(obj[8].toString());
				personaldto.setPositionTitle(obj[9].toString());
				personaldto.setProfileType(obj[10].toString());
				List<AddressDto> addressDto = new ArrayList<>();
				AddressDto addresses = new AddressDto();
				addresses.setStreetName(obj[11].toString());
				addresses.setDistrictName(obj[12].toString());
				addresses.setStateName(obj[13].toString());
				addresses.setPincode(Integer.parseInt(obj[14].toString()));
				addressDto.add(addresses);
				personaldto.setAddresses(addressDto);
				if (personalDetails.containsKey(personaldto.getId())) {
					List<AllPersonalDetailsDto> personalDtos = personalDetails.get(personaldto.getId());
					if (personaldto.getAddresses() != null) {
						if (personaldto.getAddresses().get(0) != null) {
							personalDtos.get(0).getAddresses().add(personaldto.getAddresses().get(0));
						}
					}
					if (personaldto.getEmails() != null) {
						if (personaldto.getEmails().get(0) != null) {
							personalDtos.get(0).getEmails().add(personaldto.getEmails().get(0));
						}
					}
					if (personaldto.getPhoneNumbers() != null) {
						if (personaldto.getPhoneNumbers().get(0) != null) {
							personalDtos.get(0).getPhoneNumbers().add(personaldto.getPhoneNumbers().get(0));
						}
					}
					personalDetails.put(personaldto.getId(), personalDtos);
				} else {
					List<AllPersonalDetailsDto> allPersonalDto = new ArrayList<>();
					allPersonalDto.add(personaldto);
					personalDetails.put(personaldto.getId(), allPersonalDto);
				}
			}
			for (Map.Entry<Integer, List<AllPersonalDetailsDto>> entry : personalDetails.entrySet()) {
				
				List<String> EmailIds = common.differentEmails(entry.getValue().get(0).getEmails());
				List<Long> phoneNum = common.differentPhones(entry.getValue().get(0).getPhoneNumbers());
				List<AddressDto> personAddress = common.differentAddresses(entry.getValue().get(0).getAddresses());
				entry.getValue().get(0).setPhoneNumbers(phoneNum);
				entry.getValue().get(0).setEmails(EmailIds);
				entry.getValue().get(0).setAddresses(personAddress);
				pers.add(entry.getValue().get(0));
			}

		} catch (Exception e) {
			logger.error("Exception occured in the translateToAllPersonalDto method :{}",e);
			e.printStackTrace();
		}
		return pers;
	}
	
	public PersonalDetailsModel personalDetailsMethod(AllPersonalDetailsDto personalDet) {
		PersonalDetailsDao personalDao = new PersonalDetailsDao();
		PersonalDetailsModel personModel = new PersonalDetailsModel();
		try {
			if (personalDet.getId() != null) {
				personModel = personalDao.getPersonalDetails(personalDet.getId());
			}
			if (personalDet.getFirstName() != null) {
				personModel.setFirstName(personalDet.getFirstName());
			}
			if (personalDet.getLastName() != null) {
				personModel.setLastName(personalDet.getLastName());
			}
			if (personalDet.getAge() != null) {
				personModel.setAge(personalDet.getAge());
			}
			if(personalDet.getGender()!=null){
				GenderDao gender = new GenderDao();
				personModel.setGender(gender.getGenderDetailByGenderName(personalDet.getGender()));
			}
			if(personalDet.getTeamFullName()!=null){
				TeamDao team = new TeamDao();
				personModel.setTeams(team.getTeamDetailsByTeamFullname(personalDet.getTeamFullName()));
			}
			if(personalDet.getPositionTitle()!=null && personalDet.getProfileType()!=null){
				PositionTitleDao positionDao = new PositionTitleDao();
				personModel.setPostitle(positionDao.getPositionTitleByName(personalDet.getPositionTitle()));
			}
			if(personModel.getIsActive()==null){
				personModel.setIsActive(true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personModel;

	}
}
