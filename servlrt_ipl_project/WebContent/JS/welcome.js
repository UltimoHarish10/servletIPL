const positiontitle = "Position Title";
const profiletype = "Profile Type";
const teams = "Teams";
const countries = "Countries";
const dropDownButton = "dropdown-button";
const dropDownButton1 = "dropdown-button1";
const dropDownButton2 = "dropdown-button2";
const dropDownButton3 = "dropdown-button3";
const selected = " Selected";
var pageNumber;
var isFirstPage;
var firstPageStats;
var isLastPage;
var myElement = document.getElementsByClassName("dropdown-contents");
var checkingElement = document.getElementsByClassName("button-classes");

function popup(event) {
	var clickedElement = event.target;
	var clickedElementParentChild = clickedElement.offsetParent.children[1];
	var clickedElementSuperParent = clickedElement.offsetParent.offsetParent;
	var clickedFromButton = false;

	for(var i=0;i<clickedElementSuperParent.children.length;i++){
		if(clickedElementSuperParent.children[i].children[1].style.display === "block"){
			var count = 0;
		for (var j = 0; j < clickedElementSuperParent.children[i].children[1].children.length; j++) {
			if (clickedElementSuperParent.children[i].children[1].children[j].children[0].checked) {
				count++;
			}
		}
		if (count != 0) {
			clickedElementSuperParent.children[i].children[0].children[0].textContent = count
					+ selected;
		} else {
			if (clickedElementSuperParent.children[i].className === dropDownButton) {
				clickedElementSuperParent.children[i].children[0].children[0].textContent = positiontitle;
			} else if (clickedElementSuperParent.children[i].className === dropDownButton1) {
				clickedElementSuperParent.children[i].children[0].children[0].textContent = profiletype;
			} else if (clickedElementSuperParent.children[i].className === dropDownButton2) {
				clickedElementSuperParent.children[i].children[0].children[0].textContent = teams;
			} else if (clickedElementSuperParent.children[i].className === dropDownButton3) {
				clickedElementSuperParent.children[i].children[0].children[0].textContent = countries;
			}
		}
		clickedElementSuperParent.children[i].children[1].style.display = "none";
		clickedFromButton = true;
		}
	}
	if (clickedElementParentChild.style.display === 'none' && !clickedFromButton) {
		clickedElementParentChild.style.display = "block";
	} else {
		var count = 0;
		for (var i = 0; i < clickedElementParentChild.children.length; i++) {
			if (clickedElementParentChild.children[i].children[0].checked) {
				count++;
			}
		}
		if (count != 0) {
			clickedElement.offsetParent.children[0].children[0].textContent = count
					+ selected;
		} else {
			if (clickedElement.offsetParent.className === dropDownButton) {
				clickedElement.offsetParent.children[0].children[0].textContent = positiontitle;
			} else if (clickedElement.offsetParent.className === dropDownButton1) {
				clickedElement.offsetParent.children[0].children[0].textContent = profiletype;
			} else if (clickedElement.offsetParent.className === dropDownButton2) {
				clickedElement.offsetParent.children[0].children[0].textContent = teams;
			} else if (clickedElement.offsetParent.className === dropDownButton3) {
				clickedElement.offsetParent.children[0].children[0].textContent = countries;
			}
		}
		clickedElementParentChild.style.display = "none";
	}
}

