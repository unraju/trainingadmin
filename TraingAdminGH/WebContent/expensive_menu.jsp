<%@page import="cricket.struts.actionforms.common.SeriesForm"%>
<%@page import="common.util.RetriveContextData"%>
<%@page import="common.struts.actionforms.user.UserForm"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.util.Constants"%>
<%@page import="common.struts.actionforms.user.UserRoleForm"%>
<%@page import="common.struts.actionforms.user.ActivityForm"%>
<%@page import="common.hibernate.bf.user.User"%>
<%@page errorPage="/error.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">
	function showsubmenu(id) {
		submenu = document.getElementById(id);
		for (i = 10; i <= 110; i++) {
			submenu1 = document.getElementById(i);
			if (i == id) {
				submenu.style.display = "block";

				submenu.backgroundColor = "#D4D0C8";

			} else if (i != id && submenu1 != null) {
				submenu1.style.display = "none";
			}
		}
	}
	function showAlert() {

		alert('Please Login');
	}
	function showCaptainAlert() {

		alert('Selecting current player as a Captain will cost only 50 points.');
	}

	function filterItemTypes(id) {

		for (i = 1; i <= 5; i++) {
			if (i == id) {
				var selObj = document.getElementById('bd' + id);
				//selObj.style.display = "block";

			} else {
				var selObj = document.getElementById('bd' + i);
				//selObj.style.display="none";	
			}
		}
	}
</script>

<script>
	view = false;
	function view_only() {
		view = true;
		document.StatPac_Survey_Software.submit();
	}
	function open_survey_software_results() {
		for (i = 0; i < document.StatPac_Survey_Software.Choice.length; i++) {
			if (document.StatPac_Survey_Software.Choice[i].checked == true) {
				document.StatPac_Survey_Software.vote.value = "" + (i + 1);
			}
		}
		if ((document.StatPac_Survey_Software.vote.value == "")
				&& (view == false)) {
			alert("Please make a selection.");
			return;
		}
		if (view == true) {
			document.StatPac_Survey_Software.view_mode.value = "1";
		} else {
			document.StatPac_Survey_Software.view_mode.value = "0";
		}
		view = false;
		url = "survey_software_results.html";
		args = 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=500,height=200,left=20,top=20';
		result = window.open(url, "survey_software", args);
	}
</script>


<!-- End of StatCounter Code -->


</head>
<%
 
    UserForm user = (UserForm) session.getAttribute(Constants.USER);
    List<UserRoleForm> userRoleForms = null;
    //List<Activity> activities = (ArrayList<Activity>)session.getAttribute(Constants.USER_ACTIVITIES);
    if (user != null)
    {
  if (user.getUserRoles() != null && user.getUserRoles().size() > 0)
  {
    userRoleForms = (ArrayList<UserRoleForm>) user.getUserRoles();
  }
    }
    else if (user == null)
    {
 		 userRoleForms = (ArrayList<UserRoleForm>) application.getAttribute(Constants.DEFAULT_USER_ROLES);
    }
    //System.out.println("### userRoleForms ###"+userRoleForms);
    int menu = 0;
    String aaa;
    if (session.getAttribute("menu") != null)
    {
  aaa = (String) session.getAttribute("menu");
  menu = Integer.parseInt(aaa);

    }
    else if (userRoleForms != null && userRoleForms.size() > 0)
    {
  menu = userRoleForms.get(0).getId().intValue();
    }
%>

<body>
	<!--  onload="showsubmenu(<%=menu%>)" >-->

	<div id="navmenu">
		<ul>
			<%
			  if (userRoleForms != null)
			    {
			  for (UserRoleForm userRole : userRoleForms)
			  {
			    if (menu == 0)
			    {
			      menu = userRole.getId().intValue();
			    }
			    if (userRole.isUserAssociated() && user == null)
			    {
			%>
			<li title="<%=userRole.getDiscription()%>"><a
				href="prelogin.do?menu=<%=userRole.getId().toString()%>
					&submenu=<%=userRole.getActivities().get(0).getId().toString()%>">
					<%=userRole.getRole()%></a></li>
			<%
			  }
			    else
			    {
			%>
			<li title="<%=userRole.getDiscription()%>"><a
				href="<%=userRole.getActivities().get(0).getActivityUrl()%>.do?menu=<%=userRole.getId().toString()%>
				&submenu=<%=userRole.getActivities().get(0).getId().toString()%>">
					<%=userRole.getDiscription()%></a></li>
			<%
			  }
			  }
			    }
			%>
		</ul>
	</div>

	<div id="sublinks">
		<%
		  if (userRoleForms != null)
		    {
		  for (UserRoleForm userRole : userRoleForms)
		  {
		    if (menu != 0 && menu == userRole.getId().intValue())
		    {
		%>
		<ul id="<%=userRole.getId().intValue()%>">
			<%
			  for (ActivityForm activity : userRole.getActivities())
			      {
			        if (userRole.isUserAssociated() && user == null)
			        {
			%>
			<li><A HREF="viewRules.do"> <%=activity.getActivity()%></A></li>
			<%
			  }
			        else
			        {
			%>
			<li><A
				HREF="<%=activity.getActivityUrl()%>.do?menu=<%=userRole.getId().toString()%>&submenu=<%=activity.getId().toString()%>">
					<%=activity.getActivity()%></A></li>
			<%
			  }
			      }
			%>
		</ul>
		<%
		  }
		  }
		    }
		%>
	</div>

</body>
</html>