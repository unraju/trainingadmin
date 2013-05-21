<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  try
  {
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cricket.struts.actionforms.team.TeamSheduleForm"%>
<%@page import="cricket.struts.actionforms.team.CoreTeamForm"%>
<%@page errorPage="/error.jsp"%>
<html>
<head>
<script type="text/javascript" src="script/autosuggest2.js"></script>
<script type="text/javascript" src="script/suggestions2.js"></script>
<script type="text/javascript">
	<%
	  List<TeamSheduleForm> teamSheduleForms = (ArrayList<TeamSheduleForm>) session.getAttribute(Constants.TEAM_SHEDULE);
		String  loadFunctionString = "";
		for (TeamSheduleForm teamSheduleForm : teamSheduleForms)
	{
		  loadFunctionString=loadFunctionString+ "set"+"T"+teamSheduleForm.getId().toString()+"()"+";";
	%>
function  set<%="T"+teamSheduleForm.getId().toString()+"()"%> {
  var oTextbox = new AutoSuggestControl(document.getElementById("<%="T"+teamSheduleForm.getId().toString()%>"), new StateSuggestions());        
}

<% } %>
</script>
<SCRIPT language="JavaScript" src="js/billpointeutil.js" type="text/javascript"></SCRIPT>
<SCRIPT language="JavaScript" src="js/scw.js" type="text/javascript"></SCRIPT>
</head>

<body onload="<%=loadFunctionString%>">
<center>
	<div id="pageheader">
		 Update Match Schedule
	</div>

<%
	if (request.getAttribute(Constants.VALIDATION_MESSAGE) != null) {
%>
	<div id="messageStyle">
	<img src="images/warning_wipeout_invoiceDB.png" width="16px" height="16px" /> <%=request.getAttribute(Constants.VALIDATION_MESSAGE)%>
	</div>
<%
	}
%> 
	
<html:form action="updateTeamShedule.do">
<div id="tableStyle"> 
<table>
	<tr>
		<th>Sl.No</th>
		<th width="80px">Match </th>
		<th width="140px">First Team</th>
		<th width="140px">Second Team</th>
		<th width="200px">Date</th>
		<th width="80px">Venue</th>
			
	</tr>
	<%
	  int count = 0;
	      for (TeamSheduleForm teamSheduleForm : teamSheduleForms)
	      {
	        count++;
	%>
	<tr>
		<td align="center"><%=count%></td>
		<td><input type="text"  name="<%=teamSheduleForm.getId().toString()+"T1"%>"  value="<%=teamSheduleForm.getMatchName()%>" /></td>
			<td align="center"><select name="<%=teamSheduleForm.getId().toString()+"T2"%>">
				<%
				  for (CoreTeamForm coreTeamForm : (ArrayList<CoreTeamForm>) session.getAttribute(Constants.SEARCHED_CORE_TEAMS))
				        {
				          if (teamSheduleForm.getFirstTeamForm() != null && teamSheduleForm.getFirstTeamForm().getId() != null
				              && teamSheduleForm.getFirstTeamForm().getId().longValue() == coreTeamForm.getId().longValue())
				          {
				%>
				<option value="<%=String.valueOf(coreTeamForm.getId())%>"
					selected="selected"><%=coreTeamForm.getName()%></option>
				<%
				  }
				          else
				          {
				%>
				<option value="<%=String.valueOf(coreTeamForm.getId())%>">
				<%=coreTeamForm.getName()%></option>
				<%
				  }
				%>
				<%
				  }
				%>
			</select></td>
			<td align="center"><select name="<%=teamSheduleForm.getId().toString()+"T3"%>">
				<%
				  for (CoreTeamForm coreTeamForm : (ArrayList<CoreTeamForm>) session.getAttribute(Constants.SEARCHED_CORE_TEAMS))
				        {
				          if (teamSheduleForm.getSecondTeamForm() != null && teamSheduleForm.getSecondTeamForm().getId() != null
				              && teamSheduleForm.getSecondTeamForm().getId().longValue() == coreTeamForm.getId().longValue())
				          {
				%>
				<option value="<%=String.valueOf(coreTeamForm.getId())%>"
					selected="selected"><%=coreTeamForm.getName()%></option>
				<%
				  }
				          else
				          {
				%>
				<option value="<%=String.valueOf(coreTeamForm.getId())%>">
				<%=coreTeamForm.getName()%></option>
				<%
				  }
				%>
				<%
				  }
				%>
			</select></td>
		<td width="200px"  >
			<input type="text"  name="<%=teamSheduleForm.getId().toString()+"T4"%>"   value="<%=teamSheduleForm.getDate()%>" id="<%="fromdateID"+count%>" size="10"
				onkeyup="maskDate(this)" onblur="reformatDate(this)" 
				styleClass="textBoxExtraSmall alignRight" ></input>
				<label> <img
				src="images/datepicker.gif" 
				onclick="scwShow(scwID('<%="fromdateID"+count%>'),event);" /> </label>
				
				 <input type="text" size="6"  name="<%=teamSheduleForm.getId().toString()+"T7"%>" id="<%="T"+teamSheduleForm.getId().toString()%>" value="<%=teamSheduleForm.getTime()%>"	style="textBoxSmall"  maxlength="5"></input>
			 <!-- 
				 <select name="<%=teamSheduleForm.getId().toString()+"T7"%>" id="<%="T"+teamSheduleForm.getId().toString()%>" >
				 
				 <option value="09:30"> 09:30 </option>
				 <option value="14:30"> 14:30</option>
				 </select>
				  -->
		</td>
		<td ><input type="text"  name="<%=teamSheduleForm.getId().toString()+"T5"%>"  value="<%=teamSheduleForm.getVenue()%>"/></td>
		
	</tr>

	<%
	  }
	%>
		
</table>
</div>

	<br>
	<html:submit property="operation" value="Save"/>
	<html:submit property="operation" value="Cancel"/>
</html:form>
</center>
</body>
</html>
<%
  }
  catch (Exception e)
  {
    e.printStackTrace();
  }
%>