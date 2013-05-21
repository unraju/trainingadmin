<%@page import="cricket.struts.actionforms.common.SCTNewsForm"%>
<%@page import="cricket.struts.actionforms.common.CountryForm"%>
<%@page import="java.util.List"%>
<%@page import="cricket.hibernate.bf.common.Country"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="cricket.struts.actionforms.team.PlayerForm"%>

<%@page errorPage="/error.jsp"%>
<html>

<body>

<center>
<div id="pageheader">Manage SCT News</div>

			<%
			  if (request.getAttribute(Constants.ERROR_MESSAGE) != null)
		    {
			%>
			<div id="messageStyle"><img
				src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px" />
			<%=request.getAttribute(Constants.ERROR_MESSAGE)%></div>
			<%
					  }
			%> 
			<%
			 	 List<SCTNewsForm> sctNewsForms = (ArrayList<SCTNewsForm>) session.getAttribute(Constants.SCT_NEWS);
			%> 
<html:form action="manageSCTNews.do">
<div id="tableStyle">
	<table>
		<tr>
		<th width="10px">No</th>
		<th width="100px">Date</th>
		<th width="400px">News</th>
		<th>Live</th>
		<th>Order</th>
		<th>Change</th>
		</tr>
		
			<%
				 int count =0;
				  for (SCTNewsForm sctNewsForm : sctNewsForms)
				    {
					  count++;
				%>
			<tr>
			<td><%=count %></td>
			<td><%=sctNewsForm.getDate()%></td>
			<td><%=sctNewsForm.getNews()%></td>
			<td><%=sctNewsForm.getLiveString()%></td>
			<td><%=sctNewsForm.getPriority()%></td>
			<td align="center">
			<a href="manageSCTNews.do?selectedId=<%=sctNewsForm.getId()%>&operation=<%=Constants.UPDATE_BTN_VALUE%>">
			<div style="font-size: 12px; color: #0000FF">Change</div>
			</a></td>
			</tr>
			<%
				  }
				%>
		
	</table>
</div>

	<html:submit property="operation" value="<%=Constants.ADD_BTN_VALUE%>"></html:submit>
	<html:submit property="operation" value="<%=Constants.PUBLISH_NEWS%>"></html:submit>	


			<%
			// SCTNewsForm  sctNewsForm = (SCTNewsForm) request.getAttribute(Constants.SCTNEWS_FORM);
			String aaa= (String)request.getAttribute(Constants.SCTNEWS_ADD_SCTION);
		 	if(aaa != null && aaa.equals(Constants.SCTNEWS_ADD_SCTION))
		 	{
			%>
	
	<div id="pageheader">Add/Update SCT News</div>
	
	<div  id="newuser_registration">
		<%
			if (request.getAttribute(Constants.ERROR_MESSAGE) != null) {
		%>
			<div id="messageStyle">
			<img src="images/warning_wipeout_invoiceDB.png" width="16px" height="16px" /> <%=request.getAttribute(Constants.ERROR_MESSAGE)%>
			</div>
		<%
			}
		%>

	
		<table align="center">
		
		<tr>
			<td align="right"></td>
			<td align="left"><html:hidden property="id" 
				name="sctNewsForm" />
			</td>
		</tr>
		<tr>
			<td align="right"  width="250px">News :</td>
			<td align="left" width="350px"><html:textarea property="news"   cols="45"  rows="6" 
				name="sctNewsForm"    />
			<div style="color: red"><html:errors property="name" /></div>
			</td>
		</tr>
			
		<tr>
			<td align="center">Live News :</td>
			<td>
			<html:radio name="sctNewsForm" property="live" value="true"> Yes </html:radio>
			<html:radio name="sctNewsForm" property="live" value="false"> No </html:radio>
			</td>
		</tr>
		<tr>
			<td align="center">Priority Order :</td>
			<td align="left"><html:text property="priority"   
				name="sctNewsForm" /></td>
		</tr>
	</table>
	
<html:submit property="operation" value="<%=Constants.SAVE_BTN_VALUE%>"></html:submit>
<html:submit property="operation"  value="<%=Constants.CANCEL_BTN_VALUE%>"></html:submit>
	<html:reset/> <BR>
</div><%} %>

</html:form>

</center>

</body>
</html>