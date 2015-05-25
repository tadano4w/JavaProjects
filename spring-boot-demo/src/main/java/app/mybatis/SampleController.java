package app.mybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mybatis")
public class SampleController {

	@Autowired
	SampleService service;

    @RequestMapping("/")
    public String index() {
    	System.out.println(service.getValue().getValue());
        return "index";
    }
}
