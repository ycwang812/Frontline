package com.frontline.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	private String charset;

	public EncodingFilter() {
	}

	public void destroy() {
		charset = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// doBeforeProcessing
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		response.getWriter();

		chain.doFilter(request, response);

		// doAfterProcessing
	}

	public void init(FilterConfig config) throws ServletException {

		charset = config.getInitParameter("charset");
	}

}
