<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page errorPage="/error.jsp" %>
<%@page import="common.util.Constants"%>
<%@page import="common.struts.actionforms.user.ActivityForm"%>
<%@page import="java.util.ArrayList"%>
<html>
<body>
<center>
	<div id="pageheader">
		Manage Activities 
	</div>


		<%
			if (request.getAttribute(Constants.VALIDATION_MESSAGE) != null) {
		%>
		<div style="color: red" align="center" >
		<img align="bottom"  src="images/warning_wipeout_invoiceDB.png" width="20px" height="18px" > <%=request.getAttribute(Constants.VALIDATION_MESSAGE)%>
		</div>
		<%
			}
		%> 
		
<html:form action="addUpdateActivities.do"><br>
<div id="tableStyle">
<table>
		
	<tr>
		<th>Select</th>
		<th>No</th>
		<th width="250">Activity</th>
		<th width="250">Action Url</th>
		<th width="50">Priority</th>
		<th >Active</th>
			
	</tr>
	<%
	   int count = 0;
		for (ActivityForm activityForm  : (ArrayList<ActivityForm>) session
		.getAttribute(Constants.SEARCHED_ACTIVITIES)) 
		{
			count++;
	%>
	
	<tr>
		<td  align="center"><input type="radio" value=<%=activityForm.getId()%> name="id" /></td>
		<td  align="center"><%=count%></td>
		<td ><%=activityForm.getActivity()%></td>
		<td><%=activityForm.getActivityUrl()%></td>
		<td align="center"><%=activityForm.getPriority()%></td>
		<td align="center"><%=activityForm.getActiveString()%></td>
	</tr>

	<% } %>
		
</table>
</div>

<br>
	<html:submit property="operation" value="Add"></html:submit>
	<html:submit property="operation" value="Update"></html:submit>
	<html:submit property="operation" value="Delete"></html:submit>
	</html:form>

</center>
</body>
</html>