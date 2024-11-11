package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import project.repository.StudentRepostory;

@Service
public class StudentsService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Autowired	
	StudentRepostory studentRepostory;
	
	
	public void addStudents() {
		
		jdbcTemplate.execute("TRUNCATE `student_test_db`.`stud`;");
		
		for (int i = 1; i <= 10; i++) {			
			studentRepostory.addStudent("Name" + i);
		}
		
				
	}

}
