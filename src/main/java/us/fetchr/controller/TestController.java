package us.fetchr.controller;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import us.fetchr.apiresources.ApiCreateCourseOfStudy;
import us.fetchr.apiresources.ApiCreateStudent;
import us.fetchr.apiresources.ApiCreateTimeLinePost;
import us.fetchr.dao.StudentDao;
import us.fetchr.dao.StudentToTimelineMapDao;
import us.fetchr.dao.TimeLinePostDao;
import us.fetchr.dbModels.CourseOfStudy;
import us.fetchr.dbModels.Student;
import us.fetchr.dbModels.StudentToCourseMapper;
import us.fetchr.dbModels.StudentToTimelineMapping;
import us.fetchr.dbModels.TimeLinePost;
import us.fetchr.service.CPSSOservice;
import us.fetchr.util.GeneralUtility;

@RestController
public class TestController {
	@Autowired
	CPSSOservice cpssoService;
	
	
	@RequestMapping(value="/test", method=RequestMethod.POST)
	public String createUser(@RequestBody String myJson){
		ApiCreateStudent aStudent = GeneralUtility.fromJson(myJson, ApiCreateStudent.class);
		
		Student student = new Student();
		student.setName(aStudent.getName());
		student.setUsername(aStudent.getUsername());
		student.setPassword(aStudent.getPassword());
		student.setEmail(aStudent.getEmail());
		student.setHobbies(aStudent.getHobbies());
		student.setDOB(GeneralUtility.parseToDate(aStudent.getDOB().toString()));
		student.setBest_movies(aStudent.getBest_movies());
		student.setBest_books(aStudent.getBest_books());
		
		cpssoService.saveStudent(student);
		
		List<ApiCreateTimeLinePost> timeLinePostList = aStudent.getTimeLinePosts();
		for(int i = 0; i < timeLinePostList.size(); i++)
		{
			ApiCreateTimeLinePost aPost = timeLinePostList.get(i);
			TimeLinePost post = new TimeLinePost();
			
			post.setTimelinePost(aPost.getTimelinePost());
			post.setTime(new Timestamp(System.currentTimeMillis()));
			//post.setTime(GeneralUtility.parseToTime(aPost.getTime().toString()));
			cpssoService.saveTimeLinepost(post);
			
			StudentToTimelineMapping mapping = new StudentToTimelineMapping();
			mapping.setStudentId(cpssoService.findStudentByUsername(student.getUsername()).getId());
			mapping.setTimelineId(cpssoService.findTimelinepostByTimelinePost(post.getTimelinePost()).getId());
			cpssoService.saveStudToTimelineMapping(mapping);
		}
		
		List<ApiCreateCourseOfStudy> courseOfStudyList = aStudent.getCourseOfStudy();
		for(int i = 0; i < courseOfStudyList.size(); i++)
		{
			ApiCreateCourseOfStudy aCourse = courseOfStudyList.get(i);
			CourseOfStudy courseOfStudy = new CourseOfStudy();
			
			courseOfStudy.setCourses(aCourse.getCourses());
			cpssoService.saveCourseOfSTudy(courseOfStudy);
			
			StudentToCourseMapper aMapper = new StudentToCourseMapper();
			aMapper.setStudentId(cpssoService.findStudentByUsername(student.getUsername()).getId());
			aMapper.setCourseId(cpssoService.findCourseOfStudyByCourses(courseOfStudy.getCourses()).getId());
			cpssoService.saveStudToCourseMapper(aMapper);
		}
		
		return "success";
		
		
	}
}
