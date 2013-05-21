<%@page import="common.util.RetriveContextData"%>
<%@page import="cricket.struts.actionforms.player.PlayerScoresForm"%>
<%@page import="cricket.struts.actionforms.common.CountryForm"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cricket.hibernate.bf.common.Country"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="cricket.struts.actionforms.team.PlayerForm"%>

<%@page errorPage="/error.jsp"%>
<html>
<META HTTP-EQUIV="Refresh"
      CONTENT="<%=session.getMaxInactiveInterval() %>;URL=logout.do?sessionTimeout=true">
<body>

<center>
	<div id="pageheader">
		Update Player
	</div>
	
		<%
			if (request.getAttribute(Constants.ERROR_MESSAGE) != null) {
		%>
			<div id="messageStyle">
			<img src="images/warning_wipeout_invoiceDB.png" width="16px" height="16px" /> <%=request.getAttribute(Constants.ERROR_MESSAGE)%>
			</div>
		<%
			}
		%>
			<%
			 PlayerForm payerForm = (PlayerForm) request.getAttribute(Constants.PLAYER_FORM);
		     List<CountryForm> countries =  new RetriveContextData().getCountriesData();
		
			%>
	
<html:form action="updatePlayer.do">
		<div  id="newuser_registration">
		<table align="center">
		
		<tr>
			<td align="right"></td>
			<td align="left"><html:hidden property="id" 
				name="playerForm" />
			</td>
		</tr>
		<tr>
			<td align="right">Player Name:</td>
			<td align="left"><html:text property="name" 
				name="playerForm" size="30" maxlength="30" />
			<div style="color: red"><html:errors property="name" /></div>
			</td>
		</tr>
		
		<tr>
			<td align="right">Skill:</td>
			<td align="left">
							
			 <html:radio property="skill" value="<%=Constants.SKILL_BM%>" > Btm</html:radio>
			 <html:radio property="skill" value="<%=Constants.SKILL_BL%>" > Blr</html:radio>
			 <html:radio property="skill" value="<%=Constants.SKILL_WK%>" > Wkr</html:radio>
			<div style="color: red"><html:errors property="skill" /></div>
			</td>
		</tr>
		
		<tr>
		<td align="right">Country :</td>
		<td align="left">
		 	<html:select
				property="country" name="playerForm">
				<html:option value="Please Select..">Please Select..
				</html:option>
				<%
				  for (CountryForm country : countries)
				    {
				%>
				<html:option value="<%=country.getId()%>">
					<%=country.getCountry()%>
				</html:option>


				<%
				  }
				%>
			</html:select>
			<div style="color: red"><html:errors property="country" /></div>
		</td>
	</tr>
		
		<tr>
			<td align="right">Active :</td>
			<td>
			<html:radio name="playerForm" property="active" value="true"> Yes </html:radio>
			<html:radio name="playerForm" property="active" value="false"> No </html:radio>
			</td>
		</tr>
		
		<tr>
			<td align="right">ODI Matches :</td>
			<td align="left"><html:text property="matches" 
				name="playerForm" size="20" maxlength="20" />
			</td>
		</tr>
			<tr>
			<td align="right" width="150px">Runs :</td>
			<td align="left"><html:text property="runs" 
				name="playerForm" size="20" maxlength="20" />
			</td>
		</tr>
			<tr>
			<td align="right">Wickets :</td>
			<td align="left"><html:text property="wickets" 
				name="playerForm" size="20" maxlength="20" />
			</td>
		</tr>
			<tr>
			<td align="right">Centuries :</td>
			<td align="left"><html:text property="centuries" 
				name="playerForm" size="20" maxlength="20" />
			</td>
		</tr>
			<tr>
			<td align="right">Half Centuries :</td>
			<td align="left"><html:text property="halfCenturies" 
				name="playerForm" size="20" maxlength="20" />
			</td>
		</tr>
		
	</table>
	</div>
	<div id="tablestyle">
	<table>
		<tr>
		<th>Series</th>
		<th>Matches</th>
		<th>Runs </th>
		<th>Wickets</th>
		<th>100's'</th>
		<th>50's</th>
		</tr>
		<% for(PlayerScoresForm playerScoresForm:payerForm.getPlayerScores())
		{
		%>
		<input type="hidden"  name="<%=playerScoresForm.getSeriesTypeId()+"ID"%>"  value="<%=playerScoresForm.getId().toString()%>" size="5" />
		<tr>
		<td><%=playerScoresForm.getSeriesTypeName() %></td>
		<td><input type="text"  name="<%=playerScoresForm.getSeriesTypeId()+"M"%>"  value="<%=playerScoresForm.getMatches()%>" size="5" /></td>
		<td><input type="text"  name="<%=playerScoresForm.getSeriesTypeId()+"R"%>"  value="<%=playerScoresForm.getRuns()%>" size="5" /></td>
		<td><input type="text"  name="<%=playerScoresForm.getSeriesTypeId()+"W"%>"  value="<%=playerScoresForm.getWickets()%>" size="5"/></td>
		<td><input type="text"  name="<%=playerScoresForm.getSeriesTypeId()+"C"%>"  value="<%=playerScoresForm.getCenturies()%>" size="5"/></td>
		<td><input type="text"  name="<%=playerScoresForm.getSeriesTypeId()+"H"%>"  value="<%=playerScoresForm.getHalfCenturies()%>" size="5"/></td>
		</tr>
		<%} %>
	</table>
	
 
	
<html:submit property="operation" value="<%=Constants.SAVE_BTN_VALUE%>"></html:submit>
<html:submit property="operation"  value="<%=Constants.CANCEL_BTN_VALUE%>"></html:submit>
<html:reset/> <BR>
</div>
</html:form>
</center>
</body>
</html>