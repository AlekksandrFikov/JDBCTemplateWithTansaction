package project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepostory {

	@Autowired
	JdbcTemplate jdbcTemplate; 
	
	public void addStudent(String name) {
		
		String sql = "INSERT INTO `student_test_db`.`stud` (`name`) VALUES (?);";
		jdbcTemplate.update(sql, name);
		
	}
	
}
