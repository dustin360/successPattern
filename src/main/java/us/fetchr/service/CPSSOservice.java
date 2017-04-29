package us.fetchr.service;

import java.util.List;

import us.fetchr.dbModels.CourseOfStudy;
import us.fetchr.dbModels.Staff;
import us.fetchr.dbModels.Student;
import us.fetchr.dbModels.StudentToCourseMapper;
import us.fetchr.dbModels.StudentToTimelineMapping;
import us.fetchr.dbModels.TimeLinePost;

public interface CPSSOservice 
{
	//student
	Student findStudentByUsername(String username);
	Student findStudentByUsernameAndEmail(String username, String email);
	void saveStudent(Student student);
	
	//staff
	Staff findStaffByUsername(String username);
	Staff findStaffByUsernameAndEmail(String username, String email);
	
	//course of study
	CourseOfStudy findCourseOfStudyById(long id);
	CourseOfStudy findCourseOfStudyByCourses(String courses);
	void saveCourseOfSTudy(CourseOfStudy course);
	
	//student to timeline map
	List<StudentToTimelineMapping> findStudToTimelinemapByStudentId(long studentId);
	void saveStudToTimelineMapping(StudentToTimelineMapping mapping);
	
	//student to course mapper
	List<StudentToCourseMapper> findStudToCourseMapByStudentId(long studentId);
	void saveStudToCourseMapper(StudentToCourseMapper mapping);
	
	//timeline post
	TimeLinePost findTimelinepostByTimelinePost(String timelinePost);
	TimeLinePost findTimeLinePostById(long id);
	void saveTimeLinepost(TimeLinePost post);
	
	
	
	
}
