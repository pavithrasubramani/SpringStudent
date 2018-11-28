package com.tickets.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.tickets.biz.StudentService;
import com.tickets.data.exception.DataAccessException;
import com.tickets.domains.Student;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private StudentService studentservice;
	
	private String email;
	private String password;
	private String name;
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() throws DataAccessException {
		Student s;
		s = getStudentservice().getstudentByEmail(getEmail());
		if (s.getPassword().equals(getPassword())) {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			session.setAttribute("userSession", s);
			return "dashboard?faces-redirect=true";
		}
		else{
			String errorMessage = "Invalid Login";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
			FacesContext.getCurrentInstance().addMessage("form:msgId", message);
		}

		return null;
	}

    
	public String Register() throws DataAccessException {
		Student s=new Student();
		s.setName(getName());
		s.setEmail(getEmail());
		
	 String Password=BCrypt.hashpw(getPassword(), BCrypt.gensalt());
	    s.setPassword(Password);
		   studentservice.register(s);
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			session.setAttribute("userSession", s);
			return "index?faces-redirect=true";
		}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		if (session.getAttribute("userSession") != null) {
			session.invalidate();
		}
		return "index?faces-redirect=true";
	}

	public StudentService getStudentservice() {
		return studentservice;
	}

	public void setStudentservice(StudentService studentservice) {
		this.studentservice = studentservice;
	}
	
	
}	
