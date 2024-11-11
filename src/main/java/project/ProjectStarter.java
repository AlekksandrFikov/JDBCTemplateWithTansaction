package project;
 

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import project.service.StudentsService;


public class ProjectStarter {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext("project");
		
		StudentsService studentsService = context.getBean(StudentsService.class);
		studentsService.addStudents();
		

	}

}
