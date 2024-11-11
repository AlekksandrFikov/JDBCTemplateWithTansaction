package project.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import project.repository.StudentRepostory;

@Service
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class StudentsService {

	JdbcTemplate jdbcTemplate;
	StudentRepostory studentRepostory;

	public StudentsService(JdbcTemplate jdbcTemplate, StudentRepostory studentRepostory) {
		this.jdbcTemplate = jdbcTemplate;
		this.studentRepostory = studentRepostory;
	}

	@Transactional
	public void addStudents() {
		addTenStudents();
	}

	@Transactional(noRollbackFor = { RuntimeException.class })
	public void addStudentsWithNoRollbackForNullPointerException() {
		addTenStudents();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	private void addTenStudents() {

		jdbcTemplate.execute("TRUNCATE `student_test_db`.`stud`;");

		for (int i = 1; i <= 10; i++) {

			if (i < 5)
				studentRepostory.addStudentWithinNestedTransaction("Name" + i);
			else
				studentRepostory.addStudentWithinNewTransaction("Name" + i);

			if (i == 10)
				throw new NullPointerException("Generated exception. It's Ok!. See results in DataBase...");
		}
	}

}
