package app.hello;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/hello")
    String home(@RequestParam(value="name", required=false, defaultValue="World") String language, Model model) {
        model.addAttribute("language", language);
        return "home/hello";
    }

}