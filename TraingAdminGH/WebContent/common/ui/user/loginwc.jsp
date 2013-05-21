<%@page import="common.ehcache.elements.FrontPageSeriesDataElement"%>
<%@page import="common.util.RetriveContextData"%>
<%@page import="common.struts.actionforms.user.UserForm"%>
<%@page import="cricket.struts.actionforms.team.CurrentMatchSheduleForm"%>
<%@page import="java.util.Date"%>
<%@page import="cricket.struts.actionforms.team.TeamSheduleForm"%>
<%@page import="cricket.struts.actionforms.common.SCTNewsForm"%>
<%@page import="cricket.hibernate.bf.common.SCTNews"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="cricket.struts.actionforms.team.PlayerForm"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cricket.struts.actionforms.team.UserTeamForm"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="common.util.Constants"%>
<%@page errorPage="/error.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<link href="/css/boxcurve.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/EPR/js/date-picker.js"></script>
<script type="text/javascript" src="/EPR/js/common.js"></script>

</head>
<body>
<center>
<%
  RetriveContextData contextData = new RetriveContextData();
    FrontPageSeriesDataElement frontPageSeriesDataElement = contextData.getFronPageSeriesData();
    List<UserTeamForm> userTeams = frontPageSeriesDataElement.getUserTeamForms();

    List<PlayerForm> bestBatsMan = frontPageSeriesDataElement.getBestBatsmen();
    List<PlayerForm> bestBowlers = frontPageSeriesDataElement.getBestBowlers();
    List<PlayerForm> bestPlayers = frontPageSeriesDataElement.getSctBestPlayers();

    List<SCTNewsForm> sctNewsForms = frontPageSeriesDataElement.getSctNewsForms();
    UserForm user = (UserForm) session.getAttribute(Constants.USER);
