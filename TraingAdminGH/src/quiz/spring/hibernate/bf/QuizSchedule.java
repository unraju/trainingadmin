package quiz.spring.hibernate.bf;

import java.io.Serializable;
import java.util.Date;

public class QuizSchedule implements Serializable
{

	public QuizSchedule()
	{

	}

	private Long id;

	private Quiz todayQuiz;;

	private Long quizNo;

	private Date startDate;

	private Date endDate;

	private Date createdDate;
	
	private boolean live;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Quiz getTodayQuiz()
	{
		return todayQuiz;
	}

	public void setTodayQuiz(Quiz todayQuiz)
	{
		this.todayQuiz = todayQuiz;
	}

	public Long getQuizNo()
	{
		return quizNo;
	}

	public void setQuizNo(Long quizNo)
	{
		this.quizNo = quizNo;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public boolean isLive()
	{
		return live;
	}

	public void setLive(boolean live)
	{
		this.live = live;
	}
	
	
	
}
