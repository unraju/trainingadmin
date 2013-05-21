package webservices.restful.quiz;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import webservices.restful.user.UserWSTO;

@XmlRootElement(name = "QuizDetails")
public class QuizDetailsTO implements Serializable
{

	public QuizDetailsTO()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	private List<QuizWSTO> quizData;

	private List<UserQuizScoresWSTO> quizScores;

	private UserWSTO userWSTO;

	private String errorMessage;

	@XmlElement(name = "Quiz")
	public List<QuizWSTO> getQuizData()
	{
		return quizData;
	}

	public void setQuizData(List<QuizWSTO> quizData)
	{
		this.quizData = quizData;
	}

	@XmlElement(name = "QuizScores")
	public List<UserQuizScoresWSTO> getQuizScores()
	{
		return quizScores;
	}

	public void setQuizScores(List<UserQuizScoresWSTO> quizScores)
	{
		this.quizScores = quizScores;
	}
	
	@XmlElement(name = "User")
	public UserWSTO getUserWSTO()
	{
		return userWSTO;
	}

	public void setUserWSTO(UserWSTO userWSTO)
	{
		this.userWSTO = userWSTO;
	}

	@XmlElement(name = "ExceptionMessage")
	public String getErrorMessage()
	{
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString()
	{
		return "QuizDetailsTO [quizData=" + quizData + ", quizScores=" + "" + ", errorMessage=" + errorMessage + "]";
	}

}
