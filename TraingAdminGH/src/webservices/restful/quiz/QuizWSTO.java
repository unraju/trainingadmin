package webservices.restful.quiz;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import common.util.Constants;
@XmlRootElement(name="Quiz")
public class QuizWSTO  implements Serializable {

		
	public QuizWSTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Long id;

	private Long quizNo;

	private String date;

	private String question;

	private String option1;

	private String option2;

	private String option3;

	private String option4;

	private String answer;

	private String live = Constants.TEXT_NO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	 @XmlElement
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	 @XmlElement
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	 @XmlElement
	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}
	 @XmlElement
	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}
	 @XmlElement
	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}
	 @XmlElement
	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}
	 @XmlElement
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	 @XmlElement
	public String getLive() {
		return live;
	}

	public void setLive(String live) {
		this.live = live;
	}
	 @XmlElement
	public Long getQuizNo() {
		return quizNo;
	}

	public void setQuizNo(Long quizNo) {
		this.quizNo = quizNo;
	}

	}
