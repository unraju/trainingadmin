<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="common.util.Constants"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.struts.actionforms.user.UserRoleForm"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>
<center>

<%
  if (session.getAttribute(Constants.SEARCHED_ROLES) != null)
    {
%>
<div id="subPageHeader">
Searched Roles
</div>
		<%
		  if (request.getAttribute(Constants.VALIDATION_MESSAGE) != null)
		  {
		%>
			<div id="messageStyle">
			<img src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px" /> <%=request.getAttribute(Constants.VALIDATION_MESSAGE)%>
			</div>
		<%
		  }
		%>
<div id="tableStyle">

<table>
		
	<tr>
		<th>No</th>
		<th>Role Name</th>
		<th>Activities</th>
		<th>Order</th>
		<th>Active</th>
		<th>User Assoc</th>
		<th>Default Role</th>
		<th>Action</th>
	</tr>
	<%
	  if (session.getAttribute(Constants.SEARCHED_ROLES) != null)
	  {
	    int count = 0;
	    for (UserRoleForm roleForm : (ArrayList<UserRoleForm>) session.getAttribute(Constants.SEARCHED_ROLES))
	    {
	      count++;
	%>

	<tr>
		<td><%=count%></td>
		<td><%=roleForm.getRole()%></td>
		<td width="350px"><%=roleForm.getActivitiesAsString()%></td>
		<td align="center"><%=roleForm.getPriority()%></td>
		<td align="center"><%=roleForm.getActive()%></td>
		<td align="center"><%=roleForm.getUserAssociatedString()%></td>
		<td align="center"><%=roleForm.getDefaultRoleString()%></td>
		<td><a
			href="populateRole.do?id=<%=roleForm.getId()%>&operation=Update">Update</a></td>

	</tr>

	<%
	  }
	  }
	%>

</table>
	</div>
<%
  }
%>
</center>
</body>
</html>
