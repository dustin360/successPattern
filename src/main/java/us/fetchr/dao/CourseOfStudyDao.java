package us.fetchr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import us.fetchr.dbModels.CourseOfStudy;

public interface CourseOfStudyDao extends JpaRepository<CourseOfStudy, Long>{
	CourseOfStudy findById(long id);
	CourseOfStudy findByCourses(String courses);
}
