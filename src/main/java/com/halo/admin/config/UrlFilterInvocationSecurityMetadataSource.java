package com.halo.admin.config;


import com.halo.admin.entity.Menu;
import com.halo.admin.entity.Role;
import com.halo.admin.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
     * @return 如果返回null或者空集合则表示改路径下的资源不需要任何权限，将直接请求路径对应的资源
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        log.debug("request:{}", requestUrl);
        if ("/admin/logina".equals(requestUrl)) {
            return null;
        }
        List<Menu> menus = menuService.findAllMenus();
        for (Menu menu : menus) {
            //此处遍历菜单，将请求路径与菜单中的请求路径规则进行匹配，此处返回第一个匹配到的菜单，在设置菜单的匹配规则时尽量唯一
            if (menu.getRoles().size() > 0 && antPathMatcher.match(menu.getUrl(), requestUrl)) {
                List<Role> roles = menu.getRoles();
                log.debug(Arrays.toString(roles.toArray()));
                List<String> roleNames = roles.stream().map(Role::getName).collect(Collectors.toList());
                return SecurityConfig.createList(roleNames.toArray(new String[roleNames.size()]));
            }
        }
        //如果请求路径没有对应菜单相匹配，也就是说该路径不属于任何角色，则默认该路径需要登录才能访问，即登录后的用户可以访问任何未分配角色的地址
        return SecurityConfig.createList("ROLE_LOGIN");
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
