<%@page import="common.util.UserUtil"%>
<%@page import="common.util.RetriveContextData"%>
<%@page import="cricket.struts.actionforms.common.SeriesForm"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cricket.struts.actionforms.team.CoreTeamForm"%>
<%@page import="java.util.List"%>
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
<html:form action="populateUserTeam.do">
			<%
				UserTeamForm userTeamForm = (UserTeamForm) session
							.getAttribute(Constants.USER_TEAM);
			    SeriesForm seriesForm =  new RetriveContextData().getCurrentSeriesForm();
			%>
	
			<div id="pageheader">
					My <%=seriesForm.getName() %> Team  - <%=userTeamForm.getName()%>
			</div>
		
			
			
			<html:hidden property="id" name="userTeamForm" />


	<table align="center">
		
		<tr>
			<td align="left">Available Substitutions </td>
			<td>: <%=userTeamForm.getAllowedSubstitutions()-userTeamForm.getUsedSubstitutions()%>
			</td>
		</tr>
		</table>
		
			<%
				if (session.getAttribute(Constants.ERROR_MESSAGE) != null) {
			%>
					<div id="messageStyle"><img
				src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px" />
			<%=session.getAttribute(Constants.ERROR_MESSAGE)%></div>
			<%
				}
			%>
			<%
				if (request.getAttribute(Constants.ERROR_MESSAGE) != null) {
			%>
					<div id="messageStyle"><img
				src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px" />
			<%=request.getAttribute(Constants.ERROR_MESSAGE)%></div>
			<%
				}
			%>
<div id="tableStyle"> 
		
	<table>

		<tr>
			<th>No</th>
			<th>Skill</th>
			<th width="150px">Player</th>
			<th width="150px">Team</th>
			<%
				if (session.getAttribute(Constants.OPERATION) != null
							&& session.getAttribute(Constants.OPERATION).equals(
									Constants.CHANGE_PLAYER)) {
			%>

			<th>Change Player</th>
			<%
				}
			%>
		</tr>
		<%
			int count = 0;
				for (PlayerForm playerForm : userTeamForm.getPlayers()) {
					count++;
		%>
		<tr>
			<td align="center" width="25px"><%=count%></td>
			<td width="75px"><%=playerForm.getSkill()%></td>
	
			<td width="150px"><%=playerForm.getName()%>
			<%
			  if (playerForm.isCaptain())
			  {
			%>
			 <font color="red">(C)</font>
			<%
			  }
			%>
			</td>
			<td width="125px">
			<%if(UserUtil.getSeries().intValue() != 1){ %>
			<%=playerForm.getCoreTeamName()%>
			<%} else
			  {%>
				<%=playerForm.getCountry()%>
			<%} %>
			</td>

			<%
			if (session.getAttribute(Constants.OPERATION) != null
					&& session.getAttribute(Constants.OPERATION)
										.equals(Constants.CHANGE_PLAYER)) {
			if(!playerForm.isCaptain()){
			  %>
			  	<td>
				<a href="populateUserTeam.do?selectedId=<%=playerForm.getId()%>&operation=<%=Constants.CHANGE_PLAYER%>" >
				<div style="font-size: 10px;color: #0000FF">
					Change Player / Select As Captain
				</div>
				</a>
				</td>
				<%}
					else
				  {%>
				  	<td>
				 <a href="populateUserTeam.do?selectedId=<%=playerForm.getId()%>&operation=<%=Constants.CHANGE_PLAYER%>" >
				<div style="font-size: 10px;color: #0000FF">
					Change Captain 
				</div>
				</a>
				</td>
				  <%} }%>
	
		</tr>
		<%
			}
		%>
	
	</table>
	</div>
	<br>
	<%
		if (session.getAttribute(Constants.OPERATION) != null
					&& session.getAttribute(Constants.OPERATION).equals(
							Constants.CHANGE_PLAYER)) {
	%>

		<!--<html:submit property="operation" value="Substitute Player/Captain"
		alt="Chnage Player" title="Submit" />-->
		<br><br>
		<div style="border: thin solid;border-color:gray;padding: 4px;background:#82CAFA;width:75%;" >
		<div align="left">Please Note -</div>
			<div  align="left" style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  	 Players changed before match scheduled time are only considered for that match.
		   </div>
		</div>
	<%
		} else if (session.getAttribute(Constants.OPERATION) != null
					&& session.getAttribute(Constants.OPERATION).equals(
							Constants.UPDATE)) {
	%>
		
		<html:submit property="operation" value="Click here to change Team" alt="Update"
		title="Submit" /><br><br>
		<div style="border: thin solid;border-color:gray;padding: 4px;background:#82CAFA;width: 55%;" >
		You are allowed to finalise your team until - <%=seriesForm.getStartDate() %>. After then change of any player considered as substitution.
		</div>
	<%
		}
	%>
</html:form>
</center>
</body>
</html>