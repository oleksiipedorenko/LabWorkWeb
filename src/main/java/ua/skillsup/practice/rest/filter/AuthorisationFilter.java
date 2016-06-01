package ua.skillsup.practice.rest.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

import static javax.swing.text.html.CSS.getAttribute;

/**
 * Created by oleksii on 6/1/16.
 */
public class AuthorisationFilter implements Filter {

	public static final String AUTH_ATTR = "AUTH_TIME";
	private static final int DELAY = 20;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) servletRequest).getSession();
		LocalDateTime time = (LocalDateTime) session.getAttribute(AUTH_ATTR);
		if (time == null || LocalDateTime.now().isAfter(time.plusMinutes(DELAY))) {
			((HttpServletResponse)servletResponse).sendRedirect("/login");
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}
