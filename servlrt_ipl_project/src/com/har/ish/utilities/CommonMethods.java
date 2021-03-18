package com.har.ish.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.har.ish.dto.AddressDto;
import com.har.ish.dto.FilterDto;

public class CommonMethods {
	
	public static final Logger logger = LoggerFactory.getLogger(CommonMethods.class);
	
	public static final String PLAYEREXPORT = "Player Export";
	public static final String PERSONALDETAILSEXPORT = "PERSONAL DETAILS EXPORT";
	public static final String FIRSTNAME = "FIRST NAME";
	public static final String LASTNAME = "LAST NAME";
	public static final String FULLNAME = "FULL NAME";
	public static final String HYPHEN = "-";
	public static final String GENDER = "GENDER";
	public static final String DATEOFBIRTH = "DATE OF BIRTH";
	public static final String TEAMSHORTNAME = "TEAM SHORT NAME";
	public static final String TEAMFULLNAME = "TEAM FULL NAME";
	public static final String POSITIONTITLE = "POSITION TITLE";
	public static final String PROFILETYPE = "PROFILE TYPE";
	public static final String PHONENUMBER = "PHONE NUMBER";
	public static final String EMAILADDRESS = "EMAIL ADDRESS";
	public static final String APPLICATIONXLSX = "application/xlsx";
	public static final String CONTENTDISPOSITION = "Content-Disposition";
	public static final String PLAYERATTACHMENT = "attachment; filename=PlayersList.xlsx";
	public static final String TIMESNEWROMAN = "Times New Roman";
	public static final String USERNAME = "UserName";
	public static final int SEVENHUNDRED = 700;
	public static final int SEVENTHOUSANDFIVEHUNDRED = 7500;
	public static final int CONTENTFONTSIZE = 12;
	public static final int HEADERNAMEFONTSIZE = 20;
	public static final int HEADERFONTSIZE = 15;
	public static final int THREEHUNDRED = 300;
	public static final int TWOHUNDRED = 200;
	public static final int HUNDRED = 100;
	public static final boolean TRUE = true;
	public static final int FOURHUNDRED = 400;
	public static final int FIVEHUNDRED = 500;
	public static final int FOURHUNDREDANDSEVENTY = 470;
	public static final int THREETHOUSAND = 3000;
	public static final int ZERO = 0;
	public static final int SEVEN = 7;
	public static final int ONE = 1;
	public static final String SLASH = "/";
	public static final String USER = "User";
	public static final String ISAUTHENTICATED = "isAuthenticated";
	public static final String PASSWORD = "Password";
	public static final String PERSONALDETAILS = "Personal Details";
	public static final String DROPDOWNVALUES = "DropDownValues";
	public static final String FILENAME = "fileName";
	public static final String PLAYERSLIST = "PlayersList.xlsx";
	public static final String PERSONALDETAILSFE = "Personal-Details";
	public static final String CURRENTPAGE = "currentPage";
	public static final String FROMPAGE = "fromPage";
	public static final Integer ZEROINTEGER = 0;
	
