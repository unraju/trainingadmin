package webservices.restful.quiz;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "QuizScores")
public class UserQuizScoresWSTO
{

	private String name;

	private String score;

	private String answeredCount;
	@XmlElement
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	@XmlElement
	public String getScore()
	{
		return score;
	}

	public void setScore(String score)
	{
		this.score = score;
	}
	@XmlElement
	public String getAnsweredCount()
	{
		return answeredCount;
	}

	public void setAnsweredCount(String answeredCount)
	{
		this.answeredCount = answeredCount;
	}

}
