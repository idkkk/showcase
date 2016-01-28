package org.rubik.datasource.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 限速过滤器。
 * 
 * @author xiajinxin
 * @since 2015-04-25
 */
//@Component
public class RateLimitFilter implements Filter {

	private static Logger LOGGER = LoggerFactory.getLogger(RateLimitFilter.class);
	
	private static RateLimiter rateLimiter;

	public void init(FilterConfig filterConfig) throws ServletException {
		rateLimiter = RateLimiter.create(100.0); //每秒不超过100个任务被提交
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (rateLimiter.tryAcquire()) {
			chain.doFilter(request, response);
		} else {
			LOGGER.debug("System limitation reached!");
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_BAD_GATEWAY);
		}
	}

	public void destroy() {
	}
}
