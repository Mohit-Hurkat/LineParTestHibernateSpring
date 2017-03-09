package com.test.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.test.bean.Question;

@Component
public class QuestionDaoImpl implements QuestionDao {
	@Autowired
	private HibernateTemplate template;
	private Question ques = null;

	@Override
	public boolean insert(Question question) throws IOException, ClassNotFoundException, SQLException {
			int quesId=(int) template.execute(new MaximumNumber());
			question.setQuestion_Id(quesId+1);
			template.save(question);
			return true;
	}

	@Override
	public Question search(int questionId) throws IOException, ClassNotFoundException, SQLException {
		Question ques=(Question)template.get(Question.class, questionId);
		return ques;
	}

	@Override
	public List<Question> displayAll(int subjectId) throws IOException, ClassNotFoundException, SQLException {
			List<Question> quesList =new ArrayList<Question>();
			quesList=template.loadAll(Question.class);
			return quesList;
	}

	@Override
	public boolean update(int questionId, Question question) throws IOException, ClassNotFoundException, SQLException {
		template.update(question);
		return true;
	}

	@Override
	public boolean delete(int questionId) throws IOException, ClassNotFoundException, SQLException {
		Question ques=(Question)template.get(Question.class, questionId);
		template.delete(ques);
		return true;
	}

	public String answer(int questionId) throws IOException, ClassNotFoundException, SQLException {
		Question ques=(Question)template.get(Question.class, questionId);
		return ques.getAns();
	
}
}
class MaximumNumber implements HibernateCallback{
	public Object doInHibernate(Session session)throws HibernateException,SQLException{
		Query query = session.createSQLQuery("SELECT MAX(QUESTION_ID) as value FROM QUESTIONS").addScalar("value", new IntegerType());
		int quesId = (int) query.uniqueResult();
		return quesId;
	}
}
