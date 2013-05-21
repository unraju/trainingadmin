
<%@page import="cricket.struts.actionforms.common.SunSignForm"%>
<%@page import="common.util.RetriveContextData"%>
<%@page import="cricket.struts.actionforms.team.UserTeamForm"%>
<%@page import="java.util.List"%>
	
	
	<% 
	  	List<SunSignForm> sunSignForms = contextData.getSunSignData();
	 %>
	 
	<table width="100%">
			<tr>
			<%
					int size = 0;
					for(SunSignForm signForm:sunSignForms)
					{
					  int count = 0;
						size++;
						if (size > 3)
						{
							%>
								<tr>
							<%
						}
					%>
						<td style="width: 270px" valign="top" >
						  <div id="tableStyle">
							<table width="270px" style="border: thin;border-color: black;">
							<tr style="width: 270px" >
								<th width="270px;"> <%=signForm.getName() %></th>
							</tr>
								<% 
									
									for (UserTeamForm userTeam : userTeams)
    								{
									
								  if(userTeam.getSunsignId().intValue() ==  signForm.getId().intValue()) {
								    count++;%>
									<tr>
										<td width="270px"><%=count%>. <%=userTeam.getName()%> (<%=userTeam.getUserName()%>) - <%=userTeam.getScore()%></td>
									</tr>
								<%} }%>
							</table>
							</div>
						</td>
						<%
							if (size == 3) 
							{
								size = 0;
								%>
									</tr>
								<%
							}
								}
						%>
			</table>	

	
	
	
	
	
