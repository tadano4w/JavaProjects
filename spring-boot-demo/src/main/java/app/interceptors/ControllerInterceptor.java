package app.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// HandlerInterceptorAdapterを使用すると、Controllerの前後＋ビュー表示後にインタセプトできる。
public class ControllerInterceptor extends HandlerInterceptorAdapter {

	private static final Logger log = LoggerFactory
			.getLogger(ControllerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		// 各メソッド間でパラメータを受け渡す場合は、インスタンス変数ではなくリクエストパラメータで渡す
		request.setAttribute("a", 3);

		// 呼び出される先のクラスが特定する方法が不明なので、ログの出し分けができない。
		log.info("preHandle");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		Integer a = (Integer)request.getAttribute("a");
		log.info("postHandle");
}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		Integer a = (Integer)request.getAttribute("a");
		log.info("afterCompletion");
}
}
