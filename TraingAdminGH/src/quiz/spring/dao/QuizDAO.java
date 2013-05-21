package quiz.spring.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import quiz.spring.form.QuizTO;
import quiz.spring.hibernate.bf.Quiz;

import common.util.HibernateUtil;

/**
 * @author raju
 */

@Repository
public class QuizDAO {

	/*
	 * @Autowired(required = true) // This is used to autowire the dependency of
	 * the dao on the SessionFactory. private SessionFactory sessionFactory;
	 */

	public void addQuiz(QuizTO quizTO) {
		// sessionFactory.getCurrentSession().save(addModule);
		System.out.println("###  addModule ###" + quizTO);
		Session session = HibernateUtil.getSession();
		// Transaction tx = session.beginTransaction();
		session.save(quizTO);
		// tx.commit();
	}

	public List<QuizTO> fetchQuizDetails() {
		List<QuizTO> quizTOs = new ArrayList<QuizTO>();
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Quiz.class);
		criteria.addOrder(Order.desc("quizNo"));
		Collection<Quiz> list = criteria.list();
		for (Quiz quiz : list) {
			quizTOs.add(quiz.getQuizTO());
		}
		return quizTOs;
	}
	
	
}
