package com.halo.admin.controller;

import com.halo.admin.service.MenuService;
import com.halo.admin.vo.MenuModel;
import com.halo.admin.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Hailuo
 * @Date: 2019/4/3 15:19
 * @Description：
 */
@Controller()
@Slf4j
public class IndexController {
    @Autowired
    private MenuService menuService;



	@RequestMapping("/hello")
	@ResponseBody
	public Response hello() {
		return Response.buildResponse();
	}

	@RequestMapping("/index")
	public String index(Model model){
        List<MenuModel> menus = menuService.currentUserMenu();
        model.addAttribute("menus",menus);
		return "page/index";
	}

	@RequestMapping("/main")
	public String main(){
		return "page/main";
	}


}
