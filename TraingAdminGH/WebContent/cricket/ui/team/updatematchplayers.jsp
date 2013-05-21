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

<div style="border:0.5px;border-color:silver;width:80%;padding:5px;">
<html:form action="updateTeamShedule.do">
<table cellpadding="0"  >

	<tr>
	<td align="center"> <%=teamSheduleForm.getFirstTeamForm().getName()%> </td>
	<td></td>
	<td align="center"> <%=teamSheduleForm.getSecondTeamForm().getName()%> </td>
	</tr>		
	<tr>
	<td>
	<table cellpadding="0"  border=".1">		
	<tr>
		<th >Select</th>
		<th>Sl.No</th>
		<th width="150px">Player Name</th>
	</tr>
		<%
			int slno = 0;
				for (PlayerForm coreTeamForm : teamSheduleForm.getFirstTeamForm().getPlayers())
				{
					slno++;
		%>
	<tr>
		<td align="center"><input type="checkbox"  name="<%=coreTeamForm.getId() + "M1"%>"  value=""/></td>
		<td align="center"><%=slno%></td>
		<td><%=coreTeamForm.getName()%></td>
	</tr>

	<%
		}
	%>
		</table>
		</td>
		<td width="20px"></td>
		<td>
	<table cellpadding="0"  border=".1">		
	<tr>
		<th >Select</th>
		<th>Sl.No</th>
		<th width="150px">Player Name</th>
	</tr>
	<%
		int slno1 = 0;
			for (PlayerForm coreTeamForm : teamSheduleForm.getSecondTeamForm().getPlayers())
			{
				slno1++;
	%>
	<tr>
		<td align="center"><input type="checkbox"  name="<%=coreTeamForm.getId() + "M1"%>"  value=""/></td>
		<td align="center"><%=slno1%></td>
		<td style="background-color: white;"><%=coreTeamForm.getName()%></td>
	</tr>

	<%
		}
	%>
		</table>
		</td>
		</tr>
</table>
	<br>
	<html:submit property="operation" value="Update Team Scores"/>
	<html:submit property="operation" value="Cancel"/>
	<html:reset></html:reset>
</html:form>
</div>
</center>
</body>
</html>