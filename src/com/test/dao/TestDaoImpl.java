package com.test.dao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;

import java.io.IOException;
import com.test.bean.Question;
import com.test.bean.Subject;

public class TestDaoImpl implements TestDao {
	private Configuration cfg;
	private SessionFactory factory;
	private Session session;

	public TestDaoImpl() {
		cfg = new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();
	}

	public int result(String username, int subjectId) throws ClassNotFoundException, SQLException {
		session = factory.openSession();
		Transaction tx = null;
		int result = 0;
		try {
			tx = session.beginTransaction();
			Query query = session.createSQLQuery("Select * from RESULT WHERE USERNAME=:user AND SUBJECTID=:subId ")
					.addScalar("value", new IntegerType());
			query.setString("user", username);
			query.setInteger("subId", subjectId);
			result = (int) query.uniqueResult();
			System.out.println(result + "hola");
			System.out.println(subjectId);
			tx.commit();
			return result;

		} catch (Exception ex) {
			tx.rollback();
		} finally {
			session.close();
		}
		return result;
	}

	public boolean dateCheck(int subjectId) throws SQLException, ClassNotFoundException {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from SUBJECT WHERE SUBJECT_ID=:subId ");
			query.setInteger("subId", subjectId);
			List<Subject> subjectList = query.list();
			String sdate = subjectList.get(0).getStart_();
			String edate = subjectList.get(0).getEnd_();
			if (sdate.compareTo(date1) < 0 && edate.compareTo(date1) > 0) {
				System.out.println(sdate);
				System.out.println(edate);
				System.out.println(date1);
				tx.commit();
				return true;
			}

		} catch (Exception ex) {
			tx.rollback();
		} finally {
			session.close();
		}
		return false;
	}

	public boolean check_questions(int subjectId, String username)
			throws ClassNotFoundException, SQLException, IOException {
		session = factory.openSession();
		Transaction tx = null;
		int result = 0;
		try {
			tx = session.beginTransaction();
			Query query = session.createSQLQuery("Select COUNT(*) as value from QUESTIONS WHERE  SUBJECT_ID=:subId ")
					.addScalar("value", new IntegerType());
			query.setInteger("subId", subjectId);
			result = (int) query.uniqueResult();
			System.out.println(result);
			Query query1 = session.createQuery("UPDATE QUESTIONS SET VALUE=0  where VALUE=:value");
			query1.setString("value", username);
			query.executeUpdate();
			tx.commit();
			return true;

		} catch (Exception ex) {
			tx.rollback();
		} finally {
			session.close();
		}
		return false;
	}

	public List<Question> getQuestions(String username, int subjectId)
			throws ClassNotFoundException, SQLException, InterruptedException {
		session = factory.openSession();
		Transaction tx = null;
		try {
			System.out.println("m");
			tx = session.beginTransaction();
			Query query = session.createSQLQuery("SELECT * FROM QUESTIONS WHERE rownum <=10 AND SUBJECT_ID =:subId AND VALUE !=:value ORDER BY DBMS_RANDOM.RANDOM");
			query.setInteger("subId", subjectId);
			query.setString("value", username);
			List<Question> quesList =query.list();
			System.out.println(quesList);
			// int id=quesList.get(0).getQuestion_Id();
			// int id1=quesList.get(1).getQuestion_Id();
			// System.out.println(id);
			// System.out.println(id1);
			Query query1 = session.createSQLQuery("UPDATE QUESTIONS SET VALUE=:value where QUESTION_ID=:questId");
			query1.setString("value", username);
			query1.setInteger("questId", quesList.get(0).getQuestion_Id());
			int s = query.executeUpdate();
			System.out.println("value of s" + s);
			tx.commit();
			System.out.println(quesList);
			return quesList;
		} catch (Exception ex) {
			tx.rollback();
		} finally {
			session.close();
		}
		return null;
	}

}
