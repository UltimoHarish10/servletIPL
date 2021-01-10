function resetfuns()
{
	/*window.alert("sometext");*/
	var username = document.getElementById("userinp1");
	var password = document.getElementById("userinp2");
	username.value="";
	password.value="";
	document.getElementById("userinp1").style.borderColor = '';
	document.getElementById("userinp2").style.borderColor = '';
}

function submitfuns()
{
	var username = document.getElementById("userinp1");
	var password = document.getElementById("userinp2");
	document.getElementById("buttons1").disabled = true;
	var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function(){
		  if(this.readyState==4 && this.status==200){
			  /*document.getElementById("jsp1").innerHTML =
			      this.responseText;*/
			  var UserAdd = xhr.getResponseHeader("UserAdd");
			  if(UserAdd==1){
				  /*window.location.href = "http://localhost:8080/servlrt_ipl_project/VIEWS/Strat.jsp";*/
				  window.location.href = "http://localhost:8080/servlrt_ipl_project/login";
			  }
		  }
	  };
	var passobject = new Object();
	passobject.username = username.value;
	passobject.password = password.value;
	xhr.open('POST', '/servlrt_ipl_project/newUser', true);
	xhr.setRequestHeader("Content-type", "application/json");
   xhr.send(JSON.stringify(passobject));
}
function validateEmail(emailField){
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    if (emailField!=null && reg.test(emailField.value) == false) 
    {
        document.getElementById("userinp1").style.borderColor = "red";
    }
    else{
    	document.getElementById("userinp1").style.borderColor = "green";
    }
}
function validatePassword(passwordField){
	if(passwordField.value!=null){
		if(passwordField.value.length>=8){
			document.getElementById("userinp2").style.borderColor = "green";
		}
		else{
			document.getElementById("userinp2").style.borderColor = "red";
		}
	}
	else{
		document.getElementById("userinp2").style.borderColor = "red";
	}
}
function changing1(){
	var username = document.getElementById("userinp1");
	var password = document.getElementById("userinp2");
	var count = validation(username,password);
	if(count==2){
		document.getElementById("buttons1").disabled = false;
	}
	else{
		document.getElementById("buttons1").disabled = true;
	}
}
function validation(emailField,passwordField){
	var count=0;
	 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	    if (emailField!=null && reg.test(emailField.value) == false) 
	    {
	        document.getElementById("userinp1").style.borderColor = "red";
	    }
	    else{
	    	document.getElementById("userinp1").style.borderColor = "green";
	    	count=count+1;
	    }
	    if(passwordField.value!=null){
			if(passwordField.value.length>=8){
				document.getElementById("userinp2").style.borderColor = "green";
				count=count+1;
			}
			else{
				document.getElementById("userinp2").style.borderColor = "red";
			}
		}
		else{
			document.getElementById("userinp2").style.borderColor = "red";
		}
	    return count;
}