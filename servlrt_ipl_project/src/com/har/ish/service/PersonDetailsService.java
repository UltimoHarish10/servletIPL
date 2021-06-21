package com.har.ish.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.har.ish.dao.AddressDao;
import com.har.ish.dao.EmailDao;
import com.har.ish.dao.PersonalDetailsDao;
import com.har.ish.dao.PhoneDao;
import com.har.ish.dao.PositionTitleDao;
import com.har.ish.dao.StateDao;
import com.har.ish.dao.TeamDao;
import com.har.ish.dto.AllPersonalDetailsDto;
import com.har.ish.dto.FilterDto;
import com.har.ish.model.AddressModel;
import com.har.ish.model.EmailModel;
import com.har.ish.model.PersonalDetailsModel;
import com.har.ish.model.PhoneModel;
import com.har.ish.translators.AddressTranslators;
import com.har.ish.translators.AllPersonalDtoTranslator;
import com.har.ish.translators.CommonTranslator;
import com.har.ish.translators.EmailTranslator;
import com.har.ish.translators.PhoneTranslator;
import com.har.ish.utilities.CommonMethods;

public class PersonDetailsService {

	public static final Logger logger = LoggerFactory.getLogger(PersonDetailsService.class);

	public String getPersonalDetails(Integer personId) {
		logger.info("getPersonalDetails method is started");
		String jsonString = new String();
		PersonalDetailsDao personDetailsDao = new PersonalDetailsDao();
		AllPersonalDetailsDto pers = new AllPersonalDetailsDto();
		AllPersonalDtoTranslator translator = new AllPersonalDtoTranslator();
		ObjectMapper mapper = new ObjectMapper();
		try {
			PersonalDetailsModel per = personDetailsDao.getPersonalDetails(personId);
			pers = translator.translateToPersonalDto(per, pers);
			jsonString = mapper.writeValueAsString(pers);
			System.out.println(jsonString);
		} catch (Exception e) {
			logger.error("Exception occured in the getPersonalDetails method : {}", e);
			e.printStackTrace();
		}
		logger.info("getPersonalDetails method is completed");
		return jsonString;
	}

	public AllPersonalDetailsDto getPersonalDet(Integer personId) {
		logger.info("getPersonalDet method is started");
		PersonalDetailsDao personDetailsDao = new PersonalDetailsDao();
		AllPersonalDetailsDto pers = new AllPersonalDetailsDto();
		AllPersonalDtoTranslator translator = new AllPersonalDtoTranslator();
		try {
			PersonalDetailsModel per = personDetailsDao.getPersonalDetails(personId);
			pers = translator.translateToPersonalDto(per, pers);
		} catch (Exception e) {
			logger.error("Exception occured in the getPersonalDet method : {}", e);
			e.printStackTrace();
		}
		logger.info("getPersonalDet method is completed");
		return pers;
	}