%>
<table  width="100%" style="padding: 0px;">
	<tr>
		<td  width="100%">
		<table  style="padding: 0px;">
			<tbody>
				<tr style="padding: 0px;">
					<td width="100%;" >

					<div id="wc_sct_header" align="center">
					<h1>WC 2011 Super Cric Team</h1>
					<h3><a href="viewLatestStandings.do"> WC 2011 - Final
					Standings </a></h3>
					<table  style="padding: 0px;">
						<tr>
							<%
							  int count = 0;
							    int size = 0;
							    int userTeamCount = 15;
							    if (userTeams != null && userTeams.size() < 9)
							    {
								  userTeamCount = userTeams.size();
							    }
							    do
							    {
							  size++;
							  if (size > 5)
							  {
							%>
							<tr>
								<%
								  }
								%>
								<td style="width: 175px">
								<div style="font-size: 12px; color: white; padding-left: 12px">
								<%=count + 1%>.&nbsp; <%=userTeams.get(count).getUserName()%> -
								<%=userTeams.get(count).getScore()%></div>
								</td>
								<%
								  if (size == 5)
								  {
								    size = 0;
								%>
							</tr>
							<%
							  }
							  count++;
							    }
							    while (count < userTeamCount);
							%>
						
					</table>
					</div>
					</td>
				</tr>
			</tbody>
		</table>
		</td>
	</tr>

	<tr>
		<td>
		<table  style="padding: 0px;">
			<tr>
				<td valign="top" style="padding-top: 0px;" width="65%" >
				<div id="bestplayer_table">
				<table >
					<tbody>
						<tr>
							<th width="190px"><a href="viewBestPlayers.do?id=1">WC
							Best Players</a></th>
							<th width="190px"><a href="viewBestPlayers.do?id=2">WC
							Best Batsmen</a></th>
							<th width="190px"><a href="viewBestPlayers.do?id=3">WC
							Best Bowlers</a></th>
						</tr>
						<%
						  if (bestPlayers != null && bestPlayers.size() > 0)
						    {
						  int size1 = 0;
						  for (int i = 1; i <= 6; i++)
						  {
						%>
						<tr>
							<td align="left" style="padding: 2px" width="190px">
							<div style="font-size: 12px; color: black; padding-left: 6px">
							<%=size1 + 1%>. <%=bestPlayers.get(size1).getName()%> - <%=bestPlayers.get(size1).getScore()%>(P)
							</div>
							</td>

							<td align="left" style="padding: 2px" width="190px">
							<div style="font-size: 12px; color: black; padding-left: 6px">
							<%=size1 + 1%>. <%=bestBatsMan.get(size1).getName()%> - <%=bestBatsMan.get(size1).getRuns()%>(R)
							</div>
							</td>

							<td align="left" style="padding: 2px" width="190px">
							<div style="font-size: 12px; color: black; padding-left: 6px">
							<%=size1 + 1%>. <%=bestBowlers.get(size1).getName()%> - <%=bestBowlers.get(size1).getWickets()%>(W)
							</div>
							</td>

						</tr>
						<%
						  size1++;
						  }
						    }
						%>
					</tbody>
				</table>
				</div>
				</td>
				<td valign="top">

				<div id="bestplayer_table">
				<table  style="padding: 0px;">
					<tr>
						<th valign="top" ><a href="viewTeamShedule.do">WC
						2011 Matches/Results</a></th>

					</tr>
					<%
					  List<CurrentMatchSheduleForm> currentMatchSheduleForms = frontPageSeriesDataElement.getCurrentMatchSheduleForms();
					    if (currentMatchSheduleForms != null && currentMatchSheduleForms.size() > 0)
					    {
					  int ccc = 0;
					  for (CurrentMatchSheduleForm teamSheduleForm : currentMatchSheduleForms)
					  {
					    ccc++;
					    if (teamSheduleForm.isScoresGenerated())
					    {
					%>
					<tr>
						<td align="left" valign="top"><font size="2px"><img
							src="images/rule.png" width="12px" height="12px" /> <%=teamSheduleForm.getMatchShortName()%>
						: <%=teamSheduleForm.getFirstTeamName()%> Vs <%=teamSheduleForm.getSecondTeamName()%><font
							size="2px"><a
							href="viewMatchScores.do?id=<%=teamSheduleForm.getId()%>"
							style="text-decoration: none"> - <font size="2px"><%=teamSheduleForm.getMatchResult()%></font></a></td>
					</tr>
					<%
					  }
					    else
					    {
					%>
					<tr>
						<td align="left" valign="top"><font size="2px"><img
							src="images/rule.png" width="12px" height="12px" /><font size="2px"><%=teamSheduleForm.getDate()%>
						<%=teamSheduleForm.getFirstTeamName()%> Vs <%=teamSheduleForm.getSecondTeamName()%>
						(<%=teamSheduleForm.getVenue()%>)</font>
						</div>
						</td>
					</tr>
					<%
					  }
					    if (ccc > 3)
					    {
					      break;
					    }
					  }
					    }
					%>

				</table>
				</div>
				</td>

			</tr>
			<tr>
				<td valign="top">
				<div id="bestplayer_table">
				<table width="100%" >
					<tr>
						<th>Super Cric Team - WC 2011 News Update</th>
					</tr>

					<%
					  int aaa = 1;
					    if (sctNewsForms != null && sctNewsForms.size() > 0)
					    {
					  for (SCTNewsForm newsForm : sctNewsForms)
					  {
					    aaa++;
					%>
					<tr>
						<td align="left" valign="top" width="100%"
							style="padding: 4px; font-size: 12px;"><img
							src="images/rule.png" width="12px" height="12px" /> <%=newsForm.getNews()%></td>
					</tr>
					<%
					  if (aaa > 10)
					    {
					      break;
					    }
					  }
					    }
					%>

				</table>
				</div>
				</td>
				<td align="right" valign="top"><img src="images/sachinwc.jpg" width="310px"
					height="310px" style="float: right :" /></td>
			</tr>
			<!--<tr>
			<td><img src="images/Sachin1.jpg" width="580px" height="300px"
				style="float: left;" /></td>
			<td><img src="images/sachin3.jpg" width="305px" height="300px"
				style="float: right :" /></td>
		</tr>
	-->
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table  style="padding: 0px;">
			<tr>
				<td>
				<div id="harsh_header">
				<div id="title">
				<h1><a href="http://guessthelogo.blogspot.com/" target="_blank">Logo
				Quiz of the Day</a></h1>
				<font color="white">Tantalizing logo quiz every day.</font></div>

				<div id="topright">Tease your brain with logo quiz everyday.
				Its Logo Quizzing at it's best. This blog is pure fun for quiz
				lovers and also logo lovers. A logo is posted everyday and you are
				to guess the logo and post your replies in comments section. Correct
				answers will be posted the next day.</div>
				</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>


</center>
</body>
</html>