	public List<String> differentEmails(List<String> entry){
		List<String> entries=null;
		try{
			 entries = new ArrayList<String>(new HashSet<>(entry));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return entries;
	}
	
	public List<Long> differentPhones(List<Long> entry){
		List<Long> entries=null;
		try{
			 entries = new ArrayList<Long>(new HashSet<>(entry));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return entries;
	}
	
	public List<AddressDto> differentAddresses(List<AddressDto> entry){
		List<AddressDto> entries=new ArrayList<>();
		try{
			entries.add(entry.get(0));
			 for(int i=1;i<entry.size();i++){
				 if(!checkDifference(entry.get(i-1),entry.get(i))){
					 entries.add(entry.get(i));
				 }
			 }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return entries;
	}
	
	public boolean checkDifference(AddressDto a , AddressDto b){
		boolean check=false;
		int count=0;
		if(a.getStreetName().toLowerCase().equals(b.getStreetName().toLowerCase())){
			count++;
		}
		if(a.getDistrictName().toLowerCase().equals(b.getDistrictName().toLowerCase())){
			count++;
		}
		if(a.getStateName().toLowerCase().equals(b.getStateName().toLowerCase())){
			count++;
		}
		if(a.getPincode().toString().toLowerCase().equals(b.getPincode().toString().toLowerCase())){
			count++;
		}
		if(count==4){
			check=TRUE;
		}
		return check;
	}
	public FilterDto lowerCaseConversion(FilterDto filterDto){
		logger.info("lowerCaseConversion method is started");
		try{
			if(filterDto.getCountries() != null && !filterDto.getCountries().isEmpty()){
				List<String> countries = new ArrayList<>();
				for(String country : filterDto.getCountries()){
					countries.add(country.toLowerCase());
				}
				filterDto.setCountries(countries);
			}
			if(filterDto.getPositionTitle() != null && !filterDto.getPositionTitle().isEmpty()){
				List<String> positionTitles = new ArrayList<>();
				for(String position : filterDto.getPositionTitle()){
					positionTitles.add(position.toLowerCase());
				}
				filterDto.setPositionTitle(positionTitles);
			}
			if(filterDto.getProfileTypes() != null && !filterDto.getProfileTypes().isEmpty()){
				List<String> profileTypes = new ArrayList<>();
				for(String profiles : filterDto.getProfileTypes()){
					profileTypes.add(profiles.toLowerCase());
				}
				filterDto.setProfileTypes(profileTypes);
			}
			if(filterDto.getTeams() != null && !filterDto.getTeams().isEmpty()){
				List<String> teams = new ArrayList<>();
				for(String team : filterDto.getTeams()){
					teams.add(team.toLowerCase());
				}
				filterDto.setTeams(teams);
			}
			
		}
		catch(Exception e){
			logger.error("Exception occured in the lowerCaseConversion method : {}",e);
			e.printStackTrace();
		}
		logger.info("lowerCaseConversion method is completed");
		return filterDto;
	}
	
	public Map<String,Integer> translateToMap(List<Object[]> objs){
		Map<String,Integer> maps = new HashMap<>();
		try{
		if(objs != null && !objs.isEmpty()){
		for(Object[] obj : objs){
			maps.put(String.valueOf(obj[1]),Integer.parseInt(String.valueOf(obj[0])));
			}
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return maps;
	}
	
	public XSSFFont populateHeaderFonts(XSSFSheet sheet) {
		XSSFFont font = sheet.getWorkbook().createFont();
		font.setBold(TRUE);
		font.setFontName(TIMESNEWROMAN);
		font.setFontHeightInPoints((short)HEADERFONTSIZE);
		font.setColor(IndexedColors.WHITE.getIndex());
		return font;
	}
	
	public XSSFFont populateHeaderNameFonts(XSSFSheet sheet) {
		XSSFFont font = sheet.getWorkbook().createFont();
		font.setBold(TRUE);
		font.setFontName(TIMESNEWROMAN);
		font.setFontHeightInPoints((short)HEADERNAMEFONTSIZE);
		font.setColor(IndexedColors.WHITE.getIndex());
		return font;
	}
	
	public CellStyle populateHeaderNameCellStyle(XSSFSheet sheet){
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		HorizontalAlignment align = HorizontalAlignment.CENTER;
		cellStyle.setAlignment(align);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setWrapText(TRUE);
		return cellStyle;
	}

	public CellStyle populateHeaderCellStyle(XSSFSheet sheet){
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		HorizontalAlignment align = HorizontalAlignment.CENTER;
		cellStyle.setAlignment(align);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setFillForegroundColor(IndexedColors.MAROON.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setWrapText(TRUE);
		return cellStyle;
	}
	
	public CellStyle populateContentCellStyle(XSSFSheet sheet){
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		HorizontalAlignment align = HorizontalAlignment.CENTER;
		cellStyle.setAlignment(align);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setWrapText(TRUE);
		return cellStyle;
	}
	
	public XSSFFont populateContentFonts(XSSFSheet sheet){
		XSSFFont font = sheet.getWorkbook().createFont();
		font.setFontName(TIMESNEWROMAN);
		font.setFontHeightInPoints((short)CONTENTFONTSIZE);
		font.setColor(IndexedColors.BLACK.getIndex());
		return font;
	}
	
}
