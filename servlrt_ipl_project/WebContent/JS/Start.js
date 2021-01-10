function resetfuns()
{
	/*window.alert("sometext");*/
	var username = document.getElementById("userinp1");
	var password = document.getElementById("userinp2");
	document.getElementById("passwordResultError").innerHTML = "";
	username.value="";
	password.value="";
}

function submitfuns()
{
	var username = document.getElementById("userinp1");
	var password = document.getElementById("userinp2");
	if(username.value.length > 0  && password.value.length > 0){
	  var xhr = new XMLHttpRequest();
	  document.getElementById("loader").style.display = "block";
	  xhr.onreadystatechange = function(){
		  if(this.readyState==4 && this.status==200){
			  /*document.getElementById("jsp1").innerHTML =
			      this.responseText;*/
			  var isAuthenticated = xhr.getResponseHeader("isAuthenticated");
			  document.getElementById("loader").style.display = "none";
			  if(isAuthenticated){
				  /*window.location.href = "http://localhost:8080/servlrt_ipl_project/VIEWS/welcome.jsp";*/
				  window.location.href = "http://localhost:8080/servlrt_ipl_project/getpersonsbyfilters";
			  }
		  }
		  else if(this.readyState==4 && this.status!=200){
			  document.getElementById("loader").style.display = "none";
			  document.getElementById("passwordResultError").innerHTML = "Invalid Credentials";
		  }
	  };
	var passobject = new Object();
	passobject.username = username.value;
	passobject.password = password.value;
	/*xhr.open('POST', '../welcome', true);*/
	xhr.open('POST', '/servlrt_ipl_project/welcome', true);
	xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(JSON.stringify(passobject));
	}
	else{
		document.getElementById("passwordResultError").innerHTML = "Enter Valid Credentials";
	}
}
function mailpagefuns(){
	/*window.location.href = "http://localhost:8080/servlrt_ipl_project/VIEWS/newUser.jsp";*/
	window.location.href = "http://localhost:8080/servlrt_ipl_project/newUserRegisteration";
}