document
		.addEventListener(
				"click",
				function(event) {
					var clickedInside = true;
					for (var i = 0; i < checkingElement.length; i++) {
						for (var j = 0; j < myElement.length; j++) {
							clickedInside = (myElement[j]
									.contains(event.target) || checkingElement[i]
									.contains(event.target));
							if (clickedInside) {
								break;
							}
						}
						if(clickedInside){
							break;
						}
					}
					if (!clickedInside) {
						 var element = null;
						var elements = document
								.getElementsByClassName("dropdown-contents");
						for (var i = 0; i < elements.length; i++) {
							if (elements[i].style.display === 'block') {
								 element = elements[i].offsetParent;
								break;
							}
						}
						var count = 0;
						if(typeof element != "undefined" && element != null){
						for (var i = 0; i < element.children[1].children.length; i++) {
							if (element.children[1].children[i].children[0].checked) {
								count++;
							}
						}
						if (count != 0) {
							element.children[0].children[0].textContent = count
									+ " Selected";
						} else {
							if (element.className === dropDownButton) {
								element.children[0].children[0].textContent = positiontitle;
							} else if (element.className === dropDownButton1) {
								element.children[0].children[0].textContent = profiletype;
							} else if (element.className === dropDownButton2) {
								element.children[0].children[0].textContent = teams;
							} else if (element.className === dropDownButton3) {
								element.children[0].children[0].textContent = countries;
							}
						}
						element.children[1].style.display = "none";
					}
					}
				});


function exportDetails(){
	var exportObject = new Object();
	var positionTitleArr = [];
	var profileTypeArr = [];
	var teamsArr = [];
	var nationArr =[];
	var parentElement = document.getElementsByClassName("dropdown-contents");
	for(var i=0;i<parentElement.length;i++){
		var childElement = parentElement[i].children.length;
		for(var j=0;j<childElement;j++){
			if(parentElement[i].children[j].children[0].checked){
				if(i===0){
					positionTitleArr.push(parentElement[i].children[j].textContent.trim());
				}
				else if(i===1){
					profileTypeArr.push(parentElement[i].children[j].textContent.trim());
				}
				else if(i===2){
					teamsArr.push(parentElement[i].children[j].textContent.trim());
				}
				else if(i===3){
					nationArr.push(parentElement[i].children[j].textContent.trim());
				}
			}
		}
	}
	exportObject.positionTitle = positionTitleArr;
	exportObject.profileTypes = profileTypeArr;
	exportObject.teams = teamsArr;
	exportObject.countries = nationArr;
	console.log(exportObject);
	console.log(positionTitleArr);
	console.log(profileTypeArr);
	console.log(teamsArr);
	console.log(nationArr);
	var req = new XMLHttpRequest();
	 req.open("POST", '/servlrt_ipl_project/getExportPlayers', true);
	 req.setRequestHeader("Content-type", "application/json");
	 req.responseType = "blob";
	 req.onreadystatechange = function(){
		if(this.readyState==4 && this.status==200){
         var blob = req.response;
         var fileName = req.getResponseHeader("fileName"); //if you have the fileName header available
         var link=document.createElement('a');
         link.href=window.URL.createObjectURL(blob);
         link.download=fileName;
         link.click();
	 }
	 else if(this.readyState==4 && this.status!=200){
		 window.location.href = "http://localhost:8080/servlrt_ipl_project/error";
	 }
	};
     req.send(JSON.stringify(exportObject));
}


function logout(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET",'/servlrt_ipl_project/logout', true);
	xhr.onreadystatechange = function(){
		if(this.readyState==4 && this.status==200){
			window.location.href = "http://localhost:8080/servlrt_ipl_project/login";
		}
		else if(this.readyState==4 && this.status!=200){
			window.location.href = "http://localhost:8080/servlrt_ipl_project/error";
		}
	}
	xhr.send();
}

