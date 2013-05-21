<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"  %>

<%@page import="common.util.Constants"%>
<%@page errorPage="/error.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

		<div id="pageheader">
		Configure Static Data
		</div>
	
	<div  id="change_players">
		<%
			if (request.getAttribute(Constants.ERROR_MESSAGE) != null) {
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

<html:form action="addstatic.do" method="get">

<table >
	<tr>
		<td align="right">Select Static Type : </td><td align="left">
		<html:select property="staticType" name="staticForm">
							<option value="ItemType" selected>ItemType</option>
							<option value=" "> </option>
							</html:select>
					<div style="color: red"><html:errors property="staticType" /></div>
		</td>
		</tr>
		
	<tr>
		<td align="right">Enter Item Code :</td><td align="left"> 
		<html:text property="code" name="staticForm" size="20" maxlength="44"/>
		<div style="color: red"><html:errors property="code" /></div></td>
		
	</tr>
	<tr>
		<td align="right">Enter Item Description :</td><td align="left"> 
		<html:text property="description" name="staticForm" size="20" maxlength="44"/>
		<div style="color: red"><html:errors property="description" /></div></td>
		
	</tr>
	<tr>
			<td></td>
			<td align="left"><html:reset/><html:submit value="Add"/> 
			</td>
		</tr>
	</table>
	
</html:form>
</div>
</body>
</html>