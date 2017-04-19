package us.fetchr.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Student 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String username;
	private String password;
	private String email;
	
	@Transient
	private List<TimeLinePost> timeLinePost;
	private String hobbies;
	private String course_of_study;
	private Date DOB;
	private String best_movies;
	private String best_books;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public String getCourse_of_study() {
		return course_of_study;
	}
	public void setCourse_of_study(String course_of_study) {
		this.course_of_study = course_of_study;
	}

	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public String getBest_movies() {
		return best_movies;
	}
	public void setBest_movies(String best_movies) {
		this.best_movies = best_movies;
	}
	public String getBest_books() {
		return best_books;
	}
	public void setBest_books(String best_books) {
		this.best_books = best_books;
	}
	public List<TimeLinePost> getTimeLinePost() {
		return timeLinePost;
	}
	public void setTimeLinePost(List<TimeLinePost> timeLinePost) {
		this.timeLinePost = timeLinePost;
	}	
}
