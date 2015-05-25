package app;

import java.sql.Driver;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
@MapperScan(basePackages = "app")
public class ApplicationDataSourceConfiguration {

	@SuppressWarnings("unchecked")
	@Bean
	protected DataSource getDataSource(Environment env)
			throws ClassNotFoundException {

		String driverClassName = env
				.getProperty("spring.datasource.driverClassName");
		String url = env.getProperty("spring.datasource.url");

		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass((Class<Driver>) Class
				.forName(driverClassName));
		dataSource.setUrl(url);

		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setConfigLocation(new ClassPathResource(
				"/config/MapperConfig.xml"));
		sessionFactory.setFailFast(true);
		return sessionFactory.getObject();
	}
}