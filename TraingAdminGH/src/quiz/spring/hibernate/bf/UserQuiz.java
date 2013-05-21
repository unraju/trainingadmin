package quiz.spring.hibernate.bf;

import java.io.Serializable;
import java.util.Date;

import webservices.restful.quiz.UserQuizScoresWSTO;
import webservices.restful.user.UserWSTO;

import common.hibernate.bf.user.User;
import common.util.Constants;
import common.util.DateUtil;
import common.util.Utility;

public class UserQuiz implements Serializable
{

	public UserQuiz()
	{
		score = 0L;
		answered = false;
		answeredCount = 0L;
	}

	private Long id;

	private Date lastAnsweredDate;

	private Long score;

	private User user;

	private boolean answered;

	private Long answeredCount;

	private Long userId;

	private Long sunSignId;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Date getLastAnsweredDate()
	{
		return lastAnsweredDate;
	}

	public void setLastAnsweredDate(Date lastAnsweredDate)
	{
		this.lastAnsweredDate = lastAnsweredDate;
	}

	public Long getScore()
	{
		return score;
	}

	public void setScore(Long score)
	{
		this.score = score;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public boolean isAnswered()
	{
		return answered;
	}

	public void setAnswered(boolean answered)
	{
		this.answered = answered;
	}

	public Long getAnsweredCount()
	{
		return answeredCount;
	}

	public void setAnsweredCount(Long answeredCount)
	{
		this.answeredCount = answeredCount;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public Long getSunSignId()
	{
		return sunSignId;
	}

	public void setSunSignId(Long sunSignId)
	{
		this.sunSignId = sunSignId;
	}

	public UserWSTO getUserWSTO()
	{
		UserWSTO userQuizTO = new UserWSTO();
		userQuizTO.setAnswered(answered + "");
		userQuizTO.setAnsweredCount(answeredCount + "");
		userQuizTO.setLastAnsweredDate(Utility.convertDateToString(lastAnsweredDate, Constants.DATE_FORMAT_DD_MM_YYYY));
		userQuizTO.setUserScore(score + "");

		userQuizTO.setUserName(user.getName());
		userQuizTO.setContactNo(user.getContactNo());
		userQuizTO.setEmilId(user.getEmailId());
		// user.setActive(Utility.convertBooleanToChar(this.active));
		userQuizTO.setDob(DateUtil.getStringFromDate(user.getDob(), Constants.DATE_FORMAT_DD_MMM_YYYY));
		// user.setCreatedDate(DateUtil.getStringFromDate(this.createdDate,
		// "dd/MM/yyyy"));
		// if (gender != null) user.setGender(gender);
		userQuizTO.setUserRef(user.getUserRef());
		// TODO Auto-generated method stub
		return userQuizTO;
	}

	public UserQuizScoresWSTO getUserScoreWSTO()
	{
		UserQuizScoresWSTO userQuizScoresWSTO = new UserQuizScoresWSTO();
		userQuizScoresWSTO.setName(user.getName());
		userQuizScoresWSTO.setScore(score + "");
		return userQuizScoresWSTO;
	}

}
