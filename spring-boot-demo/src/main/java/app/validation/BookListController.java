package app.validation;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// Validation annotationを使用するサンプル
//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html
//JSR-303/JSR-349 Bean Validation

@Controller
@RequestMapping("/booklist")
public class BookListController {

	@RequestMapping("/")
	String index(Book book) {
		book.setName("名前");
		book.setPrice(1000);
		return "/booklist/index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	String add(@Valid @ModelAttribute("modelName") Book book, BindingResult errors) {

		// @ModelAttribute#valueを指定しなければ、クラス名を先頭小文字にした文字がモデル名となり、
		// それがビュー側で参照する際の名前になる。

		if (errors.hasErrors()) {
			return "/booklist/index";
		}

		return "/booklist/index";
	}
}
