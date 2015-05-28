package app;

import java.sql.Driver;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
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
    public SqlSession sqlSessionTemplate(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setConfigLocation(new ClassPathResource("/config/MapperConfig.xml"));
		factoryBean.setFailFast(true);

        return new SqlSessionTemplate(factoryBean.getObject());
    }
}