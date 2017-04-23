package us.freeenergy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import us.fetchr.ServiceLocator;
import us.fetchr.cpsso.entities.CourseOfStudy;
import us.fetchr.cpsso.entities.Student;
import us.fetchr.cpsso.entities.TimeLinePost;
import us.fetchr.cpsso.service.CPSSOservice;

@Service
public class ServiceClass 
{
	private static final CPSSOservice cpssoService = ServiceLocator.getInstance().getCPSSOservice();
	
	//**************OTHERS************************
	public <T> T save(T t)
	{
		cpssoService.createNewRecord(t);
		return t;
	}
	public <T> T update(T t){
		cpssoService.updateRecord(t);
		return t;
	}
	
	//*************STUDENT*******************
	public Student findByUsernameandPassword(String username, String password){
		String query = "select e from Student e where e.username = '"+ username+"' and e.password = '"+ password+"' ";
		Student student = (Student) cpssoService.getUniqueRecordByHQL(query);
		if (student == null){
			return null;
		}
		else 
			return student;
	}
	
	public Student findByUsernameandEmail(String username, String email){
		String query = "select e from Student e where e.username = '"+ username+"' and e.email = '"+ email+"' ";
		Student student = (Student) cpssoService.getUniqueRecordByHQL(query);
		if (student == null){
			return null;
		}
		else 
			return student;
	}
	
	//************STUDENT TO COURSE MAPPER************8
	public CourseOfStudy findCourseofStudyById(long id){
		String query = "select e.courseOfStudy from StudentToCourseMapper e where e.student.id = '"+id+"'   ";
		List<CourseOfStudy> courseOfStudyList =  (List<CourseOfStudy>) cpssoService.getAllRecordsByHQL(query);
		if (courseOfStudyList.isEmpty()){
			return null;
		}
		else
			return courseOfStudyList.get(0);
	}
	
	//************STUDENT TO TIMELINEPOST MAPPER************8
	public TimeLinePost findTimelinePostByStudentId(long id){
		String query = "select e.timeLinePost from StudentToTimelinePostMapper e where e.student.id = '"+id+"'   ";
		List<TimeLinePost> timeLinePostList =  (List<TimeLinePost>) cpssoService.getAllRecordsByHQL(query);
		if (timeLinePostList.isEmpty()){
			return null;
		}
		else
			return timeLinePostList.get(0);		//return the FIRST POST
	}
}
