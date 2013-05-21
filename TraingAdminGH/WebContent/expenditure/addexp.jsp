<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@page import="common.util.RetriveContextData"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<SCRIPT language="JavaScript" src="js/billpointeutil.js" type="text/javascript"></SCRIPT>
<SCRIPT language="JavaScript" src="js/scw.js" type="text/javascript"></SCRIPT>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"  %>

<%@page import="java.util.List"%>
<%@page import="expenditure.actionform.ItemTypeTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="expenditure.actionform.CashFlowTypeTO"%>
<%@page import="common.util.Constants"%>
<%@page errorPage="/error.jsp"%>
<html>
<head>
	<script type="text/javascript">

</script>

</head>
<% try { %>
<body  >
<div align="center" style="padding: 20px">
		<div id="pageheader">
		Add Your Expenses/Savings
		</div>
<div  id="change_players">
		<%
		  if (request.getAttribute(Constants.ERROR_MESSAGE) != null && !("".equals(request.getAttribute(Constants.ERROR_MESSAGE))))
		  {
		%>
			<div id="messageStyle">
			<img src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px" /> <%=request.getAttribute(Constants.ERROR_MESSAGE)%>
			</div>
		<%
		  }
		%>
		<%
		  if (request.getAttribute("success") != null)
		  {
		%>
			<div id="messageStyle"> Details added Successfully </div>
		<%
		  }
		%>
<html:form action="addexp.do" method="get">

<table >
	<tr>
		<td align="right">Enter Item Name :</td><td align="left"> 
		<html:text property="itemName" name="addExpForm" size="20" maxlength="44" tabindex="1" />
		<div style="color: red"><html:errors property="itemName" /></div></td>
		
	</tr>
	
	<tr>
		<td align="right">Cash Flow Type :</td><td align="left"><%
		   	RetriveContextData contextData = new RetriveContextData();
    			 
		 	List<ItemTypeTO> itemTypes = contextData.getItemTypes();
		    List<CashFlowTypeTO> cashFlowTypes =contextData.getCashFlowTypes();
		    for (CashFlowTypeTO cashFlowTypeTO : cashFlowTypes)
		    {
		      if(cashFlowTypeTO.getCashFlowType().startsWith("Exp"))
		      {
			%> 
			
			<input type="radio"  name="cashFlowType" checked="checked" tabindex="2"  disabled="disabled"
				value="<%=cashFlowTypeTO.getId().toString()%>"
				onchange="filterItemTypes(<%=cashFlowTypeTO.getId().toString()%>)"/>
				<%=cashFlowTypeTO.getCashFlowType()%>
			 <%
			   } else { %>
			     <input type="radio"  name="cashFlowType" disabled="disabled"
				value="<%=cashFlowTypeTO.getId().toString()%>"
				onchange="filterItemTypes(<%=cashFlowTypeTO.getId().toString()%>)"/>
				<%=cashFlowTypeTO.getCashFlowType()%>
			 <%} }%>	
		</td>
		
	</tr>
	
	<tr>
		<td align="right">Item Type:</td>
		<td align="left">
		
		<%
		 // for (CashFlowTypeTO flowTypeTO : cashFlowTypes)
		   // {
		%>
			<html:select tabindex="4"
				property="itemId" name="addExpForm">
				<html:option value="Please Select">
				</html:option>
				
				<%
				  for (ItemTypeTO itemto : itemTypes)
				        {
				        //  if (itemto.getCashFlowType().equals(flowTypeTO.getCashFlowType()))
				       //   {
							%>
							<html:option value="<%=itemto.getId().toString()%>"  >
								<%=itemto.getDescription()%>
							</html:option>
			
							<%
						//  }
				        }
				%>
			</html:select>
			<%
			 // }
			%>
		
		</td>
	</tr>

		<tr>
		<td align="right">Amount :</td><td align="left">
		<html:text property="amount" name="addExpForm" size="20" tabindex="5" />
		<div style="color: red"><html:errors property="amount" /></div>
		</td>
	</tr>
	<tr>
		<td align="right">Payment Type :</td>
			<td align="left"> 
			<html:radio property="paymentType" name="addExpForm" value="CASH" tabindex="6" />Cash 
			<html:radio property="paymentType" name="addExpForm" value="CARD" tabindex="7"/>Card 
			<html:radio property="paymentType" name="addExpForm" value="COUP" tabindex="8"/>Coupans
			<div style="color: red"><html:errors property="paymentType" /></div>
		</td>
	</tr>
	<tr>
		
		<td align="right">Card Type : </td><td align="left"><html:select property="cardType" name="addExpForm" tabindex="9">
							<option value="PleaseSelect" selected="selected">Please Select...</option>
							<option value="HDFC">HDFC</option>
							<option value="HSBC">HSBC</option>
							<option value="CITI">CITI</option>
							<option value="HDFCD">HDFCD</option>
							<option value="HDFCNB">HDFCNB</option>
							<option value="ICICINB">ICICINB</option>
							<option value="ICICID">ICICID</option>
							</html:select>
					<div style="color: red"><html:errors property="cardType" /></div>

		</td>
	</tr>

	<tr>
		<td align="right">Purchase Date :</td>
		<td align="left">
			<html:text property="date" styleId="fromdateID" size="12" 
				onkeyup="maskDate(this)" onblur="reformatDate(this)"
				styleClass="textBoxExtraSmall alignRight" 
				maxlength="10"> </html:text> <label > <img 
				src="images/datepicker.gif" alt="Click Here"
				onclick="scwShow(scwID('fromdateID'),event);"  /> </label>
				<div style="color: red"><html:errors property="date" /></div>
			</td>
	</tr>
	
			<tr>
			<td></td>
			<td align="left"><html:reset  tabindex="13"/><html:submit tabindex="12"/> 
			</td>
		</tr>
	</table>
	
</html:form>
</div>
</body>
<% } catch(Exception e) { e.printStackTrace();}%>
</html>
