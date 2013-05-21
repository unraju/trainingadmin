<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.*" %>
<%@page session="true" import="common.util.Constants,expenditure.actionform.*"  %>
<%@page errorPage="/error.jsp"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<SCRIPT language="JavaScript" src="js/billpointeutil.js" type="text/javascript"></SCRIPT>
<SCRIPT language="JavaScript" src="js/scw.js" type="text/javascript"></SCRIPT>
 
<html>
<body>
	
	<div id="pageheader">
		Search / Update Records
	</div>
	
	<div  id="change_players">
	
		<%
			if (request.getAttribute(Constants.ERROR_MESSAGE) != null && !("".equals(request.getAttribute(Constants.ERROR_MESSAGE)))) {
		%>
			<div id="messageStyle">
			<img src="images/warning_wipeout_invoiceDB.png" width="16px" height="16px" /> <%=request.getAttribute(Constants.ERROR_MESSAGE)%>
			</div>
		<%
			}
		%>

<html:form action="viewexp.do">

<table>
	<tr>
		<td align="right">Item Name : </td><td align="left">
		<html:text property="itemName" name="viewExpForm" size="20"/></td>
		
	</tr>
	<tr>
		<td align="right">Cash Flow Type :</td><td align="left"><%
		  List<ItemTypeTO> itemTypes = (ArrayList<ItemTypeTO>) session.getAttribute(Constants.ITEM_TYPES);
		    List<CashFlowTypeTO> cashFlowTypes = (ArrayList<CashFlowTypeTO>) session.getAttribute(Constants.CASH_FLOW_TYPES);
		    for (CashFlowTypeTO cashFlowTypeTO : cashFlowTypes)
		    {
			%> 
			<input type="radio"  name="cashFlowType" 
				value="<%=cashFlowTypeTO.getId().toString()%>"
				onchange="filterItemTypes(<%=cashFlowTypeTO.getId().toString()%>)"/>
				<%=cashFlowTypeTO.getCashFlowType()%>
			<% } %>
			 <input type="radio"  name="cashFlowType" checked="checked"
				value="All"
				onchange="filterItemTypes("all")"/> All				
		</td>
		
	</tr>
	
	<tr>
		<td align="right">Item Type :</td>
		<td align="left">
		<%
		 // for (CashFlowTypeTO flowTypeTO : cashFlowTypes)
		   // {
		%>
			<html:select
				property="itemId" name="viewExpForm">
				<html:option value="All">All
				</html:option>
				<%
				  for (ItemTypeTO itemto : itemTypes)
				        {
				        //  if (itemto.getCashFlowType().equals(flowTypeTO.getCashFlowType()))
				         // {
				%>
				<html:option value="<%=itemto.getId().toString()%>">
					<%=itemto.getDescription()%>
				</html:option>

				<%
				  //}
				        }
				%>
			</html:select></div>
			<%
			 // }
			%>
		
		</td>
	</tr>

	<tr>
		<td align="right">Payment Type :</td><td align="left"> <html:radio
			property="paymentMode" name="viewExpForm" value="CASH" />Cash 
			<html:radio property="paymentMode" name="viewExpForm"
			value="CARD" />Card <html:radio property="paymentMode" name="viewExpForm" value="COUP" />Coupans
		</td>
	</tr>
	<tr>
		
		<td align="right">Card Type :</td><td align="left"> <html:select property="cardType" name="viewExpForm">
							<option value="Please Select" selected>Please Select...</option>
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
		<td align="right">From Date :</td>
			
				
			<td  align="left" >
			<html:text property="date" name="viewExpForm" maxlength="10"
				styleId="fromDateID" size="15"
				onkeyup="maskDate(this)" onblur="reformatDate(this)" 
				styleClass="textBoxExtraSmall alignRight" ></html:text>
				<label> <img
				src="images/datepicker.gif" 
				onclick="scwShow(scwID('fromDateID'),event);" /> </label>
		</td>
	</tr>
	
	<tr>
		<td align="right">To Date :</td>
			<td  align="left" >
			<html:text property="toDate" name="viewExpForm" maxlength="10"
				styleId="toDateID" size="15"
				onkeyup="maskDate(this)" onblur="reformatDate(this)" 
				styleClass="textBoxExtraSmall alignRight" ></html:text>
				<label> <img
				src="images/datepicker.gif" 
				onclick="scwShow(scwID('toDateID'),event);" /> </label>
		</td>
	</tr>

	
</table>
<html:submit value="Serach"/> 
<html:reset/> <BR>

<BR>
<BR>

 </html:form>
 </div>
<%@ include file="viewexpdetails.jsp"%>
</body>
</html>