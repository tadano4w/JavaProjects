package app.mybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mybatis")
public class SampleController {

	@Autowired
	SampleService service;

    @RequestMapping("/")
    public String index() {
        return "/mybatis/index";
    }

    @RequestMapping(value="/select", method = RequestMethod.POST)
    public String select() {
    	System.out.println(service.getValue().getValue());
        return "/mybatis/index";
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insert(String name) {
    	service.register(name);
        return "/mybatis/index";
    }
}