window.onload = function(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET","/servlrt_ipl_project/persons",true);
	xhr.setRequestHeader("currentPage","1");
	xhr.setRequestHeader("fromPage","0")
	isFirstPage = true;
	xhr.onreadystatechange = function(){
		if(this.readyState==4 && this.status==200){
			var LastPageStatus = xhr.getResponseHeader("LastPage");
			if(LastPageStatus === "true"){
				isLastPage = true;
			}
			else{
				isLastPage = false;
			}
			document.getElementsByClassName("Loader-personal-details")[0].style.display = "none";
			var persondetails = JSON.parse(xhr.response);
			console.log(persondetails.size); 
			var x;
			for(x in persondetails){
				var singlePersonDetailsDiv = document.createElement("div");
				singlePersonDetailsDiv.className = "single-person-details";
				singlePersonDetailsDiv.id = persondetails[x].id;
				var divBorderDetailsDiv = document.createElement("div");
				divBorderDetailsDiv.className = "border-details-div";
				singlePersonDetailsDiv.appendChild(divBorderDetailsDiv);
				var divProfilePicDiv = document.createElement("div");
				divProfilePicDiv.className = "profile-pic-div";
				var img = document.createElement("img");
				img.src = "/servlrt_ipl_project/Images/person-24px.svg";
				img.style.width = "31px";
				img.style.height = "33px";
				img.style.left = "8px";
				img.style.top = "6px";
				img.style.position = "relative";
				divProfilePicDiv.appendChild(img);
				singlePersonDetailsDiv.appendChild(divProfilePicDiv);
				var divProfileNameDiv = document.createElement("div");
				divProfileNameDiv.className = "profile-Name-div";
				var divNamesDiv = document.createElement("div");
				divNamesDiv.className = "names-div";
				divNamesDiv.textContent = persondetails[x].firstName.concat(" ",persondetails[x].lastName);
				divProfileNameDiv.appendChild(divNamesDiv);
				singlePersonDetailsDiv.appendChild(divProfileNameDiv);
				var divProfileTeamDiv = document.createElement("div");
				divProfileTeamDiv.className = "profile-team-div";
				var divProfileTeamNameDiv = document.createElement("div");
				divProfileTeamNameDiv.className = "profile-team-name-div";
				divProfileTeamNameDiv.textContent = persondetails[x].teamShortName;
				divProfileTeamDiv.appendChild(divProfileTeamNameDiv);
				singlePersonDetailsDiv.appendChild(divProfileTeamDiv);
				var divProfilePositionDiv = document.createElement("div");
				divProfilePositionDiv.className = "profile-position-div";
				var divPositionTitleNameDiv = document.createElement("div");
				divPositionTitleNameDiv.className = "position-title-name-div";
				divPositionTitleNameDiv.textContent = persondetails[x].positionTitle;
				divProfilePositionDiv.appendChild(divPositionTitleNameDiv);
				singlePersonDetailsDiv.appendChild(divProfilePositionDiv);
				var divProfileTypeDiv = document.createElement("div");
				divProfileTypeDiv.className = "profile-type-div";
				var divProfileTypeNameDiv = document.createElement("div");
				divProfileTypeNameDiv.className = "profile-type-name-div";
				divProfileTypeNameDiv.textContent = persondetails[x].profileType;
				divProfileTypeDiv.appendChild(divProfileTypeNameDiv);
				singlePersonDetailsDiv.appendChild(divProfileTypeDiv);
				var divDateOfBrthdiv = document.createElement("div");
				divDateOfBrthdiv.className = "date-of-brth-div";
				var divDOBDiv2 = document.createElement("div");
				divDOBDiv2.className = "DOB-div-2";
				divDOBDiv2.textContent = persondetails[x].dateOfBirth;
				divDateOfBrthdiv.appendChild(divDOBDiv2);
				singlePersonDetailsDiv.appendChild(divDateOfBrthdiv);
				var personalDetailcontainerDiv = document.getElementsByClassName("personal-details-container")[0];
				singlePersonDetailsDiv.setAttribute("onclick", "getDetails()");
				personalDetailcontainerDiv.appendChild(singlePersonDetailsDiv);
			}
			var divPaginationDiv = document.createElement("div");
			divPaginationDiv.className = "pagination-div";
			var divPreviousDiv = document.createElement("div");
			divPreviousDiv.className = "previous-div";
			var previousButtonDiv = document.createElement("button");
			previousButtonDiv.className = "btn btn-primary";
			previousButtonDiv.className = "button-previous";
			previousButtonDiv.textContent ="<< Previous";
			previousButtonDiv.style.display = "none";
			divPreviousDiv.appendChild(previousButtonDiv);
			divPreviousDiv.setAttribute("onclick", "getPreviousPage()");
			divPaginationDiv.appendChild(divPreviousDiv);
			var divPageNoDiv = document.createElement("div");
			divPageNoDiv.className = "page-No-div";
			divPageNoDiv.textContent = "1";
			divPaginationDiv.appendChild(divPageNoDiv);
			var divNextDiv = document.createElement("div");
			divNextDiv.className = "next-div";
			var nextButtonDiv = document.createElement("button");
			nextButtonDiv.className = "btn btn-primary";
			nextButtonDiv.className = "button-previous";
			nextButtonDiv.textContent ="Next >>";
			divNextDiv.appendChild(nextButtonDiv);
			divNextDiv.setAttribute("onclick", "getNextPage()");
			divPaginationDiv.appendChild(divNextDiv);
			var personalDetailcontainerDiv = document.getElementsByClassName("personal-details-container")[0];
			personalDetailcontainerDiv.appendChild(divPaginationDiv);
		}
		else if(this.readyState==4 && this.status!=200){
			window.location.href = "http://localhost:8080/servlrt_ipl_project/error";
		}
	}
	xhr.send();
}

