package com.xt8.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import com.xt8.model.User;
import com.xt8.util.Constants;

public class SessionFilter implements Filter {

	public FilterConfig config;

	public void destroy() {
		this.config = null;
	}

	public static boolean isContains(String container, String[] regx) {
		boolean result = false;

		for (int i = 0; i < regx.length; i++) {
			if (container.indexOf(regx[i]) != -1) {
				return true;
			}
		}
		return result;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(
				(HttpServletResponse) response);

		String logonStrings = config.getInitParameter("logonStrings");
		String includeStrings = config.getInitParameter("includeStrings");
		String redirectPath = hrequest.getContextPath()
				+ config.getInitParameter("redirectPath");
		String disabletestfilter = config.getInitParameter("disabletestfilter");

		if (disabletestfilter.toUpperCase().equals("Y")) {
			chain.doFilter(request, response);
			return;
		}
		String[] logonList = logonStrings.split(";");
		String[] includeList = includeStrings.split(";");

		if (!this.isContains(hrequest.getRequestURI(), includeList)) {
			chain.doFilter(request, response);
			return;
		}

		if (this.isContains(hrequest.getRequestURI(), logonList)) {
			chain.doFilter(request, response);
			return;
		}
		HttpSession session = hrequest.getSession();
		if (session != null
				&& session.getAttribute(Constants.LOGINED_USER) != null) {
			chain.doFilter(request, response);
			return;
		} else {
			wrapper.sendRedirect(redirectPath);
			return;
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}
}