<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cricket.struts.actionforms.team.TeamSheduleForm"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>
<center>
	<div id="pageheader">
		 World Cup 2001 - Shedule
	</div>
	<%
		if (request.getAttribute(Constants.VALIDATION_MESSAGE) != null) {
	%>
		<div id="messageStyle">
		<img src="images/warning_wipeout_invoiceDB.png" width="16px" height="16px" /> <%=request.getAttribute(Constants.VALIDATION_MESSAGE)%>
		</div>
	<%
		}
	%> 
<html:form action="populateTeamShedule.do">
<div id="tableStyle"> 
<table>
	
	<tr>
		<th>No</th>
		<th>Match </th>
		<th>First Team</th>
		<th>Second Team</th>
		<th>Date</th>
		<th>Venue</th>
			
	</tr>
	<%
		List<TeamSheduleForm> teamSheduleForms = (ArrayList<TeamSheduleForm>) session
		.getAttribute(Constants.TEAM_SHEDULE);
		if(teamSheduleForms != null)
		{
		int count=0;
		for (TeamSheduleForm teamSheduleForm  : teamSheduleForms) 
		{
		  count++;
	%>
	<tr>
		<td align="center"><%=count%></td>
		<td ><%=teamSheduleForm.getMatchName()%></td>
		<td ><%=teamSheduleForm.getFirstTeamForm().getName()%></td>
		<td ><%=teamSheduleForm.getSecondTeamForm().getName()%></td>
		<td ><%=teamSheduleForm.getDisplayDate()%></td>
		<td ><%=teamSheduleForm.getVenue()%></td>
	</tr>

	<% }} %>
		
</table>
</div>

	<br>
	<html:submit property="operation" value="Update"/>
	
</html:form>
</center>
</body>
</html>