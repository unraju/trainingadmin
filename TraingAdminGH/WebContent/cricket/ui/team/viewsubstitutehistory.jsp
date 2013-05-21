<%@page import="common.util.RetriveContextData"%>
<%@page import="cricket.struts.actionforms.common.SeriesForm"%>
<%@page import="cricket.struts.actionforms.team.PlayerSubstitutionForm"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cricket.hibernate.bf.player.*"%>
<%@page import="common.util.DateUtil"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>

<center>
	<%
		SeriesForm seriesForm =  new RetriveContextData().getCurrentSeriesForm();
	%>
<div id="pageheader"> 
My <%=seriesForm.getName() %> Substitutions History
</div>
<html:form action="populateTeamShedule.do">
	<div id="tableStyle">
	<table>
	
		<tr>
			<th>No</th>
			<th >Date</th>
			<th >Type</th>
			<th >Old Player</th>
			<th >New Player</th>
			<th >Points Deducted</th>
			<th >Status</th>
		</tr>
		<%
		  List<PlayerSubstitutionForm> substitutions = (ArrayList<PlayerSubstitutionForm>) session.getAttribute(Constants.SUBSTITUTIONS_HISTORY);
		    int count = 0;
		    for (PlayerSubstitutionForm playerSubstitution : substitutions)
		    {
		      if(playerSubstitution.getPointsDeducted() == null)
		      {
		        playerSubstitution.setPointsDeducted(0l);
		      }
		      count++;
		%>
		<tr>
			<td><%=count%></td>
			<td><%=playerSubstitution.getDateSTring()%></td>
			<td><%=playerSubstitution.getSubstitionType()%></td>
			<td><%=playerSubstitution.getOldPlayerName()%></td>
			<td><%=playerSubstitution.getNewPlayerName()%></td>
			<td><%=playerSubstitution.getPointsDeducted()%></td>
			<td><%=playerSubstitution.getStatus()%></td>
		</tr>

		<%
		  }
		%>

	</table>
	</div>
	
</html:form>
</center>
</body>
</html>