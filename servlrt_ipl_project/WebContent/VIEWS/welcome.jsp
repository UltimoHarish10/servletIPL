<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/CSS/welcome.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/welcome.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/JS/welcome.js">
</script>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	
		response.setHeader("Pragma","no-cache");
		response.setHeader("Expires", "0");
	
		System.out.println("into the Jsp ");
	%>
	<div class = full-body-class>
		<div class = left-side-page>
			
		</div>
	</div>
</body>
</html>