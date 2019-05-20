package com.halo.admin.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halo.admin.vo.Response;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hailuo
 * @Date: 2019/4/4 10:48
 * @Description： 登录成功，封装返回数据.生成token并返回
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> data = new HashMap<>();
		data.put("token", "11111111111111111111111");
		out.write(objectMapper.writeValueAsString(Response.buildResponse(data)));
		out.flush();
		out.close();
	}
}
