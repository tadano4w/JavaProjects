package app.validation;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// カスタムバリデータを使用するサンプル
// http://area-b.com/blog/2015/01/31/2332/

@Controller
@RequestMapping("/booklist2")
public class BookList2Controller {

	@InitBinder
	void initBinder(WebDataBinder binder) {
		// ここでカスタムバリデータを設定する
		binder.setValidator(new BookValidator());
	}

	@RequestMapping("/")
	String index(Book book) {
		book.setName("名前2");
		book.setPrice(10000);
		return "/booklist2/index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	String add(@Valid @ModelAttribute Book book, BindingResult errors) {

		if (errors.hasErrors()) {
			return "/booklist2/index";
		}

		return "/booklist2/index";
	}
}
