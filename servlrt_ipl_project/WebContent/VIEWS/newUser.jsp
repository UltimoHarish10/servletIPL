<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NewUserPage</title>
<link href="<%=request.getContextPath()%>/CSS/newUser.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/bootstrap.min.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/JS/newUser.js">
</script>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	
		response.setHeader("Pragma","no-cache");
		response.setHeader("Expires", "0");
	%>
	<div class="userinfo">
		<div class = "staticprops">
			<div class = "textprops">
				<div class = "textprops1">
				</div>
				<div class = "textprops1">
					<div class = "props1">
					</div>
					<div class= "props2">
						<div class = "textprop1" >
							ENTER YOUR EMAIL :
						</div>
						<div class = "textprop1">
							ENTER YOUR PASSWORD :
						</div>
					</div>
				</div>
				<div class = "textprops1">
				</div>
			</div>
		</div>
		<div class = "staticprops1">
			<div class = "textprops2">
				<div class = "textprops3">
				</div>
				<div class = "textprops3">
					<div class = "inputboxs">
						<div class = "inputboxs1">
							<input type="text" class="form-control" id="userinp1" onblur="validateEmail(this);"/onchange="changing1()">
						</div>
						<div class = "inputboxs1">
							<input type="Password" class="form-control" id="userinp2"  onblur="validatePassword(this);"/onchange="changing1()">
						</div>
					</div>
				</div>
			</div>
			<div class ="textprops3">
				<div class ="textprops4">
					<div class = "textbuttons">
						<button type="button" class="btn btn-primary" id="buttons" onclick="resetfuns()">RESET</button>
					</div>
					<div class ="textbuttons">
						<button type="button" disabled class="btn btn-primary" id="buttons1" onclick="submitfuns()">SAVE</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>