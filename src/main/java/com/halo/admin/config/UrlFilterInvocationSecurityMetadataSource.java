package com.halo.admin.config;


import com.halo.admin.entity.Menu;
import com.halo.admin.entity.Role;
import com.halo.admin.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author Hailuo
 * @Date: 2019/3/7 11:05
 * @Description： 一个请求到达服务器后先经过此处
 */
@Slf4j
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();


    /**
     * 获取与请求路径一致的资源信息，如果一致则封装ConfigAttribute返回
     *
     * @param o
     * @return 如果返回null或者空集合则直接请求路径对应的资源
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        log.info("request:{}", requestUrl);
        List<Menu> menus = menuService.findAllMenus();
        for (Menu menu : menus) {
            if (menu.getRoles().size() > 0 && antPathMatcher.match(menu.getUrl(), requestUrl)){
                List<Role> roles = menu.getRoles();
            };
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
