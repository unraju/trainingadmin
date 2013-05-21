

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.util.Constants"%>
<%@page import="common.*"%>
<%@page import="common.struts.actionforms.user.ActivityForm"%>
<%@page import="common.struts.actionforms.user.UserRoleForm"%>
<%@page errorPage="/error.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<script type="text/javascript">

function showsubmenu(id){
		submenu = document.getElementById(id);
		for(i=10;i<=103;i++)
		{
			submenu1 = document.getElementById(i);
			if(i==id)
			{
				submenu.style.display="block";
				
			}
			else if(i!=id && submenu1 != null)
			{
				submenu1.style.display="none";
			}
		}
	}
</script>
	
</head>
	<%

		//List<Activity> activities = (ArrayList<Activity>)session.getAttribute(Constants.USER_ACTIVITIES);
		List<UserRoleForm> userRoleForms = (ArrayList<UserRoleForm>) session
		.getAttribute(Constants.USER_ROLES);

	int submenu = 0;
	String aaa;
	if (session.getAttribute("submenu") != null) {
		 aaa = (String) session.getAttribute("submenu");
		 submenu=Integer.parseInt(aaa);
		
	} else if(userRoleForms != null  && userRoleForms.size()>0)
	{
		submenu = userRoleForms.get(0).getId().intValue();
	}
	
%>

<body onload="showsubmenu(<%=submenu%>)" >



<div id="navmenu">
<ul>
	<%
		if (userRoleForms != null) {
			for (UserRoleForm userRole : userRoleForms) {
				if(submenu == 0)
				{
					submenu= userRole.getId().intValue();
				}
	%>
	<li><a href="#" onclick="javascript:showsubmenu(<%=userRole.getId().intValue()%>)" > 
			<%=userRole.getDiscription()%></a></li>
	<%
		}
		}
	%>
	</ul>
</div>

<div id="sublinks">
	<%
		if (userRoleForms != null) {
			for (UserRoleForm userRole : userRoleForms) {
			
	%>
	<ul id="<%=userRole.getId().intValue()%>">
	<%
		for (ActivityForm activity : userRole.getActivities()) {
	%>
		<li><A HREF="<%= activity.getActivityUrl()%>.do?submenu=<%=userRole.getId().toString()%>" 
		> <%=activity.getActivity()%></A></li>
	<%
		}
	%> 
	</ul>
	<%
		}
		}
	%>

</div>

</body>
</html>