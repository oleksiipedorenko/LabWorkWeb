package ua.skillsup.practice.rest.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by oleksii on 2/25/17.
 */
public class AuthFilter implements Filter {

	public final static String LOG_ATTR_NAME = "NiceNameAttr";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest,
	                     ServletResponse servletResponse,
	                     FilterChain filterChain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) servletRequest).getSession();
		LocalDateTime logInTime = (LocalDateTime) session.getAttribute(LOG_ATTR_NAME);
		if (Objects.isNull(logInTime)
				|| logInTime.plusMinutes(10).isBefore(LocalDateTime.now())) {
			((HttpServletResponse) servletResponse).sendRedirect("/login");
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}
