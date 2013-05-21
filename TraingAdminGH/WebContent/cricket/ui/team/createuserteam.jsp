<%@page import="cricket.struts.actionforms.common.SeriesForm"%>
<%@page import="common.util.RetriveContextData"%>
<%@page import="cricket.struts.actionforms.common.CountryForm"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="common.util.Constants"%>
<%@page import="cricket.struts.actionforms.team.PlayerForm"%>
<%@page import="cricket.struts.actionforms.team.UserTeamForm"%>
<%@page import="cricket.struts.actionforms.team.CoreTeamForm"%>
<%@page errorPage="/error.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<META HTTP-EQUIV="Refresh"
      CONTENT="<%=session.getMaxInactiveInterval()%>;URL=logout.do?sessionTimeout=true">
<script type="text/javascript">
<script language="JavaScript" type="text/javascript">
<!--
/*
  - Give Credit Where Its Due -
  Please acknowledge this article and its author, at
  least in code comments, when using this code.


  Thank you.
  Justin Whitford
*/

/*
  filtery(pattern, list)
  pattern: a string of zero or more characters by which to filter the list
  list: reference to a form object of type, select

  Example:
  <form name="yourForm">
    <input type="text" name="yourTextField"
       onchange="filtery(this.value,this.form.yourSelect)">
    <select name="yourSelect">
      <option></option>
      <option value="Australia">Australia</option>
       .......
*/
function filtery(pattern, list){
  /*
  if the dropdown list passed in hasn't
  already been backed up, we'll do that now
  */
  if (!list.bak){
    /*
    We're going to attach an array to the select object
    where we'll keep a backup of the original dropdown list
    */
    list.bak = new Array();
    for (n=0;n<list.length;n++){
      list.bak[list.bak.length] = new Array(list[n].value, list[n].text);
    }
  }

  /*
  We're going to iterate through the backed up dropdown
  list. If an item matches, it is added to the list of
  matches. If not, then it is added to the list of non matches.
  */
  match = new Array();
  nomatch = new Array();
  for (n=0;n<list.bak.length;n++){
    if(list.bak[n][1].toLowerCase().indexOf(pattern.toLowerCase())!=-1){
      match[match.length] = new Array(list.bak[n][0], list.bak[n][1]);
    }else{
      nomatch[nomatch.length] = new Array(list.bak[n][0], list.bak[n][1]);
    }
  }

  /*
  Now we completely rewrite the dropdown list.
  First we write in the matches, then we write
  in the non matches
  */
  for (n=0;n<match.length;n++){
    list[n].value = match[n][0];
    list[n].text = match[n][1];
  }
  for (n=0;n<nomatch.length;n++){
    list[n+match.length].value = nomatch[n][0];
    list[n+match.length].text = nomatch[n][1];
  }

  /*
  Finally, we make the 1st item selected - this
  makes sure that the matching options are
  immediately apparent
  */
  list.selectedIndex=0;
  filterItemTypes(1);
}
// -->
</script>

</head>

<body>



