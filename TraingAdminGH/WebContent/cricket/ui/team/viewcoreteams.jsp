<%@page import="cricket.struts.actionforms.common.SeriesForm"%>
<%@page import="common.util.RetriveContextData"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="cricket.struts.actionforms.team.CoreTeamForm"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>
		<% RetriveContextData contextData =  new RetriveContextData();
		SeriesForm seriesForm = contextData.getCurrentSeriesForm();
		%>

<center>
<div id="pageheader"><%=seriesForm.getName() %> Teams</div>

			<%
				if (request.getAttribute(Constants.VALIDATION_MESSAGE) != null) {
			%>
				<div id="messageStyle">
					<img src="images/warning_wipeout_invoiceDB.png" width="16px" height="16px" /> <%=request
							.getAttribute(Constants.VALIDATION_MESSAGE)%>
			</div>
			<%
				}
			%>

 <html:form action="viewCoreTeams.do">

	<%
		List<CoreTeamForm> coreTeamForms = contextData.getCoreTeamDetails(application);
	%>
	
	<table>
		<tr>

			<%
				for (CoreTeamForm coreTeamForm : coreTeamForms) {
							//if (coreTeamForm.getGroupId().equals("1")) {
			%>
			<td style="padding:4px" align="center"><a href="viewCoreTeams.do?id=<%=coreTeamForm.getId()%>" >
				<div style="font-size: 13px;color: #0000FF;">
				<%=coreTeamForm.getName()%>
				</div>
				</a></td>

			<%
				}
					//	}
			%></tr>
		
	</table>
	
	<jsp:include page="viewcoreteamplayerdetails.jsp"></jsp:include>
	
</html:form></center>
</body>

</html>