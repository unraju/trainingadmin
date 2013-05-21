<%@page import="common.util.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="java.util.Date"%>

<html>
<head>
<SCRIPT language="JavaScript" src="js/billpointeutil.js" type="text/javascript"></SCRIPT>
<SCRIPT language="JavaScript" src="js/scw.js" type="text/javascript"></SCRIPT>
</head>
<body>
<center>
	

<html:form action="updateUserProfile.do" method="post">


		<div id="pageheader">
		Update User Profile
		</div>
		
	<div  id="newuser_registration">	
		<%
			if(request.getAttribute(Constants.ERROR_MESSAGE) != null) {
		%>
			<div id="messageStyle"><img align="bottom"
				src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px">
			<%=request.getAttribute(Constants.ERROR_MESSAGE)%>
			</div>
		<%
			}
		%>
	<table>
		
			<html:hidden property="id" name="userForm"/>
		
		<tr>
			<td align="right">Name :</td>
			<td align="left"><html:text property="name" name="userForm" readonly="true" 
				size="20" maxlength="15" />
			<div id="messageStyle"><html:errors property="name" /></div>
			</td>
		</tr>

		<tr>
			<td align="right">Gender :</td>
			<td align="left"><html:radio property="gender" name="userForm"
				value="male" />Male/ <html:radio property="gender" name="userForm"
				value="female" />Female
			<div id="messageStyle"><html:errors property="gender" /></div>
			</td>

		</tr>
		<tr>
			<td align="right">Email :</td>
			<td align="left"><html:text property="emailId" name="userForm" readonly="true" 
				size="30" maxlength="40" />
			<div id="messageStyle"><html:errors property="emailId" /></div>
			</td>

		</tr>
		<tr>
			<td align="right">Phone Number :</td>
			<td align="left"><html:text property="contactNo" name="userForm" maxlength="12"
				size="20" />
			<div id="messageStyle"><html:errors property="contactNo" /></div>
			</td>

		</tr>
		<tr>
			<td align="right">Address :</td>
			<td align="left"><html:text property="address"  maxlength="50"
				size="35"
				name="userForm" />
			<div id="messageStyle"><html:errors property="address" /></div>
			</td>

		</tr>
		<tr>
			<td align="right">Date of Birth :</td>
		
		<td align="left" >
			<html:text  property="dob" styleId="styleId" readonly="true"
				name="userForm" size="12"
				onkeyup="maskDate(this)" onblur="reformatDate(this)" 
				styleClass="textBoxExtraSmall alignRight" ></html:text>
				<label> <img
				src="images/datepicker.gif" 
				onclick="scwShow(scwID(styleId),event);" /> </label>
					<div id="messageStyle"><html:errors property="dob" /></div>
		</td>
		</tr>
		<br>
		<tr>
			<td></td>
			<td align="left"><html:submit property="operation"
				value="Confirm" alt="Confirm" title="Confirm" /> 
			<html:reset></html:reset></td>
		</tr>
	</table>
	</div>

</html:form>
</center>
</body>
</html>