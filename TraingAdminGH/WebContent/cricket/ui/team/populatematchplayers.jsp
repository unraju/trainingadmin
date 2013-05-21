<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="java.util.Date"%>
<%@page import="cricket.struts.actionforms.team.TeamSheduleForm"%>

<%@page import="cricket.struts.actionforms.team.PlayerForm"%>
<%@page errorPage="/error.jsp"%>

<html>
<body>

<center>
<div id="pageheader">
		 Selected Played Players
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
		%>
<html:form action="populateSelectedMatchPlayers.do">		
<div id="tableStyle"> 
<table>
	<tr>
		<th align="center"> <%=teamSheduleForm.getFirstTeamForm().getName().trim()%> </th>
		<th></th>
		<th align="center"> <%=teamSheduleForm.getSecondTeamForm().getName().trim()%> </th>
	</tr>		
	<tr>
	<td>
	<div id="tableStyle"> 
	<table>		
	<tr>
		<th >Select</th>
		<th >order</th>
		<th>Sl.No</th>
		<th>Player Name</th>
	</tr>
		<%
		
		int slno=0;
		for (PlayerForm playerForm  : teamSheduleForm.getFirstTeamForm().getPlayers()) 
		{
		  slno++;
	%>
	<tr>
		<% if(playerForm.isSelected()) {%>
		<td align="center"><input type="checkbox" checked="checked" name="<%=playerForm.getCoreTeamPlayerId()+"M1"%>"  value="Selected"/></td>
		<% } %>
		<% if(!playerForm.isSelected()) {%>
		<td align="center"><input type="checkbox"  name="<%=playerForm.getCoreTeamPlayerId()+"M1"%>"  value="Selected"/></td>
		<% } %>
		<td align="center" dth="15px;"><input type="text"  name="<%=playerForm.getCoreTeamPlayerId()+"M2"%>" value="<%=playerForm.getOrder()%>" /></td>
		<td align="center"><%=slno%></td>
		<td><input type="text"  disabled="disabled" name="<%=playerForm.getCoreTeamPlayerId()%>"  value="<%=playerForm.getName().trim()%>"/></td>
	</tr>

	<% } %>
		</table>
		</div>
		</td>
		<td width="20px"></td>
		<td>
		<div id="tableStyle"> 
			<table>		
			<tr>
				<th >Select</th>
				<th >order</th>
				<th>Sl.No</th>
				<th width="150px">Player Name</th>
			</tr>
			<%
				int slno1=0;
				for (PlayerForm playerForm  : teamSheduleForm.getSecondTeamForm().getPlayers()) 
				{
				  slno1++;
			%>
			<tr>
				<% if(playerForm.isSelected()) 
				{%>
				<td align="center"><input type="checkbox" checked="checked" name="<%=playerForm.getCoreTeamPlayerId()+"M1"%>"  value="Selected"/></td>
				<% } %>
				<% if(!playerForm.isSelected()) 
				{%>
				<td align="center"><input type="checkbox"  name="<%=playerForm.getCoreTeamPlayerId()+"M1"%>"  value="Selected"/></td>
				<% } %>
				<td align="center" width="15px"><input type="text"  name="<%=playerForm.getCoreTeamPlayerId()+"M2"%>"  value="<%=playerForm.getOrder()%>" /></td>
				<td align="center"><%=slno1%></td>
				<td><input type="text"  disabled="disabled" name="<%=playerForm.getCoreTeamPlayerId()%>"  value="<%=playerForm.getName().trim()%>"/></td>
			</tr>
		
			<% } %>
				</table>
				</div>
		</td>
		</tr>
</table>
</div>
	<br>
	<html:submit property="operation" value="Update Team Scores"/>
	<html:submit property="operation" value="Cancel"/>
	<html:reset></html:reset>
</html:form>

</center>
</body>
</html>