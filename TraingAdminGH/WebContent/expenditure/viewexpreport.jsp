<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page session="true" import="java.util.*,expenditure.actionform.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page errorPage="/error.jsp"%>
<html>
<head>

</head>
<body>	
		
		<%! Double total; %> 
		<%
				total =0d;
			if(session
					.getAttribute("monthlyserchedresults") != null)
				{ %>
<div id="bestplayer_table">
<table >
	<tr>
		<th > No</th>
		<th>Item Name</th>
		<th>Item Type</th>
		<th>Purchase Date</th>
		<th>Payment Type</th>
		<th>Card Type</th>
		<th>Amount</th>
	</tr>
	<%
		for (AddExpActionForm expenditureTo : (ArrayList<AddExpActionForm>) session
				.getAttribute("monthlyserchedresults")) 
		{
			total = total+Double.parseDouble(expenditureTo.getAmount());
	%>
	
	<tr>
		<td  width="30px"><%=expenditureTo.getItemNo()%></td>
		<td  width="150px"><%=expenditureTo.getItemName()%></td>
		<td  width="150px"><%=expenditureTo.getItemType()%></td>
		<td  width="100px"><%=expenditureTo.getDate()%></td>
		<td  width="80px"><%=expenditureTo.getPaymentType()%></td>
		<td  width="80px"><%=expenditureTo.getCardType()%></td>
		<td width="80px"><%=expenditureTo.getAmount()%></td>
	</tr>
	<%
			}
		%>

	</table>
	</div>
	<br>
	<div align="center">Total Ependiture :<%=total%></div>
	<br>
	<% } %>

</body>
</html>