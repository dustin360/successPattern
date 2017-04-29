package us.fetchr.apiresources;

import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

public class ApiCreateStudent 
{
	private long id;
	private String name;
	private String username;
	private String password;
	private String email;
	private String hobbies;
	private String DOB;
	private String best_movies;
	private String best_books;
	private List<ApiCreateTimeLinePost> timeLinePosts;
	private List<ApiCreateCourseOfStudy> courseOfStudy;
	
	
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
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
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
	public List<ApiCreateTimeLinePost> getTimeLinePosts() {
		return timeLinePosts;
	}
	public void setTimeLinePosts(List<ApiCreateTimeLinePost> timeLinePosts) {
		this.timeLinePosts = timeLinePosts;
	}
	public List<ApiCreateCourseOfStudy> getCourseOfStudy() {
		return courseOfStudy;
	}
	public void setCourseOfStudy(List<ApiCreateCourseOfStudy> courseOfStudy) {
		this.courseOfStudy = courseOfStudy;
	}
		
}
