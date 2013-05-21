<%@page import="cricket.struts.actionforms.team.PlayerForm"%>
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
			List<PlayerForm> players = (ArrayList<PlayerForm>)session.getAttribute(Constants.SEARCHED_PLAYERS);
			if(players!=  null) {
		%>
		
		<div id="pageheader">
				Searched Players
			</div>
		<%
			if (request.getAttribute(Constants.ERROR_MESSAGE_2) != null && !("".equals(request.getAttribute(Constants.ERROR_MESSAGE_2)))) {
		%>
			<div id="messageStyle">
			<img src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px" /> <%=request.getAttribute(Constants.ERROR_MESSAGE_2)%>
			</div>
		<%
			}
		%>
<html:form action="managePlayers.do" >

<div id="tableStyle"> 
	
	<table>
		<tr>
			
		</tr>

		<tr>
			<th>Sl.No</th>
			<th width="150px">Player Name</th>
			<th width="150px">Country</th>
			<th>Skill</th>
			<th>Active</th>
			<th>Change</th>
		
		</tr>
		<%
		  int count = 0;
		    for (PlayerForm playerForm : players)
		    {
		      count++;
		%>
		<tr>
			
			<td align="center" width="50px"><%=count%></td>
			<td width="150px"><%=playerForm.getName()%></td>
			<td width="150px"><%=playerForm.getCountryName()%></td>
			<td width="75px"><%=playerForm.getSkill()%></td>
			<td width="75px"><%=playerForm.getActiveString()%></td>
			<td align="center"><a href="managePlayers.do?selectedId=<%=playerForm.getId()%>&operation=<%=Constants.UPDATE_BTN_VALUE%>">
				<div style="font-size: 13px;color: #0000FF">
					Change
				</div>
				</a></td>
		</tr>
		<%
		  }
		%>
	
	</table>
	</div>
	<div>
	<br>

	</div>
</html:form>
		<%
			}
		%>
</center>
</body>
</html>
