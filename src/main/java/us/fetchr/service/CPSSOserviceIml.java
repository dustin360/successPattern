package us.fetchr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.fetchr.dao.CourseOfStudyDao;
import us.fetchr.dao.StaffDao;
import us.fetchr.dao.StudToCourseMapperDao;
import us.fetchr.dao.StudentDao;
import us.fetchr.dao.StudentToTimelineMapDao;
import us.fetchr.dao.TimeLinePostDao;
import us.fetchr.dbModels.CourseOfStudy;
import us.fetchr.dbModels.Staff;
import us.fetchr.dbModels.Student;
import us.fetchr.dbModels.StudentToCourseMapper;
import us.fetchr.dbModels.StudentToTimelineMapping;
import us.fetchr.dbModels.TimeLinePost;

@Service
public class CPSSOserviceIml implements CPSSOservice{
	
	@Autowired
	StaffDao staffDao;
	@Autowired
	StudentDao studentDao;
	@Autowired
	StudentToTimelineMapDao studentToTimelineMapDao;
	@Autowired
	TimeLinePostDao timeLinePostDao;
	@Autowired
	CourseOfStudyDao courseOfStudyDao;
	@Autowired
	StudToCourseMapperDao studToCourseMapperDao;
	

	/***********STUDENT**************/
	@Override
	public Student findStudentByUsername(String username) {
		return studentDao.findByUsername(username);
	}

	@Override
	public Student findStudentByUsernameAndEmail(String username, String email) {
		return studentDao.findByUsernameAndEmail(username, email);
	}
	@Override
	public void saveStudent(Student student) {
		studentDao.save(student);
	}

	/***********STAFF*******************/
	@Override
	public Staff findStaffByUsername(String username) {
		return staffDao.findByUsername(username);
	}

	@Override
	public Staff findStaffByUsernameAndEmail(String username, String email) {
		return staffDao.findByUsernameAndEmail(username, email);
	}
	/********COURSE OF STUDY***********/
	@Override
	public CourseOfStudy findCourseOfStudyById(long id) {
		return courseOfStudyDao.findById(id);
	}
	@Override
	public CourseOfStudy findCourseOfStudyByCourses(String courses) {
		
		return courseOfStudyDao.findByCourses(courses);
	}
	
	@Override
	public void saveCourseOfSTudy(CourseOfStudy course) {
		courseOfStudyDao.save(course);
	}

	/*******STUDENT TO TIMELINE MAPPING*******/
	@Override
	public List<StudentToTimelineMapping> findStudToTimelinemapByStudentId(long studentId) {
		
		return studentToTimelineMapDao.findByStudentId(studentId);
	}
	
	@Override
	public void saveStudToTimelineMapping(StudentToTimelineMapping mapping) {
		studentToTimelineMapDao.save(mapping);
	}

	/*******STUDENT TO COURSE MAPPER*******/
	@Override
	public List<StudentToCourseMapper> findStudToCourseMapByStudentId(long studentId) {
		return studToCourseMapperDao.findByStudentId(studentId);
	}
	@Override
	public void saveStudToCourseMapper(StudentToCourseMapper mapping) {
		studToCourseMapperDao.save(mapping);
	}
	/********TIMELINE POST ************/
	@Override
	public TimeLinePost findTimelinepostByTimelinePost(String timelinePost) {
		return timeLinePostDao.findByTimelinePost(timelinePost);
	}
	@Override
	public TimeLinePost findTimeLinePostById(long id){
		return timeLinePostDao.findOne(id);
	}
	@Override
	public void saveTimeLinepost(TimeLinePost post) {
		timeLinePostDao.save(post);
	}


}
