package quiz.spring.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import quiz.spring.form.QuizTO;

import common.util.Constants;

/**
 * @author raju
 */
public class QuizController extends Action
{
	private QuizService quizService;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		QuizTO quizTO = (QuizTO) form;
		String action = (String) request.getParameter("action");
		HttpSession httpSession = request.getSession();
		quizService = new QuizService();
		if (action != null)
		{
			if (action.equals("Save"))
			{
				if(! isValid(quizTO))
				{
					request.setAttribute(Constants.ERROR_MESSAGE, "Please enter all therequired data");
					return mapping.findForward("success");
				}
				quizService.addQuiz(quizTO);
				request.setAttribute("Quiz", new QuizTO());
				httpSession.setAttribute("Quizs", quizService.fetchQuizDetails());
			}
			else if (action.equals("Cancel"))
			{
				request.setAttribute("Quiz", new QuizTO());
			}
			else if (action.equals("Update"))
			{
				String quidId = (String) request.getParameter("id");
				request.setAttribute("Quiz", quizService.fetchQuiz(Long.parseLong(quidId)).getQuizTO());
			}
		}
		else
		{
			httpSession.setAttribute("Quizs", quizService.fetchQuizDetails());
		}

		return mapping.findForward("success");
	}

	private boolean  isValid(QuizTO quizTO)
	{
		boolean valid=  true;
		if(quizTO.getQuestion() == null || quizTO.getQuestion().trim().equals("")
				|| quizTO.getOption1() == null || quizTO.getOption1().trim().equals("")
				|| quizTO.getOption2() == null || quizTO.getOption2().trim().equals("")
				|| quizTO.getOption3() == null || quizTO.getOption3().trim().equals("")
				|| quizTO.getOption4() == null || quizTO.getOption4().trim().equals("")
				|| quizTO.getAnswer() == null || quizTO.getAnswer().trim().equals("")
				)
		{
			valid = false;
		}
		return valid;
	}

}
