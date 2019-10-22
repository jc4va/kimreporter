package com.kimreporter.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//특정 경로에 접근하는 경우 현재 사용자가 로그인한 상태의 사용자인지 체크 
public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	// 현재 사용자가 로그인한 상태인지를 체크하고 컨트롤러를 호출 할 것인지 결정 -> 로그인 하지 않은 상태라면 Redirect
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null) {
			logger.info("USER NOT LOGGED IN");
			
			saveDest(request);
			
			response.sendRedirect("/user/login");
			return false;
		}
		return true;
	}
	
	// 원래 사용자가 원하는 페이지의 정보를 HttpSession에 dest라는 이름으로 저장, 겟방식인경우에는 URI정보 뒤의 파라미터를 함께 보관 
	private void saveDest(HttpServletRequest req) {
		String uri = req.getRequestURI();
		
		String query = req.getQueryString();
		
		if (query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?" + query;
		}
		
		if (req.getMethod().equals("GET")) {
			logger.info("dest: " + (uri + query));
			req.getSession().setAttribute("dest", uri + query);
		}
	}
}
