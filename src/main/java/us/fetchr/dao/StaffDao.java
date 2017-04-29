package us.fetchr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import us.fetchr.dbModels.Staff;

public interface StaffDao extends JpaRepository<Staff, Long>
{
	Staff findByUsername(String username);
	Staff findByUsernameAndEmail(String username, String email);
}
