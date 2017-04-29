package us.fetchr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import us.fetchr.dbModels.Student;
import us.fetchr.dbModels.StudentToTimelineMapping;

public interface StudentToTimelineMapDao extends JpaRepository<StudentToTimelineMapping, Long>{
	List<StudentToTimelineMapping> findByStudentId(long studentId);
}
