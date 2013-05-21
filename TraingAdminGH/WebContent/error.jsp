<%@page import="common.util.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page isErrorPage="true" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
<center>
<div id="pageheader">
		Please try again...
	</div>
<div style="padding-top: 25px">
<div style="border: thin silver;border-color:gray;padding: 4px;background:#82CAFA;width: 65%;color: #0000FF;" >
		System failed to process your request due to internal problems. Please try again.
	</div></div>
	<%
		if(exception != null && exception.toString() != null) 
		{
	%>
		<%=exception.toString() %>
	<%
		} 
	%>
<%//throw new ServletException(exception); %>

<br>
<br>
</center>
</body>
</html>