	public List<AllPersonalDetailsDto> getAllPersonalDetails(AllPersonalDetailsDto personDto,Integer currentPage,Integer fromPage) {
		logger.info("getAllPersonalDetails method is started");
		String jsonString = new String();
		PersonalDetailsDao personDetailsDao = new PersonalDetailsDao();
		List<AllPersonalDetailsDto> pers = new ArrayList<>();
		AllPersonalDtoTranslator translator = new AllPersonalDtoTranslator();
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Object[]> objects = personDetailsDao.getAllPersonalDetails(personDto,currentPage,fromPage);
			pers = translator.translateToAllPersonalDto(objects, pers);
			/*
			 * jsonString = mapper.writeValueAsString(pers);
			 * System.out.println(jsonString);
			 */
		} catch (Exception e) {
			logger.error("exception occured in the getAllPersonalDetails method : {}", e);
			e.printStackTrace();
		}
		logger.info("getAllPersonalDetails method is completed");
		return pers;
	}

	public void saveAllPersonalDetails(AllPersonalDetailsDto personalDet) {
		logger.info("saveAllPersonalDetails method is started");
		try {
			System.out.println("Inside save method of personal details service");
			AllPersonalDetailsDto person = new AllPersonalDetailsDto();
			boolean check = false;

			if (personalDet != null && personalDet.getId() != null) {
				person = getPersonalDet(personalDet.getId());
				check = checkDifferencePersonDetails(personalDet, person);
			} else if (personalDet != null && personalDet.getId() == null) {
				check = true;
			} else {
				check = false;
			}

			if (check) {
				List<PersonalDetailsModel> personModels = new ArrayList<>();
				AllPersonalDtoTranslator translator = new AllPersonalDtoTranslator();
				AddressTranslators addresstranslator = new AddressTranslators();
				PhoneTranslator phoneTranslator = new PhoneTranslator();
				EmailTranslator emailTranslator = new EmailTranslator();
				PersonalDetailsModel personsModel = translator.personalDetailsMethod(personalDet);
				List<AddressModel> addresses = new ArrayList<>();
				List<PhoneModel> phones = new ArrayList<>();
				List<EmailModel> emails = new ArrayList<>();
				addresses = addresstranslator.translateToAddressModel(personalDet, personsModel);
				phones = phoneTranslator.PhoneModelsTranslator(personsModel, personalDet);
				emails = emailTranslator.emailModelTranslator(personsModel, personalDet);
				personModels.add(personsModel);
				PhoneDao phonedao = new PhoneDao();
				EmailDao emaildao = new EmailDao();
				AddressDao addressdao = new AddressDao();
				PersonalDetailsDao persondao = new PersonalDetailsDao();
				persondao.savePersonDetails(personModels);
				phonedao.savePhoneModel(phones);
				emaildao.saveEmailModel(emails);
				addressdao.saveAddresses(addresses);
				System.out.println("saved the personaldetails in Db");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<AllPersonalDetailsDto> getPersonDetailsByFilters(FilterDto filterDto,
			List<AllPersonalDetailsDto> personDetails,Integer currentPage,Integer fromPage) {
		logger.info("Inside the getPersonDetailsByFilters method");
		PersonalDetailsDao personDetailsDao = new PersonalDetailsDao();
		AllPersonalDtoTranslator translator = new AllPersonalDtoTranslator();
		CommonMethods commonMethod = new CommonMethods();
		CommonTranslator commonTranslator = new CommonTranslator();
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = new String();
		try {
			filterDto = commonMethod.lowerCaseConversion(filterDto);
			Map<String, Map<String, Integer>> maps = commonTranslator.translateToFilterMap(filterDto);
			List<Object[]> personObjects = personDetailsDao.getPersonalDetailsByFilters(maps, filterDto, currentPage, fromPage);
			personDetails = translator.translateToAllPersonalDto(personObjects, personDetails);
			/*
			 * jsonString = mapper.writeValueAsString(personDetails);
			 * System.out.println(jsonString);
			 */
		} catch (Exception e) {
			logger.error("Exception occured in the getPersonDetailsByFilters method : {}", e);
			e.printStackTrace();
		}
		logger.info("getPersonDetailsByFilters method is Ended");
		return personDetails;
	}

	public boolean checkDifferencePersonDetails(AllPersonalDetailsDto personalDet, AllPersonalDetailsDto person) {
		boolean check = false;
		Integer checker = 0;
		if (!(personalDet.getFirstName().equalsIgnoreCase(person.getFirstName()))) {
			checker += 1;
		}
		if (!(personalDet.getLastName().equalsIgnoreCase(person.getLastName()))) {
			checker += 1;
		}
		if (!(personalDet.getAge().equals(person.getAge()))) {
			checker += 1;
		}
		if (!(personalDet.getPositionTitle().equalsIgnoreCase(person.getPositionTitle()))) {
			checker += 1;
		}
		if (!(personalDet.getProfileType().equalsIgnoreCase(person.getProfileType()))) {
			checker += 1;
		}
		if (!(personalDet.getTeamShortName().equalsIgnoreCase(person.getTeamShortName()))) {
			checker += 1;
		}
		if (!(personalDet.getTeamFullName().equalsIgnoreCase(person.getTeamFullName()))) {
			checker += 1;
		}
		if (!(personalDet.getGender().equalsIgnoreCase(person.getGender()))) {
			checker += 1;
		}
		if(!(personalDet.getDateOfBirth().equalsIgnoreCase(person.getDateOfBirth()))){
			 {
					checker += 1;
			}
		}
		if (checker > 0) {
			check = true;
		}
		return check;
	}

	public XSSFWorkbook exportWorkSheet(XSSFWorkbook workBook, List<AllPersonalDetailsDto> personalDetails) {
		logger.info("exportWorkSheet method is started");
		CommonMethods commonMethod = new CommonMethods();
		XSSFSheet sheet1 = workBook.createSheet(CommonMethods.PLAYEREXPORT);
		try {
			if (personalDetails != null && !personalDetails.isEmpty()) {
				int rowNum = 1;
				int currRow = 2;
				int colNum = 0;
				Row row0 = sheet1.createRow(0);
				Cell cell0 = row0.createCell(0);
				XSSFFont font0 = commonMethod.populateHeaderNameFonts(sheet1);
				CellStyle cellStyle0 = commonMethod.populateHeaderNameCellStyle(sheet1);
				cellStyle0.setFont(font0);
				cell0.setCellStyle(cellStyle0);
				sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
				cell0.setCellValue(CommonMethods.PERSONALDETAILSEXPORT);
				XSSFFont font = commonMethod.populateHeaderFonts(sheet1);
				CellStyle cellStyle = commonMethod.populateHeaderCellStyle(sheet1);
				cellStyle.setFont(font);
				sheet1.setDefaultRowHeight((short) CommonMethods.SEVENHUNDRED);
				sheet1.setColumnWidth(colNum, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
				Row row = sheet1.createRow(rowNum);
				Cell cell = row.createCell(colNum);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(CommonMethods.FIRSTNAME);
				colNum++;
				sheet1.setColumnWidth(colNum, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
				cell = row.createCell(colNum);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(CommonMethods.LASTNAME);
				colNum++;
				sheet1.setColumnWidth(colNum, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
				cell = row.createCell(colNum);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(CommonMethods.FULLNAME);
				colNum++;
				sheet1.setColumnWidth(colNum, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
				cell = row.createCell(colNum);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(CommonMethods.GENDER);
				colNum++;
				sheet1.setColumnWidth(colNum, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
				cell = row.createCell(colNum);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(CommonMethods.DATEOFBIRTH);
				colNum++;
				sheet1.setColumnWidth(colNum, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
				cell = row.createCell(colNum);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(CommonMethods.TEAMSHORTNAME);
				colNum++;
				sheet1.setColumnWidth(colNum, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
				cell = row.createCell(colNum);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(CommonMethods.TEAMFULLNAME);
				colNum++;
				sheet1.setColumnWidth(colNum, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
				cell = row.createCell(colNum);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(CommonMethods.POSITIONTITLE);
				colNum++;
				sheet1.setColumnWidth(colNum, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
				cell = row.createCell(colNum);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(CommonMethods.PROFILETYPE);
				colNum++;
				sheet1.setColumnWidth(colNum, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
				cell = row.createCell(colNum);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(CommonMethods.PHONENUMBER);
				colNum++;
				sheet1.setColumnWidth(colNum, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
				cell = row.createCell(colNum);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(CommonMethods.EMAILADDRESS);
				for (AllPersonalDetailsDto person : personalDetails) {

					int phnNumberSize = 0;
					int emailSize = 0;
					int totalSize = 0;
					if (person.getPhoneNumbers() != null && !person.getPhoneNumbers().isEmpty()) {
						phnNumberSize = person.getPhoneNumbers().size();
					}
					if (person.getEmails() != null && !person.getEmails().isEmpty()) {
						emailSize = person.getEmails().size();
					}
					if (phnNumberSize != 0 && emailSize != 0) {
						if (phnNumberSize > emailSize) {
							totalSize = phnNumberSize;
						} else if (phnNumberSize < emailSize) {
							totalSize = emailSize;
						} else {
							totalSize = emailSize;
						}
					} else {
						totalSize = 0;
					}
					if (totalSize != 0) {
						Row rows;
						Cell cells;
						CellStyle cellStyles;
						XSSFFont fonts;
						for (int i = 0; i < totalSize; i++) {
							int currCol = 0;
							rows = sheet1.createRow(currRow);
							System.out.println("The value in currRow is " + currRow);
							cellStyles = commonMethod.populateContentCellStyle(sheet1);
							fonts = commonMethod.populateContentFonts(sheet1);
							cellStyles.setFont(fonts);
							sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
							cells = rows.createCell(currCol);
							cells.setCellStyle(cellStyles);
							if (person.getFirstName() != null) {
								cells.setCellValue(person.getFirstName());
							} else {
								cells.setCellValue(CommonMethods.HYPHEN);
							}
							currCol++;
							sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
							cells = rows.createCell(currCol);
							cells.setCellStyle(cellStyles);
							if (person.getLastName() != null) {
								cells.setCellValue(person.getLastName());
							} else {
								cells.setCellValue(CommonMethods.HYPHEN);
							}
							currCol++;
							sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
							cells = rows.createCell(currCol);
							cells.setCellStyle(cellStyles);
							if (person.getFirstName() != null && person.getLastName() != null) {
								String fullName = person.getFirstName() + " " + person.getLastName();
								cells.setCellValue(fullName);
							} else {
								cells.setCellValue(CommonMethods.HYPHEN);
							}
							currCol++;
							sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
							cells = rows.createCell(currCol);
							cells.setCellStyle(cellStyles);
							if (person.getGender() != null) {
								cells.setCellValue(person.getGender());
							} else {
								cells.setCellValue(CommonMethods.HYPHEN);
							}
							currCol++;
							sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
							cells = rows.createCell(currCol);
							cells.setCellStyle(cellStyles);
							if (person.getDateOfBirth() != null) {
								cells.setCellValue(person.getDateOfBirth());
							} else {
								cells.setCellValue(CommonMethods.HYPHEN);
							}
							currCol++;
							sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
							cells = rows.createCell(currCol);
							cells.setCellStyle(cellStyles);
							if (person.getTeamShortName() != null) {
								cells.setCellValue(person.getTeamShortName());
							} else {
								cells.setCellValue(CommonMethods.HYPHEN);
							}
							currCol++;
							sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
							cells = rows.createCell(currCol);
							cells.setCellStyle(cellStyles);
							if (person.getTeamFullName() != null) {
								cells.setCellValue(person.getTeamFullName());
							} else {
								cells.setCellValue(CommonMethods.HYPHEN);
							}
							currCol++;
							sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
							cells = rows.createCell(currCol);
							cells.setCellStyle(cellStyles);
							if (person.getPositionTitle() != null) {
								cells.setCellValue(person.getPositionTitle());
							} else {
								cells.setCellValue(CommonMethods.HYPHEN);
							}
							currCol++;
							sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
							cells = rows.createCell(currCol);
							cells.setCellStyle(cellStyles);
							if (person.getProfileType() != null) {
								cells.setCellValue(person.getProfileType());
							} else {
								cells.setCellValue(CommonMethods.HYPHEN);
							}
							currCol++;
							sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
							cells = rows.createCell(currCol);
							cells.setCellStyle(cellStyles);
							if (person.getPhoneNumbers().size() - 1 <= i) {
								cells.setCellValue(person.getPhoneNumbers().get(i));
							} else {
								cells.setCellValue(CommonMethods.HYPHEN);
							}
							currCol++;
							sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
							cells = rows.createCell(currCol);
							cells.setCellStyle(cellStyles);
							if (person.getEmails().size() - 1 <= i) {
								cells.setCellValue(person.getEmails().get(i));
							} else {
								cells.setCellValue(CommonMethods.HYPHEN);
							}
							currRow++;
						}
					} else {
						Row rows;
						Cell cells;
						CellStyle cellStyles;
						XSSFFont fonts;
						int currCol = 0;
						rows = sheet1.createRow(currRow);
						System.out.println("The value in currRow is " + currRow);
						currRow++;
						cellStyles = commonMethod.populateHeaderCellStyle(sheet1);
						fonts = commonMethod.populateContentFonts(sheet1);
						cellStyles.setFont(fonts);
						sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
						cells = rows.createCell(currCol);
						cells.setCellStyle(cellStyles);
						if (person.getFirstName() != null) {
							cells.setCellValue(person.getFirstName());
						} else {
							cells.setCellValue(CommonMethods.HYPHEN);
						}
						currCol++;
						sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
						cells = rows.createCell(currCol);
						cells.setCellStyle(cellStyles);
						if (person.getLastName() != null) {
							cells.setCellValue(person.getLastName());
						} else {
							cells.setCellValue(CommonMethods.HYPHEN);
						}
						currCol++;
						sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
						cells = rows.createCell(currCol);
						cells.setCellStyle(cellStyles);
						if (person.getFirstName() != null && person.getLastName() != null) {
							String fullName = person.getFirstName() + " " + person.getLastName();
							cells.setCellValue(fullName);
						} else {
							cells.setCellValue(CommonMethods.HYPHEN);
						}
						currCol++;
						sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
						cells = rows.createCell(currCol);
						cells.setCellStyle(cellStyles);
						if (person.getGender() != null) {
							cells.setCellValue(person.getGender());
						} else {
							cells.setCellValue(CommonMethods.HYPHEN);
						}
						currCol++;
						sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
						cells = rows.createCell(currCol);
						cells.setCellStyle(cellStyles);
						if (person.getDateOfBirth() != null) {
							cells.setCellValue(person.getDateOfBirth());
						} else {
							cells.setCellValue(CommonMethods.HYPHEN);
						}
						currCol++;
						sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
						cells = rows.createCell(currCol);
						cells.setCellStyle(cellStyles);
						if (person.getTeamShortName() != null) {
							cells.setCellValue(person.getTeamShortName());
						} else {
							cells.setCellValue(CommonMethods.HYPHEN);
						}
						currCol++;
						sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
						cells = rows.createCell(currCol);
						cells.setCellStyle(cellStyles);
						if (person.getTeamFullName() != null) {
							cells.setCellValue(person.getTeamFullName());
						} else {
							cells.setCellValue(CommonMethods.HYPHEN);
						}
						currCol++;
						sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
						cells = rows.createCell(currCol);
						cells.setCellStyle(cellStyles);
						if (person.getPositionTitle() != null) {
							cells.setCellValue(person.getPositionTitle());
						} else {
							cells.setCellValue(CommonMethods.HYPHEN);
						}
						currCol++;
						sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
						cells = rows.createCell(currCol);
						cells.setCellStyle(cellStyles);
						if (person.getProfileType() != null) {
							cells.setCellValue(person.getProfileType());
						} else {
							cells.setCellValue(CommonMethods.HYPHEN);
						}
						currCol++;
						sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
						cells = rows.createCell(currCol);
						cells.setCellStyle(cellStyles);
						cells.setCellValue(CommonMethods.HYPHEN);
						currCol++;
						sheet1.setColumnWidth(currCol, CommonMethods.SEVENTHOUSANDFIVEHUNDRED);
						cells = rows.createCell(currCol);
						cells.setCellStyle(cellStyles);
						cells.setCellValue(CommonMethods.HYPHEN);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception occured in the exportWorkSheet method : {}", e);
			e.printStackTrace();
		}

		System.out.println("The SheetName is " + workBook.getSheetName(0));
		logger.info("exportWorkSheet method is completed");
		return workBook;
	}

	public FilterDto populateFilterDtoForPerson(FilterDto filterDto) {
		List<String> positionTitleValues = new ArrayList<>();
		List<String> profileTypeValues = new ArrayList<>();
		List<String> teams = new ArrayList<>();
		List<String> countries = new ArrayList<>();
		try {
			PositionTitleDao positionDao = new PositionTitleDao();
			List<Object[]> positionObjects = positionDao.getAllPositionTitleValues();
			if (positionObjects != null && !positionObjects.isEmpty()) {
				for (Object[] obj : positionObjects) {
					if (obj[0] != null) {
						positionTitleValues.add(obj[0].toString());
					}
					if (obj[1] != null) {
						int count = 0;
						for (String values : profileTypeValues) {
							if (values.equalsIgnoreCase(obj[1].toString())) {
								count++;
							}
						}
						if (count == CommonMethods.ZERO) {
							profileTypeValues.add(obj[1].toString());
						}
					}

				}
				filterDto.setPositionTitle(positionTitleValues);
				filterDto.setProfileTypes(profileTypeValues);

			}

			TeamDao teamDao = new TeamDao();
			List<String> teamObjects = teamDao.getAllActiveTeams();
			if (teamObjects != null && !teamObjects.isEmpty()) {
				filterDto.setTeams(teamObjects);
			}
			StateDao stateDao = new StateDao();
			List<String> countryObjects = stateDao.getAllCountries();
			if (countryObjects != null && !countryObjects.isEmpty()) {
				filterDto.setCountries(countryObjects);
			}
			return filterDto;
		} catch (Exception e) {
			logger.error("Exception Occured While Fetching Details For Filter Dto : {}", e);
		}
		return null;
	}
	
	public boolean isLastPage(Integer currentPage){
		PersonalDetailsDao personDao = new PersonalDetailsDao();
		boolean isLastPage = false;
		try{
			isLastPage = personDao.lastPageCheck(currentPage);
		}
		catch(Exception e){
			logger.error("Exception occured in checking isLastPage : {}",e);
			e.printStackTrace();
		}
		return isLastPage;
	}

}
