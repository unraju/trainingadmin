<%@page import="common.struts.actionforms.user.UserForm"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="common.hibernate.bf.user.User"%>
<%@page import="common.util.Constants"%>
<%@page import="java.util.Date"%>
<%@page errorPage="/error.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<%
  response.setDateHeader("Expires", 0);	
    response.setHeader("Pragma", "no-cache");
    if (request.getProtocol().equals("HTTP/1.1"))
    {
  response.setHeader("Cache-Control", "no-cache");
    }
%>
<%
  UserForm user = (UserForm) session.getAttribute(Constants.USER);
%>
<table style="padding: 0px;border-width:0px;" width="100%">
	<tr style="padding: 0px;border-width:0px;" >
		<td style="padding: 0px;border-width:0px;">
		<table  style="padding: 0px;border-width:0px;" width="100%">
			<tr style="padding: 0px;border-width:0px;">
				<td style="padding: 0px;border-width:0px;">
				<div><img src="images/wclogo.jpg" width="60px" height="60px"
					style="float: left;" /></div>
				</td>
				<td valign="top">
							<div class="mainLabel">Super Cric Team - Enjoy with
							Your Own Cricket Team
							</div>
							<div style="padding-top: 15px " >
							<table  style="padding: 0px;border-width:0px;"  width="100%"  >
								<tr style="color: blue;">
									<td  align="left" valign="bottom" style="padding: 0px;"
										style="font-size: 12px; color: blue;">
									<%
									  if (user != null && user.getName() != null)
									    {
									%> Welcome - <%=user.getName()%> <%
   }
 %>
									</td>
									<td align="right" style="font-size: 12px; color: blue;padding: 0px;" valign="bottom">
									Contact Us - info@supercricteam.com | <A
										style="text-decoration: none" HREF="prelogin.do">Home</A> | <%
									  if (user != null && user.getName() != null)
									    {
									%> <A HREF="preUpdateUserProfile.do">Update Profile </A> | <A
										HREF="prechangepwd.do">Change Password</A> | <A
										style="text-decoration: none" HREF="logout.do">Logout</A> <%
   }
     else
     {
 %> <A style="text-decoration: none" HREF="prelogin.do">Sign
									In</A> <%
   }
 %>					</td>
 						</tr>
 						</table>
					</div>
				</td>
			</tr>
		</table>
		</td>
		</tr>
	<tr style="padding: 0px;">
		<td style="padding: 0px;"><jsp:include page="/expensive_menu.jsp"></jsp:include></td>
	</tr>
</table>
<!--<div align="center" style="padding-top: 2px;"><script
				type="text/javascript">
			google_ad_client = "ca-pub-6526302357614603";
			/* Add8 Link Units */
			google_ad_slot = "5251754288";
			google_ad_width = 728;
			google_ad_height = 15;
			//
			</script> <script type="text/javascript"
				src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
			</script> <script type="text/javascript"
				src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
			</script></div>
			-->
</body>
</html>