package com.har.ish.servlets;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.har.ish.dto.AllPersonalDetailsDto;
import com.har.ish.dto.FilterDto;
import com.har.ish.service.PersonDetailsService;
import com.har.ish.utilities.CommonMethods;

import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class playerExportClass extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(playerExportClass.class);
	
	@SuppressWarnings("resource")
	public void doPost(HttpServletRequest request,HttpServletResponse response){
		logger.info("Inside playerExportClass servlet is started");
		PersonDetailsService personalServices = new PersonDetailsService();
		try{
			FilterDto filterDto = new Gson().fromJson(request.getReader(),FilterDto.class);
			List<AllPersonalDetailsDto> personDetails = new ArrayList<>();
			if(filterDto != null){
				personDetails = personalServices.getPersonDetailsByFilters(filterDto,personDetails);
			}
			else{
				AllPersonalDetailsDto personDto = new AllPersonalDetailsDto();
				personDetails = personalServices.getAllPersonalDetails(personDto,null,null);
			}
			XSSFWorkbook newWorkBook = new XSSFWorkbook();
			if(personDetails != null && !personDetails.isEmpty()){
				newWorkBook = personalServices.exportWorkSheet(newWorkBook,personDetails);
			}
			
			if(newWorkBook != null){
				response.setContentType(CommonMethods.APPLICATIONXLSX);
				response.setHeader(CommonMethods.CONTENTDISPOSITION, CommonMethods.PLAYERATTACHMENT);
				response.setHeader(CommonMethods.FILENAME, CommonMethods.PLAYERSLIST);
			   ServletOutputStream out = response.getOutputStream();
			   newWorkBook.write(out);
			   newWorkBook.close();
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
