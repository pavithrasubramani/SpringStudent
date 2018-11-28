package com.tickets.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

    @Entity
	@Table(name="student")
	public class Student {
	  @Id
	  @Column(name="id")
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private int id;
		
		@Column(name="name")
		private String name;
		
		@Column(name="email")
		private String email;
		
		@Column(name="is_active")
		private boolean is_active = true;
		
		@Column(name="password")
		private String password;
		
		

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

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
		public boolean getIs_active() {
			return is_active;
		}

		public void setIs_active(boolean is_active) {
			this.is_active = is_active;
		}
		
		
		@Override
		public String toString(){
		
			return "id="+id+", name="+name+", email="+email+", is_active="+is_active+", password="+password;
		}

		
		
}
