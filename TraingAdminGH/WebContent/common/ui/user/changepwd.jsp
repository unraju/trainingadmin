<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="common.util.*"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page errorPage="/error.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


</head>
<body>
<center>
	
	
			<div id="pageheader">
				Change Password
			</div>	
			
	<div  id="newuser_registration">
				<%
					if (request.getAttribute(Constants.ERROR_MESSAGE) != null) {
				%>
					<div id="messageStyle">
					<img src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px" /> <%=request.getAttribute(Constants.ERROR_MESSAGE)%>
					</div>
				<%
					}
				%>

	 <html:form action="changepwd.do" method="post">
	<table>

		<tr>
			<td align="right">Old Password :</td>
			<td align="left"><html:password name="changePwdForm" maxlength="15"
				size="20" property="oldPwd" />
			<div id="messageStyle"><html:errors property="oldPwd" /></div>
			</td>
		</tr>
		<tr>
			<td align="right">New Password :</td>
			<td align="left"><html:password name="changePwdForm"
				property="newPwd" size="20" maxlength="15" />
			<div id="messageStyle"><html:errors property="newPwd" /></div>
			</td>
		</tr>
		<tr>
			<td align="right">Confirm Password :</td>
			<td align="left"><html:password name="changePwdForm"
				property="cfPwd" size="20" maxlength="15" />
			<div id="messageStyle"><html:errors property="cfPwd" /></div>
			</td>
		</tr>
			<tr>
		<td></td>
			<td>
			<html:submit property="operation"
				value="Confirm" alt="Confirm" title="Confirm" /> 
				<html:reset />
				 </td>
		</tr>

	</table>
</html:form>
</div>
</center>
</body>
</html>