<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="java.util.ArrayList"%>
<%@page import="quiz.spring.form.QuizTO"%>
<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
			
			<html:form action="manageQuiz.do">

				<table>
					<tr>
						<td align="right">Quiz No:</td>
						<td align="left"><html:text  name="Quiz" property="quizNo" size="20" /></td>
					</tr>
					<tr>
						<td align="right">Quiz :</td>
						<td align="left"><html:textarea  name="Quiz" property="question"    cols="20"  rows="3"  /></td>
					</tr>

					<tr>
						<td align="right">option 1 :</td>
						<td align="left"><html:text  name="Quiz" property="option1" size="20" /></td>
					</tr>

					<tr>
						<td align="right">option 2 :</td>
						<td align="left"><html:text  name="Quiz" property="option2" size="20" /></td>
					</tr>

					<tr>
						<td align="right">option 3 :</td>
						<td align="left"><html:text  name="Quiz" property="option3" size="20" />
						</td>
					</tr>

					<tr>
						<td align="right">option 4 :</td>
						<td align="left"><html:text  name="Quiz" property="option4" size="20" />
						
					</td>
					</tr>

					<tr>
						<td align="right">answer :</td>
						<td align="left"><html:text  name="Quiz"   property="answer" size="20" />
					</td>
					</tr>

					
					<tr>
						<td align="right">Date :</td>
						<td align="left"><html:text   name="Quiz" property="date" size="20" disabled="true" />
						</td>
					</tr>
					<tr>
						<td align="right">Live :</td>
						<td align="left">
								<html:radio name="Quiz" property="live" value="Yes"> Yes </html:radio>
								<html:radio name="Quiz" property="live" value="No"> No </html:radio>
						</td>
					</tr>

				</table>
					<html:hidden name="Quiz"  property="id"   />
				<input type="submit" Value="Save" name="action" />
				<input type="submit" Value="Cancel" name="action" />
				<BR>
			</html:form>
				</div>
				<br>
					<% List<QuizTO> quizlist = (ArrayList<QuizTO>)session.getAttribute("Quizs"); %>
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
										<%
										 for(QuizTO quiz:quizlist)
										 {
										%>
										<tr>
											<td><%=quiz.getQuizNo() %></td>
											<td><%=quiz.getQuestion()%></td>
										    <td><%=quiz.getOption1() %></td>
											<td><%=quiz.getOption2() %></td>
										 	<td><%=quiz.getOption3() %></td>
										 	<td><%=quiz.getOption4() %></td>
											<td><%=quiz.getAnswer() %></td>
											<td><%=quiz.getDate() %></td>
											<td><%=quiz.getLive() %></td>
											<td 	>
											<a href="manageQuiz.do?id=<%=quiz.getId()%>&action=Update">Update</a></td>
											</tr>
										<%} %>
								</Table> 
								
	</div>
	

	</center>

</body>
</html>