package com.harrisburg.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.harrisburg.dao.CategoryDAO;
import com.harrisburg.entity.Category;

@WebFilter("/*")
public class CommonFilter implements Filter {

	private CategoryDAO categoryDao;
  
    public CommonFilter() {
    	categoryDao = new CategoryDAO();
    }


	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		
		System.out.println(path);
		
		if(!path.startsWith("/admin/")) {
			List<Category> listCategory = categoryDao.listAll();
			request.setAttribute("listCategory", listCategory);
			System.out.println("comman filter -> doFilter");
		}
			chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
