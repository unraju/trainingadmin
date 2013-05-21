<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>


<%@page import="expenditure.businessfunction.LookupType"%>
<%@page errorPage="/error.jsp"%>
<html>
<body >
<div align="center" style="border:thin;padding:20px;">
 	<div id="pageheader">
		Monthly Report
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
<html:form action="monthlyexp.do">
	
	<table  >
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
		<td align="right">Item Type:</td>
		<td align="left">
		<%
		 // for (CashFlowTypeTO flowTypeTO : cashFlowTypes)
		  //  {
		%>
			<div id="bd<% //=flowTypeTO.getId()%>"><html:select
				property="itemId" name="monthlyExpForm">
				<html:option value="All">All
				</html:option>
				<%
				  for (ItemTypeTO itemto : itemTypes)
				        {
				         // if (itemto.getCashFlowType().equals(flowTypeTO.getCashFlowType()))
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
			//  }
			%>
		
		</td>
	</tr>
		<tr>
			<td align="right">Enter Month:</td>
			<td align="left"><html:select property="month" name="monthlyExpForm">
							<option value="1">Januvary</option>
							<option value="2">Februvary</option>
							<option value="3">March</option>
							<option value="4" >Aprial</option>
							<option value="5" >May</option>
							<option value="6" >June</option>
							<option value="7" selected>July</option>
							<option value="8">August</option>
							<option value="9">September</option>
							<option value="10">October</option>
							<option value="11">November</option>
							<option value="12">December</option>
							</html:select></td>
		</tr>
		<tr>
			<td align="right">Enter Year:</td>
			<td align="left">
			<html:select property="year" name="monthlyExpForm">
							<option value="2008">2008</option>
							<option value="2009">2009</option>
							<option value="2010" selected>2010</option>
							<option value="2011">2011</option>
							<option value="2012">2012</option>
							<option value="2013">2013</option>
							<option value="2014">2014</option>
							<option value="2015">2015</option>
							
							</html:select></td>		
		</tr>
	
		<tr></tr>
		<tr>
		<td></td>
		<td align="left">
		<html:submit/>
		<html:reset></html:reset>
		</td>
		</tr>
	</table>


</html:form>	

</div>
<%@include file="viewexpreport.jsp"%>
</body>
</html>