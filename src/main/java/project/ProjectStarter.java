package project;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import project.service.StudentsService;

public class ProjectStarter {

	public static void main(String[] args) throws Exception {

		try (AbstractApplicationContext context = new AnnotationConfigApplicationContext("project")) {

			StudentsService studentsService = context.getBean(StudentsService.class);

			int key = 3; // tern switch for another case ...
			switch (key) {
			case 1:
				studentsService.addStudents();
				break;

			case 2:
				studentsService.addStudentsWithNoRollbackForNullPointerException();
				break;

			case 3:
				studentsService.addStudentsWithRollbackForException();
				break;
			}

		}
	}

}
