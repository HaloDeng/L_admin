package com.halo.admin.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halo.admin.constant.ReturnCode;
import com.halo.admin.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Hailuo
 * @Date: 2019/4/4 10:35
 * @Description： 登录失败处理
 */
@Component
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		Response r;
        log.error("授权失败！",exception);
		if (exception instanceof UsernameNotFoundException) {
			r = Response.buildResponse(ReturnCode.USER_NOT_EXIST);
		} else if (exception instanceof BadCredentialsException) {
			r = Response.buildResponse(ReturnCode.PASSWORD_INVALID);
		} else if (exception instanceof DisabledException) {
			r = Response.buildResponse(ReturnCode.USER_DISABLED);
		}else {
			r = Response.buildResponse(ReturnCode.FAIL);
		}
		pw.write(objectMapper.writeValueAsString(r));
		pw.flush();
		pw.close();
	}
}
