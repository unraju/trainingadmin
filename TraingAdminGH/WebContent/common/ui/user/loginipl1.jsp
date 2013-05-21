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
			RetriveContextData contextData=	 new RetriveContextData();
			FrontPageSeriesDataElement frontPageSeriesDataElement = contextData.getFronPageSeriesData();
			List<UserTeamForm> userTeams = frontPageSeriesDataElement.getUserTeamForms();
			
			List<PlayerForm> bestBatsMan = frontPageSeriesDataElement.getBestBatsmen();
			List<PlayerForm> bestBowlers = frontPageSeriesDataElement.getBestBowlers();
			List<PlayerForm> bestPlayers =frontPageSeriesDataElement.getSctBestPlayers();
		
			List<SCTNewsForm> sctNewsForms = frontPageSeriesDataElement.getSctNewsForms();
			UserForm user = (UserForm) session.getAttribute(Constants.USER);
		%>
<html:form action="login.do" method="post">
	<table style="padding: 0px;">
		<tr>
			<td align="center" valign="middle">  
			<div id="sct_header" align="center" >
			<h3>
			</h3>
			<br><br>
			<h1><a href="viewRules.do">
			IPL Super Cric Team Contest</a></h1>
			<!--<h3><a href="viewLatestStandings.do">
			IPL - Latest Standings 
			</a></h3>
			--><h3>
				Register yourself, select your team and enjoy IPL 12 like you have never enjoyed any IPL before. Best of Luck...
			</h3>
			
			</div>
			</td>
			<td align="center">
			<%if(user == null)
			{%>
			<div id="login">
			<div class="mainLabel" align="center">Welcome</div>
			<%
				if (request.getAttribute(Constants.ERROR_MESSAGE) != null) {
			%>
			<div id="messageStyle"><img
				src="images/warning_wipeout_invoiceDB.png" width="12px"
				height="12px" /> <%=request.getAttribute(Constants.ERROR_MESSAGE)%>
			</div>
			<%
				}
			%>
		
			<table >

				<tr>
					<td align="right">Login Id/Email Id :</td>
					<td align="left"><html:text name="loginForm" size="20"
						maxlength="40" property="loginName" />
					<div id="messageStyle"><html:errors property="loginName" /></div>
					</td>
				</tr>
				<tr>
					<td align="right">Password :</td>
					<td align="left"><html:password name="loginForm"
						property="pwd" size="20" maxlength="15" />
					<div id="messageStyle"><html:errors property="pwd" /></div>
					</td>

				</tr>
				<tr>
					<br>
				</tr>
				<tr>
					<td></td>
					<td align="right"><html:submit /><html:reset /></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td align="right"><a style="text-decoration: none" href="prenewlogin.do">New User - Register</a></td>
					<td align="left"><span style="color: black; font-size: 10px;">&nbsp;&nbsp;
					| &nbsp;&nbsp; </span><a style="text-decoration: none" href="preforgotpwd.do">Retrieve Account</a></td>
				</tr>
			</table>
			</div>
			<%} else {
			  %>
			  <img src="images/veeru.icon.jpg"  width="305px" height="170px"
				style="float: left;" />
			  
			 <!--  <jsp:include page="/poll.html"></jsp:include> -->
			  <%} %>
			</td>
			</tr>
			
			<tr>
			<td valign="top" style="padding-top: 0px;">
				
			<div id="bestplayer_table">
 				<table width="100%">
 				<tr >
				<th>
				Super Cric Team - IPL News Update
 				</th>
				</tr>
				
				<%
					int aaa = 1;
						if(sctNewsForms != null && sctNewsForms.size()>0) {
						for (SCTNewsForm newsForm : sctNewsForms) {
							aaa++;
				%>
				<tr>
					<td align="left" valign="top" style="padding: 4px;font-size: 12px;"><img src="images/rule.png" width="12px" height="12px" /> <%=newsForm.getNews()%></td>
				</tr>
				<%
					if (aaa > 10) {
								break;
							}
						} }
				%>

			</table>
			</div>
			
				</td>
			<td valign="top">
				
 				<div id="bestplayer_table">
 				<table >
 				<tr width="100%">
 				<th valign="top" width="100%"> <a href="viewTeamShedule.do">IPL 2012 Matches/Results</a></th>
 				
 				</tr>
				<%
					List<CurrentMatchSheduleForm> currentMatchSheduleForms =   frontPageSeriesDataElement.getCurrentMatchSheduleForms();
						if(currentMatchSheduleForms != null && currentMatchSheduleForms.size()>0){
						int ccc = 0;
						for (CurrentMatchSheduleForm teamSheduleForm : currentMatchSheduleForms) {
							ccc++;
								if(teamSheduleForm.isScoresGenerated())
								{
						%>	
						<tr>
							<td align="left" valign="top"><img
							src="images/rule.png" width="12px" height="12px" /> 
							<a href="viewMatchScores.do?id=<%=teamSheduleForm.getId()%>"  style="text-decoration: none">
								<font size="2px"><%=teamSheduleForm.getDate()%> <%=teamSheduleForm.getFirstTeamName()%>
							Vs <%=teamSheduleForm.getSecondTeamName()%> (<%=teamSheduleForm.getVenue()%>)</font></a></td>
						</tr>
						<%
							}
							else
							{
							  %>
							  <tr>
							<td align="left" valign="top"><img
							src="images/rule.png" width="12px" height="12px" /> <font size="2px"><%=teamSheduleForm.getDate()%> <%=teamSheduleForm.getFirstTeamName()%>
							Vs <%=teamSheduleForm.getSecondTeamName()%> (<%=teamSheduleForm.getVenue()%>)</font></div></td>
						</tr>
							  <% 
							}
							if (ccc > 3) {
								break;
							}
						}
						}
					%>

			</table>
			</div>
			</td>
			
		</tr>
		<!-- 
		<tr>
			<td align="center"><img src="images/sachinwc2.jpg" width="580px" height="300px"
				style="float: right :" />
			</td>
			<td align="center"><img src="images/iwc.jpg" width="305px" height="300px"
				style="float: right :" />
			</td>
		</tr>
		 -->
		<!--<tr>
			<td><img src="images/Sachin1.jpg" width="580px" height="300px"
				style="float: left;" /></td>
			<td><img src="images/sachin3.jpg" width="305px" height="300px"
				style="float: right :" /></td>
		</tr>
	--></table>
	<table>
		<tr>
		<td>
			<div id="harsh_header">
			<div id="title">
			<h1><a href="http://guessthelogo.blogspot.com/" target="_blank">Logo Quiz of the Day</a></h1>
			<font color="white">Tantalizing logo quiz every day.</font>
			</div>
			
			<div id="topright">Tease your brain with logo quiz everyday. Its Logo Quizzing at it's best. This blog is pure fun for quiz lovers and also logo lovers.  
			A logo is posted everyday and you are to guess the logo and post your replies in comments section. Correct answers will be posted the next day. 
			</div>
			</div>
	</td>
	</tr>
	</table>

</html:form>
</center>
</body>
</html>