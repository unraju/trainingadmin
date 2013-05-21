

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.util.Constants"%>
<%@page import="common.struts.actionforms.user.UserRoleForm"%>
<%@page import="common.struts.actionforms.user.ActivityForm"%>
<%@page errorPage="/error.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<script type="text/javascript">

function showsubmenu(id){
		submenu = document.getElementById(id);
		for(i=1;i<=10;i++)
		{
			if(i==id)
			{
				submenu.style.display="block";
			}
			else 
			{
				document.getElementById(i).style.display="none";
			}
		}
	}
</script>
	
</head>
<body onload="showsubmenu(1)">
<%
	//List<Activity> activities = (ArrayList<Activity>)session.getAttribute(Constants.USER_ACTIVITIES);
	List<UserRoleForm> userRoleForms = (ArrayList<UserRoleForm>)session.getAttribute(Constants.USER_ROLES);	
%>
<div id="navmenu">
<ul>
	<%
	if(userRoleForms != null)
	{
	int i=0;
	for(UserRoleForm userRole :userRoleForms)
	{
	  i++;
	%>
	<li><a href="#" onclick="javascript:showsubmenu(<%=i%>)"> 
			<%=userRole.getDiscription()%></a></li>
	<%} }%>
	</ul>
</div>

<div id="sublinks">
	<%
		if(userRoleForms != null)
		{
		int j=0;
		for(UserRoleForm userRole :userRoleForms)
		{
		  j++;
	%>
	<ul id="<%=j%>">
	<%
		for (ActivityForm activity : userRole.getActivities()) 
		{
	%>
		<li><A HREF="<%= activity.getActivityUrl()%>.do"> <%=activity.getActivity()%></A></li>
	<%
		}
	%> 
	</ul>
	<%
	} }
	%>

</div>

</body>
</html>