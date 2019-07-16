package com.halo.admin.controller;

import com.halo.admin.constant.ReturnCode;
import com.halo.admin.service.RoleService;
import com.halo.admin.service.UserService;
import com.halo.admin.vo.Response;
import com.halo.admin.vo.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: halo
 * @Date: 2019/7/14 12:41
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

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

    @RequestMapping("adminList")
    public String list(Model model) {
        model.addAttribute("users", userService.userList());
        return "page/admin/adminList";
    }

    @RequestMapping("addUser")
    public String addUser(Model model) {
        model.addAttribute("roles", roleService.allRoles());
        return "page/admin/addUser";
    }

    @RequestMapping("add")
    @ResponseBody
    public Response add(UserModel model) {
        Response response;
        try {
            userService.addUser(model);
            response = Response.buildResponse();
        } catch (Exception e) {
            log.error("添加用户[{}]失败！",model.toString());
            response = Response.buildFailResponse(e.getMessage());
        }
        return response;
    }

}