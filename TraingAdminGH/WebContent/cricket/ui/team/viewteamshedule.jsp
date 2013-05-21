<%@page import="cricket.struts.actionforms.common.SeriesForm"%>
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
<%@page errorPage="/error.jsp"%>
<html>
<head>
<style type="text/css">
<%@ include file="/css/cricket.css" %>
</style>
</head>
<body>
	<center>



		<%
			RetriveContextData contextData = new RetriveContextData();
			List<TeamSheduleForm> teamSheduleForms = contextData.getTeamShedule(application);
			SeriesForm seriesForm = contextData.getCurrentSeriesForm();
		%>
		<div id="pageheader">
			<%=seriesForm.getName()%>
			Schedule
		</div>
		<html:form action="viewMatchScores.do">
			<div id="tableStyle">
				<table>
					<tr>
						<!--<th>Select</th>
		-->
						<th>No</th>
						<th>Match</th>
							<th>Date</th>
					<% if(seriesForm.getId().longValue() == 3) {%>
					
						<th>First Team</th>
						<th>Second Team</th>
						<th>Venue</th>
					<%} %>
						
						<%
							String matchResult = teamSheduleForms.get(0).getMatchResult();
								if (matchResult != null && !matchResult.trim().equals(""))
								{
						%>
						<th>Result</th>
						<%
							}
						%>

					</tr>
					<%
						int count = 0;
							for (TeamSheduleForm teamSheduleForm : teamSheduleForms)
							{
								count++;
					%>
					<tr>
						<!--<td align="center"><input type="radio" value=<%=teamSheduleForm.getId()%> name="id" /></td>	-->
						
						<td align="center"><%=count%></td>
						<td><%=teamSheduleForm.getDisplayDate()%></td>
						<td><%=teamSheduleForm.getMatchName()%>
						 
						<%
 					if (teamSheduleForm.getSeriesId().equals("2"))
 						{
						%>
							<font color="blue"><%=teamSheduleForm.getFirstTeamName()%></font>
							Vs <font color="blue"><%=teamSheduleForm.getSecondTeamName()%></font>
						<%
							}
 					else if(teamSheduleForm.getSeriesId().equals("3"))
 						{
						%>
							<td ><%=teamSheduleForm.getFirstTeamName()%></td>
							<td ><%=teamSheduleForm.getSecondTeamName()%></td>
							<td ><%=teamSheduleForm.getVenue()%></td>
						<% } %>
						</td>
						
						<!--
					

			-->
						<%
							if (matchResult != null && !matchResult.trim().equals(""))
									{
										if (teamSheduleForm.getMatchResult() != null
												&& !teamSheduleForm.getMatchResult().trim().equals(""))
										{
						%><td><a
							href="viewMatchScores.do?id=<%=teamSheduleForm.getId()%>"> <%=teamSheduleForm.getMatchResult()%>
						</a></td>
						<%
							}
										else
										{
						%>
						<td></td>
						<%
							}
						%>
						<%
							}
						%>

					</tr>

					<%
						}
					%>

				</table>
			</div>
			<br>
			<!--<input type="submit" name="operation" value="View Scores"/>
	
	
-->
		</html:form>
	</center>
</body>
</html>