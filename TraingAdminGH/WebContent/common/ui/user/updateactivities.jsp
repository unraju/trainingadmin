<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<%@page import="common.struts.actionforms.user.ActivityForm"%>
<%@page import="common.util.Constants"%>
<%@page import="java.util.ArrayList"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>

	<center>
		<html:form action="updateActivities.do">
			<div id="pageheader">Update Activities Details</div>
			<div id="update_activities">
				<%
					if (request.getAttribute("error") != null && !"".equals(request.getAttribute("error")))
						{
				%>
				<div id="messageStyle">
					<img src="images/warning_wipeout_invoiceDB.png" width="12px"
						height="12px" />
					<%=request.getAttribute("error")%>
				</div>
				
				<%
					}
				%>

				<table>


					<tr>
						<th>Activity</th>
						<th>Action Url</th>
						<th>Priority</th>
						<th>Active</th>

					</tr>
					<%
						for (ActivityForm activityForm : (ArrayList<ActivityForm>) session
									.getAttribute(Constants.SEARCHED_ACTIVITIES))
							{
					%>

					<tr>


						<td><input type="text"
							name="<%=activityForm.getId().toString() + "X1"%>"
							value="<%=activityForm.getActivity()%>"  /></td>
						<td><input type="text"
							name="<%=activityForm.getId().toString() + "X2"%>"
							value="<%=activityForm.getActivityUrl()%>"  /></td>
						<td><input type="text"
							name="<%=activityForm.getId().toString() + "X3"%>"
							value="<%=activityForm.getPriority()%>"  /></td>
						<td>
							<%
								if (activityForm.isActive())
										{
							%> <input type="radio" checked="checked"
							name="<%=activityForm.getId().toString() + "X4"%>" value="true">
							Yes <input type="radio"
							name="<%=activityForm.getId().toString() + "X4"%>" value="false">
							No <%
 				}
	 			if (!activityForm.isActive())
	 			{
 				%> <input type="radio"
							name="<%=activityForm.getId().toString() + "X4"%>" value="true">
							Yes <input type="radio" checked="checked"
							name="<%=activityForm.getId().toString() + "X4"%>" value="false">
							No <%
 				}
				 %>
						</td>
					</tr>
					<%
						}
					%>

				</table>
			
			<html:submit property="operation" value="Update" alt="Submit"
				title="Submit" />
			<html:submit property="operation" value="Cancel" alt="Cancel"
				title="Cancel" />
		</div>
		</html:form>
	</center>
</body>
</html>