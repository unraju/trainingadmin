<%@page import="common.util.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
<%
	String forwardAction = "prelogin.do?menu=105&submenu=6";
	String urlWithSessionID = null;
	if (request.getAttribute(Constants.FORWARD_ACTION) != null) {
		forwardAction = (String) request
				.getAttribute(Constants.FORWARD_ACTION);

	}
	if (forwardAction != null) {
		urlWithSessionID = response.encodeRedirectURL(forwardAction);
	}
	response.sendRedirect(urlWithSessionID);
%>
</body>
</html>