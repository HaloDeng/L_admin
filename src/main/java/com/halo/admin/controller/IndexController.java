package com.halo.admin.controller;

import com.halo.admin.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Hailuo
 * @Date: 2019/4/3 15:19
 * @Description：
 */
@Controller()
@Slf4j
public class IndexController {
	@RequestMapping("/hello")
	@ResponseBody
	public Response hello() {
		return Response.buildResponse();
	}

	@RequestMapping("/index")
	public String index(){
		//由于用户配置了角色，但是还未做权限匹配
		return "page/index";
	}

}
