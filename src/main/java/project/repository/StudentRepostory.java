package project.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentRepostory {

	JdbcTemplate jdbcTemplate; 
	
	public StudentRepostory(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Transactional(	propagation = Propagation.NESTED)
	public void addStudentWithinNestedTransaction(String name) {
		
		String sql = "INSERT INTO `student_test_db`.`stud` (`name`) VALUES (?);";
		jdbcTemplate.update(sql, name);		
	}
		

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addStudentWithinNewTransaction(String name) {
		
		String sql = "INSERT INTO `student_test_db`.`stud` (`name`) VALUES (?);";
		jdbcTemplate.update(sql, name);
	}
}
