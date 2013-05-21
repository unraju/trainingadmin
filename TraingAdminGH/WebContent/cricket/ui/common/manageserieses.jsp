<%@page import="common.util.RetriveContextData"%>
<%@page import="cricket.struts.actionforms.common.SeriesForm"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>

<html>

<body>

<center>
<div id="pageheader">Manage Series Data</div>

			<%
			  if (request.getAttribute(Constants.ERROR_MESSAGE) != null)
		    {
			%>
			<div id="messageStyle"><img
				src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px" />
			<%=request.getAttribute(Constants.ERROR_MESSAGE)%></div>
			<%
					  }
			%> 
			<%
			 	 List<SeriesForm> seriesForms = new RetriveContextData().getSeriesData();;
			%> 

<div id="tableStyle">
	<table>
		<tr>
		<th >No</th>
		<th >Series Namr</th>
		<th >Type</th>
		<th>Start Date</th>
		<th>End Date</th>
		<th>Venue</th>
		<th>Teams/Shedule Update</th>
		</tr>
		
			<%
				 int count =0;
				  for (SeriesForm form : seriesForms)
				    {
					  count++;
				%>
			<tr>
			<td><%=count %></td>
			<td><%=form.getName()%></td>
			<td><%=form.getSeriesType()%></td>
			<td><%=form.getStartDate()%></td>
			<td><%=form.getEndDate()%></td>
			<td><%=form.getVenue()%></td>
			<td width="200px" align="center">
			<div style="font-size: 12px; color: #0000FF"> Schedule - 
			<a href="manageShedule.do?seriedId=<%=form.getId()%>&operation=<%=Constants.VIEW%>">
			 View</a>   | 	<a href="manageShedule.do?seriedId=<%=form.getId()%>&operation=<%=Constants.UPDATE%>">
			Update
			</a></div>
			<div style="font-size: 12px; color: #0000FF"> Teams &nbsp;&nbsp;   - 
			<a href="viewCoreTeams.do?seriedId=<%=form.getId()%>&operation=<%=Constants.VIEW%>">
			 View</a>   | 	<a href="manageCoreTeams.do?seriedId=<%=form.getId()%>&operation=<%=Constants.UPDATE%>">
			Update
			</a></div>
			</td>
			</tr>
			<%
				  }
				%>
		
	</table>
</div>

	
</center>

</body>
</html>