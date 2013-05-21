<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="common.struts.actionforms.user.UserRoleForm"%>
<%@page import="java.util.ArrayList"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>
<center>
	<div id="pageheader">
		Manage Users 
	</div>

<html:form action="searchUsers.do"><br>
<div  id="newuser_registration">	
<table>
	<tr>
		<td align="right">User Name : </td><td align="left">
		<html:text property="userName" name="searchUserForm" size="20"/></td>
		
	</tr>
	<tr>
		<td align="right">Login Id : </td><td align="left">
		<html:text property="loginName" name="searchUserForm" size="20"/></td>
		
	</tr>
	<tr>
		<td align="right">User Role :</td>
		<td align="left"><html:select property="role" name="searchUserForm" >
					<option value="ALL" selected >ALL </option>
					<%
						for (UserRoleForm userRole : (ArrayList<UserRoleForm>) session
										.getAttribute(Constants.USER_ROLES)) {
					%>
						<html:option value="<%=String.valueOf(userRole.getId())%>" > <%=userRole.getRole()%> </html:option>
						
					<%
												}
											%>
						</html:select>
			</td>
	</tr>
		<tr>
			<td align="right">Active :</td>
			<td align="left">
			<html:radio name="searchUserForm" property="active" value="true"> Yes </html:radio>
			<html:radio name="searchUserForm" property="active" value="false"> No </html:radio>
		</tr>
		<tr>
			<td></td>
			<td align="right"><html:submit value="Search" /> <html:reset /> 
			</td>
		</tr>

	</table>
	</div>
 </html:form>
 
<jsp:include  page="populatesearchedusers.jsp" />
</center>
</body>
</html>