package webservices.restful.user;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="User")
public class UserWSTO  implements Serializable {

		
	private String userName;

	// unique no
	private String userRef;

	private String emilId;

	private String userId;

	private String password;

	private String contactNo;

	private String dob;
	
	private String lastAnsweredDate;

	private String userScore;

	private String answered;

	private String answeredCount;

	private String answeredCorrectly;

	private String userErrorMessage;

	
	@XmlElement
	 public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	@XmlElement
	public String getUserScore()
	{
		return userScore;
	}

	public void setUserScore(String userScore)
	{
		this.userScore = userScore;
	}

	@XmlElement
	public String getUserRef()
	{
		return userRef;
	}

	public void setUserRef(String userRef)
	{
		this.userRef = userRef;
	}

	public String getEmilId()
	{
		return emilId;
	}

	public void setEmilId(String emilId)
	{
		this.emilId = emilId;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	 @XmlElement
	public String getContactNo()
	{
		return contactNo;
	}

	public void setContactNo(String contactNo)
	{
		this.contactNo = contactNo;
	}

	public String getDob()
	{
		return dob;
	}

	public void setDob(String dob)
	{
		this.dob = dob;
	}

	public String getLastAnsweredDate()
	{
		return lastAnsweredDate;
	}

	public void setLastAnsweredDate(String lastAnsweredDate)
	{
		this.lastAnsweredDate = lastAnsweredDate;
	}
	
	 @XmlElement
	public String getAnswered()
	{
		return answered;
	}

	public void setAnswered(String answered)
	{
		this.answered = answered;
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
	 @XmlElement
	public String getAnsweredCorrectly()
	{
		return answeredCorrectly;
	}

	public void setAnsweredCorrectly(String answeredCorrectly)
	{
		this.answeredCorrectly = answeredCorrectly;
	}
	 @XmlElement
	public String getUserErrorMessage()
	{
		return userErrorMessage;
	}

	public void setUserErrorMessage(String userErrorMessage)
	{
		this.userErrorMessage = userErrorMessage;
	}

	

	}
