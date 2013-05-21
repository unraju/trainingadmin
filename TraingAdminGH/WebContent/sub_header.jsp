<%@page import="common.hibernate.bf.user.User"%>
<%@page import="common.util.Constants"%>
<%@page import="java.util.Date"%>
<%@page errorPage="/error.jsp"%>
<%
  User user = (User) session.getAttribute(Constants.USER);
%>

<table style="width: 960px; margin-top: 0px; margin-bottom: 0px;" border="0" cellpadding="0" cellspacing="0">
<tbody><tr><td align="center"><a name="top"></a>
<table style="margin-top: 5px;" width="960" border="0" cellpadding="0" cellspacing="0">
<tbody><tr>
<td align="left">
	<a href=""> </a>
</td>
<td align="right">

<div style="width: 200px; height: 25px; float: right; text-align: right;">
<div id="translate_link">
<a class="topnav"  HREF="prelogin.do">Sign In</A>
</div>
	<br>
	<br>
	<table border="0" cellpadding="0" cellspacing="0"><tbody><tr>
	<td><input name="sitesearch" value="www.w3schools.com" type="hidden"></td>
	</tr></tbody></table>
</td>
</tr>
</tbody></table>
</td>
</tr>
</tbody>
</table>
