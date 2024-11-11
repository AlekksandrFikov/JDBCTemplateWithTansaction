package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.repository.StudentRepostory;

@Service
public class StudentsService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Autowired	
	StudentRepostory studentRepostory;
	
	@Transactional
	public void addStudents() {
		
		jdbcTemplate.execute("TRUNCATE `student_test_db`.`stud`;");
		
		for (int i = 1; i <= 10; i++) {
			
			if (i < 5) studentRepostory.addStudentWithinNestedTransaction ("Name" + i);
				else studentRepostory.addStudentWithinNewTransaction("Name" + i);
			
			if (i==10) throw new RuntimeException("Generated exception. It's Ok!. See results in DataBase...");
		}
		
				
	}

}
