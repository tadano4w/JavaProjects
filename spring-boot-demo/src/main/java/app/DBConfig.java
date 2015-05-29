package app;

import java.sql.Driver;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//Springの設定であることを示す
@Configuration

// トランザクション管理を有効にする？
// (現状これを指定しなくても自動コミット/ロールバックは行わている。)
@EnableTransactionManagement

// mybatisのMapperを自動検出する
@MapperScan(basePackages = "app")
public class DBConfig {

	/**
	 * データソース Bean定義
	 * @param env 環境情報(application.propertiesの設定値を参照できる)
	 * @return データソース
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	@Bean
	protected DataSource getDataSource(Environment env)
			throws ClassNotFoundException {

		String driverClassName = env.getProperty("spring.datasource.driverClassName");
		String url = env.getProperty("spring.datasource.url");

		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass((Class<Driver>) Class.forName(driverClassName));
		dataSource.setUrl(url);

		return dataSource;
	}

	/**
	 * DBアクセスに使用するSqlSessionTemplate Bean定義
	 * @param dataSource データソース
	 * @return SqlSessionTemplate
	 * @throws Exception
	 */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setConfigLocation(new ClassPathResource("/config/MapperConfig.xml"));
		factoryBean.setFailFast(true);

        return new SqlSessionTemplate(factoryBean.getObject());
    }

    /**
     * トランザクション管理 Bean定義
     * @param dataSource データソース
     * @return トランザクション管理
     */
    @Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}