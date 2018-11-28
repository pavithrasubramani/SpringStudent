package com.tickets.data;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tickets.data.exception.DataAccessException;
import com.tickets.domains.Student;

@Repository
@Transactional
public class StudentDAO {
	
	    private static final Logger logger = LoggerFactory.logger(StudentDAO.class);
	    @Autowired
		private SessionFactory sessionFactory;
		
		
		public void setSessionFactory(SessionFactory sf){												
			this.sessionFactory = sf;
		}

        public Student getstudentByEmail(String email) throws DataAccessException {
			Student level = null;
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			try{
			Query<Student> query = session.createQuery("from Student where email=:email", Student.class);
			query.setParameter("email", email);
			level = query.getSingleResult();			
			}
			catch(NoResultException e){
				logger.error(e.getMessage(),e);
				throw new DataAccessException(e.getMessage(),e);
			}
			transaction.commit();
			session.close();
			return level;
		}
        public Student Register(Student s) {
        	Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(s);
		   	transaction.commit();
			session.close();
			return s;	
        }
        
        public List<Student> listStudents() {
    		Session session = this.sessionFactory.getCurrentSession();
    		List<Student> studentList = (List<Student>) session.createQuery("from Student t").list();
    		for(Student t : studentList){
    			logger.info("Student List::"+t);
    		}
    		return studentList;
    	}

		


}
