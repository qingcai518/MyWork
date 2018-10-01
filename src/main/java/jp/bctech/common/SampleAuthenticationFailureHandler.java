package jp.bctech.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class SampleAuthenticationFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		String errorMsg = exception.getMessage();
		
		System.out.println("==== fail to login =====");
		System.out.println(exception);
		
		response.sendRedirect(request.getContextPath() + "/" + request.getParameter("identifier") + "/login?error=" + errorMsg);
	}
}
