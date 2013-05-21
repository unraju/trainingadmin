<%@page import="common.util.RetriveContextData"%>
<%@page import="cricket.struts.actionforms.common.SeriesForm"%>
<%@page import="cricket.struts.actionforms.common.CountryForm"%>
<%@page import="cricket.hibernate.bf.common.Series"%>
<%@page import="cricket.hibernate.bf.common.Country"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="cricket.struts.actionforms.team.PlayerForm"%>
<%@page import="cricket.struts.actionforms.team.CoreTeamForm"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>
<center>
<%  try
{%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<div id="pageheader">
		<%=session.getAttribute(Constants.OPERATION)%> Core Team Details
	</div>
	<div  id="updateCoreTeam">
	<%
		  if (request.getAttribute(Constants.ERROR_MESSAGE) != null)
		    {
		%>
			<div id="messageStyle">
			<img src="images/warning_wipeout_invoiceDB.png" width="16px" height="16px" /> <%=request.getAttribute(Constants.ERROR_MESSAGE)%>
			</div>
		<%
		  }
		%>
		<%
		 List<CountryForm> countries =  new RetriveContextData().getCountriesData();
		List<SeriesForm> serieses = new RetriveContextData().getSeriesData();
		  
		 %>

<html:form action="updateCoreTeams.do">
	
	
	<table align="center">
			
		<tr>
			<td align="right"></td>
			<td align="left"><html:hidden property="id" 
				name="coreTeamForm" />
			</td>
		</tr>
		<tr>
			<td align="right">Team Name:</td>
			<td align="left"><html:text property="name" 
				name="coreTeamForm" size="30" maxlength="30" />
			<div style="color: red"><html:errors property="name" /></div>
			</td>
		</tr>
		
		<tr>
			<td align="right">Short Name:</td>
			<td align="left"><html:text property="code" 
				name="coreTeamForm" size="20" maxlength="20" />
			<div style="color: red"><html:errors property="role" /></div>
			</td>
		</tr>
		<tr>
			<td align="right">Players Count:</td>
			<td align="left"><html:text property="playersCount" 
				name="coreTeamForm" size="20" maxlength="20" />
			<div style="color: red"><html:errors property="playersCount" /></div>
			</td>
		</tr>
		
		<tr>
			<td align="right">Country :</td>
			<td>
			<html:select
				property="countryId" name="coreTeamForm">
				<html:option value="Please Select">Please Select..
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
			<div style="color: red"><html:errors property="countryId" /></div>
			</td>
		</tr>
		
		
		<tr>
			<td align="right">Series :</td>
			<td>
			<html:select
				property="seriesId" name="coreTeamForm">
				<html:option value="Please Select">Please Select..
				</html:option>
				<%
				  for (SeriesForm series : serieses)
				    {
				%>
				<html:option value="<%=series.getId().toString()%>">
					<%=series.getName()%>
				</html:option>

				<%
				  }
				%>
			</html:select>
			<div style="color: red"><html:errors property="seriesId" /></div>
			</td>
		</tr>
		
		<!--
		
		
		<tr>
			<td align="right">City :</td>
			<td align="left"><html:text property="city" 
				name="coreTeamForm" size="20" maxlength="20" />
			<div style="color: red"><html:errors property="role" /></div>
			</td>
		</tr>
		
		<tr>
			<td align="right">Owner :</td>
			<td align="left"><html:text property="owner" 
				name="coreTeamForm" size="20" maxlength="20" />
			<div style="color: red"><html:errors property="role" /></div>
			</td>
		</tr>
		-->
			<tr>
			<td align="right">Active :</td>
			<td>
			<html:radio name="coreTeamForm" property="active" value="true"> Yes </html:radio>
			<html:radio name="coreTeamForm" property="active" value="false"> No </html:radio>
			</td>
			<tr>
			<td align="right">Group :</td>
			<td>
			<html:radio name="coreTeamForm" property="groupId" value="1"> Team A </html:radio>
			<html:radio name="coreTeamForm" property="groupId" value="2"> Team B </html:radio>
			<html:radio name="coreTeamForm" property="groupId" value="None"> None </html:radio>
			</td>
		</tr>

	</table>
	</div>

<div id="tableStyle"> 	
	<table>
	<tr>
			<th>Sl.No</th><!--
			<th>Country</th>
			--><th width="150px">Player</th>
			<th width="150px">Active</th>
			<th width="150px">Captain</th>
		
		</tr>
		<%
		 CoreTeamForm tempCoreTeamForms = (CoreTeamForm) session.getAttribute(Constants.CORE_TEAM_FORM);
			List<PlayerForm> corePlayers=(ArrayList<PlayerForm>)session.getAttribute(Constants.SEARCHED_PLAYERS);
		 int count = 0;
	    for (PlayerForm playerForm : tempCoreTeamForms.getPlayers())
	    {
	      //System.out.println("### playerForm ###"+playerForm.getId());
	      //System.out.println("### count ###"+count);
	      count++;
		%>
		<tr>
			<td align="center" width="50px"><%=count%></td>
			
			
			<!--<td align="center" width="150px">
			<select
				name="<%=playerForm.getId().toString()+"C1"%>" id="<%=playerForm.getId().toString()+"C1"%>"
				<%=playerForm.getId().toString()+"P1"%>">
				<option value="Please Select">Please Select</option>
				<%
						for(CountryForm country:countries)
						{
					%>
					<option value="<%=playerForm.getId().toString()%>" >
						<%=country.getCountry()%>
					</option>
				<%
				 } 
				%>
			</select></td>
	
			--><td align="center" width="150px">
			<select
				name="<%=playerForm.getId().toString()+"P1"%>" >
				<option value="Please Select">Please Select</option>
				<%
						
						for (PlayerForm playerForm1 : corePlayers)
						{
							 if(playerForm.getCoreTeamPlayerId()!= null && playerForm.getCoreTeamPlayerId().longValue() == playerForm1.getId().longValue())
							  {
				%>
					<option value="<%=playerForm1.getId().toString()%>" selected="selected" >
						<%=(playerForm1.getCountryShortName()+" - "+playerForm1.getName())%>
					</option>
				<%
					} else
						{
				  	%>
						<option value="<%=playerForm1.getId().toString()%>"> 
									<%=(playerForm1.getCountryShortName()+" - "+playerForm1.getName())%>
						</option>
				<%
				} } 
				%>
			</select></td>

			<td>
			<%
			  if (playerForm.isActive())
			      {
			%>
			<input type="radio" checked="checked" name="<%=playerForm.getId().toString()+"P4"%>"  value="true"> Yes 
			<input type="radio"  name="<%=playerForm.getId().toString()+"P4"%>"  value="false"> No 
			<%
			  }
			      if (!playerForm.isActive())
			      {
			%>
			<input type="radio"  name="<%=playerForm.getId().toString()+"P4"%>"  value="true"> Yes 
			<input type="radio" checked="checked" name="<%=playerForm.getId().toString()+"P4"%>"  value="false"> No 
			<%
			  }
			%>
			</td>
			
					<td>
			<%
			  if (playerForm.isCaptain())
			      {
			%>
			<input type="radio" checked="checked" name="<%=playerForm.getId().toString()+"P5"%>"  value="true"> Yes  
			<input type="radio"  name="<%=playerForm.getId().toString()+"P5"%>"  value="false"> No 
			<%
			  }
			      if (!playerForm.isCaptain())
			      {
			%>
			<input type="radio"  name="<%=playerForm.getId().toString()+"P5"%>"  value="true"> Yes 
			<input type="radio" checked="checked" name="<%=playerForm.getId().toString()+"P5"%>"  value="false"> No 
			<%
			  }
			%>
			</td>
			
		</tr>
		<%
		  }
	    %>
	
		
	</table>
</div>
<br>
	<html:submit property="operation" value="Save" alt="Submit"
		title="Submit" />
	<html:submit property="operation" value="Cancel" alt="Cancel"
		title="Cancel" />

</html:form></center>
</body>
</html>
<% } catch(Exception e)
{ e.printStackTrace();}
%>

