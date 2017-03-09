package com.test.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.test.bean.Question;
import com.test.bean.Subject;

@Component
public class SubjectDaoImpl implements SubjectDao {
	@Autowired
	private HibernateTemplate template;
	private Subject subj = null;

	@Override
	public boolean insert(Subject subject) throws IOException, ClassNotFoundException, SQLException {
		int subId=(int) template.execute(new MaximumNum());
		subject.setSubject_Id(subId+1);
		template.save(subject);
		return true;
	}

	@Override
	public Subject search(int subjectId) throws IOException, ClassNotFoundException, SQLException {
		subj=(Subject)template.get(Subject.class, subjectId);
		return subj;
	}

	@Override
	public List<Subject> displayAll() throws IOException, ClassNotFoundException, SQLException {
		List<Subject> subList =new ArrayList<Subject>();
		subList=template.loadAll(Subject.class);
		return subList;
	}

	@Override
	public boolean update(int subjectId, Subject subject) throws IOException, ClassNotFoundException, SQLException {
		template.update(subject);
		return true;
	}

	@Override
	public boolean delete(int subjectId) throws IOException, ClassNotFoundException, SQLException {
		subj=(Subject)template.get(Subject.class, subjectId);
		template.delete(subj);
		return true;
	}

	@Override
	public String subname(int subjectId) throws IOException, ClassNotFoundException, SQLException {
		subj=(Subject)template.get(Subject.class, subjectId);
		return subj.getSubject();
	}

//	@Override
//	public List<Subject> showSubject(String subjectName) throws IOException, ClassNotFoundException, SQLException {
//		
//	}
}

class MaximumNum implements HibernateCallback{
	public Object doInHibernate(Session session)throws HibernateException,SQLException{
		Query query = session.createSQLQuery("SELECT MAX(SUBJECT_ID) as value FROM QUESTIONS").addScalar("value", new IntegerType());
		int quesId = (int) query.uniqueResult();
		return quesId;
	}
}
