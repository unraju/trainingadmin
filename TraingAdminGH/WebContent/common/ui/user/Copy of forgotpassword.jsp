<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		<div id="pageheader">
		Reset Your Password
		</div>
		

<div  id="newuser_registration">
		<%
 		if (request.getAttribute(Constants.ERROR_MESSAGE) != null) {
 		%>
		<div id="messageStyle"><img align="bottom"
			src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px">
		<%=request.getAttribute(Constants.ERROR_MESSAGE)%></div>
		<%
			}
		%> 	
<html:form action="forgotpwd.do" method="post">
	<table  >

		<tr>
			<td align="right">Login Name : </td>
			<td align="left"><html:text name="forgotPwdForm" maxlength="12"
				size="20" property="loginName" />
			<div id="messageStyle"><html:errors property="loginName"/>
			</div>
			</td>
		</tr>
		<tr>
			<td align="right">Date of Birth :</td>
		
			<td align="left"><html:text property="dob" styleId="fromdateID" size="16" readonly="true"
				onkeyup="maskDate(this)" onblur="reformatDate(this)"
				styleClass="textBoxExtraSmall alignRight" tabindex="12"
				></html:text> <label> <img
				src="images/datepicker.gif" alt="Click Here"
				onclick="scwShow(scwID('fromdateID'),event);" /> </label>
				<div id="messageStyle"><html:errors property="dob" /></div>
				</td>

		</tr>
		<!--  
		<tr>
			<td></td>
			<td align="right">( format- dd/MM/YYYY)</td>
		</tr>
		 -->
		<tr>
			<td align="right">Enter New Password : </td>
			<td align="left"><html:password name="forgotPwdForm" property="pwd"
				size="20" maxlength="12" />
			<div id="messageStyle"><html:errors property="pwd" /></div>
			</td>

		</tr>
		<tr>
			<td align="right">Re-Enter Password : </td>
			<td align="left"><html:password name="forgotPwdForm" property="reEnteredPwd"
				size="20" maxlength="12" />
			<div id="messageStyle"><html:errors property="reEnteredPwd" /></div>
			</td>
		</tr>
			</table>
			
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<html:submit property="operation" value="Submit" alt="Submit"
			title="Submit" />
		<html:submit property="operation" value="Cancel" alt="Cancel"
			title="Cancel" />
		<html:reset />
</html:form>
</div>

</center>
</body>
</html>