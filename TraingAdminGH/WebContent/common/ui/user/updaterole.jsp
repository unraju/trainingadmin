<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<%@page import="common.struts.actionforms.user.UserRoleForm"%>
<%@page import="common.struts.actionforms.user.ActivityForm"%>
<%@page import="common.util.Constants"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>

	<center>
		<html:form action="updateRole.do">
			<div id="pageheader">
				<%=session.getAttribute(Constants.OPERATION)%>
				Role Details
			</div>

			<div id="newuser_registration">

				<%
					if (request.getAttribute("error") != null && !"".equals(request.getAttribute("error")))
						{
				%>
				<div style="color: red" align="center">
					<div id="messageStyle"><img align="bottom"
					src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px">
					<%=request.getAttribute("error")%>
				</div>
				<tr />
				<%
					}
				%>


				<table>

					<tr>
						<td align="right"></td>
						<td align="left"><html:hidden property="id"
								name="userRoleForm" /></td>
					</tr>

					<tr>
						<td align="right">Role Name:</td>
						<td align="left"><html:text property="role"
								name="userRoleForm" size="20" maxlength="22" />
							<div style="color: red">
								<html:errors property="role" />
							</div></td>
					</tr>

					<tr>
						<td align="right">Role Description:</td>
						<td align="left"><html:text property="discription"
								name="userRoleForm" size="20" maxlength="22" />
							<div style="color: red">
								<html:errors property="discription" />
							</div></td>
					</tr>
					<tr>
						<td align="right">Priority:</td>
						<td align="left"><html:text property="priority"
								name="userRoleForm" size="20" maxlength="22" />
							<div style="color: red">
								<html:errors property="priority" />
							</div></td>
					</tr>
					<tr>
						<td align="right">Active :</td>
						<td align="left"><html:radio property="active"
								name="userRoleForm" value="true"> Yes </html:radio> <html:radio
								property="active" name="userRoleForm" value="false"> No </html:radio>
						</td>

					</tr>
					<tr>
						<td align="right">User Associated :</td>
						<td align="left"><html:radio property="userAssociated"
								name="userRoleForm" value="true"> Yes </html:radio> <html:radio
								property="userAssociated" name="userRoleForm" value="false"> No </html:radio>
						</td>
					</tr>

					<tr>
						<td align="right">Default Role :</td>
						<td align="left"><html:radio property="defaultRole"
								name="userRoleForm" value="true"> Yes </html:radio> <html:radio
								property="defaultRole" name="userRoleForm" value="false"> No </html:radio>
						</td>
					</tr>

					<tr>
						<td align="right">Select Role Activities :</td>
					</tr>
					<%
						UserRoleForm roleForm = (UserRoleForm) session.getAttribute("userRoleForm");
							for (ActivityForm activityForm : roleForm.getAllActivities())
							{
								if (activityForm.isChecked())
								{
					%>
					<tr>
						<td></td>
						<td><input type="checkbox" checked="checked" value="Selected"
							name="<%=activityForm.getId().toString()%>" /> <%=activityForm.getActivity()%>
						</td>
					</tr>
					<%
						}
								else
								{
					%>
					<tr>
						<td></td>
						<td><input type="checkbox" value="Selected"
							name="<%=activityForm.getId().toString()%>" /> <%=activityForm.getActivity()%></td>
					</tr>
					<%
						}
							}
					%>
					<tr>
						<td></td>
						<td align="left"><html:submit property="operation"
								value="Save" alt="Submit" title="Submit" /> <html:submit
								property="operation" value="Cancel" alt="Cancel" title="Cancel" />
						</td>
					</tr>
				</table>
			</div>

		</html:form>
	</center>
</body>
</html>