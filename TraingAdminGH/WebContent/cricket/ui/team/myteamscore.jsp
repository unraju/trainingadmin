<%@page import="common.util.UserUtil"%>
<%@page import="common.util.RetriveContextData"%>
<%@page import="cricket.struts.actionforms.common.SeriesForm"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="cricket.struts.actionforms.team.PlayerForm"%>
<%@page import="cricket.struts.actionforms.team.UserTeamForm"%>
<%@page errorPage="/error.jsp"%>
<html>
<META HTTP-EQUIV="Refresh"
      CONTENT="<%=session.getMaxInactiveInterval()%>;URL=logout.do?sessionTimeout=true">
<body>

<center>
<%
  	UserTeamForm userTeamForm = (UserTeamForm) session.getAttribute(Constants.USER_TEAM);
    SeriesForm seriesForm = new RetriveContextData().getCurrentSeriesForm();
%>
<html:form action="populateUserTeam.do">
	<div id="pageheader"> 
		My <%=seriesForm.getName()%> Team Score (<%=userTeamForm.getName()%>) - <%=userTeamForm.getScore()%>
	</div>
	
	

	<table align="center">
		<tr>
			<td align="left" >Team Name </td>
			<td>: <%=userTeamForm.getName()%>
			</td>
		</tr>
		<tr>
			<td align="left">Allocated Substitutions</td>
			<td>: <%=userTeamForm.getAllowedSubstitutions()%>
			</td>
		</tr>
		<tr>
			<td align="left">Used Substitutions</td>
			<td>: <%=userTeamForm.getUsedSubstitutions()%>
			</td>
		</tr>
		<tr>
			<td align="left">Deducted points </td>
			<td align="left">: <%=userTeamForm.getDeductedSubScore()%>
			</td>
		</tr>
	</table>

	
	<div id="tableStyle">
	<table>
		<tr>
			<th>No</th>
			<th>Skill</th>
			<th>Player</th>
			<th>Active</th>
			<th>Runs</th>
			<%
			  if (UserUtil.getSeries().intValue() == 2)
			  {
			%>
			<th>4's</th>
			<th>6's</th>
			<th>50's</th>
			<th>100's</th>
			<%
			  }
			%>
			<th>Wickets</th>
			<th>Catches</th>
			<th>Score</th>
		</tr>
		<%
		  int count = 0;
		  for (PlayerForm playerForm : userTeamForm.getPlayers())
		  {
		    count++;
		%>
		<%
		  if (playerForm.isActive())
		    {
		%>
		<tr>
			<td><%=count%></td>
			<td><%=playerForm.getSkill()%></td>

			<td ><a href="viewUserPlayerMatchScores.do?id=<%=playerForm.getId()%>&back=3" ><%=playerForm.getName()%></a>
			<%
			  if (playerForm.isCaptain())
			      {
			%>
			 <font color="red">(C)</font>
			<%
			  }
			%>
			<%
		 	 if (UserUtil.getSeries().intValue() != 1)
			  {
			%>
			 	  (<%=playerForm.getCoreTeamName()%>)
			<%} %>
			</td>
			<td align="center" ><%=playerForm.getActiveString()%></td>

			<td align="center"><%=playerForm.getRuns()%></td>
			
			<%
		 	 if (UserUtil.getSeries().intValue() == 2)
			  {
			%>
			<td width="25px" align="center"><%=playerForm.getFours()%></td>
			<td width="25px" align="center"><%=playerForm.getSixers()%></td>
			<td width="25px" align="center"><%=playerForm.getHalfCenturies()%></td>
			<td width="25px" align="center"><%=playerForm.getCenturies()%></td>
			<%
			  }
			%>
			<td width="50px" align="center"><%=playerForm.getWickets()%></td>

			<td width="50px" align="center"><%=playerForm.getCatches()%></td>

			<td width="50px" align="center"><%=playerForm.getScore()%></td>

		</tr>
		<%
		  }
		    else
		    {
		%>
		<tr>
		
			<td ><font color="#185E87"><%=count%></font></td>
			<td width="50px"><font color="#185E87"><%=playerForm.getSkill()%></font></td>

			<td width="150px"><font color="#185E87"><a href="viewUserPlayerMatchScores.do?id=<%=playerForm.getId()%>&back=3" ><%=playerForm.getName()%></a></font>
			<%
			  if (playerForm.isCaptain())
			      {
			%>
			 <font color="red">(C)</font>
			<%
			  }
			%>
			<%
		 	 if (UserUtil.getSeries().intValue() != 1)
			  {
			%>
			 	  (<%=playerForm.getCoreTeamName()%>)
			<%} %>
			</td>
			<td align="center" ><font color="#185E87"><%=playerForm.getActiveString()%></font></td>

			<td width="50px" align="center"><font color="#185E87"><%=playerForm.getRuns()%></font></td>
			
			<%
		 	 if (UserUtil.getSeries().intValue() == 2)
			  {
			%>
			<td width="25px" align="center"><%=playerForm.getFours()%></td>
			<td width="25px" align="center"><%=playerForm.getSixers()%></td>
			<td width="25px" align="center"><%=playerForm.getHalfCenturies()%></td>
			<td width="25px" align="center"><%=playerForm.getCenturies()%></td>
			<%
			  }
			%>

			<td width="50px" align="center"><font color="#185E87"><%=playerForm.getWickets()%></font></td>

			<td width="50px" align="center"><font color="#185E87"><%=playerForm.getCatches()%></font></td>

			<td width="50px" align="center"><font color="#185E87"><%=playerForm.getScore()%></font></td>
		</tr>
	    <%
	      }
	      }
	    %>
	</table>
	</div>
	
	<br>

</html:form>
<div style="border: thin solid;border-color:gray;padding: 4px;background:#82CAFA;width:75%;" >
		<div align="left">Please Note -</div>
			<div  align="left" style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  	 Top 11 players are currently part of your team and rest are substituted players.
		   </div>
		</div>
</center>
</body>
</html>