function getPreviousPage(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET","/servlrt_ipl_project/persons",true);
	var fromPage = document.getElementsByClassName("page-No-div")[0].textContent;
	xhr.setRequestHeader("currentPage",(parseInt(fromPage)-1).toString());
	xhr.setRequestHeader("fromPage",fromPage);
	if((parseInt(fromPage)-1) === 1){
		firstPageStats = true;
	}
	var pageNumber = (parseInt(fromPage)-1).toString();
	var elementsOfParentDiv = document.getElementsByClassName("single-person-details");
	[...elementsOfParentDiv].forEach(function(elem) {
		elem.remove();
	});
	document.getElementsByClassName("pagination-div")[0].remove();
	document.getElementsByClassName("Loader-personal-details")[0].style.display = "block";
	xhr.onreadystatechange = function(){
		if(this.readyState==4 && this.status==200){
			document.getElementsByClassName("Loader-personal-details")[0].style.display = "none";
			var LastPageStatus = xhr.getResponseHeader("LastPage");
			if(LastPageStatus === "true"){
				isLastPage = true;
			}
			else{
				isLastPage = false;
			}
			var persondetails = JSON.parse(xhr.response);
			detailListing(persondetails,firstPageStats,isLastPage,pageNumber);
			console.log(persondetails.size); 
			// var x;
			// for(x in persondetails){
			// 	var singlePersonDetailsDiv = document.createElement("div");
			// 	singlePersonDetailsDiv.className = "single-person-details";
			// 	singlePersonDetailsDiv.id = persondetails[x].id;
			// 	var divBorderDetailsDiv = document.createElement("div");
			// 	divBorderDetailsDiv.className = "border-details-div";
			// 	singlePersonDetailsDiv.appendChild(divBorderDetailsDiv);
			// 	var divProfilePicDiv = document.createElement("div");
			// 	divProfilePicDiv.className = "profile-pic-div";
			// 	var img = document.createElement("img");
			// 	img.src = "/servlrt_ipl_project/Images/person-24px.svg";
			// 	img.style.width = "31px";
			// 	img.style.height = "33px";
			// 	img.style.left = "8px";
			// 	img.style.top = "6px";
			// 	img.style.position = "relative";
			// 	divProfilePicDiv.appendChild(img);
			// 	singlePersonDetailsDiv.appendChild(divProfilePicDiv);
			// 	var divProfileNameDiv = document.createElement("div");
			// 	divProfileNameDiv.className = "profile-Name-div";
			// 	var divNamesDiv = document.createElement("div");
			// 	divNamesDiv.className = "names-div";
			// 	divNamesDiv.textContent = persondetails[x].firstName.concat(" ",persondetails[x].lastName);
			// 	divProfileNameDiv.appendChild(divNamesDiv);
			// 	singlePersonDetailsDiv.appendChild(divProfileNameDiv);
			// 	var divProfileTeamDiv = document.createElement("div");
			// 	divProfileTeamDiv.className = "profile-team-div";
			// 	var divProfileTeamNameDiv = document.createElement("div");
			// 	divProfileTeamNameDiv.className = "profile-team-name-div";
			// 	divProfileTeamNameDiv.textContent = persondetails[x].teamShortName;
			// 	divProfileTeamDiv.appendChild(divProfileTeamNameDiv);
			// 	singlePersonDetailsDiv.appendChild(divProfileTeamDiv);
			// 	var divProfilePositionDiv = document.createElement("div");
			// 	divProfilePositionDiv.className = "profile-position-div";
			// 	var divPositionTitleNameDiv = document.createElement("div");
			// 	divPositionTitleNameDiv.className = "position-title-name-div";
			// 	divPositionTitleNameDiv.textContent = persondetails[x].positionTitle;
			// 	divProfilePositionDiv.appendChild(divPositionTitleNameDiv);
			// 	singlePersonDetailsDiv.appendChild(divProfilePositionDiv);
			// 	var divProfileTypeDiv = document.createElement("div");
			// 	divProfileTypeDiv.className = "profile-type-div";
			// 	var divProfileTypeNameDiv = document.createElement("div");
			// 	divProfileTypeNameDiv.className = "profile-type-name-div";
			// 	divProfileTypeNameDiv.textContent = persondetails[x].profileType;
			// 	divProfileTypeDiv.appendChild(divProfileTypeNameDiv);
			// 	singlePersonDetailsDiv.appendChild(divProfileTypeDiv);
			// 	var divDateOfBrthdiv = document.createElement("div");
			// 	divDateOfBrthdiv.className = "date-of-brth-div";
			// 	var divDOBDiv2 = document.createElement("div");
			// 	divDOBDiv2.className = "DOB-div-2";
			// 	divDOBDiv2.textContent = persondetails[x].dateOfBirth;
			// 	divDateOfBrthdiv.appendChild(divDOBDiv2);
			// 	singlePersonDetailsDiv.appendChild(divDateOfBrthdiv);
			// 	var personalDetailcontainerDiv = document.getElementsByClassName("personal-details-container")[0];
			// 	singlePersonDetailsDiv.setAttribute("onclick", "getDetails()");
			// 	personalDetailcontainerDiv.appendChild(singlePersonDetailsDiv);
			// }
			// var divPaginationDiv = document.createElement("div");
			// divPaginationDiv.className = "pagination-div";
			// var divPreviousDiv = document.createElement("div");
			// divPreviousDiv.className = "previous-div";
			// var previousButtonDiv = document.createElement("button");
			// previousButtonDiv.className = "btn btn-primary";
			// previousButtonDiv.className = "button-previous";
			// previousButtonDiv.textContent ="<< Previous";
			// divPreviousDiv.appendChild(previousButtonDiv);
			// divPreviousDiv.setAttribute("onclick", "getPreviousPage()");
			// divPaginationDiv.appendChild(divPreviousDiv);
			// var divPageNoDiv = document.createElement("div");
			// divPageNoDiv.className = "page-No-div";
			// divPageNoDiv.textContent = (parseInt(fromPage)-1).toString();
			// divPaginationDiv.appendChild(divPageNoDiv);
			// var divNextDiv = document.createElement("div");
			// divNextDiv.className = "next-div";
			// var nextButtonDiv = document.createElement("button");
			// nextButtonDiv.className = "btn btn-primary";
			// nextButtonDiv.className = "button-previous";
			// nextButtonDiv.textContent ="Next >>";
			// divNextDiv.appendChild(nextButtonDiv);
			// divNextDiv.setAttribute("onclick", "getNextPage()");
			// divPaginationDiv.appendChild(divNextDiv);
			// var personalDetailcontainerDiv = document.getElementsByClassName("personal-details-container")[0];
			// personalDetailcontainerDiv.appendChild(divPaginationDiv);

		}
		else if(this.readyState==4 && this.status!=200){
			window.location.href = "http://localhost:8080/servlrt_ipl_project/error";
		}
	}
	xhr.send();
}

