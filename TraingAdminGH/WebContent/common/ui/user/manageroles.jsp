<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.*" %>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="common.struts.actionforms.user.ActivityForm"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>
<center>
<div id="pageheader">
Searched Roles
</div>
<html:form action="searchRoles.do">
<div  id="newuser_registration">	
<table>
	
	<tr>
		<td align="right">User Role : </td><td align="left">
		<html:text property="role" name="searchRoleForm" size="20"/></td>
		
	</tr>
	<tr>
		<td align="right">Active : </td><td align="left">
		<html:radio property="active" name="searchRoleForm" value="true"  > Yes </html:radio>
		<html:radio property="active" name="searchRoleForm" value="false"> No </html:radio>
		</td>
		
	</tr>
	
	<tr>
		<td align="right">Activity :</td>
		<td align="left"><html:select property="selectedActivity" name="searchRoleForm" >
					<option value="ALL" selected >ALL </option>
					<%
						for (ActivityForm activityForm : (ArrayList<ActivityForm>) session
										.getAttribute(Constants.ROLE_ACTIVITIES)) {
					%>
						<html:option value="<%=String.valueOf(activityForm.getId())%>" > <%=activityForm.getActivity()%> </html:option>
						
					<%
												}
											%>
						</html:select>
			</td>
	</tr>
		<tr>
			<td></td>
			<td>
			<html:submit property="operation"  value="<%=Constants.ADD_BTN_VALUE%>"></html:submit>
			<html:submit property="operation"  value="<%=Constants.SEARCH_BTN_VALUE%>"></html:submit>
	 		<html:reset /> 
			</td>
		</tr>

	</table>
</div>
 </html:form>

<jsp:include page="populatesearchedroles.jsp" />
</center>
</body>
</html>