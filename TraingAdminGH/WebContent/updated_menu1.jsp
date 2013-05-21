
<%@page import="common.hibernate.bf.user.Activity"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.util.Constants"%>
<%@page errorPage="/error.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
<style type="text/css" >
<%@ include file="/css/cricket.css"%>
</style>


	
</head>



<body>
<%
	List<Activity> activities = (ArrayList<Activity>)session.getAttribute(Constants.USER_ACTIVITIES);
%>

<table border="0" align="center" cellpadding="2" cellspacing="0" >
	<tr > 
	<td class="headmenu" align="center">
		My Team </td>
	<%
		int size=  activities.size();
		int current = 0;
		for (Activity activity : activities) {
			current++;
	%>
	<td class="topmenu" align="center"  >
	<a href="<%= activity.getActivityUrl()%>.do" class="leftnavlink" title="About Us">&nbsp;<%=activity.getActivity()%>&nbsp;</a> </td>
	<%if(current != size){ %>
	<td ><span style="color:black ; font-size: 10px;">|</span></td>
	<%
		}
		}
	%> 
</tr>
</table>
    

	
</body>
</html>