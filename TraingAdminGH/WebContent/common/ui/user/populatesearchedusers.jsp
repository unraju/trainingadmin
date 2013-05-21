<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="common.util.Constants"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="java.util.ArrayList"%>
<%@page import="common.struts.actionforms.user.UserForm"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>
<center>

<%
	if(session
			.getAttribute(Constants.SEARCHED_USERS) != null)
		{ %>
<div id="subPageHeader">
Searched Users
</div>
<div id="tableStyle">

<table>

	<tr>
		<th>No</th>
		<th>User Name</th>
		<th>Login Id</th>
		<th>Created Date</th>
		<th >User Roles</th>
		<th>Contact No</th>
		<th>Active</th>
		<th>Action</th>
			
	</tr>
	<% int count =0;
		for (UserForm user  : (ArrayList<UserForm>) session
		.getAttribute(Constants.SEARCHED_USERS)) 
		{
			count++;
	%>
	
	<tr>
		<td align="center"><%=count%></td>
		<td ><%=user.getName()%></td>
		<td><%=user.getLoginName()%></td>
		<td><%=user.getCreatedDate()%></td>
		<td width="200px"><%=user.getUserRolesAsString()%></td>
		<td><%=user.getContactNo()%></td>
		<td><%=user.getActive()%></td>
		<td><a href="populateUser.do?id=<%=user.getId()%>&operation=Update">Update</a></td>	
	</tr>

	<% } %>
		
</table>

	<%
			if (request.getAttribute(Constants.VALIDATION_MESSAGE) != null) {
		%>
		<div style="color: red" align="center" >
		<img align="bottom"  src="images/warning_wipeout_invoiceDB.png" width="20px" height="18px" > <%=request.getAttribute(Constants.VALIDATION_MESSAGE)%>
		</div>
		<%
			}
		%> 
	</div>
<%	} %>
</center>
</body>
</html>
