<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="common.util.*"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
<SCRIPT language="JavaScript" src="js/billpointeutil.js" type="text/javascript"></SCRIPT>
<SCRIPT language="JavaScript" src="js/scw.js" type="text/javascript"></SCRIPT>
<center>
		<div id="pageheader">
			Manage Config Data
		</div>
	
		<%
			if (request.getAttribute(Constants.ERROR_MESSAGE) != null) {
		%>
			<div id="messageStyle">
			<img src="images/warning_wipeout_invoiceDB.png" width="16px" height="16px" /> 
			<%=request.getAttribute(Constants.ERROR_MESSAGE)%>
			</div>
		<%
			}
		%>
		
	 <html:form action="manageConfigData.do">
	<table>
		<tr>
			<td align="right"></td>
			<td align="left"><html:hidden property="id" name="configDataForm"  /> </td>
		</tr>
		<tr>
			<td align="right">Core Teams Count :</td>
			<td align="left"><html:text property="coreTeamsCount" disabled="<%=(Boolean)request.getAttribute(Constants.DISABLED)%>"
				name="configDataForm" size="20" maxlength="44" /> </td>
		</tr>
		<tr>
			<td align="right">Core Team Players Count :</td>
			<td align="left"><html:text property="coreTeamPlayersCount" disabled="<%=(Boolean)request.getAttribute(Constants.DISABLED)%>"
				name="configDataForm" size="20" maxlength="44" /> </td>
		</tr>

		<tr>
			<td align="right">Free Substitution:</td>
			<td align="left"><html:text property="freeSubstututions" disabled="<%=(Boolean)request.getAttribute(Constants.DISABLED)%>"
				name="configDataForm" size="20" maxlength="44" /> </td>
		</tr>

		<tr>
			<td align="right">User Team Diary Date :</td>
		<td>
			<html:text  property="updateUserTeamCutOffDate" styleId="styleId" disabled="<%=(Boolean)request.getAttribute(Constants.DISABLED)%>"
				name="configDataForm" size="16"
				onkeyup="maskDate(this)" onblur="reformatDate(this)" 
				styleClass="textBoxExtraSmall alignRight" ></html:text>
				<label> <img
				src="images/datepicker.gif" 
				onclick="scwShow(scwID('styleId'),event);" /> </label>
		</td>
		</tr>
		<tr></tr>
		<tr>
			<td align="right"></td>
			<td align="right"></td>
		</tr>
	</table>
	<br>
<html:submit property="operation" value="<%=(String)request.getAttribute(Constants.SUBMIT_LABEL)%>" />

</html:form>
</center>
</body>
</html>