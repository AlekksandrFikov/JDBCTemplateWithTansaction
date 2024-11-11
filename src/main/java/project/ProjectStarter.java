package project;
 

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import project.service.StudentsService;


public class ProjectStarter {

	public static void main(String[] args) {

		try (AbstractApplicationContext context = new AnnotationConfigApplicationContext("project")) {
		
		StudentsService studentsService = context.getBean(StudentsService.class);
		studentsService.addStudents();
		
		}
	}

}
