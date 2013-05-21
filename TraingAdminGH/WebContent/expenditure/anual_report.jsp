<%@page import="common.util.RetriveContextData"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page import="common.util.Constants"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>

	
	 	<div id="pageheader">
			Annual Report
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

<html:form action="annualexp.do">
	<table>
	<tr>
		<td align="right">Cash Flow Type :</td><td align="left"><%
		
		RetriveContextData contextData = new RetriveContextData();
		List<ItemTypeTO> itemTypes = contextData.getItemTypes();
	    List<CashFlowTypeTO> cashFlowTypes =contextData.getCashFlowTypes();
		    for (CashFlowTypeTO cashFlowTypeTO : cashFlowTypes)
		    {
			%> 
			<input type="radio"  name="cashFlowType" 
				value="<%=cashFlowTypeTO.getId().toString()%>"
				onchange="filterItemTypes(<%=cashFlowTypeTO.getId().toString()%>)"/>
				<%=cashFlowTypeTO.getCashFlowType()%>
			<% } %>
			 <input type="radio"  name="cashFlowType" 
				value="All" 
				/> All				
		</td>
		
	</tr>
	
	<tr>
		<td align="right">Item Type:</td>
		<td align="left"> <html:select
				property="itemId"  name="annualExpForm">
		
			
				<html:option value="All"  >All</html:option>
				<%
				  for (ItemTypeTO itemto : itemTypes)
				        {
				        
				%>
				<html:option value="<%=itemto.getId().toString()%>">
					<%=itemto.getDescription()%>
				</html:option>

				<%
				  }
				       
				%>
			</div>
			
		</html:select>
		</td>
	</tr>
		<tr>
			<td align="right">Enter Year:</td>
			<td align="left">
			<html:select property="year" name="annualExpForm">
							
							<option value="2008" >2008</option>
							<option value="2009" selected>2009</option>
							<option value="2010">2010</option>
							<option value="2011">2011</option>
							<option value="2012">2012</option>
							<option value="2013">2013</option>
							<option value="2014">2014</option>
							<option value="2015">2015</option>
							
							</html:select></td>		
		</tr>
		<tr>
		<td>
		<td align="left">
		<html:submit></html:submit>
		<html:reset></html:reset>
		</td>
		</tr>
	</table>
	
</html:form>
</div>
<%@include file="viewexpreport.jsp"%>
</body>
</html>