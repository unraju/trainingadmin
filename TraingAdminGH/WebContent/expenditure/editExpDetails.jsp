<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<SCRIPT language="JavaScript" src="js/billpointeutil.js" type="text/javascript"></SCRIPT>
<SCRIPT language="JavaScript" src="js/scw.js" type="text/javascript"></SCRIPT>
<%@page errorPage="/error.jsp"%>
<%@page import="common.util.Constants"%>
<html>
<body>

	
		<div id="pageheader">
		Update Expenses/Savings Details
		</div>
<div  id="change_players">
		<%
			if (request.getAttribute(Constants.ERROR_MESSAGE) != null &&
					!("".equals(request.getAttribute(Constants.ERROR_MESSAGE)))) {
		%>
			<div id="messageStyle">
			<img src="images/warning_wipeout_invoiceDB.png" width="16px" height="16px" /> <%=request.getAttribute(Constants.ERROR_MESSAGE)%>
			</div>
		<%
			}
		%>
		<%
			if (request.getAttribute("success") != null) {
		%>
			<div id="messageStyle"> Details added Successfully </div>
		<%
			}
		%>

<html:form action="updateExp.do" >

<table>
<tr>
		<html:hidden   property="id" name="addExpForm" />
		<td align="right">Enter Item Name :</td><td align="left"> 
		<html:text  property="itemName" name="addExpForm" size="20" maxlength="44"/><html:errors property="itemName" /></td>
		
	</tr>

	<tr>
		<td align="right">Amount :</td><td align="left">
		<html:text property="amount" name="addExpForm" size="20"/></td>
	</tr>
	<tr>
		<td align="right">Payment Type :</td><td align="left"> <html:radio
			property="paymentType" name="addExpForm" value="CASH" />Cash 
			<html:radio property="paymentType" name="addExpForm"
			value="CARD" />Card <html:radio property="paymentType" name="addExpForm" value="COUP" />Coupans
		</td>
	</tr>
	<tr>
		
		<td align="right">Card Type : </td><td align="left"><html:select property="cardType" name="addExpForm">
							<option value="PleaseSelect" >Please Select...</option>
							<option value="HDFC">HDFC</option>
							<option value="HSBC">HSBC</option>
							<option value="HDFCD">HDFCD</option>
							<option value="HDFCNB">HDFCNB</option>
							<option value="ICICINB">ICICINB</option>
							<option value="ICICID">ICICID</option>
							</html:select>
		</td>
	</tr>

	<tr>
		<td align="right">Purchase Date :</td><td align="left">
		
			<html:text property="date" styleId="fromdateID" name="addExpForm" size="12"
				onkeyup="maskDate(this)" onblur="reformatDate(this)"
				styleClass="textBoxExtraSmall alignRight" tabindex="12"
				maxlength="10"></html:text> <label> <img
				src="images/datepicker.gif" alt="Click Here"
				onclick="scwShow(scwID('fromdateID'),event);" /> </label>
	</tr>
	<tr>
	</tr>
</table>
<br>
<html:submit property="operation" value="Update" ></html:submit>
<html:submit property="operation" value="Delete"></html:submit>
<html:submit property="operation" value="Cancel"></html:submit>

</html:form>
</div>
</body>
</html>