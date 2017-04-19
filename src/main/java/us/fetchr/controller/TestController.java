package us.fetchr.controller;

import static org.mockito.Matchers.anyString;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import us.fetchr.dao.StudentDao;
import us.fetchr.dao.StudentToTimelineMapDao;
import us.fetchr.dao.TimeLinePostDao;
import us.fetchr.mappings.StudentToTimelineMapping;
import us.fetchr.model.Student;
import us.fetchr.model.TimeLinePost;
import us.fetchr.util.GeneralUtility;

@RestController
public class TestController {
	@Autowired
	StudentDao studentDao;
	@Autowired
	StudentToTimelineMapDao studentToTimelineMapDao;
	@Autowired
	TimeLinePostDao timeLinePostDao;
	
	
	@RequestMapping(value="/test")
	public String createUser(@RequestBody String myJson){
		System.out.println(01);
		Student aStudent = GeneralUtility.fromJson(myJson, Student.class);
		System.out.println(02);
		
		Student student = new Student();
		student.setName(aStudent.getName());
		student.setUsername(aStudent.getUsername());
		student.setPassword(aStudent.getPassword());
		student.setEmail(aStudent.getEmail());
		student.setHobbies(aStudent.getHobbies());
		student.setCourse_of_study(aStudent.getCourse_of_study());
		student.setDOB(GeneralUtility.parseToDate(aStudent.getDOB().toString()));
		student.setBest_movies(aStudent.getBest_movies());
		student.setBest_books(aStudent.getBest_books());
		System.out.println(03);
		
		studentDao.save(student);
		
		List<TimeLinePost> timeLinePostList = aStudent.getTimeLinePost();
		System.out.println("size: " +timeLinePostList.size());
		for(int i = 0; i < timeLinePostList.size(); i++){
			System.out.println(05);
			TimeLinePost aPost = timeLinePostList.get(i);
			System.out.println(06);
			TimeLinePost post = new TimeLinePost();
			System.out.println(07);
			
			post.setTimelinePost(aPost.getTimelinePost());
			post.setTime(new Timestamp(System.currentTimeMillis()));
			//post.setTime(GeneralUtility.parseToTime(aPost.getTime().toString()));
			timeLinePostDao.save(post);
			System.out.println(05);
			
			StudentToTimelineMapping mapping = new StudentToTimelineMapping();
			mapping.setStudentId(studentDao.findByUsername(student.getUsername()).getId());
			mapping.setTimelineId(timeLinePostDao.findByTimelinePost(post.getTimelinePost()).getId());
			studentToTimelineMapDao.save(mapping);
		}
		
		return "success";
		
		
	}
}
