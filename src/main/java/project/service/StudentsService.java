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
	public void addStudents() throws Exception {
		addTenStudents(new RuntimeException("Generated exception. It's Ok!. See results in DataBase..."));
	}

	@Transactional(noRollbackFor = { RuntimeException.class })
	public void addStudentsWithNoRollbackForNullPointerException() throws Exception {
		addTenStudents(new NullPointerException("Generated exception. It's Ok!. See results in DataBase..."));
	}

	@Transactional(rollbackFor = { Exception.class })
	public void addStudentsWithRollbackForException() throws Exception {
		addTenStudents(new Exception("Generated exception. It's Ok!. See results in DataBase..."));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	private void addTenStudents(Exception ex) throws Exception {

		jdbcTemplate.execute("TRUNCATE `student_test_db`.`stud`;");

		for (int i = 1; i <= 10; i++) {

			if (i < 5)
				studentRepostory.addStudentWithinNestedTransaction("Name" + i);
			else
				studentRepostory.addStudentWithinNewTransaction("Name" + i);

			if (i == 10 && ex != null)
				throw ex;
		}
	}

}
