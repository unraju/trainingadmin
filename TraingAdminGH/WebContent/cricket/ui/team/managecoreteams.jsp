<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="cricket.struts.actionforms.team.CoreTeamForm"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<center>
	<div id="pageheader">
		Manage Core Teams
	</div>
	
		<%
				if (request.getAttribute(Constants.VALIDATION_MESSAGE) != null) {
		%>
			<div id="messageStyle">
			<img src="images/warning_wipeout_invoiceDB.png" width="16px" height="16px" /> 
			<%=request.getAttribute(Constants.VALIDATION_MESSAGE)%>
			</div>
		<%
			}
		%>

<html:form action="prepopulateCoreTeam.do">
		
<div id="tableStyle">
<table>
	
	<tr>
		<th width="150px">Name</th>
		<th width="150px">Country</th>
		<th width="150px">Group</th>
		<th>Active</th>
		<th>Update</th>
			
	</tr>
	<%
		List<CoreTeamForm> coreTeamForms = (ArrayList<CoreTeamForm>) session
		.getAttribute(Constants.SEARCHED_CORE_TEAMS);
		for (CoreTeamForm coreTeamForm  : coreTeamForms) 
		{
	%>
	<tr>
		
		<td ><%=coreTeamForm.getName()%></td>
		<td><%=coreTeamForm.getCountry()%></td>
		<td><%=coreTeamForm.getGroupName()%></td>
		<td><%=coreTeamForm.getActiveString()%></td>
		<td align="center"><a href="prepopulateCoreTeam.do?id=<%=coreTeamForm.getId()%>&operation=Update">
				<div style="font-size: 13px;color: #0000FF">
					Change
				</div>
				</a></td>
	</tr>

	<% } %>
		
</table>
</div>

	<br>
	<html:submit property="operation" value="Add" />
</html:form>
</center>
</body>
</html>