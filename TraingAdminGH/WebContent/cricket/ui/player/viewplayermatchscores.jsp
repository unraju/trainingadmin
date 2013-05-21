<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="common.util.UserUtil"%>
<%@page import="common.util.RetriveContextData"%>
<%@page import="cricket.struts.actionforms.common.SeriesForm"%>
<%@page import="cricket.struts.actionforms.team.PlayerMatchScoresForm"%>
<%@page import="cricket.hibernate.bf.player.PlayerMatchScores"%>
<%@page import="cricket.struts.actionforms.team.PlayerSubstitutionForm"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="cricket.struts.actionforms.team.PlayerForm"%>
<%@page import="cricket.struts.actionforms.team.UserTeamForm"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cricket.hibernate.bf.team.UserTeam"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>
<center>

		<%
		   String  backRefId =(String)request.getAttribute(Constants.BACK_REF);
		   String url = "viewBestPlayers.do";
		   String coreTeamId = (String)session.getAttribute(Constants.CORETEAM_ID);
		   SeriesForm seriesForm = new RetriveContextData().getCurrentSeriesForm();
			if(backRefId != null && backRefId.equals("1"))
			{
			  url = "viewBestPlayers.do";
			}else if(backRefId != null && backRefId.equals("2"))
			{
			  url = "viewCoreTeams.do?id="+coreTeamId;
			}else if(backRefId != null && backRefId.equals("3"))
			{
			  url = "myTeamScore.do?id=";
			}
		%>
	<div style="float: right; padding: 10px;font: bold;">
			 <A HREF="<%=url %>">Back</A> 
		</div>
				<%
				List<PlayerMatchScoresForm> playerMatchScoresForms = 
				  (ArrayList<PlayerMatchScoresForm>)request.getAttribute(Constants.PLAYER_MATCH_SCORE_FORMS);
				PlayerMatchScoresForm  playerMatchscore  =  new PlayerMatchScoresForm();
					if(playerMatchScoresForms != null && playerMatchScoresForms.size()>0) 
					{
						playerMatchscore=  playerMatchScoresForms.get(0);
					}
				%>
				
				<div id="pageheader">
				<%if(backRefId != null && !backRefId.equals("3")) {%>
					<%=seriesForm.getName()%> Scores - <%=(String)request.getAttribute(Constants.PLAYER_NAME)%>
					<%} else { %>
					 <%=(String)request.getAttribute(Constants.PLAYER_NAME)%> - Scores
					<%} %>
				</div>
		
		<div id="tableStyle">
		<table>
			<tr>
					<th>No</th>
					<th >Date</th>
					<th >Match</th>
					<th >Runs</th>
					<%if(UserUtil.getSeries().intValue() != 1){ %>
					<th width="25px">4's</th>
					<th width="25px">6's</th>
					<%} %>
					<th >Wickets</th>
					<th >Catches</th>
					<th >S/R</th>
					<th >SCT Points</th>
				</tr>
					<%
					  int slno = 0;
					  for (PlayerMatchScoresForm  playerMatchScoresForm : playerMatchScoresForms)
					  {
					    slno++;
					%>
					<tr>
						<td><%=slno%></td>
						<td><%=playerMatchScoresForm.getDateString()%></td>
						<td><%=playerMatchScoresForm.getMatchName()%></td>
						<td><%=playerMatchScoresForm.getRuns()%>
						 <%if(playerMatchScoresForm.getRuns() != null && !playerMatchScoresForm.getRuns().trim().equals("")) {%> 
							(<%=playerMatchScoresForm.getBalls()%>)
							<%} %>	
						 </td>
						 <%if(UserUtil.getSeries().intValue() != 1){ %>
						 <td><%=playerMatchScoresForm.getFours()%></td>
						<td><%=playerMatchScoresForm.getSixers()%></td>
						<%} %>
						<td><%=playerMatchScoresForm.getWickets()%></td>
						<td><%=playerMatchScoresForm.getCatches()%></td>
						<td><%=playerMatchScoresForm.getStrikRate()%></td>
						<td><%=playerMatchScoresForm.getScore()%></td>
					</tr>
			
					<%
					  }
					%>
	</table>
	</div>
</center>
</body>
</html>