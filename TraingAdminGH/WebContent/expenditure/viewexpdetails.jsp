<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page  session="true"  import="java.util.*,expenditure.actionform.*"%>
<%@page import="common.util.Constants"%>
<%@page errorPage="/error.jsp"%>
<html>
<body>
<center>
	
		<%
			if (request.getAttribute(Constants.ERROR_MESSAGE) != null && !("".equals(request.getAttribute(Constants.ERROR_MESSAGE)))) {
		%>
			<div id="messageStyle">
			<img src="images/warning_wipeout_invoiceDB.png" width="16px" height="16px" /> <%=request.getAttribute(Constants.ERROR_MESSAGE)%>
			</div>
		<%
			}
		%>
<html:form action="editExp.do" >

<%! Double total; %> 
<%
		total =0d;
	if(session
			.getAttribute("serchedresults") != null)
		{ %>
		<div id="subPageHeader">
		Searched Records
	</div>
<div id="bestplayer_table">
<table>

	<tr>
		<th>Select</th>
		<th >Item No</th>
		<th >Item Name</th>
		<th >Item Type</th>
		<th >Buy Date</th>
		<th >Pay Type</th>
		<th>Card Type</th>
		<th>Amount</th>
	
		
	</tr>
	<%
		for (AddExpActionForm expenditureTo : (ArrayList<AddExpActionForm>) session
				.getAttribute("serchedresults")) 
		{
			total = total+Double.parseDouble(expenditureTo.getAmount());
	%>
	
	<tr>
		<td align="center"><input type="radio" value=<%=expenditureTo.getId()%> name="id" /></td>
		<td ><%=expenditureTo.getItemNo()%></td>
		<td><%=expenditureTo.getItemName()%></td>
		<td><%=expenditureTo.getItemType()%></td>
		<td ><%=expenditureTo.getDate()%></td>
		<td ><%=expenditureTo.getPaymentType()%></td>
		<td ><%=expenditureTo.getCardType()%></td>
		<td><%=expenditureTo.getAmount()%></td>
		
	</tr>

	<% } %>
		
</table>
</div>
<br>
<div  align="center">
Total Expenditure :<%= total%>
</div>
<br>

	<html:submit property="operation" value="Edit"></html:submit>
	<html:submit property="operation" value="Delete"></html:submit>
<%	} %>

</html:form>
</center>
</body>
</html>
