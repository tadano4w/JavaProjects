package app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//Springの設定であることを示す
@Configuration

/**
 * validationのエラーメッセージを日本語化するための設定
 */
public class MessageConfig extends WebMvcConfigurerAdapter {

	/**
	 * メッセージソースBeanの定義
	 * @return メッセージソースBean
	 */
    @Bean
    ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        // 読み込むリソースファイルの指定
        messageSource.setBasenames("classpath:i18n/messages", "classpath:i18n/labels");

        // 0を指定すると、アクセスのたびに変更がないかがチェックされるようになり、実行中に変更が可能となる。
        // ただし、性能が悪くなるので本番環境では指定してはいけない。
        // http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/context/support/ReloadableResourceBundleMessageSource.html#setCacheSeconds-int-
        messageSource.setCacheSeconds(0);

        // native2asciiかかっているから不要かも
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    /**
     * 日本語化用のメッセージソースを読み込ませたバリデータファクトリの定義
     * @return バリデータファクトリ
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());
        return localValidatorFactoryBean;
    }

    /**
     * バリデータBeanの定義
     * @return 日本語化用のメッセージソースを読み込ませたバリデータ
     */
    @Override
    public Validator getValidator() {
        return validator();
    }
}