<center>
	<div id="pageheader">
		Select Your IPL Team
	</div>
				<%
				  //List<CountryForm> countries =  new RetriveContextData().getCountriesData();
				    if (request.getAttribute(Constants.ERROR_MESSAGE) != null)
				    {
				  List<String> errorMessages = (ArrayList<String>) request.getAttribute(Constants.ERROR_MESSAGE);
				  for (String error : errorMessages)
				  {
				%>
				<div id="messageStyle"><img
					src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px" />
				<%=error%></div>
				<%
				  }
				    }
				%>
				
				<%
				RetriveContextData contextData =  new RetriveContextData();
				 List<CoreTeamForm> coreTeamForms = contextData.getCoreTeamDetails(application);
				SeriesForm series  =  contextData.getCurrentSeriesForm();
				%>
	<html:form action="createUserTeam.do"   >
	<table>
		<tr>
			<td align="right"></td>
			<td align="left"><html:hidden property="id" name="userTeamForm" />
			</td>
		</tr>
		<tr>
			<td align="right">Team Name:</td>
			<td align="left"><html:text property="name" name="userTeamForm"
				size="20" maxlength="20" />
			<div style="color: red"><html:errors property="name" /></div>
			</td>
		</tr>

	</table>
	
	<div id="tableStyle"> 
	<table>
		<tr>
			<th>No</th>
			<th>Role</th>
			<!--<th>Country</th>
			--><th width="150px">Player</th>
			<th>Captain</th>
		</tr>
		<%
		  UserTeamForm userTeamForm = (UserTeamForm) session.getAttribute(Constants.USER_TEAM);
		  int count = 0;
		  for (PlayerForm playerForm : userTeamForm.getPlayers())
		  {
		    count++;
		%>
		<tr>
			<td align="center" width="50px"><%=count%></td>
			<td width="125px"><%=playerForm.getSkill()%></td>
			
		
			<td align="center" width="150px">
			<select
				name="<%=playerForm.getId().toString() + "P1"%>" >
				<option value="Please Select">Please Select</option>
				<%
				  for (CoreTeamForm coreTeamForm : coreTeamForms)
				    {
				      for (PlayerForm playerForm1 : coreTeamForm.getPlayers())
				      {
				        if (playerForm.getSkill().equals(playerForm1.getSkill()))
				        {
				          if (playerForm.getId() != null && playerForm.getId().longValue() == playerForm1.getCoreTeamPlayerId().longValue())
				          {
							%>
								<option value="<%=playerForm1.getCoreTeamPlayerId().toString()%>" selected="selected">
									<%=(coreTeamForm.getCode() + " - " + playerForm1.getName())%>
								</option>
							<%
							  }
				          else
				          {
							%>
									<option value="<%=playerForm1.getCoreTeamPlayerId().toString()%>"> 
												<%=(coreTeamForm.getCode() + " - " + playerForm1.getName())%>
									</option>
							<%
				 	 }
				        }
				      }
				    }
				%>
			</select></td>

			<td align="center">
			<%
			  if (playerForm.isCaptain())
			    {
			%>
				<input type="radio" checked="checked" name="captain" value="<%=playerForm.getId().toString()%>" />
				<%
				  }
				    else
				    {
				%>
				<input type="radio"  name="captain" value="<%=playerForm.getId().toString()%>" />
				<%
				  }
				%>
				</td>
		</tr>
		<%
		  }
		%>
	
	</table>
	</div>
	<div id="buttonStyle" >
	<html:submit property="operation" value="Confirm" alt="Submit"
				title="Submit" />
		 <%
		   String isNewTeam = (String) request.getAttribute(Constants.CANCEL_BTN_NEED);
		   if (isNewTeam != null && isNewTeam.equalsIgnoreCase("No"))
		   {
		 %> <html:submit property="operation" value="Cancel" alt="Cancel"
				title="Submit" /> <%
		   }
		 %>
       </div>   
</html:form>
			<ul style="padding:4px;color:blue;background:#D0D0D0;width:65%;" >
		  <%
		  
		    for (CoreTeamForm form : coreTeamForms)
		      {
		  %>
		        <li> <%=form.getCode()%>&nbsp;-&nbsp;<%=form.getName()%></li>
		      <%
		        }
		      %>
		      <div style="border: thin solid;border-color:gray;padding: 4px;color:blue;background:#82CAFA;" align="center" >
			<div align="left"  style="font-size:12px">Please Note -</div>
			<!-- <div  align="left" style="font-size:12px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Check once again...After submitting your team, you are not allowed to modify again.</div> -->
			
			
			<div  align="left" style="font-size:12px">You are allowed to finalise your team until - <%=series.getStartDate() %>. After then change of any player considered as substitution.</div>
			</div>
		  </ul>
		  <br>
		
		
</center>
</body>

</html>