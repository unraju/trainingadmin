<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="common.util.Constants"%>
<%@page import="java.util.List"%>
<%@page import="common.hibernate.bf.user.Activity"%>
<%@page errorPage="/error.jsp"%>
<%@page import="java.util.ArrayList"%>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
<div
	style="width: 80%; color: black; font-style: normal; font-size: large; vertical-align: top"
	align="center"><br>
<br>

Menu
<%
	//List<Activity> activities = (ArrayList<Activity>)session.getAttribute(Constants.USER_ACTIVITIES);
%>
<table border="0" cellpadding="1" cellspacing="10">
	<tr>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td align="left"><A HREF="prechangepwd.do">Change Password</A></td>
	</tr>
	<tr>
		<td align="left"><A HREF="logout.do">Logout</A></td>

	</tr>

</table>
</div>
</body>
</HTML>