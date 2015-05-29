package app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

// Springのデフォルト設定を有効化する
@EnableAutoConfiguration

// Springのコンポーネントを自動検出する
@ComponentScan

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
