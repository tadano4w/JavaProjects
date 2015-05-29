package app.mybatis;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import app.DBConfig;
import app.mybatis.SampleServiceTest.Config;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		// loaderを使用するとComponentScanが何故かきかないのでclassesで指定している。
		//		loader = AnnotationConfigContextLoader.class,
		classes = {DBConfig.class, Config.class},
		initializers = ConfigFileApplicationContextInitializer.class)

// トランザクションは成功しても常にロールバックさせる
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class SampleServiceTest {

	@Configuration
	@ComponentScan("app.mybatis")
	static class Config {
	}

	@Autowired
	private SampleService service;

	@Test
	public void valueが取得できること() {
		service.deleteAll();
		service.register("hoge");
		assertThat(service.getName(), is("hoge"));
	}
}
