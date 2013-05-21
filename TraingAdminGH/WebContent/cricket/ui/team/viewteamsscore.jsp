<%@page import="cricket.struts.actionforms.common.SunSignForm"%>
<%@page import="cricket.struts.actionforms.common.SeriesForm"%>
<%@page import="common.util.RetriveContextData"%>
<%@page import="cricket.struts.actionforms.team.UserTeamForm"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cricket.hibernate.bf.team.UserTeam"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>
	<center>
		<%
	    RetriveContextData contextData = new RetriveContextData();
	    SeriesForm seriesForm = contextData.getCurrentSeriesForm();
	    List<UserTeamForm> userTeams = contextData.getUserTeamScores(application);
	   %>
		<div id="bestplayer_table">
			<table>
				<tbody>
					<tr>
						<th width="400px" style="padding-left: 20px; padding-right: 20px;"
							align="center"><a href="viewLatestStandings.do?id=1"><%=seriesForm.getName()%>
								Latest Standings</a></th>
						<th width="400px" style="padding-left: 20px; padding-right: 20px;"
							align="center"><a href="viewLatestStandings.do?id=2">
								Standings Group By Sun Signs</a></th>
					</tr>
				</tbody>
			</table>

		</div>


		<%
		  String operation = (String) request.getParameter("id");
		    if (operation == null)
		    {
		 	 operation = "1";
		    }
		    if (operation.equals("1"))
		    {
		     	
		%>
		<div id="pageheader">
			<%=seriesForm.getName()%>
			Latest Standings
		</div>
		<div id="tableStyle">
			<table>
				<tr>
					<th width="35px">Sl.No</th>
					<th width="235px">Name</th>
					<th width="235px">Team Name</th>
					<th width="45px">Score</th>

				</tr>
				<%
		  		  int count = 0;
		  		  for (UserTeamForm userTeam : userTeams)
		  		  {
		  		    if (userTeam.isActive())
		  		    {
		  		      if (userTeam.getScore() == null)
		  		      {
		  		        userTeam.setScore(0l);
		  		      }
		  		      count++;
		  		%>
				<tr>
					<td><%=count%></td>
					<td><%=userTeam.getUserName()%></td>
					<td><%=userTeam.getName()%></td>
					<td><%=userTeam.getScore()%></td>
				</tr>

				<%
		  		  }
		  		  }
		  		%>

			</table>
		</div>
		<%
		  	  }
		  	    else if (operation.equals("2"))
				  	    {
								  	%>
						<div id="pageheader">
							<%=seriesForm.getName()%>
							Latest Standings Group By Sun Signs
						</div>
						
						<table width="100%">
			<tr>
			<%
					List<SunSignForm> sunSignForms = contextData.getSunSignData();
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
						
						<%
				        }
		     %>

	</center>
</body>
</html>