<%@page import="common.util.UserUtil"%>
<%@page import="cricket.struts.actionforms.common.SeriesForm"%>
<%@page import="common.util.RetriveContextData"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="cricket.struts.actionforms.team.PlayerForm"%>
<%@page import="cricket.struts.actionforms.team.UserTeamForm"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cricket.hibernate.bf.team.UserTeam"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>
		  <%
			RetriveContextData contextData =  new RetriveContextData();
		    SeriesForm seriesForm = contextData.getCurrentSeriesForm();
		  %>
		<div id="bestplayer_table">
				<table width="100%" >
				<tbody >
				<tr>
				 <th  style="padding-left: 5px;padding-right: 5px;" align="center"><a href="viewBestPlayers.do?id=1"><%=seriesForm.getName() %> Best Players</a></th>
				 <th  style="padding-left: 5px;padding-right: 5px;" align="center"><a href="viewBestPlayers.do?id=2"><%=seriesForm.getName() %> Best Batsmen</a></th>
				 <th  style="padding-left: 5px;padding-right: 5px;" align="center"><a href="viewBestPlayers.do?id=3"><%=seriesForm.getName() %> Best Bowlers</a></th>
				 </tr>
				 </tbody>
				 </table>
				 	
				 </div>
		<center>
				<%
				    String label = "";
				
				  
				    List<PlayerForm> bestPlayers = new ArrayList<PlayerForm>();

				    String operation = (String)request.getParameter("id");
				    if (operation == null)
				    {
				 	 operation = (String) session.getAttribute(Constants.BEAT_PLAYER_TYPE);
				    }
				    if (operation == null)
				    {
				      operation = "1";
				    }
				    if (operation.equals("1"))
				    {
					  bestPlayers = contextData.getSeriesSCTBestPlayer(application);
					  label = "Super Cric Team "+seriesForm.getName()+" - Best Players";
				    }
				    else if (operation.equals("2"))
				    {
					  bestPlayers = contextData.getSeriesBestBatsman(application);
					  label = seriesForm.getName()+" - Best Batsmen";
				    }
				    else if (operation.equals("3"))
				    {
					  bestPlayers = contextData.getSeriesBestBowlers(application);
					  label = seriesForm.getName()+" - Best Bowlers";
				    }
				    session.setAttribute(Constants.BEAT_PLAYER_TYPE, operation);
				%>
				<div id="pageheader">
				<%=label%>
				</div>
<html:form action="viewBestPlayers.do">

		
		
		
		<div id="tableStyle">
		<table>
			<tr>
					<th>No</th>
					<th >Player Name</th>
					<th >Team</th>
					<th >Matches</th>
					<th >Runs</th>
					<%if(UserUtil.getSeries().intValue() == 2){ %>
						<th width="25px">4's</th>
						<th width="25px">6's</th>
						<%} %>
					<th >Wickets</th>
					<th >Catches</th>
					<th>50's</th>
					<th>100's</th>
					<th>S/R</th>
					<th >SCT Points</th>
				</tr>
					<%
					  int slno = 0;
					  for (PlayerForm playerForm : bestPlayers)
					  {
					    slno++;
					%>
					<tr>
						<td><%=slno%></td>
						<td><a href="viewPlayerMatchScores.do?id=<%=playerForm.getId()%>&back=1" ><%=playerForm.getName()%></a></td>
						<td><%=playerForm.getCoreTeamName()%></td>
						<td><%=playerForm.getMatches()%></td>
						<td><%=playerForm.getRuns()%> 
						<%if(playerForm.getRuns() != null && !playerForm.getRuns().trim().equals("") && !playerForm.getRuns().trim().equals("0")) {%> 
							(<%=playerForm.getBalls()%>)
							<%} %>	
						</td>
						<%if(UserUtil.getSeries().intValue() == 2){ %>
						<td><%=playerForm.getFours()%></td>
						<td><%=playerForm.getSixers()%></td>
						<%} %>
						<td><%=playerForm.getWickets()%></td>
						<td><%=playerForm.getCatches()%></td>
						<td><%=playerForm.getHalfCenturies()%></td>
						<td><%=playerForm.getCenturies()%></td>
						<td><%=playerForm.getStrikeRate()%></td>
						<td><%=playerForm.getScore()%></td>
					</tr>
			
					<%
								  }
								%>
	</table>
	</div>
</html:form>
</center>
</body>
</html>