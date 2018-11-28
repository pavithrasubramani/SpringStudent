package com.tickets.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tickets.data.StudentDAO;
import com.tickets.data.exception.DataAccessException;
import com.tickets.domains.Student;

@Component
@Service
public class StudentService {
	
	@Autowired
	private StudentDAO studentDAO;
			
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
		}
   
	public Student getstudentByEmail(String email) {
		try {
			return this.studentDAO.getstudentByEmail(email);
		} catch (DataAccessException e) {
			
			e.printStackTrace();
		}
		return null;
		
	} ;
	public List<Student> listStudent() {
		return this.studentDAO.listStudents();
	}
	public Student register(Student s) {
		return this.studentDAO.Register(s);
	}
}