function getNextPage(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET","/servlrt_ipl_project/persons",true);
	var fromPage = document.getElementsByClassName("page-No-div")[0].textContent;
	xhr.setRequestHeader("currentPage",(parseInt(fromPage)+1).toString());
	xhr.setRequestHeader("fromPage",fromPage)
	if((parseInt(fromPage)+1) === 1){
		firstPageStats = true;
	}
	var pageNumber = (parseInt(fromPage)+1).toString();
	var elementsOfParentDiv = document.getElementsByClassName("single-person-details");
	[...elementsOfParentDiv].forEach(function(elem) {
		elem.remove();
	});
	document.getElementsByClassName("pagination-div")[0].remove();
	document.getElementsByClassName("Loader-personal-details")[0].style.display = "block";
	xhr.onreadystatechange = function(){
		if(this.readyState==4 && this.status==200){
			 document.getElementsByClassName("Loader-personal-details")[0].style.display = "none";
			var persondetails = JSON.parse(xhr.response);
			var LastPageStatus = xhr.getResponseHeader("LastPage");
			if(LastPageStatus === "true"){
				isLastPage = true;
			}
			else{
				isLastPage = false;
			}
			detailListing(persondetails,firstPageStats,isLastPage,pageNumber);
			console.log(persondetails.size); 
			// var x;
			// for(x in persondetails){
			// 	var singlePersonDetailsDiv = document.createElement("div");
			// 	singlePersonDetailsDiv.className = "single-person-details";
			// 	singlePersonDetailsDiv.id = persondetails[x].id;
			// 	var divBorderDetailsDiv = document.createElement("div");
			// 	divBorderDetailsDiv.className = "border-details-div";
			// 	singlePersonDetailsDiv.appendChild(divBorderDetailsDiv);
			// 	var divProfilePicDiv = document.createElement("div");
			// 	divProfilePicDiv.className = "profile-pic-div";
			// 	var img = document.createElement("img");
			// 	img.src = "/servlrt_ipl_project/Images/person-24px.svg";
			// 	img.style.width = "31px";
			// 	img.style.height = "33px";
			// 	img.style.left = "8px";
			// 	img.style.top = "6px";
			// 	img.style.position = "relative";
			// 	divProfilePicDiv.appendChild(img);
			// 	singlePersonDetailsDiv.appendChild(divProfilePicDiv);
			// 	var divProfileNameDiv = document.createElement("div");
			// 	divProfileNameDiv.className = "profile-Name-div";
			// 	var divNamesDiv = document.createElement("div");
			// 	divNamesDiv.className = "names-div";
			// 	divNamesDiv.textContent = persondetails[x].firstName.concat(" ",persondetails[x].lastName);
			// 	divProfileNameDiv.appendChild(divNamesDiv);
			// 	singlePersonDetailsDiv.appendChild(divProfileNameDiv);
			// 	var divProfileTeamDiv = document.createElement("div");
			// 	divProfileTeamDiv.className = "profile-team-div";
			// 	var divProfileTeamNameDiv = document.createElement("div");
			// 	divProfileTeamNameDiv.className = "profile-team-name-div";
			// 	divProfileTeamNameDiv.textContent = persondetails[x].teamShortName;
			// 	divProfileTeamDiv.appendChild(divProfileTeamNameDiv);
			// 	singlePersonDetailsDiv.appendChild(divProfileTeamDiv);
			// 	var divProfilePositionDiv = document.createElement("div");
			// 	divProfilePositionDiv.className = "profile-position-div";
			// 	var divPositionTitleNameDiv = document.createElement("div");
			// 	divPositionTitleNameDiv.className = "position-title-name-div";
			// 	divPositionTitleNameDiv.textContent = persondetails[x].positionTitle;
			// 	divProfilePositionDiv.appendChild(divPositionTitleNameDiv);
			// 	singlePersonDetailsDiv.appendChild(divProfilePositionDiv);
			// 	var divProfileTypeDiv = document.createElement("div");
			// 	divProfileTypeDiv.className = "profile-type-div";
			// 	var divProfileTypeNameDiv = document.createElement("div");
			// 	divProfileTypeNameDiv.className = "profile-type-name-div";
			// 	divProfileTypeNameDiv.textContent = persondetails[x].profileType;
			// 	divProfileTypeDiv.appendChild(divProfileTypeNameDiv);
			// 	singlePersonDetailsDiv.appendChild(divProfileTypeDiv);
			// 	var divDateOfBrthdiv = document.createElement("div");
			// 	divDateOfBrthdiv.className = "date-of-brth-div";
			// 	var divDOBDiv2 = document.createElement("div");
			// 	divDOBDiv2.className = "DOB-div-2";
			// 	divDOBDiv2.textContent = persondetails[x].dateOfBirth;
			// 	divDateOfBrthdiv.appendChild(divDOBDiv2);
			// 	singlePersonDetailsDiv.appendChild(divDateOfBrthdiv);
			// 	var personalDetailcontainerDiv = document.getElementsByClassName("personal-details-container")[0];
			// 	singlePersonDetailsDiv.setAttribute("onclick", "getDetails()");
			// 	personalDetailcontainerDiv.appendChild(singlePersonDetailsDiv);
			// }
			// var divPaginationDiv = document.createElement("div");
			// divPaginationDiv.className = "pagination-div";
			// var divPreviousDiv = document.createElement("div");
			// divPreviousDiv.className = "previous-div";
			// var previousButtonDiv = document.createElement("button");
			// previousButtonDiv.className = "btn btn-primary";
			// previousButtonDiv.className = "button-previous";
			// previousButtonDiv.textContent ="<< Previous";
			// divPreviousDiv.appendChild(previousButtonDiv);
			// divPreviousDiv.setAttribute("onclick", "getPreviousPage()");
			// divPaginationDiv.appendChild(divPreviousDiv);
			// var divPageNoDiv = document.createElement("div");
			// divPageNoDiv.className = "page-No-div";
			// divPageNoDiv.textContent = (parseInt(fromPage)+1).toString;
			// divPaginationDiv.appendChild(divPageNoDiv);
			// var divNextDiv = document.createElement("div");
			// divNextDiv.className = "next-div";
			// var nextButtonDiv = document.createElement("button");
			// nextButtonDiv.className = "btn btn-primary";
			// nextButtonDiv.className = "button-previous";
			// nextButtonDiv.textContent ="Next >>";
			// divNextDiv.appendChild(nextButtonDiv);
			// divNextDiv.setAttribute("onclick", "getNextPage()");
			// divPaginationDiv.appendChild(divNextDiv);
			// var personalDetailcontainerDiv = document.getElementsByClassName("personal-details-container")[0];
			// personalDetailcontainerDiv.appendChild(divPaginationDiv);

		}
		else if(this.readyState==4 && this.status!=200){
			window.location.href = "http://localhost:8080/servlrt_ipl_project/error";
		}
	}
	xhr.send();
}

