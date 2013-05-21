<%@page import="cricket.struts.actionforms.common.SeriesForm"%>
<%@page import="common.util.RetriveContextData"%>
<%@page import="common.struts.actionforms.user.UserForm"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.util.Constants"%>
<%@page import="cricket.struts.actionforms.common.CricRulesForm"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

<%	
	RetriveContextData  contextData =  new RetriveContextData();
	SeriesForm seriesForm = contextData.getCurrentSeriesForm();
 	 List<CricRulesForm> cricRules = contextData.getSeriesCricRuless(application);
%>
<%@page errorPage="/error.jsp"%>
<div id="rules-headers" align="center">
			<div style="font-size: 40px"><%=seriesForm.getName() %> - Super Cricket Team Contest</div>
			<div style="font-size: 15px;padding-bottom: 2px;">
				Register yourself, select your teams and enjoy <%=seriesForm.getName() %> like you have never enjoyed any series before.</div>
			<div style="font-size: 15px;width: 890px;text-align: justify;">
			<%if(seriesForm.getId().intValue() == 4 ) { %>
				<h3>Users attain maximum runs (points) at the end of the IPL4 season will be the frontrunner's and will be 
				winner of a Prize comprise of a gift worth Rs.1000/-.</h3>	
				<%} %>
	</div>


<%
	UserForm user = (UserForm) session.getAttribute(Constants.USER);
			if(user ==  null && seriesForm.getId().intValue() ==2 ) {
		%>
		<div  id="rules_links">
		
		<div class="float_l fa fbld fs11" align="center" v>
					<!--<a style="text-decoration: none" href="prenewlogin.do">Register Here</a> &nbsp;&nbsp;&nbsp;|&nbsp; &nbsp;&nbsp;
					--><a style="text-decoration: none" href="prelogin.do"> Login &nbsp;&nbsp;</a> &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
					<a style="text-decoration: none" href="prelogin.do"> Cancel </a> 
		</div>
		</div>
		<%} %>



<div id="rules_text">

<table>
	<tbody>
		<tr >
		<td></td>
		<td align="center">
			<div id="rules-headers">
			 Team Selection Rules
			 </div>
			</td>
		</tr>
		<%
		  if (cricRules != null)
		    {
		  for (CricRulesForm rulesForm : cricRules)
		  {
		    if (rulesForm != null && !rulesForm.isScoreRule)
		    {
		%>
		<tr>
			<td><img src="images/rule.png" width="30px" height="30px" /></td>
			<td align="left" valign="top" style="padding: 4px;"><%=rulesForm.getRule()%>
			</td>
		</tr>
		<%
		  }
		  }
		    }
		%>
		<tr>
		<td></td>
		<td align="center"> 
			<div id="rules-headers">
			Score System Rules
				 </div>
				 </td>
		</tr>
		
		<%
				 if (cricRules != null)
			    {
				  for (CricRulesForm rulesForm : cricRules)
				    {
				  if (rulesForm != null && rulesForm.isScoreRule)
				  {
				%>
		<tr>
			<td> <img src="images/rule.png" width="30px" height="30px" /> </td>
			<td align="left" valign="top" style="padding:4px;">
			 <%=rulesForm.getRule()%>
			 </td>
		</tr>
		<%
		  }
		    }
			    }
		%>
	</tbody>
</table>
</div>
</div>
</body>
</html>