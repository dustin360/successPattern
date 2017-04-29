package us.fetchr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import us.fetchr.dbModels.Student;

public interface StudentDao extends JpaRepository<Student, Long>{
	Student findByUsername(String username);
	Student findByUsernameAndEmail(String username, String email);
}
