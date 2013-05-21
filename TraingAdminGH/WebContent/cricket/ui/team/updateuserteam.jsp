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
<%@page errorPage="/error.jsp"%>
<html>
<META HTTP-EQUIV="Refresh"
      CONTENT="<%=session.getMaxInactiveInterval() %>;URL=logout.do?sessionTimeout=true">
<body>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<center><html:form action="updateUserTeam.do">
	<br>
	<H3 align="center">Create My Team </H3>

	<div
		style="border: solid; border: thin; border-color: silver; width: 70%; padding: 8px; color: black; vertical-align: middle"
		align="center"><br>
	<%
			if (request.getAttribute(Constants.ERROR_MESSAGE) != null) {
	%>
		<div style="color: red" align="center" >
			<img align="bottom"  src="images/warning_wipeout_invoiceDB.png" width="20px" height="18px" > <%=request.getAttribute(Constants.ERROR_MESSAGE)%>
		</div>
			<%
				}
			%> 
	<table align="center">
	
		<tr>
			<td align="right"></td>
			<td align="left"><html:hidden property="id" name="userTeamForm" />
			</td>
		</tr>
		<tr>
			<td align="right">Team Name:</td>
			<td align="left"><html:text property="name" name="userTeamForm"
				size="20" maxlength="20" />
			<div style="color: red"><html:errors property="name" /></div>
			</td>
		</tr>


	</table>
	</div>

	<table cellpadding="0" border=".1">

		<tr>
			<th>Sl.No</th>
			<th>Skill</th>
			<th width="150px">Player</th>
			<th>Captain</th>
		</tr>
		<%
		List<CoreTeamForm> coreTeamForms = (ArrayList<CoreTeamForm>) application
		.getAttribute(Constants.SEARCHED_CORE_TEAMS);
		UserTeamForm userTeamForm = (UserTeamForm) session.getAttribute(Constants.USER_TEAM);
	    int count = 0;
	    for (PlayerForm playerForm : userTeamForm.getPlayers())
	    {
	      count++;
		%>
		<tr>
			<td align="center"><%=count%></td>
			<td width="75px"><input type="text" disabled="disabled" name=""
				value="<%=playerForm.getSkill()%>" size="15" /></td>
	
			<td align="center">
			<select
				name="<%=playerForm.getId().toString()+"P1"%>" >
				<option value="Please Select">Please Select</option>
				<%
						for(CoreTeamForm coreTeamForm:coreTeamForms)
						{
						for (PlayerForm playerForm1 : coreTeamForm.getPlayers())
						{
							if(playerForm.getSkill().equals(playerForm1.getSkill()))  
							{ 
							  if(playerForm.getId().longValue() == playerForm1.getId().longValue())
							  {
				%>
					<option value="<%=playerForm1.getId().toString()%>" selected="selected">
						<%=(coreTeamForm.getCode()+" - "+playerForm1.getName())%>
					</option>
				<%
					} else
						{
				  	%>
						<option value="<%=playerForm1.getId().toString()%>"> 
									<%=(coreTeamForm.getCode()+" - "+playerForm1.getName())%>
						</option>
				<%
				} } } }
				%>
			</select></td>

			<td align="center">
			<% if(playerForm.isCaptain()) 
				{ %>
				<input type="radio" checked="checked" name="captain" value="<%=playerForm.getId().toString()%>" />
				<% } else {
			 	 %>
				<input type="radio"  name="captain" value="<%=playerForm.getId().toString()%>" />
				<%
				}
			%>
				</td>
		</tr>
		<%
		  }
	    %>
	
	</table>
	<br>
	<html:submit property="operation" value="Confirm" alt="Submit"
		title="Submit" />
		<!-- 
		<html:submit property="operation" value="Cancel" alt="Cancel"
		title="Submit" />
		 -->
</html:form>
</center>
</body>
</html>