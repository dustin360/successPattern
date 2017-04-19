package us.fetchr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import us.fetchr.mappings.StudentToTimelineMapping;
import us.fetchr.model.Student;

public interface StudentToTimelineMapDao extends JpaRepository<StudentToTimelineMapping, Long>{
	List<StudentToTimelineMapping> findByStudentId(long studentId);
}
