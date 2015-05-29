package app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import app.interceptors.ControllerInterceptor;

//Springの設定であることを示す
@Configuration

/**
 * Webアプリケーション設定
 */
public class ApplicationWebConfig extends WebMvcConfigurerAdapter {

	/**
	 * Controllerに追加するインタセプタの設定
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// 全てのコントローラの呼び出し時に実行されるインタセプタを登録
		registry.addInterceptor(new ControllerInterceptor()).addPathPatterns("/**");
	}
}