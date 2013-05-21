<%@page import="common.util.RetriveContextData"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="cricket.struts.actionforms.team.PlayerForm"%>
<%@page import="cricket.struts.actionforms.team.UserTeamForm"%>
<%@page import="cricket.struts.actionforms.team.CoreTeamForm"%>

<html>
<META HTTP-EQUIV="Refresh"
      CONTENT="<%=session.getMaxInactiveInterval()%>;URL=logout.do?sessionTimeout=true">

<script type="text/javascript">

	function showCaptainAlert() {

	alert('You are selected this player as Captain.');
	}
	
</script>
<body  onload="setFromTime();setTOTime();">
<center>
	<div id="pageheader">
		Change Player
	</div>
<div  id="change_players">


		<%
		  if (request.getAttribute(Constants.ERROR_MESSAGE) != null)
		    {
		%>
			<div id="messageStyle">
			<img  src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px" > <%=request.getAttribute(Constants.ERROR_MESSAGE)%>
			</div>
		<%
		  }
		%>

<html:form action="changeTeamPlayer.do">

	<table >
	
		<%
			  PlayerForm playerForm = (PlayerForm) session.getAttribute(Constants.PLAYER_FORM);
			  UserTeamForm userTeamForm = (UserTeamForm) session.getAttribute(Constants.USER_TEAM);
			  List<CoreTeamForm> coreTeamForms = new RetriveContextData().getCoreTeamDetails(application);
			%>
		
		
		<tr>
			<td align="right"> Player Name :</td>
			<td align="left"><%=playerForm.getName()%></td>
		</tr>
		<tr>
			<td align="right">Role :</td>
			<td align="left"><%=playerForm.getSkill()%></td>
		</tr>
		<tr>
			<td align="right">Available Substitutions :</td>
			<td align="left"><%=userTeamForm.getAvilableSubstitutions()%></td>
		</tr>
		<tr>
			<td align="right">Selecte As Captain :</td>
			<td align="left">
				<%
				  if (!playerForm.isCaptain())
				  {
				%>
				
				<html:radio name="playerForm" property="captain" value="true" onclick="showCaptainAlert()"> Yes </html:radio>
				<html:radio name="playerForm" property="captain" value="false"> No </html:radio>
				
				<%
								  }
								  else
								  {
								%>
				<html:radio disabled="true" name="playerForm" property="captain" value="true"> Yes </html:radio>
				<html:radio disabled="true" name="playerForm" property="captain" value="false"> No </html:radio>
				<%
				  }
				%>
			</td>
		</tr>

		<tr>
			<td align="right">Select New Player :</td>
			<td><select name="newPlayer">
				<option value="Please Select" selected="selected">Please Select</option>
				<%
				  if (!userTeamForm.isBlockSubstitutions())
				  {
				    for (CoreTeamForm coreTeamForm : coreTeamForms)
				    {
				      if (coreTeamForm.isActive())
				      {
				        for (PlayerForm playerForm1 : coreTeamForm.getPlayers())
				        {
				          if (playerForm1.isActive() && playerForm.getSkill().equals(playerForm1.getSkill()) && (playerForm.getId().intValue() != playerForm1.getCoreTeamPlayerId().intValue()))
				          {
				%>
					<option value="<%=playerForm1.getCoreTeamPlayerId().toString()%>"><%=(coreTeamForm.getCode() + " - " + playerForm1.getName())%>
					</option>
				<%
				  }
				        }
				      }
				    }
				  }
				%>
			</select>
					<%
					  if (request.getAttribute(Constants.ERROR_MESSAGE_2) != null)
					  {
					%>
			<div id="messageStyle">
			<img  src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px" > <%=request.getAttribute(Constants.ERROR_MESSAGE_2)%>
			</div>
		<%
		  }
		%>
			</td>
		</tr>

		<tr align="right">
			<td></td>
			<td></td>
		</tr>
	</table>
	<br>
	<html:submit property="operation" value="Confirm" alt="Confirm"
		title="Confirm"   /> <html:submit property="operation" value="Cancel"
		alt="Cancel" title="Cancel" /> <html:reset></html:reset>
</html:form>
</div>
<br>
<br>
		<div style="border: thin solid;border-color:gray;padding: 4px;background:#82CAFA; ;width: 70%;" >
			<div align="left">Please Note -</div>
			<div  align="left" style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Selecting new player will cost  25 points and one substitution is deducted.</div>
			<div  align="left" style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Selecting current player as a Captain will cost only 50 points.</div>
			<div  align="left" style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Selecting new player and designated as Captain will cost 75 points and one substitution is deducted..</div>
		</div>
</center>
</body>
</html>