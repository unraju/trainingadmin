<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="common.util.Constants"%>
<%@page errorPage="/error.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

</head>
<body>
<center><br>


<h3> Rules & Conditions</h3>

<br>
<%
	if (request.getAttribute(Constants.ERROR_MESSAGE) != null)
	{
%>
<div style="color: red" align="center"><img align="bottom"
	src="images/warning_wipeout_invoiceDB.png" width="20px" height="18px">
<%=request.getAttribute(Constants.ERROR_MESSAGE)%></div>
<%
	}
%> <html:form action="agreement.do" method="get">
	

	<p></p>
	<input type="checkbox" name="agreed"  value="Aceepted"/> Agreed all above Rules.
 </html:form>
</center>
</body>
</html>