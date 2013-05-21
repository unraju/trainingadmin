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
<%try {  %>
<center>
	<div id="pageheader">
		 Update Players Scores
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
   			TeamSheduleForm teamSheduleForm = (TeamSheduleForm) session.getAttribute(Constants.MATCH_DETAILS);
		    List<PlayerMatchScoresForm> firstTeamPlayerMatchScores = (ArrayList<PlayerMatchScoresForm>) session.getAttribute(Constants.FIRST_TEAM_PLATER_MATCH_SCORES);
	 		List<PlayerMatchScoresForm> secondTeamPlayerMatchScores = (ArrayList<PlayerMatchScoresForm>) session.getAttribute(Constants.SECOND_TEAM_PLATER_MATCH_SCORES);
		%>
<html:form action="updatePlayersScores.do">
<div id="tableStyle"> 
	<table>
			<tr align="center">
				<td>Match Result Score : <html:text property="matchResult" 
				name="teamSheduleForm" size="90" maxlength="90" />
			<div style="color: red"><html:errors property="matchResults" /></div>
			</td>
		</tr>
		
		<tr align="center">
				<td>Man of the Match : <html:text property="manOfMatch" 
				name="teamSheduleForm" size="90" maxlength="90" />
			<div style="color: red"><html:errors property="manOfMatch" /></div>
			</td>
		</tr>

		<tr>
			<th align="center"><%=teamSheduleForm.getFirstTeamForm().getName()%>
			</th>
		</tr>
		<tr align="center">
			<td align="center">Team Score : <html:text property="firstTeamScore" 
				name="teamSheduleForm" size="25" maxlength="25" />
			<div style="color: red"><html:errors property="firstTeamScore" /></div></td>
		</tr>
		<tr align="center">
			<td align="center">Team Extras : <html:text property="firstTeamExtras" 
				name="teamSheduleForm" size="15" maxlength="15" />
			<div style="color: red"><html:errors property="firstTeamExtras" /></div></td>
		</tr>
		<tr>
			<td>
			<table>
				<tr>

					<th>Sl.No</th>
					<th align="center" >Player Name</th>
					<th align="center" >Runs</th>
					<th align="center" >Balls</th>
					<th align="center" >4's</th>
					<th align="center" >6's</th>
					<th align="center" >Wickets</th>
					<th align="center" >Catches</th>
				
				</tr>
				<%
				  int slno = 0;
				    for (PlayerMatchScoresForm playerForm : firstTeamPlayerMatchScores)
				    {
				        slno++;
				%>
				<tr>
					<td align="center"><%=slno%></td>
					<td><input type="text" disabled="disabled"
						name="<%=playerForm.getId()%>" value="<%=playerForm.getPlayerName()%>" /></td>
					<td><input type="text" name="<%=playerForm.getPlayerId()+"M2"%>"  value="<%=playerForm.getRuns()%>" /></td>
					<td><input type="text" name="<%=playerForm.getPlayerId()+"M5"%>" value="<%=playerForm.getBalls()%>" /></td>
					<td><input type="text" name="<%=playerForm.getPlayerId()+"M6"%>" value="<%=playerForm.getFours()%>" /></td>
					<td><input type="text" name="<%=playerForm.getPlayerId()+"M7"%>" value="<%=playerForm.getSixers()%>" /></td>
					<td><input type="text" name="<%=playerForm.getPlayerId()+"M3"%>" value="<%=playerForm.getWickets()%>" /></td>
					<td><input type="text" name="<%=playerForm.getPlayerId()+"M4"%>" value="<%=playerForm.getCatches()%>" /></td>
			

				</tr>

				<%
				    }
				%>
			</table>
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<th align="center"><%=teamSheduleForm.getSecondTeamForm().getName()%>
			</th>
		</tr>
		<tr>
			<td align="center">Team Score : <html:text property="secondTeamScore" 
				name="teamSheduleForm" size="25" maxlength="25" />
			<div style="color: red"><html:errors property="secondTeamScore" /></div></td>
		</tr>
		<tr>
			<td align="center">Team Extras : <html:text property="secondTeamExtras" 
				name="teamSheduleForm" size="15" maxlength="15" />
			<div style="color: red"><html:errors property="secondTeamExtras" /></div></td>
		</tr>
		<tr>
			<td>
			<table>
				<tr>
					<th>Sl.No</th>
					<th align="center" >Player Name</th>
					<th align="center" >Runs</th>
					<th align="center" >Balls</th>
					<th align="center" >4's</th>
					<th align="center" >6's</th>
					<th align="center" >Wickets</th>
					<th align="center" >Catches</th>
				
				</tr>
				<%
				  int slno1 = 0;
				    for (PlayerMatchScoresForm playerForm : secondTeamPlayerMatchScores)
				    {
				        slno1++;
				%>
				<tr>
					<td align="center"><%=slno1%></td>
					<td><input type="text" disabled="disabled"
						name="<%=playerForm.getId()%>" value="<%=playerForm.getPlayerName()%>" /></td>
					<td><input type="text" name="<%=playerForm.getPlayerId()+"M2"%>"  value="<%=playerForm.getRuns()%>" /></td>
					<td><input type="text" name="<%=playerForm.getPlayerId()+"M5"%>" value="<%=playerForm.getBalls()%>" /></td>
					<td><input type="text" name="<%=playerForm.getPlayerId()+"M6"%>" value="<%=playerForm.getFours()%>" /></td>
					<td><input type="text" name="<%=playerForm.getPlayerId()+"M7"%>" value="<%=playerForm.getSixers()%>" /></td>
					<td><input type="text" name="<%=playerForm.getPlayerId()+"M3"%>" value="<%=playerForm.getWickets()%>" /></td>
					<td><input type="text" name="<%=playerForm.getPlayerId()+"M4"%>" value="<%=playerForm.getCatches()%>" /></td>
					

				</tr>

				<%
				  }
				%>
			</table>
			</td>
		</tr>
	</table>
	<br>
	
	<br>
	<html:submit property="operation" value="Save" />
	<html:submit property="operation" value="Cancel" />
	</div>
</html:form>
</center>
<% } catch(Exception e)
{ e.printStackTrace();
}
%>
</body>
</html>