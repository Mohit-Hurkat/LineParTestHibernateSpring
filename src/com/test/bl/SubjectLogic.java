package com.test.bl;

import java.util.List;
import java.io.IOException;
import java.sql.SQLException;

import com.test.bean.Subject;
import com.test.dao.SubjectDao;
import com.test.dao.SubjectDaoImpl;

public class SubjectLogic {
	private SubjectDao subjectDao=new SubjectDaoImpl();
	public boolean insert(Subject subject) throws IOException, ClassNotFoundException, SQLException{
		return subjectDao.insert(subject);
	}
	public Subject search(int subjectId) throws IOException, ClassNotFoundException, SQLException{
		return subjectDao.search(subjectId);
	}
	public List<Subject> displayAll() throws IOException, ClassNotFoundException, SQLException{
		return subjectDao.displayAll();
	}
	public boolean update(int subjectId, Subject subject) throws IOException, ClassNotFoundException, SQLException{
		return subjectDao.update(subjectId, subject);
	}
	public boolean delete(int subjectId) throws IOException, ClassNotFoundException, SQLException{
		return subjectDao.delete(subjectId);
	}
	public String subname(int subjectId) throws IOException, ClassNotFoundException, SQLException{
		return subjectDao.subname(subjectId);
	}
	public List<Subject> showSubject(String username) throws IOException, ClassNotFoundException, SQLException{
		return subjectDao.showSubject(username);
	}
}
 