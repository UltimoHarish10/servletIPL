<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>loginPage</title>
<link href="../CSS/Start.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../CSS/bootstrap.min.css">
<script type="text/javascript" src="../JS/Start.js">
</script>
</head>
<body>
	<div class="userinfo">
		<div class="staticprops">
			<div class="textprops">
				<div class="textprops1"></div>
				<div class="textprops1">
					<div class="props1"></div>
					<div class="props2">
						<div class="textprop1">USER NAME</div>
						<div class="textprop1">PASSWORD</div>
					</div>
				</div>
				<div class="textprops1"></div>
			</div>
		</div>
		<div class="staticprops1">
			<div class="textprops2">
				<div class="textprops3"></div>
				<div class="textprops6">
					<div class="inputboxs">
						<div class="inputboxs1">
							<input type="text" class="form-control" id="userinp1">
						</div>
						<div class="inputboxs1">
							<input type="Password" class="form-control" id="userinp2">
						</div>
					</div>
					<div id="passwordResultError"></div>
				</div>
			</div>

			<div class="textprops3">
				<div class="textprops4">
					<div class="textbuttons">
						<button type="button" class="btn btn-primary" id="buttons"
							onclick="resetfuns()">RESET</button>
					</div>
					<div class="textbuttons">
						<button type="button" class="btn btn-primary" id="buttons1"
							onclick="submitfuns()">SUBMIT</button>
					</div>
					<div class="loader" id="loader" style="display:none">
						<img src="..\Images\loader1.gif" width="40px" height="50px">
					</div>
				</div>
				<div class="textprops7">
					<div class="textprops5">
						<button type="button" class="btn btn-primary btn-sm" id="buttons2"
							onclick="mailpagefuns()">CREATE USER</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>