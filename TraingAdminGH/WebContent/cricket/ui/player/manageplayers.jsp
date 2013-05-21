<%@page import="common.util.RetriveContextData"%>
<%@page import="cricket.struts.actionforms.common.CountryForm"%>
<%@page import="java.util.List"%>
<%@page import="cricket.hibernate.bf.common.Country"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="cricket.struts.actionforms.team.PlayerForm"%>

<%@page errorPage="/error.jsp"%>
<html>
<META HTTP-EQUIV="Refresh"
      CONTENT="<%=session.getMaxInactiveInterval()%>;URL=logout.do?sessionTimeout=true">
<body>

<center>
	<div id="pageheader">
		Manage Players
	</div>
	<div  id="search_players">
	
		<%
		  if (request.getAttribute(Constants.ERROR_MESSAGE) != null)
		    {
		%>
			<div id="messageStyle">
			<img src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px" /> <%=request.getAttribute(Constants.ERROR_MESSAGE)%>
			</div>
		<%
		  }
		%>
			<%
			 	 PlayerForm payerForm = (PlayerForm) request.getAttribute(Constants.PLAYER_FORM);
			    List<CountryForm> countries = new RetriveContextData().getCountriesData();
			%>
	
<html:form action="managePlayers.do">
	
<table>
	<tr>
		<td align="right">Player Name : </td><td align="left">
		<html:text property="name" name="playerForm" size="20"/></td>
	</tr>
		
	<tr>
		<td align="right">Country :</td>
		<td align="left">
		 	<html:select
				property="country" name="playerForm">
				<html:option value="All">All
				</html:option>
				<%
				  for (CountryForm country : countries)
				    {
				%>
				<html:option value="<%=country.getId().toString()%>">
					<%=country.getCountry()%>
				</html:option>

				<%
				  }
				%>
			</html:select>
				
		</td>
	</tr>
	
	<tr>
		<td align="right">Skill :</td><td align="left"> 
		
			<html:radio property="skill"  value="<%=Constants.SKILL_BM%>" > Btm</html:radio>
			 <html:radio property="skill" value="<%=Constants.SKILL_BL%>" > Blr</html:radio>
			 <html:radio property="skill" value="<%=Constants.SKILL_WK%>" > Wkr</html:radio>
			  <html:radio property="skill" value="All" > All</html:radio>
	
		</td>
	</tr>

		<tr>
			<td align="right">Active :</td>
			<td align="left">
			<html:radio name="playerForm" property="active" value="true" > All </html:radio>
			<html:radio name="playerForm" property="active" value="false" disabled="true"> Yes </html:radio>
			<html:radio name="playerForm" property="active" value="false" disabled="true"> No </html:radio>
		</tr>

	</table>

<html:submit property="operation"  value="<%=Constants.ADD_BTN_VALUE%>"></html:submit>
<html:submit property="operation"  value="<%=Constants.SEARCH_BTN_VALUE%>"></html:submit>
<html:reset/> <BR>
</div>
</html:form>
		
<%@ include file="viewplayerdetails.jsp"%>

</center>

</body>
</html>