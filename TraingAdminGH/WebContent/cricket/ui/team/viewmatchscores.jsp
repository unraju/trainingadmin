<%@page import="common.util.UserUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cricket.struts.actionforms.team.PlayerMatchScoresForm"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="cricket.struts.actionforms.team.TeamSheduleForm"%>

<%@page import="cricket.struts.actionforms.team.PlayerForm"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>

<center>
		<div style="float: right; ;font: bold;font-size: 16px;">
			<A style="text-decoration: none" HREF="prelogin.do">Home</A> | <A style="text-decoration: none" HREF="viewTeamShedule.do">Match Scores</A>  
		</div>
		<%
		  TeamSheduleForm teamSheduleForm = (TeamSheduleForm) request.getAttribute(Constants.MATCH_DETAILS);
		%>
		<br>
		<div id="pageheader" >
			<font color="blue"><%=teamSheduleForm.getFirstTeamName()%></font> Vs 
		   <font color="blue"><%=teamSheduleForm.getSecondTeamName()%></font>
		</div>
		<%
		  if (request.getAttribute(Constants.VALIDATION_MESSAGE) != null)
		    {
		%>
			<div id="messageStyle">
			<img src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px" /> <%=request.getAttribute(Constants.VALIDATION_MESSAGE)%>
			</div>
		<%
		  }
		%>
			<%
			  List<PlayerMatchScoresForm> firstTeamPlayerMatchScores = (ArrayList<PlayerMatchScoresForm>) request.getAttribute(Constants.FIRST_TEAM_PLATER_MATCH_SCORES);
			    List<PlayerMatchScoresForm> secondTeamPlayerMatchScores = (ArrayList<PlayerMatchScoresForm>) request.getAttribute(Constants.SECOND_TEAM_PLATER_MATCH_SCORES);
			%>
			<div align="center" style="padding: 10px;co : blue;">
			<%=teamSheduleForm.getMatchResult()%>. Man of Match - <%=teamSheduleForm.getManOfMatch()%>.
			</div>
	


	<table>
		<tr>
			<td>
			<table width="100%">
				<tr>
					<td  width="100%">
					<div id="match_score_headers"><%=teamSheduleForm.getFirstTeamName()%>
					- <%=teamSheduleForm.getFirstTeamScore()%></div>
					</td>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<div id="tableStyle">
			<table>

				<tr>
					<th width="10px">No</th>
					<th width="180px">Player Name</th>
					<th width="60px">Runs</th>
					<%if(UserUtil.getSeries().intValue() >= 2){ %>
					<th width="25px">4's</th>
					<th width="25px">6's</th>
					<%} %>
					<th width="30px">Wickets</th>
					<th width="30px">Catches</th>
					<th>S/R</th>
					<th width="90px">SCT Points</th>
				</tr>
				<%
				  int slno = 0;
				  int firstTeamScore = 0;
				  for (PlayerMatchScoresForm playerForm : firstTeamPlayerMatchScores)
				  {

				    if (playerForm.getRuns() != null && !playerForm.getRuns().equals(""))
				    {
				      firstTeamScore = firstTeamScore + Integer.parseInt(playerForm.getRuns());
				    }
				    slno++;
				%>
				<tr>
					<td><%=slno%></td>
					<td><%=playerForm.getPlayerName()%></td>
					<td><%=playerForm.getRuns()%> <%
   if (playerForm.getRuns() != null && !playerForm.getRuns().trim().equals(""))
     {
 %>
					(<%=playerForm.getBalls()%>) <%
   }
 %>
					</td>
					<%if(UserUtil.getSeries().intValue() >= 2){ %>
					<td><%=playerForm.getFours()%></td>
					<td><%=playerForm.getSixers()%></td>
					<%} %>
					<td><%=playerForm.getWickets()%></td>
					<td><%=playerForm.getCatches()%></td>
					<td><%=playerForm.getStrikRate()%></td>
					<td><%=playerForm.getScore()%></td>
				</tr>

				<%
				  }
				%>
			</table>
				</div>
			</td>
		</tr>
	</table>

	 Extras : <%=teamSheduleForm.getFirstTeamExtras()%>
			<br>
			<br>
	
	<table>
		<tr>
			<td>
			<table width="100%">
				<tr>
					<td  width="100%">
					<div id="match_score_headers"><%=teamSheduleForm.getSecondTeamName()%>
					- <%=teamSheduleForm.getSecondTeamScore()%></div>
					</td>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<div id="tableStyle">
			<table>
				<tr>
					<th  width="10px">No</th>
					<th  width="180px">Player Name</th>
					<th  width="60px">Runs</th>
					<%if(UserUtil.getSeries().intValue() >= 2){ %>
					<th width="25px">4's</th>
					<th width="25px">6's</th>
					<%} %>
					<th  width="30px">Wickets</th>
					<th  width="30px">Catches</th>
					<th>S/R</th>
					<th  width="90px">SCT Points</th>
				</tr>
					<%
					  int slno1 = 0;
					  int secondTeamScore = 0;
					  for (PlayerMatchScoresForm playerForm : secondTeamPlayerMatchScores)
					  {
					    if (playerForm.getRuns() != null && !playerForm.getRuns().trim().equals(""))
					    {
					      secondTeamScore = secondTeamScore + Integer.parseInt(playerForm.getRuns());
					    }
					    slno1++;
					%>
						<tr>
							<td><%=slno1%></td>
							<td><%=playerForm.getPlayerName()%></td>
							<td><%=playerForm.getRuns()%> 
							<%
 							  if (playerForm.getRuns() != null && !playerForm.getRuns().trim().equals(""))
 							    {
 							%> 
							(<%=playerForm.getBalls()%>)
							<%
 							  }
 							%>
							</td>
							<%if(UserUtil.getSeries().intValue() >= 2){ %>
							<td><%=playerForm.getFours()%></td>
							<td><%=playerForm.getSixers()%></td>
							<%} %>
							<td><%=playerForm.getWickets()%></td>
							<td><%=playerForm.getCatches()%></td>
							<td><%=playerForm.getStrikRate()%></td>
							<td><%=playerForm.getScore()%></td>
						</tr>
					<%
					  }
					%>
				</table>
				</div>
			</td>
		</tr>
	</table>
	 Extras : <%=teamSheduleForm.getSecondTeamExtras()%>
	
</center>

</body>
</html>