function detailListing(persondetails, firstPageStatus, isLastPage, pageNumber){
	
			var x;
			for(x in persondetails){
				var singlePersonDetailsDiv = document.createElement("div");
				singlePersonDetailsDiv.className = "single-person-details";
				singlePersonDetailsDiv.id = persondetails[x].id;
				var divBorderDetailsDiv = document.createElement("div");
				divBorderDetailsDiv.className = "border-details-div";
				singlePersonDetailsDiv.appendChild(divBorderDetailsDiv);
				var divProfilePicDiv = document.createElement("div");
				divProfilePicDiv.className = "profile-pic-div";
				var img = document.createElement("img");
				img.src = "/servlrt_ipl_project/Images/person-24px.svg";
				img.style.width = "31px";
				img.style.height = "33px";
				img.style.left = "8px";
				img.style.top = "6px";
				img.style.position = "relative";
				divProfilePicDiv.appendChild(img);
				singlePersonDetailsDiv.appendChild(divProfilePicDiv);
				var divProfileNameDiv = document.createElement("div");
				divProfileNameDiv.className = "profile-Name-div";
				var divNamesDiv = document.createElement("div");
				divNamesDiv.className = "names-div";
				divNamesDiv.textContent = persondetails[x].firstName.concat(" ",persondetails[x].lastName);
				divProfileNameDiv.appendChild(divNamesDiv);
				singlePersonDetailsDiv.appendChild(divProfileNameDiv);
				var divProfileTeamDiv = document.createElement("div");
				divProfileTeamDiv.className = "profile-team-div";
				var divProfileTeamNameDiv = document.createElement("div");
				divProfileTeamNameDiv.className = "profile-team-name-div";
				divProfileTeamNameDiv.textContent = persondetails[x].teamShortName;
				divProfileTeamDiv.appendChild(divProfileTeamNameDiv);
				singlePersonDetailsDiv.appendChild(divProfileTeamDiv);
				var divProfilePositionDiv = document.createElement("div");
				divProfilePositionDiv.className = "profile-position-div";
				var divPositionTitleNameDiv = document.createElement("div");
				divPositionTitleNameDiv.className = "position-title-name-div";
				divPositionTitleNameDiv.textContent = persondetails[x].positionTitle;
				divProfilePositionDiv.appendChild(divPositionTitleNameDiv);
				singlePersonDetailsDiv.appendChild(divProfilePositionDiv);
				var divProfileTypeDiv = document.createElement("div");
				divProfileTypeDiv.className = "profile-type-div";
				var divProfileTypeNameDiv = document.createElement("div");
				divProfileTypeNameDiv.className = "profile-type-name-div";
				divProfileTypeNameDiv.textContent = persondetails[x].profileType;
				divProfileTypeDiv.appendChild(divProfileTypeNameDiv);
				singlePersonDetailsDiv.appendChild(divProfileTypeDiv);
				var divDateOfBrthdiv = document.createElement("div");
				divDateOfBrthdiv.className = "date-of-brth-div";
				var divDOBDiv2 = document.createElement("div");
				divDOBDiv2.className = "DOB-div-2";
				divDOBDiv2.textContent = persondetails[x].dateOfBirth;
				divDateOfBrthdiv.appendChild(divDOBDiv2);
				singlePersonDetailsDiv.appendChild(divDateOfBrthdiv);
				var personalDetailcontainerDiv = document.getElementsByClassName("personal-details-container")[0];
				singlePersonDetailsDiv.setAttribute("onclick", "getDetails()");
				personalDetailcontainerDiv.appendChild(singlePersonDetailsDiv);
			}
			var divPaginationDiv = document.createElement("div");
			divPaginationDiv.className = "pagination-div";
			divPaginationDiv.style.display = "block";
			var divPreviousDiv = document.createElement("div");
			divPreviousDiv.className = "previous-div";
			var previousButtonDiv = document.createElement("button");
			previousButtonDiv.className = "btn btn-primary";
			previousButtonDiv.className = "button-previous";
			previousButtonDiv.textContent ="<< Previous";
			divPreviousDiv.appendChild(previousButtonDiv);
			if(firstPageStatus){
				previousButtonDiv.remove();
			}
			else{
				previousButtonDiv.style.display = "block";
			}
			divPreviousDiv.setAttribute("onclick", "getPreviousPage()");
			divPaginationDiv.appendChild(divPreviousDiv);
			var divPageNoDiv = document.createElement("div");
			divPageNoDiv.className = "page-No-div";
			divPageNoDiv.textContent = pageNumber;
			divPaginationDiv.appendChild(divPageNoDiv);
			var divNextDiv = document.createElement("div");
			divNextDiv.className = "next-div";
			var nextButtonDiv = document.createElement("button");
			nextButtonDiv.className = "btn btn-primary";
			nextButtonDiv.className = "button-previous";
			nextButtonDiv.textContent ="Next >>";
			divNextDiv.appendChild(nextButtonDiv);
			divNextDiv.setAttribute("onclick", "getNextPage()");
			divPaginationDiv.appendChild(divNextDiv);
			if(isLastPage){
				divNextDiv.remove();
			}
			else{
				divNextDiv.style.display = "block";
			}
			var personalDetailcontainerDiv = document.getElementsByClassName("personal-details-container")[0];
			personalDetailcontainerDiv.appendChild(divPaginationDiv);
 }