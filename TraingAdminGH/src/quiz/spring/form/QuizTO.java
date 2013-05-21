package quiz.spring.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import quiz.spring.hibernate.bf.Quiz;

import common.util.Constants;
import common.util.Utility;
@XmlRootElement(name="Quiz")
public class QuizTO extends ActionForm implements Serializable {

		
	public QuizTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Long id;

	private Long quizNo;

	private String date;

	//@NotEmpty(message = "question Should Not be Empty")
	private String question;

	//@NotEmpty(message = "option1 Should Not be Empty")
	private String option1;

	//@NotEmpty(message = "option2 Should Not be Empty")
	private String option2;

	//@NotEmpty(message = "option3 Should Not be Empty")
	private String option3;

	//@NotEmpty(message = "option4 Should Not be Empty")
	private String option4;

	//@NotEmpty(message = "answer Should Not be Empty")
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

	public Object getQuiz() {
		Quiz quiz = new Quiz();
		quiz.setQuestion(question);
		quiz.setOption1(option1);
		quiz.setOption2(option2);
		quiz.setOption3(option3);
		quiz.setOption4(option4);
		quiz.setId(id);
		quiz.setQuizNo(quizNo);
		quiz.setAnswer(answer);
		quiz.setLive(Utility.convertStringToBoolean(live));
		return quiz;
	}

	public void copyDate(Quiz quiz) {
		quiz.setQuestion(question);
		quiz.setOption1(option1);
		quiz.setOption2(option2);
		quiz.setOption3(option3);
		quiz.setOption4(option4);
		// quiz.setId(id);
		quiz.setQuizNo(quizNo);
		quiz.setAnswer(answer);
		quiz.setLive(Utility.convertStringToBoolean(live));
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		// TODO Auto-generated method stub
		return super.validate(mapping, request);
	}

}
