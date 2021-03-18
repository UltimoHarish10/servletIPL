<%@page import="com.har.ish.utilities.CommonMethods"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage = "error400.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.har.ish.dto.FilterDto"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Personal Details</title>
<link href="<%=request.getContextPath()%>/CSS/welcome.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/CSS/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/CSS/welcome.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/CSS/Avenir Medium/Avenir Medium.ttf">
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		Object obj = session.getAttribute(CommonMethods.DROPDOWNVALUES);
		FilterDto dropdownValues = new FilterDto();
		if(obj instanceof FilterDto){
			dropdownValues = (FilterDto)obj;
		}
		List<String> positionTitles = dropdownValues.getPositionTitle();
		System.out.println("The position titles inside Jsp are "+ positionTitles.get(0));
		List<String> profileTypes = dropdownValues.getProfileTypes();
		List<String> teams = dropdownValues.getTeams();
		List<String> countries = dropdownValues.getCountries();
		request.setAttribute("positionTitles", positionTitles);
		request.setAttribute("profileTypes", profileTypes);
		request.setAttribute("teams", teams);
		request.setAttribute("countries", countries);
	%>
	<div class=full-body-class id="full-body-class-id">
		<div class=left-side-page id="left-side-class">
			<div class=logo-div>
				<img src="<%=request.getContextPath()%>/Images/LOGO (2).png">
			</div>
			<div class=profile-div>
				<img
					src="<%=request.getContextPath()%>/Images/2x/baseline_person_white_18dp.png"
					style="width: 29px; height: 29px;"> <span class=text-decor>Profiles</span>
			</div>
		</div>
		<div class=right-side-page id="right-side-class">
			<div class=top-right-side-div>
				<button type="button" class="btn btn-primary logout-button-class" onclick="logout()">Logout</button>
			</div>
			<div class=search-bar-div>
				<div class="dropdown All-Drop-Down">
					<div class="dropdown-button">
						<button class="button-classes" id="button-dropdown-Id"
							type="button" onclick="popup(event)">
							<span id="dropdown-button-clicker">Position Title </span><span><img
								src="<%=request.getContextPath()%>/Images/caret-down-solid.svg"
								style="width: 29px; height: 21px; margin-bottom: 5px; margin-left: 6px;">
							</span>
						</button>
						<div class="dropdown-contents" id="drop-down-item-content"
							style="display: none;">
							<c:forEach var="value" items="${positionTitles}">
								<div class="dropdown-elements">
									<input type="checkbox" id="checkerId"> <span
										class="checkmark"></span>
									<c:out value="${value}" />
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="dropdown-button1">
						<button class="button-classes" id="button-dropdown-Id"
							type="button" onclick="popup(event)">
							<span id="dropdown-button-clicker">Profile Type </span><span><img
								src="<%=request.getContextPath()%>/Images/caret-down-solid.svg"
								style="width: 29px; height: 21px; margin-bottom: 5px; margin-left: 6px;">
							</span>
						</button>
						<div class="dropdown-contents" id="drop-down-item-content"
							style="display: none;">
							<c:forEach var="value" items="${profileTypes}">
								<div class="dropdown-elements">
									<input type="checkbox" id="checkerId"> <span
										class="checkmark"></span>
									<c:out value="${value}" />
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="dropdown-button2">
						<button class="button-classes" id="button-dropdown-Id"
							type="button" onclick="popup(event)">
							<span id="dropdown-button-clicker">Teams </span><span><img
								src="<%=request.getContextPath()%>/Images/caret-down-solid.svg"
								style="width: 29px; height: 21px; margin-bottom: 5px; margin-left: 6px;">
							</span>
						</button>
						<div class="dropdown-contents" id="drop-down-item-content"
							style="display: none;">
							<c:forEach var="value" items="${teams}">
								<div class="dropdown-elements">
									<input type="checkbox" id="checkerId"> <span
										class="checkmark"></span>
									<c:out value="${value}" />
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="dropdown-button3">
						<button class="button-classes" id="button-dropdown-Id"
							type="button" onclick="popup(event)">
							<span id="dropdown-button-clicker">Countries </span><span><img
								src="<%=request.getContextPath()%>/Images/caret-down-solid.svg"
								style="width: 29px; height: 21px; margin-bottom: 5px; margin-left: 6px;">
							</span>
						</button>
						<div class="dropdown-contents" id="drop-down-item-content"
							style="display: none;">
							<c:forEach var="value" items="${countries}">
								<div class="dropdown-elements">
									<input type="checkbox" id="checkerId"> <span
										class="checkmark"></span>
									<c:out value="${value}" />
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class = "search-and-export">
					<div class = "Search-button-class" Id="button-Id-Search">
						<button type="button" class="btn btn-primary button-Search">Search</button>
					</div>
					<div class = "Export-button-class" Id="button-Id-Export">
						<button type="button" class="btn btn-primary button-Export" onclick="exportDetails()">Export</button>
					</div> 
				</div>
			</div>
			<div class=listing-parent-div>
				<div class=header-div>
					<div class = "Name-div">
						Name
					</div>
					<div class = "Team-div">
						Team
					</div>
					<div class = "Position-Title-div">
						Position Title
					</div>
					<div class = "Type-div">
						Type
					</div>
					<div class = "DOB-div">
						Date Of Birth
					</div>
				</div>
				<div class="personal-details-container">
					<div class= "Loader-personal-details">
						<img
								src="<%=request.getContextPath()%>/Images/loader1.gif">
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/welcome.js"></script>
</body>
</html>