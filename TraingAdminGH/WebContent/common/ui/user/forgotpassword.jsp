<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page errorPage="/error.jsp"%>

<html>
<head>
<SCRIPT language="JavaScript" src="js/scw.js" type="text/javascript"></SCRIPT>
<SCRIPT language="JavaScript" src="js/billpointeutil.js" type="text/javascript"></SCRIPT>
</head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
	<center>
		<div id="pageheader">Reset Your Password</div>


		<div id="newuser_registration">
			<%
 		if (request.getAttribute(Constants.ERROR_MESSAGE) != null) {
 		%>
			<div id="messageStyle">
				<img align="bottom" src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px">
				<%=request.getAttribute(Constants.ERROR_MESSAGE)%></div>
			<%
			}
		%>
			<html:form action="forgotpwd.do" method="post">
				<table>
					<tr><td/><td/></tr>
					<tr>
						<td align="right">Registered Email Id :</td>
						<td align="left"><html:text name="forgotPwdForm" maxlength="30" size="30" property="emailId" />
							<div id="messageStyle">
								<html:errors property="emailId" />
							</div></td>
					</tr>
				</table>


				<html:submit property="operation" value="Submit" alt="Submit" title="Submit" />
				<html:submit property="operation" value="Cancel" alt="Cancel" title="Cancel" />

			</html:form>
		</div>

	</center>
</body>
</html>