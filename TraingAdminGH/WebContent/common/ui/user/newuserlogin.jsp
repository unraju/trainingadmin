<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="java.util.Date"%>


<html>
<head>
<SCRIPT language="JavaScript" src="js/billpointeutil.js" type="text/javascript"></SCRIPT>
<SCRIPT language="JavaScript" src="js/scw.js" type="text/javascript"></SCRIPT>

</head>
<body>
<center>
		<div id="pageheader">
		Enter Registration Details
		</div>
		
	

<html:form action="newlogin.do" method="post">

<div  id="newuser_registration">	
		<%
			if (request.getAttribute("error") != null
						&& !"".equals(request.getAttribute("error"))) {
		%>
		<div id="messageStyle"><img align="bottom"
			src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px">
		<%=request.getAttribute("error")%>
		</div>
		<%
			}
		%>
	<table>
		
		<tr>
			<td align="right">Name :</td>
			<td align="left"><html:text property="name" name="userForm"
				size="20" maxlength="15" />
			<div id="messageStyle"><html:errors property="name" /></div>
			</td>
		</tr>
		<tr>
			<td align="right">Email :</td>
			<td align="left"><html:text property="emailId" name="userForm"
				size="30" maxlength="30" />
			<div id="messageStyle"><html:errors property="emailId" /></div>
			</td>

		</tr>
		<!-- <tr>
			<td align="right">Login Name :</td>
			<td align="left"><html:text property="loginName"
				name="userForm" size="20" maxlength="15" />
			<div id="messageStyle"><html:errors property="loginName" /></div>
			</td>
		</tr> -->
		<tr>
			<td align="right">Password :</td>
			<td align="left"><html:password property="pwd"
				name="userForm" size="20" maxlength="15" />
			<div id="messageStyle"><html:errors property="pwd" /></div>
			</td>

		</tr>
		<tr>
			<td align="right">Re-Enter Password :</td>
			<td align="left"><html:password property="reEnterPwd"
				name="userForm" size="20" maxlength="15" />
			<div id="messageStyle"><html:errors property="reEnterPwd" /></div>
			</td>

		</tr>
		<tr>
			<td align="right">Gender :</td>
			<td align="left"><html:radio property="gender" name="userForm"
				value="male" />Male/ <html:radio property="gender" name="userForm"
				value="female" />Female
			<div id="messageStyle"><html:errors property="gender" /></div>
			</td>

		</tr>
		
		<tr>
			<td align="right">Phone Number :</td>
			<td align="left"><html:text property="contactNo" name="userForm" maxlength="12"
				size="20" />
			<div id="messageStyle"><html:errors property="contactNo" /></div>
			</td>

		</tr>
		<tr>
			<td align="right">Address :</td>
			<td align="left"><html:text property="address"  maxlength="50"
				size="35"
				name="userForm" />
			<div id="messageStyle"><html:errors property="address" /></div>
			</td>

		</tr>
		<tr>
			<td align="right">Date of Birth :</td>
		
		<td align="left" >
			<html:text  property="dob" styleId="styleId" readonly="true"
				name="userForm" size="12"
				onkeyup="maskDate(this)" onblur="reformatDate(this)" 
				styleClass="textBoxExtraSmall alignRight" ></html:text>
				<label> <img
				src="images/datepicker.gif" 
				onclick="scwShow(scwID('styleId'),event);" /> </label>
					<div id="messageStyle"><html:errors property="dob" /></div>
		</td>
		</tr>
		<tr>
		
		</tr>	
		<tr></tr>
		<tr></tr>
		<tr>
			<td></td>
			<td align="left"><html:submit property="operation"
				value="Submit" alt="Submit" title="Submit" /> <html:submit
				property="operation" value="Cancel" alt="Cancel" title="Cancel" />
			<html:reset></html:reset></td>
		</tr>
	</table>
	</div>
	<div style="border: thin solid;border-color:gray;padding: 4px;background:#82CAFA; ;width: 100%;" >
			<div align="left">Please Note -</div>
			<div  align="left" style="font-size: 14px;">If you already registered earlier, use same account.<br> In case if you forgot credentials use <b>Retrieve Account</b> on login section.</div>
		</div>
</html:form>
</center>
</body>
</html>