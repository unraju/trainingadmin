package quiz.spring.hibernate.bf;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import quiz.spring.form.QuizTO;
import webservices.restful.quiz.QuizWSTO;

import common.util.Constants;
import common.util.Utility;


public class Quiz implements Serializable {

	
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}

	private Long id;

	private Long quizNo;

	private Date date;

	private String question;

	private String option1;

	private String option2;

	private String option3;

	private String option4;

	private String answer;

	private boolean live;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public Long getQuizNo() {
		return quizNo;
	}

	public void setQuizNo(Long quizNo) {
		this.quizNo = quizNo;
	}

	public QuizTO getQuizTO() {
		QuizTO quiz = new QuizTO();
		quiz.setQuestion(question);
		quiz.setOption1(option1);
		quiz.setOption2(option2);
		quiz.setOption3(option3);
		quiz.setOption4(option4);
		quiz.setId(id);
		quiz.setQuizNo(quizNo);
		quiz.setAnswer(answer);
		quiz.setLive(Utility.convertBooleanToString(live));
		quiz.setDate(Utility.convertDateToString(date, Constants.DATE_TEME_AM__PM_SHORT));
		return quiz;
	}
	
	public QuizWSTO getQuizWSTO() {
		QuizWSTO quiz = new QuizWSTO();
		quiz.setQuestion(question);
		quiz.setOption1(option1);
		quiz.setOption2(option2);
		quiz.setOption3(option3);
		quiz.setOption4(option4);
		quiz.setId(id);
		quiz.setQuizNo(quizNo);
		quiz.setAnswer(answer);
		quiz.setLive(Utility.convertBooleanToString(live));
		quiz.setDate(Utility.convertDateToString(date, Constants.DATE_TEME_AM__PM_SHORT));
		return quiz;
	}

}
