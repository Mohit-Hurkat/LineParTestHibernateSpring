package com.test.dao;

import java.io.IOException;
import java.sql.SQLException;
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
import com.test.bean.Result;

@Component
public class ResultDaoImpl implements ResultDao {
	@Autowired
	private HibernateTemplate template;
	
	public List<Result> show(String username) throws ClassNotFoundException, SQLException, IOException {
		List<Result> resList=(List<Result>) template.execute(new ResultSet(username));
		return resList;
	}

	public boolean set(Result result) throws ClassNotFoundException, SQLException, IOException {
		template.save(result);
		return true;
	}
}
class ResultSet implements HibernateCallback{
	String username;
	ResultSet(String user){
	username=user;
	}
	public Object doInHibernate(Session session)throws HibernateException,SQLException{
		Query query = session.createQuery("FROM RESULT WHERE USERNAME=:user");
		query.setString("user", username);
		List<Result> resList = query.list();
		return resList;
		}
}


