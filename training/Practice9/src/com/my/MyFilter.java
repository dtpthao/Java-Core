package com.my;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//@WebFilter("/*")
public class MyFilter implements Filter {
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("MyFilter#doFilter before");
		
//		request.setCharacterEncoding(
//				(String)request.getServletContext().getAttribute("encoding"));
		response.setContentType("text/html; charset=utf-8");
		chain.doFilter(request, response);
		System.out.println("MyFilter#doFilter after");
		
		
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("MyFilter#init");
	}

}
