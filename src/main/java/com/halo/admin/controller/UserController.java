package com.halo.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hailuo
 * @Date: 2019/4/24 15:16
 * @Description：
 */
@Controller
@Slf4j
@RequestMapping("/admin")
public class UserController {


	/**
	 * 登录页面,未授权请求和登录错误都会返回到该请求
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String error, Model model) {
		if ("".equals(error)) {
			//如果带了参数error表示登录失败了，配置在SecurityConfig的failureUrl中
			model.addAttribute("msg","用户名或密码错误");
		}
		return "page/login";
	}
}
