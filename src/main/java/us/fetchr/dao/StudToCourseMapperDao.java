package us.fetchr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import us.fetchr.dbModels.StudentToCourseMapper;

public interface StudToCourseMapperDao extends JpaRepository<StudentToCourseMapper, Long>{
	List<StudentToCourseMapper> findByStudentId(long studentId);

}
