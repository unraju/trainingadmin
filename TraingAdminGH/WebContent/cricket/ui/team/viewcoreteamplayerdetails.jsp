<%@page import="common.util.UserUtil"%>
<%@page import="cricket.struts.actionforms.common.SeriesForm"%>
<%@page import="common.util.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="cricket.struts.actionforms.team.PlayerForm"%>
<%@page import="cricket.struts.actionforms.team.CoreTeamForm"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>
<center>
 <%
   CoreTeamForm tempCoreTeamForms = (CoreTeamForm) request.getAttribute(Constants.CORE_TEAM_FORM);
   
 %>
	
	<table>
		
		<tr>
			<td align="left">
				<div style="font-size: 18px">
				<%=tempCoreTeamForms.getName()%> - Player Scores
				</div>
				</td>
		</tr>
	
	</table>
	

<div id="tableStyle">
	<table >

		<tr>
			<th>No</th>
			<th>Name</th>
			<th>Role</th>
			<th>Matches</th>
			<th>Runs</th>
			<%if(UserUtil.getSeries().intValue() == 2){ %>
			<th width="25px">4's</th>
			<th width="25px">6's</th>
			<%} %>
			<th>50's</th>
			<th>100's</th>
			<th>Wkts</th>
			<th>Caths</th>
			<th>S/R</th>
			<th>SCT Points</th>
		</tr>
		<%
			if(tempCoreTeamForms != null)
			{
		  int count = 0;
		    for (PlayerForm playerForm : tempCoreTeamForms.getPlayers())
		    {
		  count++;
		%>
	<tr>
		<td><%=count%></td>
		<%if(UserUtil.getSeries().intValue() == 1){ %>
		<td width="150px"><a href="viewPlayerMatchScores.do?id=<%=playerForm.getCoreTeamPlayerId()%>&back=2" ><%=playerForm.getName()%></a>
		<%
		  if (playerForm.isCaptain())
		  {
		%>
		 <font color="blue">(C)</font>
		<%
		  }
		%>
		</td>
		<%} else {%>
		<td width="150px"><a href="viewPlayerMatchScores.do?id=<%=playerForm.getCoreTeamPlayerId()%>&back=2" ><%=playerForm.getName()%></a>
		<%
		  if (playerForm.isCaptain())
		  {
		%>
		 <font color="blue">(C)</font>
		<%
		  }
		%>
		</td>
		<%}%>
		<td><%=playerForm.getSkill()%></td>
		<!--<td><%=playerForm.getActiveString()%></td>-->
		<td><%=playerForm.getMatches()%> </td>
		<td><%=playerForm.getRuns()%> 
		<%if(playerForm.getRuns() != null && !playerForm.getRuns().trim().equals("") && !playerForm.getRuns().trim().equals("0")) {%> 
					(<%=playerForm.getBalls()%>)
		 <%} %>	</td>
		 <%if(UserUtil.getSeries().intValue() == 2){ %>
		<td><%=playerForm.getFours()%></td>
		<td><%=playerForm.getSixers()%></td>
			<%} %>
		<td><%=playerForm.getHalfCenturies()%></td>
		<td><%=playerForm.getCenturies()%></td>
		<td><%=playerForm.getWickets()%></td>
		<td><%=playerForm.getCatches()%></td>
		<td><%=playerForm.getStrikeRate()%></td>
		<td><%=playerForm.getScore()%></td>
	</tr>
	<%
	  } }
	%>

</table>
	</div>

</center>
</body>
</html>