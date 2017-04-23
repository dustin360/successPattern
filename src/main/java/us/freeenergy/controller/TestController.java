package us.freeenergy.controller;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import us.fetchr.cpsso.entities.CourseOfStudy;
import us.fetchr.cpsso.entities.Student;
import us.fetchr.cpsso.entities.StudentToCourseMapper;
import us.fetchr.cpsso.entities.StudentToTimelinePostMapper;
import us.fetchr.cpsso.entities.TimeLinePost;
import us.fetchr.cpsso.enums.Courses;
import us.freeenergy.model.ApiCreateStudent;
import us.freeenergy.model.ApiCreateTimelinePost;
import us.freeenergy.service.ServiceClass;
import us.freeenergy.util.GeneralUtility;

@RestController
public class TestController {
	
	@Autowired
	ServiceClass serviceClass;
	
	
	@RequestMapping(value="/test")
	public String createUser(@RequestBody String myJson){
		ApiCreateStudent aStudent = GeneralUtility.fromJson(myJson, ApiCreateStudent.class);
		System.out.println("01");
		Student student = new Student();
		student.setName(aStudent.getName());
		student.setUsername(aStudent.getUsername());
		student.setPassword(aStudent.getPassword());
		student.setEmail(aStudent.getEmail());
		student.setHobbies(aStudent.getHobbies());
		//student.setCourse_of_study(aStudent.getCourse_of_study());
		student.setDOB(GeneralUtility.parseToDate(aStudent.getDOB()));
		student.setBest_movies(aStudent.getBest_movies());
		student.setBest_books(aStudent.getBest_books());
		serviceClass.save(student);
		System.out.println("02");
		

		//***********POSTS***************
		List<ApiCreateTimelinePost> timeLinePostList = aStudent.getTimeLinePost();
		for(int i = 0; i < timeLinePostList.size(); i++)
		{
			ApiCreateTimelinePost aPost = timeLinePostList.get(i);
			
			TimeLinePost post = new TimeLinePost();
			post.setTimelinePost(aPost.getTimelinePost());
			post.setTime(new Timestamp(System.currentTimeMillis()));
			serviceClass.save(post);
			
			StudentToTimelinePostMapper mapper = new StudentToTimelinePostMapper();
			mapper.setStudent(student);
			mapper.setTimeLinePost(post);
			serviceClass.save(mapper);
		}
		System.out.println("03");
			
		
		
		//***********COURSE OF STUDY**************
		CourseOfStudy courseOfStudy = new CourseOfStudy();
		courseOfStudy.setCourses(Courses.ENGINEERING);
		serviceClass.save(courseOfStudy);
		System.out.println("04");
		
		StudentToCourseMapper mapper = new StudentToCourseMapper();
		mapper.setStudent(student);
		mapper.setCourseOfStudy(courseOfStudy);
		serviceClass.save(mapper);
		System.out.println("05");
		return "success";
		
		
	}
}
