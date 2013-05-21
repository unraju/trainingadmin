<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page errorPage="/error.jsp"%>

<%@page import="common.util.Constants"%>
<style type="text/css">
<%@include file="/css/cricket.css"%>
</style>

<%@page errorPage="/error.jsp"%>
<html>
<META HTTP-EQUIV="Refresh" CONTENT="<%=session.getMaxInactiveInterval()%>;URL=logout.do?sessionTimeout=true">
<body>

	<center>
		<div id="pageheader">Manage Quiz</div>
		<div id="search_players">

			<%
				if (request.getAttribute(Constants.ERROR_MESSAGE) != null) {
			%>
			<div id="messageStyle">
				<img src="images/warning_wipeout_invoiceDB.png" width="12px" height="12px" />
				<%=request.getAttribute(Constants.ERROR_MESSAGE)%>
			</div>
			<%
				}
			%>

			<form:form action="saveQuiz.dsc" commandName="quizTO">

				<table>
					<tr>
						<td align="right">Quiz No:</td>
						<td align="left"><form:input path="quizNo" size="20" /><br><form:errors path="quizNo" cssClass="errorMessage" /></td>
					</tr>
					<tr>
						<td align="right">Quiz :</td>
						<td align="left"><form:textarea path="question"    cols="20"  rows="3"  /><br><form:errors path="question" cssClass="errorMessage" /></td>
					</tr>

					<tr>
						<td align="right">option 1 :</td>
						<td align="left"><form:input path="option1" size="20" /><br><form:errors path="option1" cssClass="errorMessage" /></td>
					</tr>

					<tr>
						<td align="right">option 2 :</td>
						<td align="left"><form:input path="option2" size="20" /><br><form:errors path="option2" cssClass="errorMessage" /></td>
					</tr>

					<tr>
						<td align="right">option 3 :</td>
						<td align="left"><form:input path="option3" size="20" /><br><form:errors path="option3" cssClass="errorMessage" /></td>
					</tr>

					<tr>
						<td align="right">option 4 :</td>
						<td align="left"><form:input path="option4" size="20" /><br><form:errors path="option4" cssClass="errorMessage" /></td>
					</tr>

					<tr>
						<td align="right">answer :</td>
						<td align="left"><form:input path="answer" size="20" /><br><form:errors path="answer" cssClass="rror" /></td>
					</tr>

					
					<tr>
						<td align="right">Date :</td>
						<td align="left"><form:input path="date" size="20" disabled="true" /><br><form:errors path="answer" cssClass="rror" /></td>
					</tr>
					<tr>
						<td align="right">Live :</td>
						<td align="left"><form:radiobutton path="live" value="<%=Constants.TEXT_YES %>" /><%=Constants.TEXT_YES %> <form:radiobutton path="live"
								value="<%=Constants.TEXT_NO %>"   /><%=Constants.TEXT_NO %> </td>
					</tr>

				</table>
					<form:hidden  path="id"  />
				<input type="submit" Value="Save" name="actions" />
				<input type="submit" Value="Cancel" name="actions" />
				<BR>
			</form:form>
				</div>
				<br>
		<div id="tableStyle"> 
				<Table>
									<tr>
										<th>Quiz No</th>
										<th>Quiz</th>
										<th>Option1</th>
										<th>Option2</th>
										<th>Option3</th>
										<th>Option4</th>
										<th>Answer</th>
										<th>Date</th>
										<th>Live</th>
										<th></th>
									</tr>
										<c:forEach var="quiz" items="${quizlist}">
									
										<tr>
											<td> ${quiz.quizNo}</td>
											<td>${quiz.question}</td>
										    <td >${quiz.option1}</td>
											<td >${quiz.option2}</td>
											<td > ${quiz.option3}</td>
											<td > ${quiz.option4}</td>
											<td >${quiz.answer}</td>	
											<td >${quiz.date}</td>
											<td >${quiz.live}</td>	
											<td 	>
											<a href="preUpdateQuiz.dsc?id=${quiz.id}">Update</a></td>
											</tr>
										</c:forEach>
								</Table> 
								
	</div>
	

	</center>

</body>
</html>