package project;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@EnableTransactionManagement
public class ProjectConfig {

	@Bean
	public DataSource dataSource() {
		
			DriverManagerDataSource driverManager = new DriverManagerDataSource();
			driverManager.setUrl("jdbc:mysql://localhost:3306/student_test_db");
			driverManager.setUsername("root");
			driverManager.setPassword("rainbow");
			
		return driverManager;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {			
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {			
		return new DataSourceTransactionManager(dataSource);
	}
	
}
