<%@page import="common.util.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page errorPage="/error.jsp" %>
<html>
<body>
<center>
	
<div id="pageheader">
	 Add Activity Details
	</div>


<html:form action="addActivity.do">
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

	<table align="center">
	
		<tr>
			<td align="right">Activity :</td>
			<td align="left"><html:text property="activity" 
				name="activityForm" size="20" maxlength="22" />
			<div style="color: red"><html:errors property="activity" /></div>
			</td>
		</tr>
		
		<tr>
			<td align="right">Activity Url :</td>
			<td align="left"><html:text property="activityUrl" 
				name="activityForm" size="20" maxlength="22" />
			<div style="color: red"><html:errors property="activityUrl" /></div>
			</td>
		</tr>
		
		<tr>
		<td align="right">Active : </td><td align="left">
		<html:radio property="active" name="activityForm" value="true"  > Yes </html:radio>
		<html:radio property="active" name="activityForm" value="false"> No </html:radio>
		</td>
		</tr>
		<tr>
			<td align="right">Priority :</td>
			<td align="left"><html:text property="priority" 
				name="activityForm" size="20" maxlength="4" />
			<div style="color: red"><html:errors property="priority" /></div>
			</td>
		</tr>
		<tr>
		<td></td>
			<td align="left"><html:submit property="operation"
				value="Save" alt="Submit" title="Submit" /> <html:submit
				property="operation" value="Cancel" alt="Cancel" title="Cancel" />
			</td>
		</tr>
	</table>
	</div>
</html:form></center>
</body>
</html>