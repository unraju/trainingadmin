<%@page import="cricket.struts.actionforms.team.ScoreReportForm"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.util.DateUtil"%>
<%@page import="cricket.hibernate.bf.team.ScoreReport"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>
<center>
	<div id="pageheader">
		Generate Score Report
	</div>

<html:form action="generateScoreReport.do">
<div id="tableStyle"> 
	<table>
		<tr>
			<th>Sl.No</th>
			<th >Date</th>
			<th >Executed</th>
			<th >Status</th>
			<th >Description</th>
			
		</tr>
		<%
		  List<ScoreReportForm> scoreReports = (ArrayList<ScoreReportForm>) session.getAttribute(Constants.SCORE_REPORTS);
		    int count = 0;
		    if(scoreReports != null && scoreReports.size()>0)
		    {
		    for (ScoreReportForm scoreReport : scoreReports)
		    {
		      if(scoreReport != null)
		      {
		      count++;
		%>
		<tr>
			<td align="center"><%=count%></td>
			<td align="center"><%=scoreReport.getDate()%></td>
			<td align="center"><%=scoreReport.getUserName()%></td>
			<td align="center"><%=scoreReport.getStatus()%></td>
			<td align="center"><%=scoreReport.getDiscription()%></td>
		</tr>

		<%
		  } } }
		%>

	</table>
	</div>
	<br>
			<html:submit property="operation" value="Generate Report" alt="Generate Report"
			title="Submit" />
			<html:submit property="operation" value="<%=Constants.SEND_MAIL_BTN%>" alt="Send Score Mail"/>
			<html:submit property="operation" value="<%=Constants.PUBLISH_REPORTS%>" alt="Publish Reports"/>
</html:form>
</center>
</body>
</html>