package com.harrisburg.controller.frontend;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter("/*")
public class CustomerLoginFilter implements Filter {

	private static final String[] REQUIRED_URLS = {
			"/view_profile", "/edit_profile", "/update/profile"
	};
 
    public CustomerLoginFilter() {
    }


	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		if(path.startsWith("/admin/")) {
			chain.doFilter(request, response);
			return;
		}
		boolean loggedIn =  session != null && session.getAttribute("loggedCustomer") != null;
		
		System.out.println("path: "+ path );
		System.out.println("loggedIn: "+ loggedIn);
		
		String requestPath = httpRequest.getRequestURL().toString();
		
		if(!loggedIn && isLoginRequired(requestPath)) {
			String queryString = httpRequest.getQueryString();
			String redirectURL = requestPath;
			if(queryString != null) {
				redirectURL = requestPath.concat("?").concat(queryString);
			}
			
			session.setAttribute("redirectURL", redirectURL);
			
			String loginPage = "frontend/login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
			dispatcher.forward(request, response);
			
		}else {
			chain.doFilter(request, response);
		}
	}
	
	private boolean isLoginRequired(String requestUrl) {
		for(String loginRequiredUrl: REQUIRED_URLS) {
			if(requestUrl.contains(loginRequiredUrl)) {
				return true;
			}
		}
		
		return false;
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
