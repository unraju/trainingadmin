<%@page import="common.util.RetriveContextData"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cricket.struts.actionforms.team.TeamSheduleForm"%>
<%@page errorPage="/error.jsp"  %>
<html>
<body>
<center>
	<div id="pageheader">
		Update Match Scores
	</div>

<html:form action="populateAllMatchPlayers.do">
<div id="tableStyle"> 
<table>
	<tr>
		<th>No</th>
		<th>Match </th>
		<th>1st Team</th>
		<th>2nd Team</th>
		<th>Date</th>
		<th>Venue</th>
		<th>Upd</th>
			
	</tr>
	<%
		List<TeamSheduleForm> teamSheduleForms = new RetriveContextData().getTeamShedule(application);
		int count=0;
		if(teamSheduleForms != null && teamSheduleForms.size()>0)
		{
		for (TeamSheduleForm teamSheduleForm  : teamSheduleForms) 
		{
		  count++;
	%>
	<tr>
		<td align="center"><%=count%></td>
		<td ><%=teamSheduleForm.getMatchName()%></td>
		<td ><%=teamSheduleForm.getFirstTeamName()%></td>
		<td ><%=teamSheduleForm.getSecondTeamName()%></td>
		<td align="center"><%=teamSheduleForm.getDisplayDate()%></td>
		<td ><%=teamSheduleForm.getVenue()%></td>
		<td align="center"><a href="populateAllMatchPlayers.do?id=<%=teamSheduleForm.getId()%>" >
				<div style="font-size: 13px;color: #0000FF">
					Change
				</div>
				</a></td>
	
	</tr>

	<% } }%>
		
</table>
</div>
</html:form>
</center>
</body>
</html>