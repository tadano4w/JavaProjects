package app.hello;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello2")
public class Hello2Controller {

	@RequestMapping("/")
    String index() {
        return "/hello2/index";
    }
}