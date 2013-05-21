<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<%@page import="common.struts.actionforms.user.UserRoleForm"%>
<%@page import="common.struts.actionforms.user.UserForm"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>

	<center>
		<html:form action="updateUser.do">
			<div id="pageheader">Update User details</div>

<div  id="newuser_registration">	
			<%
				if (request.getAttribute("error") != null && !"".equals(request.getAttribute("error")))
					{
			%>
			<div id="messageStyle"><img align="bottom"
			src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px">
				<%=request.getAttribute("error")%>
			</div>
			<%
				}
			%>

			<table>

				<tr />

				<tr>
					<td align="right"></td>
					<td align="left"><html:hidden property="id" name="userForm" />
					</td>
				</tr>
				<tr>
					<td align="right">Name :</td>
					<td align="left"><html:text property="name" name="userForm"
							disabled="true" size="20" maxlength="20" />
						<div style="color: red">
							<html:errors property="name" />
						</div></td>
				</tr>
				<tr>
					<td align="right">Login Name :</td>
					<td align="left"><html:text property="loginName"
							disabled="true" name="userForm" size="20" maxlength="44" />
						<div style="color: red">
							<html:errors property="loginName" />
						</div></td>
				</tr>

				<tr>
					<td align="right">Email :</td>
					<td align="left"><html:text property="emailId" name="userForm"
							disabled="true" size="20" />
						<div style="color: red">
							<html:errors property="emailId" />
						</div></td>

				</tr>
				<tr>
					<td align="right">Phone Number :</td>
					<td align="left"><html:text property="contactNo"
							name="userForm" disabled="true" size="20" />
						<div style="color: red">
							<html:errors property="contactNo" />
						</div></td>

				</tr>
				<tr>
					<td align="right">Address :</td>
					<td align="left"><html:textarea property="address"
							disabled="true" name="userForm" />
						<div style="color: red">
							<html:errors property="address" />
						</div></td>

				</tr>
				<tr>
					<td align="right">Date of Birth :</td>
					<td align="left"><html:text property="dob" name="userForm"
							disabled="true" />
						<div style="color: red">
							<html:errors property="dob" />
						</div></td>

				</tr>
				<tr>
					<td align="right">Status :</td>
					<td><html:radio property="active" name="userForm" value="Y"> Yes </html:radio>
						<html:radio property="active" name="userForm" value="N"> No </html:radio>
					</td>
				</tr>
				<tr>
					<td align="right">Select User Roles :</td>
				</tr>
				<%
					UserForm userForm = (UserForm) session.getAttribute("userForm");
						for (UserRoleForm roleForm : userForm.getAllUserRoles())
						{
							if (roleForm.isChecked())
							{
				%>
				<tr>
					<td></td>
					<td><input type="checkbox" checked="checked" value="Selected"
						name="<%=roleForm.getId().toString()%>" /> <%=roleForm.getRole()%>
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
						name="<%=roleForm.getId().toString()%>" /> <%=roleForm.getRole()%></td>
				</tr>
				<%
					}
						}
				%>
				<tr>
					<td></td>
					<td align="left"><html:submit property="operation"
							value="Update" alt="Submit" title="Submit" /> <html:submit
							property="operation" value="Cancel" alt="Cancel" title="Cancel" />
					</td>
				</tr>
			</table>
			</div>

		</html:form>
	</center>
</body>
</html>